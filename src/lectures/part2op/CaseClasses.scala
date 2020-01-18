package lectures.part2op

object CaseClasses extends App {

   case class Person(name: String, age: Int)

  // 1. class parameters are fields

  val jim = new Person("Jim", 34)
  println(jim.age)

  // 2. sensible toString
  //println(instance) == println(instance.toString)
  println(jim.toString)

  // 3. equals and hashcode impelmented out of the box
  val jim2 = new Person("Jim", 34)
  println(jim == jim2)

  // 4. CCs have handy copy method
  val jim3 = jim.copy(age = 45)
  println(jim3)

// 5. Case classes have companion objects. By using case infront of e class automatically creates a companion object
  // Don't really need to use 'new' when instantiating a case class, can use the apply method () like when mary val is called below

  val thePerson = Person
  val mary = Person("Mary", 23)

  // 6. CCs are serializable useful for Akka implementation
  // 7. CCs have extractor patterns  - CCs can be used in PATTERN MATCHING
  // case objects have the same properties as case classes

  case object UnitedKingdom {
    def name: String = "The Uk of GB and NI"
  }

}
