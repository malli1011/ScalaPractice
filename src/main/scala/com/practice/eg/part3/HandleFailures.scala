package com.practice.eg
package com.practice.eg.part3

import scala.util.{Failure, Random, Success, Try}

object HandleFailures extends App {

  private val aSuccess = Success(3)
  private val aFailure = Failure(new RuntimeException("SUPER FAILURE"))

  println(aSuccess)
  println(aFailure)

  private def unSafeMethod(): String = throw new RuntimeException("NO STRING FOR YOUR BUSTER")

  //Try objects via the apply method
  private val potentialFailure = Try(unSafeMethod())
  println(potentialFailure)

  //syntax sugar
  private val anotherFailure = Try {
    throw new RuntimeException("NO STRING FOR YOUR BUSTER")
  }
  if (anotherFailure.isSuccess)
    println(anotherFailure.get)

  private val fallBackTry = Try(unSafeMethod()) orElse Try {
    "Backup String"
  }

  println(fallBackTry.get)

  //If you design the API
  private def betterUnsafeMethod(): Try[String] = Failure(new RuntimeException)

  private def betterBackupMethod(): Try[String] = Success("A Valid Result")

  private val betterFallback = betterUnsafeMethod() orElse betterBackupMethod()
  println(betterFallback.get)

  //map flatMap filter
  println(aSuccess.map(_ * 2))
  println(aSuccess.flatMap(x => Success(x * 10)))
  println(aSuccess.filter(_ > 10))

  //Exercise

  private val hostname = "localhost"
  private val port = "8080"

  private def renderHTML(page: String): Unit = println(page)

  class Connection {
    val random = new Random(System.nanoTime())

    def get(url: String): String = {
      if (random.nextBoolean()) s"<html>....$url....</html>"
      else throw new RuntimeException("Connection interrupted")
    }

    def getSafe(url: String): Try[String] = Try(get(url))
  }

  private object HttpService {
    private val random = new Random(System.nanoTime())

    private def getConnection(host: String, port: String): Connection = {
      if (random.nextBoolean()) new Connection
      else throw new RuntimeException("All connections are being used")
    }

    def getSafeConnection(host: String, port: String): Try[Connection] = Try(HttpService.getConnection(host, port))
  }

  //if you get html page print it to console
  private val possibleConnection = HttpService.getSafeConnection(hostname, port)
  private val possibleHtml = possibleConnection.flatMap(conn => conn.getSafe("/test"))
  possibleHtml.foreach(renderHTML)

  //shorthand version
  HttpService.getSafeConnection(hostname, port).flatMap(con => con.getSafe("/test")).foreach(renderHTML)

  //for-comprehension version
  for {
    conn <- HttpService.getSafeConnection(hostname, port)
    html <- conn.getSafe("/test")
  } renderHTML(html)

  /* Imperative code
  try{
  connection = HttpService.getConnection(host,port)
  try{
      html = connection.get("/test")
      renderHtml(html)
      }catch(AnotherException){
      }
  }catch(Exception){
  }
  */
}
