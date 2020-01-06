package lectures.part2op

object Generics extends App {

  class MyList[+A] {
    // the type[A] is called type parameterised where they are given a collection list of a parameter type A meaning it can be anything
    // use the type A inside the class definition
    // val listOfIntegers = new MyList[Int]
    // this is a generic class. We can also have a generic trait, but these can't be instantiated
    def add[B >: A](element: B): MyList[B] = ???
    // the above says if to a list of As I put in a list of Bs which is a super type of A, then this lisrt turns into a list of Bs

    /* A = Cat
       B = Animal
     */

  }

  class MyMap[Key, Value] // key, and value here are also generic types

  val listOfIntegers = new MyList[Int] // the Int replaces the +A in the class MyList[+A] above
  val listOfStrings = new MyList[String] // the String replaces the +A in the class MyList[+A] above

  // generic methods

//  object MyList {
//    def empty[A]: MyList[A] = ???
//  }
//
//  val emptyIntList = MyList.empty[Int]

  //  Variance problem

  class Animal
  class Cat extends Animal
  class Dog extends Animal

  // Does a List of Cats extends to a List of Animals

  // 1.  yes List[Cat] extends List[Animal] - COVARIANCE Covariant classes can be substituted. we can change a list of animals for a list of cats because cats are sub class of animal
  class CovariantList[+A] // the plus infront of the A makes this a covariant list
  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]
  // animalList.add(new Dog)?? Does this work as well? => we return a list of Animals

  // 2, No list of cats and animals are seperate things. List You can keep list of animals and cat seperate - INVARIANCE

  class InvariantList[A] // no plus sign in the data type. Invariant classes can't be substituted

  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal] // Cannot change 'Animal' for cat as above
  // if you defined InvariantList[A] and [A] to be an [Animal] as above, it can't be a list of [Cat] for example. Unlike option 1.

  // 3. Hell no! CONTRAVARIANCE classes - the opposite of COVARIANCE. can be subbed for super class.

  class ContravariantList[-A]
  val contravariantList: ContravariantList[Cat] = new ContravariantList[Animal] // Cats are a subclass of Animals
  class Trainer[-A]
  val trainer: Trainer[Cat] = new Trainer[Animal] // A cat is a form of animal, but can have a trainer of Animals which also trains cats


  //bounded types only let you use your generic classes only for a subclass of a different type or superclass of a different type
  class Cage[A <: Animal](animal: A) // this only accepts As which are a subclass of Animal
  val cage = new Cage(new Dog) // this is an acceptable parameter of new Dog as it is a subclass of Animal

  // the above is a lower bounded type. You can have a upper bounded type which would be class Cage[A>: Animal] which
  //only accepts super types of Animal, not subclasses.

}
