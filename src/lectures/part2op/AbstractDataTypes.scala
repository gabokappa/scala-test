package lectures.part2op

object AbstractDataTypes extends App {

  // abstract classes that contained unimplemented methods or classes.

  abstract class Animal {
    val creatureType: String = "wild" // non-abstract member as defined
    def eat: Unit // abstract member
    // the above value and method aren't implemented or defined so they are 'abstract' as they have no values. The subclasses do that for you
    // an abstract class cannot be instantiated. They are MEANT to be extended only. In the extension, you need to override the abstract fields or
    // else you get an error.
  }

  class Dog extends Animal {
    override val creatureType: String = "Canine"
    override def eat: Unit = println("crunch crunch")
  }

  // traits

  trait Carnivore {
    def eat(animal: Animal): Unit // this is an abstract method
    val preferredMeal: String = "fresh meat" // a non-abstract member as defined
  }

  trait ColdBlooded

  class Crocodile extends Animal with Carnivore with ColdBlooded {
    override val creatureType: String = "croc"
    def eat: Unit = println("nomnomnom")
    def eat(animal: Animal): Unit = println(s"I'm a croc and I'm eating ${animal.creatureType}")

  }

  val dog = new Dog
  val croc = new Crocodile
  croc.eat(dog)

  // traits vs abstract classes
// 1 - traits don't have constructor parameters
  //  2 - can inheret multiple traits but one class
  // 3 - traits = behaviour, what they do. an abstract class is what they are.



}
