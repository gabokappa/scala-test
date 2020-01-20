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
  nTimesBetter(plusOne, 10)(plusOne(1) // the 1 here passed initially by plus10 // This line will recurr until 10 is 0




   */

  // curried function
  val superAdder: Int => (Int => Int) = (x: Int) => (y: Int) => x + y
  val add3 = superAdder(3) // y => 3 + y
  println(add3(10))

}
