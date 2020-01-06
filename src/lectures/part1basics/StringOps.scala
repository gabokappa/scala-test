package lectures.part1basics

object StringOps extends App {
  val str: String = "Hello I am learning Scala"

  println(s" Returns the character at an index ${str.charAt(2)}")
  println(str.substring(7, 11))
  println(str.split(" ").toList)
  println(str.startsWith("Hello"))
  println(str.replace(" ", "-"))
  println(str.toLowerCase())
  println(str.toUpperCase())
  println(str.length)

  val aNumberString = "45"
  val aNumber = aNumberString.toInt
  println('a' +: aNumberString :+ 'z')
  println(str.toLowerCase().reverse)
  println(str.take(2))

  // Scala-specific: String interpolators.

  val name: String = "Gabriel"
  val age: Int = 32

  val greeting = s"Hello, I am called $name and I will be ${age + 1} in 2020"
  println(greeting)

  //F-interpolators

  val speed = 1.2f
  val sentence = f"$name%s can eat $speed%2.2f pizzas per sitting"
  println(sentence)

  //raw-interpolator

  println(raw"This is a \n newline")
}
