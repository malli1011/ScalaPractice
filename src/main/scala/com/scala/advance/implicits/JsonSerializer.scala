package com.practice.eg
package com.scala.advance.implicits

import com.scala.advance.implicits.JsonSerializer.JSONNumber

import java.sql.Date

object JsonSerializer extends App {

  /*
      Users, posts, feeds
      Serialize to JSON
     */

  case class User(name: String, age: Int, email: String)

  case class Post(content: String, createdAt: Date)

  case class Feed(user: User, posts: List[Post])

  sealed trait JSONValue {
    def stringfy: String
  }

  final case class JSONString(value: String) extends JSONValue {
    override def stringfy: String = "\"" + value + "\""
  }

  final case class JSONNumber(value: Int) extends JSONValue {
    override def stringfy: String = value.toString
  }

  final case class JSONArray(values: List[JSONValue]) extends JSONValue {
    override def stringfy: String = values.map(_.stringfy).mkString("[", ",", "]")
  }

  final case class JSONObject(vals: Map[String, JSONValue]) extends JSONValue {
    override def stringfy: String = vals.map {
      case (key, value) => "\"" + key + "\":" + value.stringfy
    }.mkString("{", ",", "}")
  }

  val data = JSONObject(Map(
    "user" -> JSONString("Daniel"),
    "posts" -> JSONArray(List(
      JSONString("Scala Rocks!"),
      JSONNumber(453)
    ))
  ))

  println(data.stringfy)

  // type class
  /*
    1 - type class
    2 - type class instances (implicit)
    3 - pimp library to use type class instances
   */

  trait JSONConverter[T] {
    def convert(v: T): JSONValue
  }

  implicit object NumberConverter extends JSONConverter[Int] {
    override def convert(v: Int): JSONValue = JSONNumber(v)
  }

  implicit object StringConverter extends JSONConverter[String] {
    override def convert(v: String): JSONValue = JSONString(v)
  }

  implicit object UserConverter extends JSONConverter[User] {
    override def convert(user: User): JSONValue = JSONObject(
      Map(
        "name" -> JSONString(user.name),
        "age" -> JSONNumber(user.age),
        "email" -> JSONString(user.email)
      )
    )
  }

  implicit object PostConverter extends JSONConverter[Post] {
    def convert(post: Post): JSONValue = JSONObject(Map(
      "content" -> JSONString(post.content),
      "created:" -> JSONString(post.createdAt.toString)
    ))
  }

  implicit object FeedConverter extends JSONConverter[Feed] {
    def convert(feed: Feed): JSONValue = JSONObject(Map(
      "user" -> UserConverter.convert(feed.user),
      "posts" -> JSONArray(feed.posts.map(post => PostConverter.convert(post)))
    ))
  }

  implicit class JSONOps[T](value: T) {
    def toJSON(implicit converter: JSONConverter[T]): JSONValue = converter.convert(value)
  }


  val now = new Date(System.currentTimeMillis())
  val john = User("John", 34, "john@rockthejvm.com")
  val feed = Feed(john, List(
    Post("hello", now),
    Post("look at this cute puppy", now)
  ))

  println(feed.toJSON.stringfy)
}
