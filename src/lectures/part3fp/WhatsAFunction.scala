package lectures.part3fp

object WhatsAFunction extends App {
  // use functions as first class elements

  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }

  //we can call doubler like a function doubler calls the apply method and prints four. Doubler is an instance of function like class
  //
  println(doubler(2))

  // function types are supported by default by Scala. thi is equivalent Function1[A, B]

  val stringToIntConverter = new Function1[String, Int] {
    override def apply(string: String): Int = string.toInt
  }
println(stringToIntConverter("3") + 4)

  // syntactic sugar to define the adder. insteaf of using adder: Function2[Int, Int, Int] to indicate two Int paramaters and a return object of Int
  // it changes to adder: ((Int, Int) => Int)
  val adder: ((Int, Int) => Int) = new Function2[Int, Int, Int] {
    override def apply(a: Int, b: Int): Int = a + b
  }

  // Function types Function2[A, B, R] === (A, B) => R

  // ALL SCALA FUNCTIONS ARE OBJECTS

  /*
  1. a function which takes 2 strings and concatenates them
  2. transform the MyPredicate and MyTransformer into function types
  3. define a function which takes an int as an argument and it returns another function which takes an int and returns an int
    - what's the type of the function
    - how to implement the function
   */

  def conca: (String, String) => String = new Function2[String, String, String] {
    override def apply(a: String, b: String): String = a + b
   // the above can also be expressed like an abstract method:  def conca: (String, String) => String = (a: String, b: String) => a + b
  }
  println(conca("Hell", " is here!"))


}

// OOP below
//class Action {
//  def execute(element: Int): String = ???
//}

/* OOP  abstracted
trait Action[A, B] {
  def execute(element: A): B
  }
 */

// TODO a better way of doing it would be the following

trait MyFunction[A, B] {
  def apply(element: A): B
}

