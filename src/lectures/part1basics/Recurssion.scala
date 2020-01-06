package lectures.part1basics

object Recurssion extends App {

  def factorial(n: Int): Int =
    if(n <= 1) 1
    else {
      println("Computing factorial of " + n + " - First need factorial of " + (n-1))
      val result = n * factorial(n-1)
      println("Computed factorial of " + n)

      result
    }
println(factorial(10))

  def anotherFactorial(n: Int): BigInt = {
    def factHelper(x: Int, accumulator: BigInt): BigInt =
      if(x <= 1) accumulator
      else factHelper(x-1, x * accumulator)

    factHelper(n, 1)
  }
  /* anotherFactorial(10) = factHelper(10, 1)
  factHelper(9, 10 * 1)
  factHelper(8, 9 * 10 * 1)
  factHelper(7, 8 * 9 * 10 * 1)
TAIL RECURSION = use the recursive call as the last expression
   */

// println(anotherFactorial(5000))

// 1 . concatenate

  def concatenateTailrec(aString: String, n: Int,  accumulator: String): String =
    if(n <= 0) accumulator
  else concatenateTailrec(aString, n-1, aString + accumulator)
  // println(concatenateTailrec("Hiya ", 4, ""))
  // concatenateTailrec("Hiya ", 3, "Hiya ")
  // concatenateTailrec("Hiya ", 2, "Hiya Hiya Hiya")
  //


  //

  def isPrime(n: Int): Boolean = {
    def isPrimeTailrec(t: Int, isStillPrime: Boolean ): Boolean =
      if (!isStillPrime) false
      else if (t <= 1) true
    else isPrimeTailrec(t - 1, n % t != 0 && isStillPrime)

    isPrimeTailrec(n / 2, true)


  }
//  println(isPrime(2003))
//  println(isPrime(20))

  def fibonacci(n: Int): Int = {
    def fiboTailrec(i: Int, last: Int, nextToLast: Int): Int =
      if(i >= n) last
    else fiboTailrec(i + 1, last + nextToLast, last)

    if (n <= 2) 1
    else fiboTailrec(2, 1, 1)
  }
  println(fibonacci(8))
}
