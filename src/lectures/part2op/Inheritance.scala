package lectures.part2op

object Inheritance extends App {

  class Animal {
    def eat = println("nomnom")
  }

  class Cat extends Animal {
    def crunch = {
      println("hello")
    }
  }

  val cat = new Cat

  cat.eat

  class Person(name: String, age: Int)
  class Adult(name: String, age: Int, idCard: String) extends Person(name, age)

  //overriding vs overloading. Know your difference. Overloading is about same method calls but different signatures

  class Dog extends Animal {
    override def eat = println("woof crunch")
    // if Animal had a val creatureType, this can be overriden in Dog constuctor by using (override val creatureType: String)
    // or in the body by simply override val creatureType = "what you want"
  }

  val dog = new Dog
  dog.eat

  // type substitution (broadL polymorphism)

  val unknownAnimal: Animal = new Dog
  unknownAnimal.eat
  // a method call would always go to the most ovverriden method possible
// super is to reference a method from a parent class

  // to prevent overrides
  // 1 - use final key word in front of a member(which is a method)
  // 2 - use final in front of an entire class, which would prevent inheritance/extension from that class
  // 3 - use 'seal' infront of the class which prevents extension of classes in other files. It can be extended in this file


}
