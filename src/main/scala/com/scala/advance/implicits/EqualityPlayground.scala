package com.practice.eg

object EqualityPlayground extends App {


  case class User(name: String, age: Int, email: String)

  trait Equal[T] {
    def apply(a: T, b: T): Boolean
  }

  implicit object NameEquality extends Equal[User] {
    override def apply(a: User, b: User): Boolean = a.name == b.name
  }

  object FullEquality extends Equal[User] {
    override def apply(a: User, b: User): Boolean = a.name == b.name && a.email == b.email
  }

  object Equal {
    def apply[T](a: T, b: T)(implicit equilizer: Equal[T]): Boolean = equilizer.apply(a, b)
  }

  val john = User("John", 32, "john@rockthejvm.com")
  val anotherJohn = User("John", 45, "anotherJohn@rtjvm.com")

  println(Equal(john, john))
  println(Equal(john, anotherJohn))

  /*
      Exercise - improve the Equal TC with an implicit conversion class
      ===(anotherValue: T)
      !==(anotherValue: T)
     */

  implicit class TypeSafeEquals[T](a: T) {
    def ===(b: T)(implicit equality: Equal[T]): Boolean = equality.apply(a, b)

    def !==(b: T)(implicit equality: Equal[T]): Boolean = !equality.apply(a, b)
  }

  println(john === anotherJohn)
  println(john !== anotherJohn)
}
