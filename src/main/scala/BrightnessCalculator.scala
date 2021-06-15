import java.io.File
import javax.imageio.ImageIO


object BrightnessCalculator {

  /**
   *
   * @param filepath
   * @return
   */
  def getBrightness(filepath: String): Int = {

    val picture = ImageIO.read(new File(filepath))
    val perfectWhiteRGBBrightness = 255

    def getBrightnessHelper(x: Int, y: Int): Double = {
      val color = picture.getRGB(x, y)
      val red = (color & 0xff0000) / 65536
      val green = (color & 0xff00) / 256
      val blue = (color & 0xff)
      0.2126 * red + 0.7152 * green + 0.0722 * blue // (0.2126*R + 0.7152*G + 0.0722*B)
    }

    val pixelBrightnessSeq: Seq[Double] = for {
      x <- 0 until picture.getWidth
      y <- 0 until picture.getHeight
    } yield getBrightnessHelper(x, y)


    val unscaledBrightness = pixelBrightnessSeq.sum / pixelBrightnessSeq.length  // 0 - 255 brightness of whole picture (dark -> light)

    ((unscaledBrightness / perfectWhiteRGBBrightness * 100) - 100).abs.toInt  // normalised 0 - 100 brightness (light -> dark)
  }

}
