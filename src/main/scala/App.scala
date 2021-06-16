import java.io.File
import javax.imageio.ImageIO
import scala.collection.parallel.CollectionConverters._


object App {
  def main(args: Array[String]): Unit = {
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


    val Folder = FileReader.getFileNames("pics")

    Folder.par.foreach(calculateAndSave)
    println("Something Less Rated M'y")



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
    val newPicture = FolderCleaner.copyPicture(picturePath)

    ImageIO.write(newPicture, extension, new File("out\\" + newName))

  }




}

