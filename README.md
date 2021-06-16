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


* script only assesses pictures with .jpg, .jpeg and .png formats
   *
   * cutOff value is compared with brightness: if brightness < cutOff the image name is amended with _bright_brightness
   * and _dark_brightness otherwise