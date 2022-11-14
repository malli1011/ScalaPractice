package com.practice.eg
package com.practice.eg.part2

object AnonymousClasses extends App {
  abstract class Animal {
    def eat(): Unit
  }

  val funnyAnimal: Animal = new Animal() {
    override def eat(): Unit = println("ahahahahahaah")
  }


  println(funnyAnimal.getClass)
  funnyAnimal.eat()

  class Person(name: String) {
    def hello(): Unit = println(s"Hi, my name is $name. How can I help you?")
  }

  //we can create anonymous class for non abstract classes also.
  val p = new Person("Malli") {
    override def hello(): Unit = println("Hi, How can I help you?")
  }

  p.hello()
}
