package lectures.part2op

object AnonymousClasses extends App {

  abstract class Animal {
    def eat: Unit
  }


  // anonymous class. The compiler below instantiates an anonymous class with a location name and extends animal in order to run
  // the function below.
  val funnyAnimal: Animal = new Animal {
    override def eat: Unit = println("laughing a lot")

    /*
    The equivalent of class AnonymousClasses$$anon$1 extends Animal {
    override def eat: Unit = println("laughing a lot")
    }

    val funnyAnimal: Animal = new AnonymousClasses$$anon$1 extends Animal
     */

  }
// can override the method, need to use the key word override. Remember need to implement all abrstract methods and fields
  //
  println(funnyAnimal.getClass)


  class Person(name: String) {
    def sayHi: Unit = println(s"Hi, my name is $name, how can I help?")
  }

  val jim = new Person("Jim") {
    override def sayHi: Unit = println(s"Hi, my name is Jim, how can I be of service?")
  }


  /*
  1. create a Generic trait called MyPredicate[-T] with method test(T) = > returns Boolean
  2. Generic trait MyTransformer[-A, B](has a method transform(A) => B that changes anything from type A to type B)
  3. MyList has the following functions:
    -map(transformer) = > returns MyList of different type
    -filler(predicate) = > returns MyList
    -flatMap(transformer from A to MyList[B] => returns MyList[B]

    class EvenPredicate extends MyPredicate[Int]
    class StringToIntTransformer extends MyTransformer[String, Int]

examples below:
    [1, 2, 3].map(n * 2) = [2, 4, 6]
    [1, 2, 3, 4].filter(n % 2) = [2, 4]
    [1, 2, 3].flatMap(n => [n, n+1]) => 1, 2, 2, 3, 3, 4
       */

}
