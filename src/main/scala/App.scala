import java.io.File
import javax.imageio.ImageIO
import scala.collection.parallel.CollectionConverters._

object App {
  def main(args: Array[String]): Unit = {

    val sourceFolderDirectory      = "pics"
    val destinationFolderDirectory = "out"

    FileManager.deleteFolderContents(destinationFolderDirectory)

    val folder = FileManager.getFileNames(sourceFolderDirectory)
    folder.par.foreach(calculateAndSave)

  }

  def calculateAndSave(picturePath: String): Unit = {
    val cutOff: Int       = 80
    val fileName: String  = picturePath.split("""\\""").reverse.head
    val name: String      = fileName.split("\\.").head
    val extension: String = fileName.split("\\.")(1)
    val brightness        = BrightnessCalculator.getBrightness(picturePath)

    val newName =
      if (brightness >= cutOff)
        name + "_dark_" + brightness + "." + extension
      else
        name + "_bright_" + brightness + "." + extension

    val newPicture = FileManager.copyPicture(picturePath)
    ImageIO.write(newPicture, extension, new File("out\\" + newName)) // save

  }

}
