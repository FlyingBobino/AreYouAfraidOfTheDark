/*
1. check if out directory is empty, clear if it isn;t
2. Read the input files
3. File.foreach(calculateAndSave)
calculateAndSave:
3a check brightness, normalise to scale 0 to 100 (light -> dark)
def normalising((val_brightness/val_perfectly_white(255))*100-100)
3b if brightness > cut off: concatenate name pic + dark.png, else + bright.png
3c rename picture file name by concatenating to pic_dark/bright_brightness.png
*/
   app