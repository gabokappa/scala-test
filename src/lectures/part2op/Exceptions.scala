package lectures.part2op

import java.nio.BufferUnderflowException

object Exceptions extends App {

  // 1. Crash your program with OutofMemoryError

//  val array = Array.ofDim(Int.MaxValue)

  // 2 Crash with StackOverflowError with an infinite recurssive error

//  def infinite: Int = 1 + infinite
//  val noLimit = infinite

  // 3 PocketCalculator that can add, subtract, multiply, divide and also throw some OverflowExeptions. UndeflowExceptions and MathcalculationException

  class OverflowException extends RuntimeException
  class UnderflowException extends RuntimeException
  class MathCalculationException extends RuntimeException("Hey, you can't divide by 0")
  object PocketCalculator {
    def add(x: Int, y: Int)= {
      val result = x + y

      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result > 0) throw new UnderflowException
      else result
    }

    def subtract(x: Int, y: Int) = {
      val result = x -y
      if (x > 0 && y < 0 && result < 0) throw new OverflowException
      else if (x < 0 && y > 0 && result > 0 ) throw new UnderflowException
      else result
    }

    def multiply(x: Int, y: Int) = {
      val result = x * y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result < 0) throw new OverflowException
      else if (x > 0 && y < 0 && result > 0) throw new UnderflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }

    def divide(x: Int, y: Int):Float = {
      if (y == 0) throw new MathCalculationException
      else x.toFloat / y.toFloat

    }

  }

    /*
  try {
    // compute a value
  } catch {
    case e: RuntimeException => // another value
  } finally {
    // block for side effects or code that WILL ALWAYS RUN NO MATTER WHAT
  }
     */

}
