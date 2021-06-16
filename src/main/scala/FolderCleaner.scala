import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import scala.language.postfixOps

object FolderCleaner extends App{

//  val source = new File("C:\\Users\\ziolk\\IdeaProjects\\AreYouAfraidOfTheDarkSBT\\pics\\a.jpg")
//  val directory = new File("C:\\Users\\ziolk\\IdeaProjects\\AreYouAfraidOfTheDarkSBT\\out\\a_trololo.jpg")

//  val source = "C:\\Users\\ziolk\\IdeaProjects\\AreYouAfraidOfTheDarkSBT\\pics\\a.jpg"
//  val directory = "C:\\Users\\ziolk\\IdeaProjects\\AreYouAfraidOfTheDarkSBT\\out\\a_Dupa.jpg"

//  def isEmpty(path: File): Boolean =
//    if (path.isDirectory && path.list.length == 0) true
//    else false

//  println(isEmpty(directory))

//  def moveRenameFile(source: String, destination: String): Unit = {
//    val path = Files.move(
//      Paths.get(source),
//      Paths.get(destination),
//      StandardCopyOption.REPLACE_EXISTING
//    )
//  }
//  moveRenameFile(source, directory)

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





