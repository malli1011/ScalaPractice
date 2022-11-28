package com.practice.eg
package com.practice.eg.part2

object CaseClasses extends App {
  /*
   CaseClass are similar to record in Java15. it will have equals,hashCode,toString
  */

  case class Person(name: String, age: Int)

  //1. Class parameters are automatically promoted as fields
  val jim = new Person("Jim", 34)
  println(jim.name)

  //2. sensible toString
  //println(instance) = println(instance.toString) // similar to Java
  println(jim)
  //3. equals and hashCode implemented OOTB
  val jim2 = new Person("Jim", 34)
  println(jim == jim2)

  //4. CaseClasses (CC) have handy copy method
  val jim3 = jim.copy(age = 45)
  println(jim3)

  //5. CCs have companion objects
  val thePerson = Person
  val marry = Person("Marry",23)
  //6. CCs are serializable and CCs have extractor patterns = CCs can be used in PATTERN MATCHING

  //Case objects are similar to CaseClasses except point 5, they are not companion objects
  case object UnitedKingdom {
    def name: String = "the UK of GB and NI"
  }

}
