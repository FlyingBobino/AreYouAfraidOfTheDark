import java.awt.image.BufferedImage
import java.io.File
import scala.io.Source
import javax.imageio.ImageIO

object FileReader extends App{

//  val filepath = "C:\\Users\\ziolk\\Desktop\\abc.txt"
//  val filepath2 = "C:\\Users\\ziolk\\Desktop\\891208.jpg"

  // val x = Source.fromFile(filepath2)
//  val photo1: BufferedImage = ImageIO.read(new File(filepath2)) // buffered image
//
//  val loop = for {
//    x <- 1 to photo1.getHeight
//    y <- 1 to photo1.getWidth
//  }  yield (x,y)




//  def folderCounter(path: String): Int = {
//    val pics = getListOfFiles(path)
//    pics.length
//  }
//  println(folderCounter("C:\\Users\\ziolk\\Desktop\\New folder\\photos\\bright"))

//  val extensionFilter = d.getName.contains(".jpg") || d.getName.contains(".png")

  def extensionFilter(f: File): Boolean =
    f.getName.contains(".jpg") || f.getName.contains(".png")


  def getListOfFiles(dir: String):List[File] = {
    val d = new File(dir)
    if (d.exists && d.isDirectory) {
      d.listFiles.filter(file => file.isFile && extensionFilter(file)).toList
    } else {
      List[File]()
    }
  }



  def getFileNames(path: String): List[String] = {
    val pics = getListOfFiles(path)
    pics.map(_.getName) // underscore (_) maps the all pics to individual values from lists (syntactic sugar)
  }
  println(getFileNames("C:\\Users\\ziolk\\Desktop\\New folder\\photos\\bright"))


}
