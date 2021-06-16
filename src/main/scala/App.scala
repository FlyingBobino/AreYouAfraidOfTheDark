import java.io.File
import javax.imageio.ImageIO
import scala.collection.parallel.CollectionConverters._

/** Object responsible for performing operations on the picture*/
object App {
  def main(args: Array[String]): Unit = {

    val sourceFolderDir = "pics"
    val destFolderDir   = "destination"

    FileManager.deleteFolderContents(destFolderDir)
    // comment out line above if you want to retain pictures from previous runs

    val directorySeq: Seq[String] = FileManager.getFileNames(sourceFolderDir)
    directorySeq.par.foreach(dir => calculateRenameAndSave(dir, destFolderDir))

  }

  /** Obtains brightness, renames pictures name accordingly and saves in destination folder
    *
    * @param sourceFileDir Directory of a picture  to be amended
    * @param destFolderDir Directory of a destination folder for pictures to be saved
    */
  def calculateRenameAndSave(sourceFileDir: String, destFolderDir: String): Unit = {
    val cutOff: Int       = 80
    val fileName: String  = sourceFileDir.split("""\\""").reverse.head
    val name: String      = fileName.split("\\.").head
    val extension: String = fileName.split("\\.")(1)
    val brightness        = BrightnessCalculator.getBrightness(sourceFileDir)

    val newName =
      if (brightness >= cutOff)
        name + "_dark_" + brightness + "." + extension
      else
        name + "_bright_" + brightness + "." + extension

    val newPicture = FileManager.copyPicture(sourceFileDir)
    ImageIO.write(newPicture, extension, new File(s"$destFolderDir\\" + newName))

  }

}
