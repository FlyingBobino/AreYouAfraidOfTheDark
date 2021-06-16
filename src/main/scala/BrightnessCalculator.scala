import java.io.File
import javax.imageio.ImageIO

object BrightnessCalculator {

  /** Method that calculates the brightness of a whole picture in 0 - 100 (light -> dark) scale
    *
    * @param filepath a directory of a picture
    * @return a brightness of a picture
    */
  def getBrightness(filepath: String): Int = {

    val picture                   = ImageIO.read(new File(filepath))
    val perfectWhiteRGBBrightness = 255

    /** Method that obtains RGB values of a pixel and convert it to the brightness in 0-255 (dark -> light) scale
      *
      * @param width width of a picture in pixels
      * @param height height of a picture in pixels
      * @return unscaled brightness
      */
    def getBrightnessHelper(width: Int, height: Int): Double = {
      val color = picture.getRGB(width, height)
      val red   = (color & 0xff0000) / 65536
      val green = (color & 0xff00) / 256
      val blue  = (color & 0xff)
      0.2126 * red + 0.7152 * green + 0.0722 * blue
    }

    val pixelBrightnessSeq: Seq[Double] = for { // sequence calculating brightness of each pixel
      x <- 0 until picture.getWidth
      y <- 0 until picture.getHeight
    } yield getBrightnessHelper(x, y)

    val unscaledBrightness =
      pixelBrightnessSeq.sum / pixelBrightnessSeq.length // 0 - 255 brightness of whole picture (dark -> light)

    ((unscaledBrightness / perfectWhiteRGBBrightness * 100) - 100).abs.toInt // normalised 0 - 100 brightness (light -> dark)
  }

}
