package lectures.part3fp

import scala.util.{Failure, Random, Success, Try}

object HandlingFailure extends App {

  // creating success and failure

  val aSuccess = Success(3)
  val aFailure = Failure(new RuntimeException("SUPER FAILURE"))

  println(aSuccess)
  println(aFailure)

  // the Try companion object apply method takes care of creating success and failures for you.

  def unsafeMethod(): String = throw new RuntimeException("NO STRING FOR YOU!")

  // Try object via the apply method
  val potentialFailure = Try(unsafeMethod())
  println(potentialFailure)
  // Try catches the exception and wraps it up in a failure if it gets caught.

  //syntax sugar
  val anotherPotentialFailure = Try {
    // code that might throw
  }

  // utilities. You can test whether a try is a success or a failure

  println(s"Testing if potentialfailure is a success : ${potentialFailure.isSuccess}")
  println(s"Testing if potentialfailure is a failure: ${potentialFailure.isFailure}")

  // orElse

  def backupMethod(): String = "A valid result"

  // below line is how you use unsafe APIs. you chain Try methods
  val fallbackTry = Try(unsafeMethod()).orElse(Try(backupMethod()))
  println(s"printin fallbackTry with chained Try methods: $fallbackTry")

  // IF you are designing the API and you know your code might throw an exception, wrap your computation around a Try method.

  def betterUnsafeMethod(): Try[String] = Failure(new RuntimeException)
  def betterBackupMethod(): Try[String] = Success("A valid result")
  val betterFallback = betterUnsafeMethod().orElse(betterBackupMethod())
  println(betterFallback)

  // whenever you think your code will return null then use Options. If you think it will throw exceptions then use Try.

  // Try has map, flatMap and filter.

  println(aSuccess.map(_ * 2))
  println(aSuccess.flatMap(x => Success(x * 10)))
  println(aSuccess.filter(_ > 10)) // this turns into failure which returns an exception

  // now try for-comprehensions

/*
Exercise - connect to a server
 */

  val host = "localhost"
  val port = "8080"
  def renderHTML(page: String) = println(page)

  class Connection {
    // this method can return an exception
    def get(url: String): String = {
      val random = new Random(System.nanoTime())
      if(random.nextBoolean()) "<html>...</html>"
      else throw new RuntimeException("Connection interrupted")
    }

    // adding safe APIs on the inside of the class
  // by using Try in the below line, I'm containing any potential exceptions that might be thrown along the way.
    def getSafe(url: String): Try[String] = Try(get(url))
  }

  object HttpService {
    val random = new Random(System.nanoTime())

    def getConnection(host: String, port: String): Connection = {
      if (random.nextBoolean()) new Connection
      else throw new RuntimeException("Someone else took the port")

      // adding safe APIs on the inside of the object
    }
      def getSafeConnection(host: String, port: String): Try[Connection] = Try(getConnection(host, port))
    }
  // if you get the html page from the connection print it to the console. i.e call renderHTML

  val possibleConnection = HttpService.getSafeConnection(host, port)
  val possibleHtml = possibleConnection.flatMap(connection => connection.getSafe("/home"))
  possibleHtml.foreach(renderHTML) // if you get both a good connection and the html out of it it prints the <html> etc

  // shorthand version of the above is to chain the calls

  HttpService.getSafeConnection(host, port)
  .flatMap(connection => connection.getSafe("/home"))
    .foreach(renderHTML)

// for-comprehensions version

  for {
    connection <- HttpService.getSafeConnection(host, port)
    html <- connection.getSafe("/")
  } renderHTML(html)

  /*
  The above two shorthand nd for-comprehensions would've been rendered

  try {
  connection = HttpService.getConnection(host, port)
  try {
  page = connection.get("/home")
  renderHTML(page)
  } catch (some other exception) {

  }
  } catch (exception) {
  }
   */

}
