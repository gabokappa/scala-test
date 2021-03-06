package lectures.part3fp

object HOFsCurries extends App {

val superFunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = null

  // Higher order function

  // function that applies another function n times over a value x
  // nTimes(f, n, x) f = function, n = times, x = subject
  // nTimes(f, 3, x) is equivalent to f(f(f(x)) = nTimes(f, 2, f(x))

  def nTimes(f: Int => Int, n: Int, x: Int): Int =
    if (n <= 0) x
    else nTimes(f, n-1, f(x)) // recursion line as it calls it self until n === 0

  val plusOne = (x: Int) => x + 1
  println(s"printing plusone: ${nTimes(plusOne, 10, 1)}")

  /* the immediate above then equates to
  nTimes(plusOne, 9, 2)
  nTimes(plusOne, 8, 3)
  nTimes(plusOne, 7, 4)
  nTimes(plusOne, 6, 5)
    ... so on and so forth until the middle number n reaches 0 and it just returns the values

   */

  //nTimesBetter below takes a function f and n as parameter and it returns a function Int => Int
  // nTimesBetter(f, n) = x => f(f(f...(x))) instead of applying f to x, it returns a lambda
  // increment10 = nTimesBetter(plusOne, 10) = x => plusOne(plusOne.....(x)) // this returns lambda that applies plusOnes 10 times to x
  // so can use later val y = increment10(1) // TODO the 1 in this line is the x in the above line

def nTimesBetter(f: Int => Int, n: Int): (Int => Int) =
  if (n <= 0) (x: Int) => x // return the identity function instead of just x. You are returning a lambda
  else (x: Int) => nTimesBetter(f, n-1)(f(x)) // here (f, n-1) are applied to (f(x))

  val plus10 = nTimesBetter(plusOne, 10)
  println(plus10(1))

  /*
  The above then becomes -
  plus10(1)
  because n is 10 then it goes to the else statement in nTimesBetter
  nTimesBetter(plusOne, 10)(plusOne(1) // the 1 here passed initially by plus10 // This line will recurr until n is 0
  nTimesBetter(plusOne, 9)(plusOne(2))
  nTimesBetter(plusOne, 8)(plusOne(3))
  nTimesBetter(plusOne, 7)(plusOne(4))
  nTimesBetter(plusOne, 6)(plusOne(5))
  nTimesBetter(plusOne, 5)(plusOne(6))
  nTimesBetter(plusOne, 4)(plusOne(7))
  nTimesBetter(plusOne, 3)(plusOne(8))
  nTimesBetter(plusOne, 2)(plusOne(9))
  nTimesBetter(plusOne, 1)(plusOne(10))


   */

  // curried function
  val superAdder: Int => (Int => Int) = (x: Int) => (y: Int) => x + y
  val add3 = superAdder(3) // y => 3 + y
  println(add3(10))
  // can call superAdder directly passing the two parameter lists. I supplied the different lists
  println(s"passing 2 parameter lists ${superAdder(3)(10)}")

  // functions with multiple parameter lists. Below I define a function with multiple parameter lists

  def curriedFormatter(c: String)(x: Double): String = c.format(x)

  val standardFormat: (Double => String) = curriedFormatter("%4.2f")
  val preciseFormat: (Double => String) = curriedFormatter("%10.8f")

  println(standardFormat(Math.PI))
  println(preciseFormat(Math.PI))

  // To NOTE, for functions with multiple parameter lists, if you want to define smaller function later you have to define their type
  // like we did in the val standardFormat and preciseFormat.

  // functional programming is passing functions as parameters, which return functions as results

  // currying is functions with multiple parameter lists

/*
1 MyList2
-foreach method A => Unit

[1,2,3].foreach(x => println(x))

- sort function ((A, A) => Int) => MyList2

[1,2,3].sort((x, y) => y - x) => [3,2,1]

-zipWith(list, (A, A) => B => MyList2[B]

[1,2,3].zipWith[4,5,6], x * y) => [1 * 4, 2 * 5, 3 * 6] = [4,10,18]

- fold(start)(function) => a value
[1,2,3].fold(0)(x + y) = 6

2. toCurry(f: (Int, Int) => Int) => (Int => Int => Int)
fro,Curry(f: (Int => Int => Int)) => (Int, Int) => Int

3. compose(f,g) => x =>  f(g(x)) // compose(f,g) returns a lambda that for every value "x" it applies "g" and then "f".

// this is function compostition the above

andThen(g,g) => x => g(f(x)) this version applies 'f' first then x.



 */

  // section 2

  // toCurry takes a function which has two Int params and returns an Int and it returns an Int function that returns another Int functions which returns and Int
  def toCurry(f:(Int, Int) => Int): (Int => Int => Int) =
    x => y => (f(x, y))

  def fromCurry(f: (Int => Int => Int)): (Int, Int) => Int =
    (x, y) => f(x)(y)

  // Look up the documentation for FunctionX

  /* ORIGINAL
  def compose(f: Int => Int, g: Int => Int): Int => Int =
    x => f(g(x))

   */

  def compose[A, B, T](f: A => B, g: T => A): T => B =
    x => f(g(x))

  def andThen(f: Int => Int, g: Int => Int): Int => Int =
    x => g(f(x))

  def superAdder2: (Int => Int => Int) = toCurry((_  + _))

  def add4 = superAdder2(4)
  println(add4(17))

  val simpleAdder = fromCurry(superAdder)
  println(simpleAdder(4, 17))

  val add2 = (x: Int) => x + 2

  val times3 = (x: Int) => x * 3

  val composed = compose(add2, times3)
  val ordered = andThen(add2, times3)

  println(composed(4)) // applies times3 to 4 first so that is 12. And then the result of that function you add2 which is 14
  println(ordered(4)) // applies this in order so add2 is applied to so it becomes 6 and it times by 3 is 18.

}
