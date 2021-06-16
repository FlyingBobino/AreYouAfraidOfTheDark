# Are You Afraid Of The Dark

Programme assesses the brightness of the pictures located in the source folder (in scale of 0 to 100 (light -> dark)). 
Based on the specific cut off value, it amends the file name and saves it in destination folder.


This project is focusing on the problem provided by the Scalac company as a part of the recruitment process for the
summer camp.
All contents of the problem are at the bottom of this file.

## Usage

User can run the programme without any changes using the provided example set: the pictures from "pics" 
folder will be amended and saved in the "destination" folder. 

To use it on the new set user must provide in the App object a directory of the source folder, from which 
pictures are obtained, and destination folder, to which amended files are saved:

```scala
val sourceFolderDir = "pics"  
val destFolderDir   = "destination"
```

If the folders are outside the project folder, full directory must be provided e.g., "C:\UserName\FolderName"

Cut off value can be changed in the App object in the calculateRenameAndSave method (26th line)


### Noteworthy:

Script only assesses pictures with the ".jpg", ".png" and ".jpeg" extensions. If user decide to check different 
extension he/she must amend extensionFilter in FileManager object such that:

```scala
f.getName.contains(".png") || f.getName.contains(s".$extensionName")
```


Recommended cut off value: 80                                                                                       |
It is based on the outcomes from the test set - 100% accuracy and seemingly the best generalisation ability.

Script deletes the pictures from the destination folder between runs - to avoid it, user should comment out the 12th
line in the App object.

RGB to Brightness conversion is based on luminance formula taken from:
https://stackoverflow.com/questions/596216/formula-to-determine-perceived-brightness-of-rgb-color

Normalisation (scaling) was performed by diving all the values by the highest value (255 - responding to pure white),
subtracting by 100 (inversing from black -> white to white -> black) and taking absolute value


## Problem Contents

### Work to be done :
Your goal will be to investigate part of that pipeline. It was observed that users often are in
dark rooms when going over the identification process.
Obviously, that makes identification much harder so making sure “dark” images are dropped
is a crucial step.

What you need to do is to write a simple application that, when run, will go over the provided
input directory, read all jpg and png files, analyze them and write them back to an output
folder with metadata attached. The output and input directories are read from a config.
This metadata is coming from the analysis process and has two pieces of information:
- one of two labels: “dark” or “bright”, where “dark” are images that should be rejected
  according to the algorithm
- a score between 0 and 100 (inclusively) showing how dark the image is. So if a perfectly
  white image goes into the process a 0 score should be assigned, and when a perfectly dark
  image goes into the process a 100 score is given
  
As you probably already noticed those 2 metadata aren’t fully independent. There will be a
“cut off” point. All images with scores above will be considered “dark” and all images with
scores below will be “bright”. As programming is often exploration we don’t know what value
will make the most sense, so make sure this value can be easily changed between runs.

As mentioned above "business" doesn’t know beforehand what “dark” precisely means and
when images stop being “dark”. Instead they did provide us with a set of images divided into
“dark” and “bright”. Looking at the dark images should give you some intuition on what you
are looking for.
(Zip is attached to this task)

When creating your solution make sure that as many images as possible are correctly
associated. Obviously, the solutions should also work with images not provided in this set.

The client is operating at web-scale so, when correctness is assured, make sure you use
parallelism to achieve good throughput when processing many images.

When submitting the task we expect to receive from you:
- a score showing how many images from the demo set are correctly associated by
  your solution
- the source code with demo images included ready to be run
  
### Assumptions :
To make your task easier here are some assumptions.
Both "in" and "out" directories will exist, so creating them is not needed. The "in" directory
will be non-empty and "out" will be empty when the process starts (no name collisions will
happen).

The "in" folder is "flat" - doesn't contain any subfolders. It also doesn't hold any files other
than jpg and png.
It's good enough if the app is started with a simple sbt run (or similar in your build tool of
choice). The example photos should be added to the repository so the app can be started
with just one command.

###  Example :
When we run the script given an input folder “in”, output folder “out” and configured “cut off“
point of 55, then the final result should be this directory structure:
```
in/
  perfectly_white.png
  perfectly_black.jpg
  colorful_image.jpg
out/
  perfectly_white_bright_0.png
  perfectly_black_dark_100.jpg
  colorful_image_dark_55.jpg
```

### Criteria :
Your solution will be judged based on those criteria:
- it should be on time: it’s ok to ask for more time to polish a solution that delivers value
(say: works but needs documentation, works slowly or correctly classifies only 75% of
images etc.), but it’s undesired if you need the additional time to get a solution to work at all
- code quality: the code should reflect your programming skill
- precision: your solution should strive to correctly classify 100% of the provided images,
but doesn’t have to work for ANY image
  
Obviously, the solution should be your own. It could be inspired by existing research, tools or
algorithms, but you should be the author. Dishonest candidates will be removed from the
process.

Good luck