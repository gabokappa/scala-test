package exercises.part2ex

object OOEX1 extends App {

  val author = new Writer("Gabo", "Marquez", 1927)
  val novel = new Novel("One Hundred Years of Solitude", 1967, author)

  println("The author's age is " + novel.authAge)
  println(novel.isWrittenBy(author))

  val counter = new Counter
  counter.inc.print
  counter.inc.inc.inc.print
  counter.inc(10).print

}

class Writer(firstName: String, surname: String, val year: Int) {
  def fullName: String = firstName + " " + surname
}

class Novel(name: String, year: Int, author: Writer) {
  def authAge: Int = year - author.year
  def isWrittenBy(author: Writer): Boolean = author == this.author // could also omit Boolean here
  def copy(newYear: Int): Novel = new Novel(name, newYear, author)
}

class Counter(val count: Int = 0) {
  def inc = {
    println("Incrementing")
    new Counter(count + 1)
  }
  def dec = {
    println("Decrementing")
    new Counter(count - 1)
  }

  def inc(n: Int): Counter = {
    if (n <= 0) this
    else inc.inc(n-1)
//    new Counter(count + n)
  }
  def dec(n: Int): Counter = {
    if (n <= 0) this
    else dec.dec(n-1)
  }

  def print = println(count)
}

//  val writer = new Writer("Gabo", "Marquez", 1927)
//  println(writer)
//  println(writer.fullName)
//  val novel = new Novel("One hundred years of solitude", 1967, writer.fullName)
//  println(novel)
//  println(novel.authAge(writer.birthYear))
//  println(novel.isWrittenBy())
//
//}
//
//class Writer(firstName: String, surname: String, val birthYear: Int) {
//
//  def fullName: String = s"${this.firstName} ${this.surname}"
//
//}

//class Novel(bookName: String, release: Int, author: String) {
//
//  def authAge(authBirth: Int): Int = release - authBirth
//  def isWrittenBy(): String = s"This book was written by $author"
//
//  def Copy(newRelease: Int): Unit = new Novel(bookName, newRelease, author)

//}