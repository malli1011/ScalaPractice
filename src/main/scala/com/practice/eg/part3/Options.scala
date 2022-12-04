package com.practice.eg
package com.practice.eg.part3

import scala.util.Random

object Options extends App {

  private val firstOption: Option[Nothing] = None
  private val secondOption: Option[Int] = Some(10)

  println(firstOption.orElse(secondOption).get)
  println(secondOption.map(_ * 2).isEmpty)


  private var config = Map("host" -> "122.22.123.43", "port" -> "8080")

  class Connection {
    def connect() = "connected"
  }

  private object Connection {
    private val random = new Random(System.nanoTime())

    def apply(host: String, port: String): Option[Connection] = {
      if (random.nextBoolean()) Some(new Connection)
      else None
    }
  }

  private val host = config.get("host")
  private val port = config.get("port")

  private val connectionSts = host.flatMap(h => port.flatMap(p => Connection(h, p))).map(c => c.connect())
  println(connectionSts.getOrElse("Not connected"))

}
