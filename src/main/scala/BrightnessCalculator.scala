import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import math._
import scala.io.Source

object BrightnessCalculator extends App {

  val filepath =  "C:\\Users\\ziolk\\Desktop\\New folder\\photos\\bright\\a.jpg"
  val photo1: BufferedImage = ImageIO.read(new File(filepath)) // buffered image




  def getBrigtness(x: Int, y: Int): Double = {
    val color =photo1.getRGB(x,y)
    val red = (color & 0xff0000) / 65536
    val green = (color & 0xff00) / 256
    val blue = (color & 0xff)
    0.2126 * red + 0.7152 * green + 0.0722 * blue     // (0.2126*R + 0.7152*G + 0.0722*B)
  }

  val loop = for {
    x <- 0 until photo1.getWidth
    y <- 0 until photo1.getHeight
  }  yield getBrigtness(x,y)

  val avg = loop.sum / loop.length
  println(avg)
}
