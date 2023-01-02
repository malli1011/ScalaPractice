package com.practice.eg
package com.scala.advance.implicits

object TypeClasses extends App {

  //type class is trait that takes a type and describes what operation can be applied on that

  trait HTMLWritable {
    def toHtml: String
  }

  case class User(name: String, age: Int, email: String) extends HTMLWritable {
    override def toHtml: String = s"<div>$name ($age yo) <a href=$email/> </div>"
  }

  User("John", 32, "john@rtjvm.com").toHtml

  trait HTMLSerializer[T] {
    def serialize(value: T): String
  }

  implicit object UserSerializer extends HTMLSerializer[User] {
    def serialize(user: User): String = s"<div>${user.name} (${user.age} yo) <a href=${user.email}/> </div>"
  }

  val john = User("John", 32, "john@rockthejvm.com")
  println(UserSerializer.serialize(john))

  // 1 - we can define serializers for other  types

  import java.util.Date

  implicit object DateSerializer extends HTMLSerializer[Date] {
    override def serialize(date: Date): String = s"<div>${date.toString()}</div>"
  }

  // 2 - we can define MULTIPLE serializers
  object PartialUserSerializer extends HTMLSerializer[User] {
    def serialize(user: User): String = s"<div>${user.name} </div>"
  }


  //Part2

  private object HTMLSerializer {
    def serialize[T](value: T)(implicit serializer: HTMLSerializer[T]): String = serializer.serialize(value)
  }

  implicit object IntSerialize extends HTMLSerializer[Int] {
    override def serialize(value: Int): String = s"<div color='blue'>$value</div>"
  }

  println(HTMLSerializer.serialize(100))
  println(HTMLSerializer.serialize(john))
  println(HTMLSerializer.serialize(new Date()))

}
