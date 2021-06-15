import java.io.File


object FileReader {

  private def extensionFilter(f: File): Boolean =
    f.getName.contains(".jpg") || f.getName.contains(".png")


  private def getListOfFiles(dir: String):List[File] = {
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

}
