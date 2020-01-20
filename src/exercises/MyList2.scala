package exercises

import java.security.spec.MGF1ParameterSpec

abstract class MyList2[+A] {

  /*
  implement your own collection:
  a list that holds integers
  a method head = first element of the list
  a method tail = remainder of the list
  a method isEmpty = which returns a boolean to see if it is empty
  a method called add(Int) => which receives an integer which returns a new list with this element added.
  override toString => , which returns a string representation of the list.

   */

  def head: A
  def tail: MyList2[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyList2[B]
  def printElements: String
  // polymorphic call
  override def toString: String = "[" + printElements + "]"

  def map[B](transformer: A => B): MyList2[B]
  def flatMap[B](transformer: A => MyList2[B]): MyList2[B]
  def filter(predicate: A => Boolean): MyList2[A]
  def ++[B >: A](list: MyList2[B]): MyList2[B]

  // hofs

  def foreach(f: A => Unit): Unit




}

object Empty extends MyList2[Nothing] {

  def head: Nothing = throw new NoSuchElementException
  def tail: MyList2[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MyList2[B] = new Cons(element, Empty)
  def printElements: String = ""

  def map[B](transformer: Nothing => B): MyList2[B] = Empty
  def flatMap[B](transformer: Nothing => MyList2[B]): MyList2[B] = Empty
  def filter(predicate: Nothing => Boolean): MyList2[Nothing] = Empty
  def ++[B >: Nothing](list: MyList2[B]): MyList2[B] = list

  // hofs

  def foreach(f: Nothing => Unit): Unit = () // the () is the unit value

}

class Cons[+A](h: A, t: MyList2[A]) extends MyList2[A] {
  // linked list being composed

  def head: A = h
  def tail: MyList2[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](element: B): MyList2[B] = new Cons(element, this)
  def printElements: String =
    if(t.isEmpty) "" + h
    else h + " " + t.printElements

  /* FLATMAP WORKS THIS WAY if you pass a list [1, 2]
  //  [1,2].flatMap(n => [n, n+1])
  //  = [1,2] ++ [2].flatMap(n => [n, n+1])
  //  = [1,2] ++ [2,3] ++ Empty.flatMap(n => [n , n+1])
  //  = [1,2] ++ [2, 3] ++ Empty
  //  = [1, 2, 2, 3]
  //
  //   */


def flatMap[B](transformer: A => MyList2[B]): MyList2[B] =
  transformer(h) ++ t.flatMap(transformer)


  /*
  Filter works in this way
  [1,2,3].filter(n % 2 == 0) =
  // the first element fails as not divisible by two so it returns
  [2,3].filter(n % 2 == 0) =
  // this time it passes 2 so it is
  new Cons(2, [3].filter(n % 2 == 0))
  new Cons(2, Empty.filter(n % 2 == 0) (if you look at the Empty object, filter being called on nothing returns Empty
  new Cons(2, Empty)


   */

  def filter(predicate: A => Boolean): MyList2[A] =
    if(predicate(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)

  /*
  The formula below is the equivalent because when we print below we override it like this: override def transform(elem: Int): Int = elem * 2

  as an example [ 1, 2, 3].map(n * 2)
  = new Cons( 2, [2,3].map(transformer = [2, 3].map(n * 2) // the 'transformer' is n * 2
  = new Cons(2, new Cons(4, [3].map(n * 2)))
  = new Cons(2, new Cons(4, new Cons(6, Empty.map(n * 2))))
  = new Cons(2, new Cons(4, new Cons(6, Empty)))

   */

  def map[B](transformer: A => B): MyList2[B] =
    new Cons(transformer(h), t.map(transformer))

  /* EXPLANATION OF ++ below
//  [1, 2] ++ [3, 4, 5]
//  = new Cons(1, [2] ++ [3, 4, 5])
//  = new Cons(1, new Cons(2, Empty ++ [3, 4, 5]))
//  = new Cons(1, new Cons(2, new Cons(3, new Cons(4, new Cons(5)))))
//   */

  def ++[B >: A](list: MyList2[B]): MyList2[B] = new exercises.Cons(h, t ++ list)

  def foreach(f: A => Unit): Unit = {
    f(h) // As Cons has a h as the head of the list, this applies the function to the 0 element in that list. i.e the head
    t.foreach(f) // this takes the tail list and applies foreach which in turn applies function f to each element in the list
  }

}
// these are basically function types
//trait MyPredicate[-T] { // this is T => Boolean
//  def test(elem: T): Boolean
//}
//
//trait MyTransformer[-A, B] { // A => B
//  def transform(elem: A): B
//}




object ListTest2 extends App {
//  val list = new Cons(1, new Cons( 2, new Cons(3, Empty)))
//  println(list.tail.head)
//  println(list.add(4).head)
//  println(list.isEmpty)
//
//  // polymorphic call
//  println(list.toString)

  val listOfIntegers10: MyList2[Int] = Empty
  val listOfStrings10: MyList2[String] = Empty

  val listOfIntegers: MyList2[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val cloneListOfIntegers: MyList2[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val anotherListOfIntegers: MyList2[Int] = new Cons(4, new Cons(5, new Cons(6, Empty)))
  val listOfStrings: MyList2[String] = new Cons("Hello", new Cons("Scala", Empty))

  println(listOfIntegers.toString)
  println(listOfStrings.toString)
  println(listOfIntegers == cloneListOfIntegers)



  println(s" Times each element by two longform ${listOfIntegers.map(new Function1[Int, Int] {
    override def apply(elem: Int): Int = elem * 2
  }).toString}")


  println(s" Times each element in list by two using lambda ${listOfIntegers.map(elem => elem * 2).toString}")

  // the above could be listOfIntegers.map(_ * 2).toString

  println(listOfIntegers.filter(new Function1[Int, Boolean] {
    override def apply(elem: Int): Boolean = elem % 2 == 0
  }).toString)

  println(s" Using lambda ${listOfIntegers.filter(elem => elem % 2 == 0).toString}")

  // the above could be listOfIntegers.filter(_ % 2 ==0).toString

  println(listOfIntegers.flatMap(new Function1[Int, MyList2[Int]] {
    override def apply(elem: Int): MyList2[Int] = new Cons(elem, new Cons(elem + 1, Empty))
  }).toString)

  println(s"Using lambda ${listOfIntegers.flatMap(elem => new Cons(elem, new Cons(elem + 1, Empty))).toString}")

}