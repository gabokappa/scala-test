package lectures.part3fp

object TuplesAndMaps extends App {

  // tuples are finite orderered "lists
  val aTuple = new Tuple2(2, "Hello, Scala") // Tuple2[Int, String] is the same as (Int, String) The parenthesis is syntactic sugar.
  val aTuple2 = Tuple2(2, "Hello, Scala") // same as above but different syntax
  val aTuple3 = (2, "Hello, Scala") // all these versions are different declarations of the same thing. a two type tuple or Tuple2

  println(aTuple._1)
  println(aTuple.copy(_2 = "goodbye java")) // same stylw with case classes
  println(aTuple.swap) // this swaps the order of the elements

  // Maps

  // Maps are collections to associate keys to values. Keys are the values we index these maps, and the values are the data that correspond to those keys

  val aMap: Map[String, Int] = Map()

  val phoneBook = Map(("Jim", 555), ("Daniel", 789)) // in the map we've put tuples string and int, or also called pairings
  val phoneBook2 = Map("Jim" -> 555, "Daniel" -> 789) // exactly the same as above just different syntax
  // a -> b is syntactic sugar for (a, b)
  println(s"this is phoneBook ${phoneBook}")
  println(s"thus is phoneBook2 ${phoneBook2}")

  // map ops

  // we can query a map if it has a keys
  println(phoneBook.contains("Jim")) // this returns a boolean true as it does contain that key
  println(phoneBook("Jim")) // this returns the value of the key. if the key doesn't exist and exception is thrown

  // to avoid raising an exception and guard against errors you can apply a method that returns a default value if the key isn't found

  val phoneBook3 = Map("Jim" -> 555, "Daniel" -> 789).withDefaultValue(-1)
  println(phoneBook3("Gabriel")) // this no longer raises an exception but returns a minues one

  // how to add a pairing

  val newPairing = "Mary" -> 678
  val newPhoneBook = phoneBook + newPairing
  println(newPhoneBook)

  // functionals on maps

  //map, flatMap, filter

  println(phoneBook.map(pair => pair._1.toLowerCase -> pair._2))

  // filterKeys
  // pass a predicate through the filterKeys

  println(phoneBook.filterKeys(x => x.startsWith("D")))
  // the above is the lambda syntax. These print only the keys that pass trhe predicate
  println(phoneBook.filterKeys(_.startsWith("J")))


  // mapValues
  // this iterates through all the values, gives them a label number and times them by 10
  println(phoneBook.mapValues(number => number * 10))
  println(phoneBook.mapValues(number => "0245-" + number))

  // conversions to other collections

  println(s"I'm being turned into a List: ${phoneBook.toList}")
  println(s"I'm turning from a List to a map ${List(("Daniel", 555)).toMap}")

  val names = List("Bob", "Gabriel", "Barry", "Garry", "Andy")
  println(names.groupBy(name => name.charAt(0)))


}
