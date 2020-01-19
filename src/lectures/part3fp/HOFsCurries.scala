package lectures.part3fp

object HOFsCurries extends App {

val superFunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = null

  // Higher order function

  // function that applies another function n times over a value x
  // nTimes(f, n, x) f = function, n = times, x = subject
  // nTimes(f, 3, x) is equivalent to f(f(f(x)) = nTimes(f, 2, f(x))

  def nTimes(f: Int => Int, n: Int, x: Int): Int =
    if (n <= 0) x
    else nTimes(f, n-1, f(x))

  val plusOne = (x: Int) => x + 1
  println(s"printing plusone: ${nTimes(plusOne, 10, 1)}")

  /* the immediate above then equates to
  nTimes(plusOne, 9, 2)
  nTimes(plusOne, 8, 3)
  nTimes(plusOne, 7, 4)
  nTimes(plusOne, 6, 5)
    ... so on and so forth until the middle number n reaches 0 and it just returns the values

   */

def nTimesBetter(f: Int => Int, n: Int): (Int => Int) =
  if (n <= 0) (x: Int) => x
  else (x: Int) => nTimesBetter(f, n-1)(f(x))

  val plus10 = nTimesBetter(plusOne, 10)
  println(plus10(1))

  // curried function
  val superAdder: Int => (Int => Int) = (x: Int) => (y: Int) => x + y
  val add3 = superAdder(3) // y => 3 + y
  println(add3(10))

}
