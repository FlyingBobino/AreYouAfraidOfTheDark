import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

object FileManager extends App {

  val folderDirectory = "C:\\Users\\ziolk\\IdeaProjects\\AreYouAfraidOfTheDarkSBT\\out"

  private def extensionFilter(f: File): Boolean =
    f.getName.contains(".jpg") || f.getName.contains(".png") || f.getName.contains(".jpeg")

  def getListOfFiles(dir: String): List[File] = {
    val d = new File(dir)
    if (d.exists && d.isDirectory) {
      d.listFiles.filter(file => file.isFile && extensionFilter(file)).toList
    } else {
      List[File]()
    }
  }

  def getFileNames(path: String): List[String] = {
    val pics = getListOfFiles(path)
    pics.map(_.getPath)
  }

  def deleteFolderContents(folderDirectory: String): Unit = {
    val fileNames = getFileNames(folderDirectory)
    if (!fileNames.isEmpty) {
      val pics = getListOfFiles(folderDirectory)
      pics.map(_.delete())
    }
  }

  def copyPicture(filePath: String): BufferedImage = {
    val orgPicture = ImageIO.read(new File(filePath))
    val w          = orgPicture.getWidth
    val h          = orgPicture.getHeight
    val newPicture = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB)
    for (x <- 0 until w)
      for (y <- 0 until h) {
        newPicture.setRGB(x, y, orgPicture.getRGB(x, y) & 0xffffff)
      }

    newPicture
  }

  deleteFolderContents(folderDirectory)
}
