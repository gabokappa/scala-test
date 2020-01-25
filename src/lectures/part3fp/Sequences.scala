package lectures.part3fp

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


}
