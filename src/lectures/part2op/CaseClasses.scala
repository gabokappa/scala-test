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

// 5. Case classes have companion objects

  val thePerson = Person
  val mary = Person("Mary", 23)

  // 6. CCs are serializable useful for Akka implementation
  // 7. CCs have extractor patterns  - CCs can be used in PATTERN MATCHING

  case object UnitedKingdom {
    def name: String = "The Uk of GB and NI"
  }

}
