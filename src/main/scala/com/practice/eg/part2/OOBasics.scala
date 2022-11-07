package com.practice.eg
package com.practice.eg.part2

object OOBasics extends App {
  val person = new Person("Malli", 25)

  println("Person age: " + person.age)
  person.greet("Danial")
  person.greet()
  val person2 = new Person()

  println("Person age: " + person2.age)
  person2.greet("Danial")
  person2.greet()
}


class Person(name: String, var age: Int = 0) {
  val x = 2;
  println("This is internal variable : "(x + 1))

  def greet(name: String): Unit = println(s"${this.name} says: Hi, $name")

  def greet(): Unit = println(s"Hi, I am $name")

  def this() = this("No Name")
}
