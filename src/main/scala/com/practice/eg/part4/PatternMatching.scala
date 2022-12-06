package com.practice.eg
package com.practice.eg.part4

import scala.util.Random

object PatternMatching extends App {

  private val random = new Random()
  private val x = random.nextInt(10)

  //this is similar to switch case in other languages
  private val description = x match
    case 1 => "the one"
    case 2 => "double or nothing"
    case 3 => "third time in the charm"
    case _ => "something else" //_ = wildcard for default

  println(x)
  println(description)

  //1. Decompose values
  private case class Person(var name: String, var age: Int)

  private val bob = Person("Bob", 10)
  println(s"Name: ${bob.name}, Age: ${bob.age}")

  private val greeting = bob match
    case Person(n, a) if a < 18 => s"Hi, My name is $n and I am minor"
    case Person(n, a) => s"Hi, My name is $n and I am $a years old"
    case null => "Hi, I don't know who I am"

  println(greeting)

  /*
  1. cases are matched in order
  2. what if not cases match? MatchError
  3. type fo the PM expression? unified type fo all the types in all the cases
  4. PM works really well with case classes
  */

  sealed class Animal

  case class Dog(breed: String) extends Animal

  private case class Parrot(greeting: String) extends Animal

  private val animal: Animal = Dog("Terra Nova")

  animal match
    case Dog(breed) => println(s"Matched a dog of $breed breed")
    case Parrot(msg) => println(s"Parrot say $msg")
    case _ => println("Is neither dog nor parrot")

}
