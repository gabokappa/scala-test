package lectures.part3fp

import java.nio.channels.NetworkChannel

object TuplesAndMaps extends App {

  // tuples are finite orderered "lists
  val aTuple = new Tuple2(2, "Hello, Scala") // Tuple2[Int, String] is the same as (Int, String) The parenthesis is syntactic sugar.
  val aTuple2 = Tuple2(2, "Hello, Scala") // same as above but different syntax
  val aTuple3 = (2, "Hello, Scala") // all these versions are different declarations of the same thing. a two type tuple or Tuple2

  println(aTuple._1)
  println(aTuple.copy(_2 = "goodbye java")) // same style with case classes
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

  /*
  1. What would happen if there were two original entries "Jim" -> 555 and "JIM" -> 900? // it prints/ picks the last mention
  2. Social network based on maps
  - add a person to the network
  - remove a person
  - friend (mutual)
  - unfriend

  - number of friends of a person
  - how many people have NO friends
  - if there is a social connection between two people(direct or not)
   */

  val duplicates = Map(("Jim", 555), ("JIM", 789))

  println(duplicates.map(pair => pair._1.toLowerCase -> pair._2))

  def add(network: Map[String, Set[String]], person: String): Map[String, Set[String]] =
    network + (person -> Set())

  def friend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)

    network + (a -> (friendsA + b)) + (b -> (friendsB + a))
    // the new pairing created here replace the existing pairings if there was one.
  }

  def unfriend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)

    network + (a -> (friendsA - b)) + (b -> (friendsB - a))
  }

  def remove(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
  // Cannot just remove the key from the network Map, because that person's friends will have a key in their friends list that will be no longer existant.
    // new auxillary methods below
    def removeAux(friends: Set[String], networkAcc: Map[String, Set[String]]): Map[String, Set[String]] =
      if (friends.isEmpty) networkAcc
      else removeAux(friends.tail, unfriend(networkAcc, person, friends.head))

    val unfriended = removeAux(network(person), network)
    unfriended - person
    // at this point, unfriended has removed the friends list from the key the key can be simply removed form the network
  }

  val empty: Map[String, Set[String]] = Map()
  val network = add(add(empty, "Bob"), "Mary")
  println(s"This network $network")

  println(friend(network, "Bob", "Mary"))
  val friendedNet = friend(network, "Bob", "Mary")

  println(unfriend(friend(network, "Bob", "Mary"), "Bob", "Mary"))
  println(unfriend(friendedNet, "Bob", "Mary"))

  println(network)

  println(remove(friendedNet, "Bob"))

  // Jim, Bob, Mary

  val people = add(add(add(empty, "Bob"), "Mary"), "Jim")
  val jimBob = friend(people, "Bob", "Jim")
  val testNet = friend(jimBob, "Mary", "Bob")

  println(s"Printing our test network: $testNet")

  def nFriends(network: Map[String, Set[String]], person: String): Int =
    if (!network.contains(person)) 0
    else (network(person).size)

  println(nFriends(testNet, "Bob"))

  def mostFriends(network: Map[String, Set[String]]): String =
    network.maxBy(pair => pair._2.size)._1

  // In the above line, we call the method maxBy on the map. maxBy receives a lambda from the pairing to a value. The value has to be comparable.
  // so for each key value pair in the map, we return the size of the value of the key. So the size of the Set per key, i.e the size of friends in the set.
  // as we're calling the maxBy, it returns the pairing in other words the key value pair that has the largest number/size
  // and we ask to return the first element ._1 i.e the key of that pair to get the name of the person with most friends


  println(mostFriends(testNet))

  def nPeopleWithNoFriends(network: Map[String, Set[String]]): Int =
    // another way of doing this would be:
    // TODO one way to do this: network.filter(pair => pair._2.isEmpty).size
    // in the above, we filter for each pair, and for each pair check if the value of that pair is empty, then return size so the number of the pairs that match that filter
    network.filterKeys(k => network(k).isEmpty).size
  // call .filterKeys on the network which will filter through the keys whose friends list is empty.
  // the network(k) returns the value, so the set of friends for that person aka key. The size methods returns the numbers of pairings that fall under this mapping.
  // TODO a short way to write this is network.count(pair => pair._2.isEmpty) // for every pairing test whether pair._2 is empty and then count how many answer true to that
  // TODO an even SHORTER way to write this network.count(_._2.isEmpty. This is shorthand lambda syntax

    println(nPeopleWithNoFriends(testNet))

  def socialConnection(network: Map[String, Set[String]], a: String, b: String): Boolean = {
    // bfs is breadth first search
    // the below is can I find target in discovered people having already searched/ considered people  in considered people already
    def bfs(target: String, consideredPeople: Set[String], discoveredPeople: Set[String]): Boolean ={
      if(discoveredPeople.isEmpty) false
      else {
        val person = discoveredPeople.head
        if  (person == target) true
        else if(consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople.tail)
        else bfs(target, consideredPeople + person, discoveredPeople.tail ++ network(person))
      }
    }
    bfs(b, Set(), network(a) + a)
  }

  println(socialConnection(testNet, "Bob", "Mary"))
  println(socialConnection(network, "Mary", "Bob")) // this returns false as there is no connection

}
