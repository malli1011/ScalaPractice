package com.practice.eg
package com.practice.eg.part2

object ScalaObjects extends App {

  //Scala doesn't have static types, instead we use object to define the static types in Scala
  object Person {
    //Static/Class level functionality
    val N_EYES = 2
    val canFly = false

    def apply(father: Person, mother: Person): Person = new Person("Bobbie " + father.name)
  }

  class Person(var name: String, age: Int = 0) {
    //instance level functionality
    //We can access this only by creating an instance of Person
  }

  var p1 = Person
  var p2 = Person

  println(p1 == p2)


  var p3 = new Person("Marry", 33)
  var p4 = new Person("Marry", 33)
  var p5 = new Person("John", 30)
  println(p3 == p4)
  var child = Person(p5,p4);
  println(child.name)

}
