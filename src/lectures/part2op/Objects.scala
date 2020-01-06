package lectures.part2op

object Objects extends App {

  //SCALA DOES NOT HAVE CLASS-LEVEL FUNCTIONALITY EQ "STATIC
  //BUT IT DOES HAVE OBJECT reference like below

  object Person { // is its only instance and is type object
    // "static"/"class" - level of functionality
    val N_EYES = 2
    def canFly: Boolean = false

    // factory method to build a person

    def apply(mother: Person, father: Person): Person = new Person("Bobby")
  }

  class Person(val name: String) {
    // instance-level functionality
  }

  //COMPANIONS when they are written, both object and class in the same file.

  println(Person.N_EYES)
  println(Person.canFly)

  // Scala object is a SINGLETON INSTANCE. The object is its own type and its own instance. They are singleton instances by definition.
// they both mary and john point to the exact same Person instance.
  val mary = Person
  val john = Person
  val david = new Person("David")
  val martha = new Person("Martha")


  println(s"Are mary and john pointing to the same person? ${mary == john}")
  println(s"Are martha and david the same object? ${martha == david}")

  val bobbie = Person(martha, david) // this is the apply method being envoked just using () instead of using the .apply
  println(bobbie.name)

//  val bobbie = Person.apply(david, martha) - same as above.

  // Scala Applications = Scala object with an important method
  // a method main which receives an array of string as a parameter, it needs to have this exact definition
  // def main(args: Array[String]): Unit ={
  //put ALL THE CODE IN HERE
  //} this is if your oging to get rid of extends app
  // The method has to have the exact definition as above.


}
