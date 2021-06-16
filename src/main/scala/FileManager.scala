import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

object FileManager {

  /** Method that is used in order to pass only the files with required extensions
    *
    * @param f a file
    * @return a Boolean that returns true if a file has one of the accepted extensions
    */
  private def extensionFilter(f: File): Boolean =
    f.getName.contains(".jpg") || f.getName.contains(".png") || f.getName.contains(".jpeg")

  /** Method that lists the files with acceptable extensions from the folder with the acceptable extensions
    *
    * @param folderDir directory of a folder
    * @return a list of files that are in the provided folder
    */
  private def getListOfFiles(folderDir: String): List[File] = {
    val d = new File(folderDir)
    if (d.exists && d.isDirectory) {
      d.listFiles.filter(file => file.isFile && extensionFilter(file)).toList
    } else {
      List[File]()
    }
  }

  /** Method that obtains individual files directories with the acceptable extensions
    *
    * @param folderDir directory of a folder
    * @return list of strings containing the files directories
    */
  def getFileNames(folderDir: String): List[String] = {
    val pics = getListOfFiles(folderDir)
    pics.map(_.getPath)
  }

  /** Method that deletes all the files with acceptable extensions in the provided folder
    *
    * @param folderDir directory of a folder
    */
  def deleteFolderContents(folderDir: String): Unit = {
    val fileNames = getFileNames(folderDir)
    if (fileNames.nonEmpty) {
      val pics = getListOfFiles(folderDir)
      pics.map(_.delete())
    }
  }

  /** Method that makes a copy of a picture
    *
    * @param fileDir directory of a picture
    * @return a copied BufferedImage
    */
  def copyPicture(fileDir: String): BufferedImage = {
    val orgPicture = ImageIO.read(new File(fileDir))
    val w          = orgPicture.getWidth
    val h          = orgPicture.getHeight
    val newPicture = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB)
    for (x <- 0 until w)
      for (y <- 0 until h) {
        newPicture.setRGB(x, y, orgPicture.getRGB(x, y) & 0xffffff)
      }

    newPicture
  }
}
