package lectures.part4pm

object PatternsEverywhere extends App {

  // big idea #1

  try {
    // some code
  } catch {
    case e: RuntimeException => "runtime"
    case npe: NullPointerException => "npe"
    case _ => "something else"
  }

  // catches are actually MATCHES

  /*
  try {
  // code
  } catch (e) {
  e match {
   case e: RuntimeException => "runtime"
    case npe: NullPointerException => "npe"
    case _ => "something else"
  }
 }
   */

  // big idea #2 this is for-comprehensions ?

  val list = List(1, 2, 3, 4)
  val evenOnes = for {
    x <- list if x % 2 == 0 // ?!
  } yield (10 * x)

  // generators are also based on pattern matching. They are composed more or less the same way.

  val tuples = List((1, 2), (3, 4))
  val filterTuples = for {
    (first, second) <- tuples
  } yield first * second // in this example this yields List(2, 12)

  // Other patterns available as well. case classes, :: operators, etc...

  println(filterTuples)

  // big idea 3

  val tuple = (1, 2, 3)
  val (a, b, c) = tuple
  // the above is assigning multiple values by exploiting the name binding property of pattern matching.
  println(c)
  // multiple value defenitions based on Pattern matching
  // not limited to Tuples....

  val head :: tail = list /// this is essentially pattern mathcing

  println(head)
  println(tail)

  // big idea #4 partial function

  val mappedList = list.map {
    case v if v % 2 == 0 => v + " is even"
    case 1 => "the one"
    case _ => "this is something else"
  }

  // the above expression is called a partial function literal and it is the same as the expression below.

  val mappedList2 = list.map { x => x match {
      case v if v % 2 == 0 => v + " is even"
      case 1 => "the one"
      case _ => "this is something else"
    }
  }

  println(s"printing mappedList: ${mappedList}")
  println(s"printing mappedList2: ${mappedList2}")



}

