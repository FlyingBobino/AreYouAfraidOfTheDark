import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import scala.io.Source
import javax.imageio.ImageIO

object FileReader extends App{

  val filepath = "C:\\Users\\ziolk\\Desktop\\abc.txt"
  val filepath2 = "C:\\Users\\ziolk\\Desktop\\891208.jpg"

  // val x = Source.fromFile(filepath2)
  val photo1: BufferedImage = ImageIO.read(new File(filepath2)) // buffered image

  val loop = for {
    x <- 1 to photo1.getHeight
    y <- 1 to photo1.getWidth
  }  yield (x,y)


  def getListOfFiles(dir: String):List[File] = {
    val d = new File(dir)
    if (d.exists && d.isDirectory) {
      d.listFiles.filter(_.isFile).toList
    } else {
      List[File]()
    }
  }

  def folderCounter(path: String): Int = {
    val pics = getListOfFiles(path)
    pics.length
  }
  println(folderCounter("C:\\Users\\ziolk\\Desktop\\New folder\\photos\\bright"))


  def getFileNames(path: String): List[String] = {
    val pics = getListOfFiles(path)
    pics.map(_.getName) // underscore (_) maps the all pics to individual values from lists (syntactic sugar)
  }
  println(getFileNames("C:\\Users\\ziolk\\Desktop\\New folder\\photos\\bright"))
}
