package com.practice.eg
package com.practice.eg.part2

import scala.language.postfixOps

object MethodNotations extends App {

  class Person(val name: String, favoriteMovie: String) {
    def likes(movie: String): Boolean = movie == favoriteMovie

    def hangOutWith(friend: Person): String = s"${this.name} likes to hang out with ${friend.name}"

    def unary_! : String = s"$name, What the heck!"

    def unary_~ : String = s"$name, What the heck~"

    def unary_+ : String = s"$name, What the heck+"

    def unary_- : String = s"$name, What the heck-"

    def isAlive: Boolean = true

    def apply(): String = s"Hi, My name is $name and I like $favoriteMovie"
  }

  val mary = new Person("Mary", "Inception")

  //infix notation or operator notation. this only works with single argument methods.
  // this makes the method to be used as operator. it is also called syntactic sugar

  //The below two method calls are equivalent
  println(mary.likes("Inception"))
  println(mary likes "Inception")

  private val friend = new Person("Tom", "Flight Club")
  println(mary hangOutWith friend)
  println(mary.hangOutWith(friend))

  //prefix notation
  val x = -1
  val y = 1.unary_-
  //unary_ prefix only works with - + ~ !
  println(y)

  println(!mary)
  println(~mary)
  println(+mary)
  println(-mary)

  //postfix notation //can applied to methods with no parameters.
  println(mary.isAlive)
  println(mary isAlive)

  //apply is a special function which can be called on the object using () with out method name
  println(mary.apply())
  println(mary())

}
