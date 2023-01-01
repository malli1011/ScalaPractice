package com.practice.eg
package com.scala.advance.implicits

import scala.language.implicitConversions

object ImplicitsIntro extends App {

  // in the below code -> is an implicit function
  val pair: Tuple = "One" -> 1
  //println(pair)

  case class Person(name: String) {
    def greet = s"hi my name is $name!"
  }

  implicit def fromStringToPerson(str: String): Person = Person(str)

  println("Peter".greet)
  //now using implicits we can call the greet method on a string object
  //Compiler will look for classes that has a greet method

  //implicit parameters
  def increment(x: Int)(implicit amount: Int) = x + amount

  implicit val defaultAmount: Int = 10

  println(increment(5))

}
