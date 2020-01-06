package lectures.part2op

object OOBasics extends App {

  val person = new Person("Gabriel", 32)
println(person.age)
  person.greet("Shorty")
}
// this is a constructor
class Person(name: String, val age: Int = 0) {
  //body

  val x = 2
//  println(x)
//  println(1 + 3 + " printed when the class was instantiated")

  // this function as it is defined in a class is called a method
  def greet(name: String): Unit = println(s"${this.name} says: Hi, $name")

  //multiple constructors. The below is an auxillary constructor

  def this(name: String) = this(name, 0)

}

// class parameters are NOT FIELDS. ADD the key word val in the class parameters in order to access them