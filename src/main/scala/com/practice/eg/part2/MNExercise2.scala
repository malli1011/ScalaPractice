package com.practice.eg
package com.practice.eg.part2

import scala.language.postfixOps

object MNExercise2 extends App {
  var marry = new Person2("Marry", "Inception")
  println((marry + "Rock Star") ())
  println((+marry).age)
  println(marry.learnsScala())
  println(marry learns "Java")
}

class Person2(var name: String, var favouriteMovie: String, var age: Int = 0) {
  //  val x = 2;
  //  println("This is internal variable : "(x + 1))

  def greet(name: String): Unit = println(s"${this.name} says: Hi, $name")

  def greet(): Unit = println(s"Hi, I am $name")

  def this() = this("No Name", "No favourite Movie")

  def +(nickName: String): Person2 = new Person2(s"$name ($nickName)", favouriteMovie)

  def unary_+ : Person2 = new Person2(this.name, this.favouriteMovie, this.age + 1)

  def apply(): String = s"Hi, My name is $name and I like $favouriteMovie"

  def apply(times: Int): String = s"$name watched $favouriteMovie $times times"

  def learnsScala(): String = this learns "Scala"

  def learns(lang: String): String = s"$name learns $lang"
}