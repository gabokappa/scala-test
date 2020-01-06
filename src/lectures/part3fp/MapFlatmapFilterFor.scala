package lectures.part3fp

object MapFlatmapFilterFor extends App {

  val list = List(1, 2, 3)
  println(list.head)
  println(list.tail)

  // map

  println(list.map(_ + 1))
  println(list.map(_ + " is a number"))


  // filter

  println(list.filter(_ % 2 == 0))

  //flatMap

  val toPair = (x: Int) => List(x, x + 1)
  println(s"flat mapping the ${list.flatMap(toPair)}")

  val numbers = List(1, 2, 3, 4)
  val chars = List('a', 'b', 'c', 'd')
  val colors = List("black", "white")

  val combinations = numbers.flatMap(n => chars.map(c => "" + c + n))
  println(combinations)

  // "How to do iterations"
  val combinations2 = numbers.flatMap(n => chars.flatMap(c => colors.map(color => "" + c + n + "-" + color)))
println(combinations2)

// foreach
  list.foreach(println)

  // the foreach above is idential to this:

//  for {
//    n <- numbers
//  }  println(n)


  // for-comprehensions are prefered than the long above

  val forCombinations = for {
    n <- numbers if n % 2 == 0
    c <- chars
    color <- colors
  } yield "" + c + n + "-" + color
      println(s"Another combo\n${forCombinations}")

  // syntax overload

  list.map { x =>
    x * 2
  }

  /*
  MyList supports for comprehensions?
  map(f: A => B) => MyList[B]
  filter(p: A => Boolean) => MyList[A]
  flatMap(f: A => MyList[B] => MyList[B]

a small collection of at most ONE element - Maybe[+T]

   */

}
