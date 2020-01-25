package lectures.part3fp

import scala.util.Random

object Sequences extends App {

  // Seqeunces have a lot of functions out of the box

  val aSequence = Seq(1, 2, 3, 4)
  val anotherSeq = Seq(7, 5, 6)
  println(aSequence) // this sequence is actually a List.
  // TODO the apply method for Sequence constructs sub classes of sequence, that is why the above is printed as list object
  println(aSequence.reverse) // here we apply the reverse method to the list
  println(aSequence(2)) // here we do a 'get' for the 2 indexed element in thr list which should be '3'
  // The apply method retrieves the value at 2 index.
  println(aSequence ++ Seq(5, 6, 7)) // concatenation
  val example1 = aSequence ++ anotherSeq
  println((example1.sorted))

  // Ranges

  val aRange: Seq[Int] = 1 to 10 // until can be used but it is not inclusive
  aRange.foreach(println)

  (1 to 10).foreach(x => println("Hello"))

  // lists
  val aList = List(1, 2, 3)
  val prepended = 42 :: aList // adding 42 42 at the beginning of the list.
  println(s"I'm prepending $prepended")
  val anotherPrepend = 42 +: aList
  println(s"Now using +: $anotherPrepend")
  val appending = aList :+ 89
println(s"Now I'm appending with 89 :+ aList = $appending") // the column is always on the side of the list

  val apples5 = List.fill(5)("apple") // fill is curried function that takes in a number and a value and constructs a list of n times that value
  println(apples5)
  println(aList.mkString("-|-")) // this concatenates each element in the list with the values passed through

  // arrays

  val numbers = Array(1, 2, 3, 4)
  val threeElements = Array.ofDim[Int](3)
  println(s"is this the memory representation? of threeElements? $threeElements")
  threeElements.foreach(println) // this prints out a default value of 0. FOr reference types like string it gives you nulls. For primitive tyopes it gives you 0 or false (so Int, float or boolean)

  // arrays are mutable
  numbers(2) = 0 // this is syntax sugar for numbers.update(2, 0)
  println(numbers.mkString)
  println(numbers.mkString(" "))

  // arrays and seq

  val numberedSeq: Seq[Int] = numbers // this is an implicit conversion happening behind the scenes
  println(numberedSeq) // this is a special kind of sequence that wraps around an array

  // vectors

  val vector: Vector[Int] = Vector(1, 2, 3)
  println(vector)

  //vectors vs lists

    val maxRuns = 1000
  val maxCapacity = 1000000

  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random // random number generator
    val times = for {
      it <- 1 to maxRuns // for 1 to 1000 times we mark the current time
    } yield {
      val currentTime = System.nanoTime() // here we mark the current time
      collection.updated(r.nextInt(maxCapacity), r.nextInt()) // here the collection is updated at a random index number up to the maxcapicty, with a random value
      System.nanoTime() - currentTime // then work out the time now and subtract currentime which was taken before the operation was completed tp see how long it takes
    }
    times.sum * 1.0 / maxRuns // here we work out the average, byt summing up the results
  }

  val numbersList = (1 to maxCapacity).toList
  val numbersVector = (1 to maxCapacity).toVector

  //LISTS
  // Lists are very efficient at updating the list. It saves the reference to the tail so very good at updating the head of the list, the first reference.
  // not efficient at updating something in the middle of the list
  // keeps refrence to tails
  println(s"using list ${getWriteTime(numbersList)}") // TODO the average a time it takes to write something to a random index in the list

  //VECTORS
  // the vector needs to traverse the whole 32 branch tree. It needs to replace the entyire chunk
  //  the depth of the tree is small which is an advantage
  //disadvantage it needs to replace an entire 32-element chunk
  println(s"using vectors ${(getWriteTime(numbersVector))}") // TODO quicker at writing at something in the middle of a random index.


}
