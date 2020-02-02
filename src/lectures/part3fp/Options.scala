package lectures.part3fp

import scala.util.Random

object Options extends App {

  val myFirstOption: Option[Int] = Some(4)
  val noOption: Option[Int] = None

  println(myFirstOption)

  def unsafeMethod(): String = null

  // Some should always have a valid value inside

  val result = Option(unsafeMethod()) // The apply method from the companion object of Option creates a Some or None depending on whether the value inside the Option is null or not. he vlaue of unsafeMethod

  println(s"Printing the result $result")

  // These are for chained methods.

  def backupMethod(): String = "A valid result"
  // in the below you have a chained result. you call Option(unsafeMethod) if this returns null, you can fall back on Option(backupMethod)
  // the Option(unsafeMethod) is your preferred option, but the Option(backupMethod) is your fallback.
  val chainedResult = Option(unsafeMethod()).orElse(Option(backupMethod()))

  // DESIGN unsafe APIs

  def betterUnsafeMethod(): Option[String] = None
  def betterBackupMethod(): Option[String] = Some("A valid result")

  val betterChainedResult = betterUnsafeMethod() orElse betterBackupMethod()

  // functions on Options

  println(myFirstOption.isEmpty)
  println(myFirstOption.get) // this is unsafe if the option is null

  // map, flatMap, filter
  println(myFirstOption.map(_ * 2))
  println(myFirstOption.filter(x => x > 10))
  println(myFirstOption.flatMap(x => Option(x * 10)))

  /*
  Exercise.
   */

  val config: Map[String, String] = Map(
    "host" -> "176.45.36.1",
    "port" -> "80"
  )

  class Connection {
    def connect = "Connected" // connect to some server
  }

  object Connection {
    val random = new Random(System.nanoTime())

    def apply(host: String, port: String): Option[Connection] =
      if (random.nextBoolean()) Some(new Connection)
      else None
  }

  // try to establish a connection, if so - print the connect method

  val host = config.get("host")
  val port = config.get("port")
  /*
  if (h !-null)
  if (p != null)
  return Connection.apply(h,p)
   */

  val connection = host.flatMap(h => port.flatMap(p => Connection.apply(h, p)))

  /*
  if (c !=null)
  return c.connect
  return null
   */
  val connectionStatus = connection.map(c => c.connect)

  //print line says if (connectionStatus == null) print(none) else print (Some connectionstatus.get))
  println(connectionStatus)

  /*
  if (status != null)
  println(status)
   */
  connectionStatus.foreach(println)

// chained calls
  config.get("host")
    .flatMap(host => config.get("port")
      .flatMap(port => Connection(host, port))
        .map(connection => connection.connect))
    .foreach((println))

  // for-comprehensions

  val forConnectionStatus = for {
    host <- config.get("host")
    port <- config.get("port")
    connection <- Connection(host, port)
  } yield connection.connect

  forConnectionStatus.foreach(println)

}
