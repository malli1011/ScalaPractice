package com.practice.eg
package com.scala.advance.implicits

object OrganiseImplicits extends App {

  //sort method takes an implicit argument, this uses default implicit from default scala package (scala.predef)
  println(List(1, 2, 4, 53, 6).sorted)

  //now the above sort will use the below implicit function as it has high precedence then the default one.
  implicit def reverseOrdering: Ordering[Int] = Ordering.fromLessThan(_ > _)
  //implicit def naturalOrdering : Ordering[Int] = Ordering.fromLessThan(_<_)
  //if you uncomment the above line then the code will not compile as we have two implicit functions in the same scope with same signature


  /*
      Implicits (used as implicit parameters):
        - val/var
        - object
        - accessor methods = defs with no parentheses
     */
  //Exercise
  case class Person(name: String, age: Int)

  private val persons = List(
    Person("Steve", 30),
    Person("Amy", 22),
    Person("John", 66)
  )

  /*implicit val alphabeticOrdering: Ordering[Person] = Ordering.fromLessThan((a, b) => a.name.compareTo(b.name) < 0)
  println(persons.sorted)*/

  //when we have to implicits in the same scope then we need to pass the implicit as argument
  /*implicit val orderingByAge: Ordering[Person] = Ordering.fromLessThan((a, b) => a.age < b.age)
  println(persons.sorted(orderingByAge))*/

  /*
       Implicit scope
       - normal scope = LOCAL SCOPE
       - imported scope
       - companions of all types involved in the method signature
         - List
         - Ordering
         - all the types involved = A or any supertype
      */
  // def sorted[B >: A](implicit ord: Ordering[B]): List[B]
  /* object Person{
     implicit val alphabeticOrdering: Ordering[Person] = Ordering.fromLessThan((a, b) => a.name.compareTo(b.name) < 0)
   }
 */

  object AlphabeticNameOrdering {
    implicit val alphabeticOrdering: Ordering[Person] = Ordering.fromLessThan((a, b) => a.name.compareTo(b.name) < 0)
  }

  private object AgeOrdering {
    implicit val ageOrdering: Ordering[Person] = Ordering.fromLessThan((a, b) => a.age < b.age)
    implicit val ageOrderingRevere: Ordering[Person] = Ordering.fromLessThan((a, b) => a.age > b.age)
  }

  //If you have multiple implicits for a given function, then put the functions in to respective object class and package them.
  //Then import the specific object

  import AgeOrdering.ageOrdering

  println(persons.sorted)

  /* import AlphabeticNameOrdering._
   println(persons.sorted)*/


  /*
    Exercise.
    - totalPrice = most used (50%), keep in a companion object
    - by unit count = 25%, we can import it as it is used only 25%
    - by unit price = 10%, can declared in local scope or can be imported
   */

  case class Purchase(nUnits: Int, unitPrice: Double) {
    private def totalPrice() = unitPrice * nUnits
  }

  object Purchase {
    implicit val totalPriceOrdering: Ordering[Purchase] = Ordering.fromLessThan((a, b) => a.totalPrice() < b.totalPrice())
  }

  object UnitCountOrdering {
    implicit val unitCountOrdering: Ordering[Purchase] = Ordering.fromLessThan((a, b) => a.nUnits < b.nUnits)
  }

  object unitPriceOrdering {
    implicit val unitPriceOrdering: Ordering[Purchase] = Ordering.fromLessThan((a, b) => a.unitPrice < b.unitPrice)
  }

}
