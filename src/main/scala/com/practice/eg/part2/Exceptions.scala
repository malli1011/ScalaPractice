package com.practice.eg
package com.practice.eg.part2

object Exceptions extends App {

  //Exceptions in scala are similar to java exceptions.
  //throwable classes extend the Throwable Class. Exception and Error are the major subtypes of Throwable

  //Catch exceptions
  def getInt(withException: Boolean): Int = {
    if (withException) throw new RuntimeException("Value less than Zero")
    else 42
  }

  def printException(value: Int): Unit = {
    try {
      if (value < 0) throw new RuntimeException("Value less than Zero")
      else if (value == 0) throw new NullPointerException("Value can't be Zero")
      else println(s"value is $value")
    } catch {
      case e: NullPointerException => println("A NullPointer Exception occurred")
      case e: RuntimeException => println("A Runtime Exception occurred")
    } finally {
      println("Finally you are done!")
      println()
    }
  }

  val result = try {
    if (true) 40
    else 41
  } catch {
    case e: NullPointerException => 43
        // if you uncomment the below type the result type will be any otherwise its Int as all other expressions will result in an Int value.
    //case e: RuntimeException => println("A Runtime Exception occurred")
  } finally {
    /* code that will get executed always but finally is optional and it doesn't influence the return type of this expression*/
    //  Use finally only for side effects like logging or closing a file.
    println("Finally you are done!")
    println()
  }
  println(s"Result: $result")
  printException(-10)
  printException(0)
  printException(10)
  getInt(true)

  //How to define your own exceptions
  class MyException extends Exception

  val exception = new MyException

  throw exception

}
