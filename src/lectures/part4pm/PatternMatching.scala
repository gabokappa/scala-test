package lectures.part4pm

import scala.util.Random

object PatternMatching extends App {

  // its like a switch on steroids

  val random = new Random
  val x = random.nextInt(10)

  val description = x match {
    case 1 => "the ONE\n"
    case 2 => "Double down\n"
    case n if n % 3 == 0 => "Third time lucky\n"
    case _ => "something else\n"
  }
  println(x)
  print(description)

  // 1. DECOMPOSE VALUESproperty of pattern matching can decompose values. Especially used in conjuction with case classes. Case classes can be deconstructed
  // or constructed with matchers

  case class Person(name: String, age: Int)
  val bob = Person("Bob", 20)

  val greeting = bob match {
      // guard clause below
    case Person(n, a) if a < 21 => s"Hi, my name is $n and I'm $a years old and not a legal adult in the US"
    case Person(n, a) => s"Hi, my name is $n and I'm $a years old"
    case _ => "WHO R YOU?"
  }
  println(greeting)

  // 1. cases are matched in order
  // 2. if no cases match an exception is raised with a MatchError
  // 3. what is the type of the pattern match expression? The compiler returns the lowest form of ancestor in terms of type S IT TRIES TO UNIFY THE RETURN TYPE
  // the unified type of all the types in all the cases
  // 4. Pattern matching works really well with case classes because they come extractor patterns out of the box

  // Pattern Matching on sealed hierarchies.

  sealed class Animal
  case class Dog(breed: String) extends Animal
  case class Parrot(greetings: String) extends Animal

  val animal: Animal = Dog("Terra Nova")
  animal match {
    case Dog(someBreed) => println(s"Matched a dog of the $someBreed breed")
  }

}
