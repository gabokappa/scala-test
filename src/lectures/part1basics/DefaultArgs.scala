package lectures.part1basics

object DefaultArgs extends App {

  def trFact(n: Int, acc: Int = 1): Int =
    if (n <= 1) acc
    else trFact(n-1, n * acc)

  val fact5 = trFact(5)
  println(fact5)

  def savePic(format: String = "jpg", width: Int = 1920, height: Int = 1080): Unit = println("saving the picutre")
  savePic(width = 800, height = 600)

}
