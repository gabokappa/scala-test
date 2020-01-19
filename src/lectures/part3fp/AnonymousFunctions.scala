package lectures.part3fp

object AnonymousFunctions extends App {

  // OOP way of coding is the below three lines
//  val doubler = new Function1[Int, Int] {
//    override def apply(x: Int) = x * 2
//  }

  // Functional programming - the below is syntactic sugar. It is a anonymous function a (LAMBDA).
  // An anonymous function from now on will be called LAMBDA
  // The Lambda section (so after) is an instance of Function1

  /*
 some type inferance going on below
  TODO val doubler = (x: Int) => x * 2

  The above code is the same as the below
  */

  // the below is an anonymous function (LAMBDA)

  val doubler: Int => Int = x => x * 2

  // multiple params in a lambda check line 32

  val adder = (a: Int, b: Int) => a + b

  // if you want to supply the type for the above function the arguments as they are two need to be in parenthesis. Compared to line
  // 23 where doubler only had one paramater it doesn't need to be in parenthesis.

  val adder2: (Int, Int) => Int =(a: Int, b: Int) => a + b

  // if no params no param lambda

  val justDoSomething: () => Int = () => 3 // this a no parameter lambda that returns three.

  println(justDoSomething) // this prints out the instance of the function
  println(justDoSomething()) // this prints out the value which is 3

  // TODO LAMBDAS must be called with parenthesis

  // curly braces with lambdas - the below is the common style

  val stringToInt = { (str:String) =>
    str.toInt
  }

  // MOAR syntactic sugar

  val niceIncrementer: Int => Int = (x: Int) => x + 1
  val niceIncrementer2: Int => Int = _ + 1 // equivalent to x => x + 1

  println(s"Nice incrementer: ${niceIncrementer(1)}")
  println(s"Nice incrementer2: ${niceIncrementer2(2)}")

  val niceAdder: (Int, Int) => Int = (a, b) => a + b

  println(s"Nice Adder: ${niceAdder(1, 2)}")

  val niceAdder2: (Int, Int) => Int = _ + _ // equivalent to (a, b) => a + b

  println(s"Nice Adder2: ${niceAdder2(2, 3)}")

  // TODO The above notation is useful for chaining higher function calls. If we don't specify the compiler won't know what the underscore is for

  // 1. MyList2: replace all FunctionX calls with lambdas
  // 2. Rewrite the adder in previous exercise as an anonymous function

  val superAdd = (x: Int) => (y: Int) => x + y

  println(s"supperAdd ${superAdd(3)(4)}")

}
