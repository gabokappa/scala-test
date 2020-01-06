package lectures.part2op

object MethodNotations extends App {

  class Person(val name: String, favouriteMovie: String, val age: Int = 0) {
    def likes(movie: String): Boolean = movie == favouriteMovie
    def hangOutWith(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    def +(nickname: String): Person = new Person(s"$name ($nickname)", favouriteMovie)

    def unary_! : String = s"$name, what the heck?!"
    def unary_+ : Person = new Person(name, favouriteMovie, age + 1)
    def isAlive: Boolean = true
    def apply(): String = s"Hi my name is $name and I like $favouriteMovie"
    def apply(n: Int): String = s"$name watched $favouriteMovie $n times"

    def learns(thing: String) = s"$name is learning $thing"
    def learnsScala: String = this learns "Scala"
  }

  val mary = new Person("Mary", "Inception")
  println(mary.likes("Inception"))
  // below is infix notation = operator notation
  println(mary likes "Inception")

  // "operators in Scala"

  val tom = new Person("Tom", "Fight Club")
  println(mary + tom)


  // prefix notation
  val x = -1 // equivalent with 1.unary_-
  val y = 1.unary_-
  // unary_prefix only works with - + ~ ! operators

  println(!mary)

  // postfix notation

  println(mary.isAlive) // or println(mary isAlive)

  // apply

  println(mary.apply())
  println(mary()) // equivalent as the one above
  println("")
  println(" THIS IS THE BEGINNING OF THE EXERCISES ______________")
println("")
  println((mary + "the rockstar").apply()) // the SAME as: println((mary + "the rockstar")())
  println((+mary).age) // this is the syntax when defined as a unary method


  println(mary.learnsScala)
  println(mary(10))

}
