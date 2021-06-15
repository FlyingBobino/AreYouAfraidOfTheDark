import java.io.File

object App {
  def main(args: Array[String]): Unit = {
    /*
    1. check if out directory is empty, clear if it isn;t
    2. Read the input files
    3. File.foreach(calculateAndSave)
      calculateAndSave:
      3a check brightness, normalise to scale 0 to 100 (light -> dark)
      def normalising((val_brightness/val_pefectly_white(255))*100-100)
      3b if brightness > cut off: concatenate name pic + dark.png, else + bright.png
      3c rename picture file name by concatenating to pic_dark/bright_brightness.png

     */
    val brightFolder = FileReader.getFileNames("C:\\Users\\ziolk\\Desktop\\New folder\\photos\\bright")
    val darkFolder = FileReader.getFileNames("C:\\Users\\ziolk\\Desktop\\New folder\\photos\\too_dark")

    brightFolder.foreach(calculateAndSave)
    println("Dupaaaaa")
    darkFolder.foreach(calculateAndSave)
  }



  def calculateAndSave(picturePath: String): Unit = {
    val cutOff: Int = 75
    val fileName: String = picturePath.split("""\\""").reverse.head
    val name: String = fileName.split("\\.").head
    val extension: String = fileName.split("\\.")(1)
    val brightness = BrightnessCalculator.getBrightness(picturePath)

    val newName =
      if (brightness >= cutOff)
        name + "_dark_" + brightness + "." + extension
      else
        name + "_bright_" + brightness + "." + extension

    println(newName)
  }

}
