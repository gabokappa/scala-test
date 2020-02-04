package lectures.part4pm

import exercises.MyList2
import exercises.Cons
import exercises.Empty




object AllThePatterns extends App {

    // 1 -constants

    val x: Any = "Scala"
    // this mean that constants can be anything from a Integer, String, Boolean etc.
    val constants = x match {
      case 1 => "A number"
      case "Scala" => "THE SCALA!!!"
      case true => "The Truth"
      //    case AllThePatterns => "A singleton object"
    }

    // 2 - match anything
    // 2.1 wildcard

    val matchAnything = x match {
      case _ => // This is the wildcard that matches anything"This is anything?"
    }

    // 2.2 variable
    val matchAVariable = x match {
      case something => s"I used something variable as a case matchers so I can use the variable in this interpolation $something"
    }

    // 3 - Matchers with Tuples
    val aTuple = (1, 2)
    val matchATuple = aTuple match {
      // below I pass a tuple of literals for matching
      case (1, 1) =>
      // below is a tuple of nested patterns. the case below, if the pattern matches(i.e the second element is a '2')
      // then it extracts 'something' from the tuple if the rest of the pattern matches and you can use something in what is returned
      case (something, 2) => s"This is what the pattern matched: $something"
    }

    // Below shows how pattern matches can happen with tuples nested in tuples.
    val nestedTuple = (1, (2, 3))
    val matchANestedTuple = nestedTuple match {
      case (_, (2, v)) => "hello"// this still matches v with the 3, so a pattern can be done with nested tuples
    }

  // 4 - case classes being pattern matched - constructor pattern is the way to call it

  // Below is marked out as for some reason can't import Cons into the

//  val aList: MyList2[Int] = Cons(1, Cons(2, Empty))
//  val matchAList = aList match {
//    case Empty =>
//    case Cons(head, Cons(subhead, subtail)) =>
//  }

  // 5 - list patterns

  val aStandardList = List(1, 2, 3, 42)
  val standardListMatching = aStandardList match {
    case List(1, _, _, _) => // this is called an extractor for list. - advanced
    case List(1, _*) => // list of arbitrary length
    case 1 :: List(_) => // this is an infix pattern
    case List(1, 2, 3) :+ 42 => // this is also an infix pattern

  }

  // 6 - type specifiers
  // the below is how you get pattern matchers to conform to certain types
  val unknown: Any = 2
  val unknownMatch = unknown match {
    case list: List[Int] => println(list) // this is an explicit type specififer to a specfic type
    case _ =>
  }

  // 7 - name binding
  /*
  // and explicit name binding
  val nameBindingMatch = aList match {
    case nonEmptyList @ Cons(_, _) => // the name @ section names a pattern. This is a name binding. this means you can use
      // the name after the =>
    case Cons(1, rest @ Cons(2, _)) => // name binding can be used inside nested patterns.
  }
*/

  // 8 - multi-patterns
//  val multipattern = aList match { // aList definition stipulated above.
//    case Empty | Cons(0, _) => // this is chained by a pipe operator. This is a COMPOUND pattern or multi patten. Can chain as many as you want.
//  }

//  // 9 - if guards
//  val secondElementSpecial = aList match {
//    case Cons(_, Cons(specialElement, _)) if specialElement % 2 == 0 => // the if guard will filter out the pattern by the predicate that it is given
//  }
//
//

  val numbers = List(1, 2, 3)
  val numbersMatch = numbers match {
    case listOfStrings: List[String] => "a list of strings"
    case listOfNumbers: List[Int] => "a list of numbers"
    case _ => "Nothing matched!"
  }

  println(numbersMatch) // this actually matches with a list of strings, despite being a list of numbers
  // JVM generics were introduced in Java 5. JVM is oblivious to generic types after type checking
  // type erasure is what the above is called.


}
