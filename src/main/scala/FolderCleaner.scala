import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import scala.language.postfixOps

import java.nio.file.Files


object FolderCleaner extends App{

  val folderDirectory = "C:\\Users\\ziolk\\IdeaProjects\\AreYouAfraidOfTheDarkSBT\\out"

  def deleteFolderContents(folderDirectory: String): Unit = {
    val fileNames = FileReader.getFileNames(folderDirectory)
    if (!fileNames.isEmpty) {
      val pics = FileReader.getListOfFiles(folderDirectory)
      pics.map(_.delete())
    }
  }

  deleteFolderContents(folderDirectory)

  def copyPicture(filePath: String): BufferedImage = {
    val orgPicture = ImageIO.read(new File(filePath))
    val w = orgPicture.getWidth
    val h = orgPicture.getHeight
    val newPicture = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB)
    for (x <- 0 until w)
      for (y <- 0 until h) {
        newPicture.setRGB(x, y, orgPicture.getRGB(x, y) & 0xffffff)
      }

    newPicture
  }
}




//  println(isEmpty(directory))

//  def moveRenameFile(source: String, destination: String): Unit = {
//    val path = Files.move(
//      Paths.get(source),
//      Paths.get(destination),
//      StandardCopyOption.REPLACE_EXISTING
//    )
//  }
//  moveRenameFile(source, directory)




