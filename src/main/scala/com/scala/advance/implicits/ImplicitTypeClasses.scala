package com.practice.eg
package com.scala.advance.implicits

object ImplicitTypeClasses extends App {

  trait HTMLSerializer[T] {
    def serialize(value: T): String
  }

  private object HTMLSerializer {
    def serialize[T](value: T)(implicit serializer: HTMLSerializer[T]): String = serializer.serialize(value)
  }

  implicit object IntSerialize extends HTMLSerializer[Int] {
    override def serialize(value: Int): String = s"<div color='blue'>$value</div>"
  }

  println(HTMLSerializer.serialize(100))
}
