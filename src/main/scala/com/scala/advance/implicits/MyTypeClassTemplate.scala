package com.practice.eg
package com.scala.advance.implicits

// TYPE CLASS
trait MyTypeClassTemplate[T] {
  def action(value: T): String

  def display(value: T): Unit
}

object MyTypeClassTemplate {
  def apply[T](implicit instance: MyTypeClassTemplate[T]) = instance
}

implicit object IntTemplate extends MyTypeClassTemplate[Int] {
  override def action(value: Int): String = s"value passed is $value"

  override def display(value: Int): Unit = println("This is an integer" + value)
}

implicit object StringTemplate extends MyTypeClassTemplate[String] {
  override def action(value: String): String = s"This is String Template.Value passed is $value"

  override def display(value: String): Unit = println("This is a String with Value: " + value)
}

object Execute extends App {
  MyTypeClassTemplate[String].display("Malli")
  println(MyTypeClassTemplate[Int].action(21))
}