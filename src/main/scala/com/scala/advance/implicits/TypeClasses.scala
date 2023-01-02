package com.practice.eg
package com.scala.advance.implicits

object TypeClasses extends App {

  trait HTMLWritable {
    def toHtml: String
  }

  case class User(name: String, age: Int, email: String) extends HTMLWritable {
    override def toHtml: String = s"<div>$name ($age yo) <a href=$email/> </div>"
  }

  User("John", 32, "john@rockthejvm.com").toHtml
  /*
    1 - for the types WE write
    2 - ONE implementation out of quite a number
   */

  // option 2 - pattern matching
  object HTMLSerializerPM {
    def serializeToHtml(value: Any) = value match {
      case User(n, a, e) =>
      case _ =>
    }
  }

  /*
    1 - lost type safety
    2 - need to  modify the code every time
    3 - still ONE implementation
   */

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

  object DateSerializer extends HTMLSerializer[Date] {
    override def serialize(date: Date): String = s"<div>${date.toString()}</div>"
  }

  // 2 - we can define MULTIPLE serializers
  object PartialUserSerializer extends HTMLSerializer[User] {
    def serialize(user: User): String = s"<div>${user.name} </div>"
  }

  // part 2
  object HTMLSerializer {
    def serialize[T](value: T)(implicit serializer: HTMLSerializer[T]): String =
      serializer.serialize(value)

    def apply[T](implicit serializer: HTMLSerializer[T]) = serializer
  }

  implicit object IntSerializer extends HTMLSerializer[Int] {
    override def serialize(value: Int): String = s"<div style: color=blue>$value</div>"
  }

  println(HTMLSerializer.serialize(42))
  println(HTMLSerializer.serialize(john))

  // access to the entire type class  interface
  println(HTMLSerializer[User].serialize(john))


  // part 3
  implicit class HTMLEnrichment[T](value: T) {
    def toHTML(implicit serializer: HTMLSerializer[T]): String = serializer.serialize(value)
  }

  println(john.toHTML) // println(new HTMLEnrichment[User](john).toHTML(UserSerializer))
  // COOL!
  /*
    - extend to new types
    - choose implementation
    - super expressive!
   */

  println(2.toHTML)
  println(john.toHTML(PartialUserSerializer))

  /*
    - type class itself --- HTMLSerializer[T] { .. }
    - type class instances (some of which are implicit) --- UserSerializer, IntSerializer
    - conversion with implicit classes --- HTMLEnrichment
   */

  //context bounds

  def htmlBoilerPlate[T](content: T)(implicit HTMLSerializer: HTMLSerializer[T]): String = {
    s"<html><body>${content.toHTML(HTMLSerializer)}</body></html>"
  }

  //the above method can be simplifies as below. here the HTMLSerializer is context bound to the type of content
  def simplifiedHtmlBoilerPlate[T: HTMLSerializer](content: T): String = {
    s"<html><body>${content.toHTML}</body></html>"
  }


  //implicitly
  //this can be used to get the implicit value defined for a class somewhere else

  case class Permissions(var str: String)

  implicit val defaultPermissions: Permissions = Permissions("0744")

  //image the above two lines are defined in some package which we can't access in our code
  //now we can get the default implicit value using implicitly as below

  val standardPerms = implicitly[Permissions]
  println(standardPerms)


  def simplifiedHtmlBoilerPlate2[T: HTMLSerializer](content: T): String = {
    val serializer = implicitly[HTMLSerializer[T]]
    s"<html><body>${content.toHTML(serializer)}</body></html>"
  }

  println(simplifiedHtmlBoilerPlate(john))
}
