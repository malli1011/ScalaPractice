package com.practice.eg
package com.practice.eg.part2

object Generics extends App {

  //Generic classes
  class MyList[A] {
    //use the type A

    def add[B <: A](element: B): MyList[B] = new MyList[B]
  }

  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]

  class MyMap[Key, Value]

  //Generic methods
  object MyList {
    def empty[A]: MyList[A] = null
  }

  //usage of generic methods
  val emptyListOfInts = MyList.empty[Int]

  //Variance problem

  class Animal

  class Cat extends Animal

  class Dog extends Animal

  //Similar to CovariantList<? extends Animal>
  //1. Covariance
  class CovariantList[+A]

  val animalList: CovariantList[Animal] = new CovariantList[Cat]

  //2. Invariance
  class InvariantList[A]

  val invariantAnimal: InvariantList[Animal] = new InvariantList[Animal]

  //3. ContraVariance
  class Trainer[-A]

  val trainer: Trainer[Cat] = new Trainer[Animal]

  //bounded types
  //Cage accepts any subtype of Animal class
  class Cage[A <: Animal](animal: A)

  val cage = new Cage(new Dog)

  class Car
  //val newCage = new Cage(new Car) // we can not use Cage with car as car is not subtype of Animal

}
