package com.practice.eg
package com.practice.eg.part3


/*
1. Expand MyList
    - foreach method A => Unit
      [1,2,3].foreach(x=> println(x))
    -sort function ((A,A) => Int) =>MyList
      [1,2,3].sort((x,y) => y-x) => [3,2,1]
    -zipWith(list, (A,A) => MyList[B}
      [1,2,3].zipWith([4,5,6], x*y)=>[1*4,2*5,3*6] = [4,10,18]
    -fold(start)(function) => a value
      [1,2,3].fold(0)(x+y) = 6
2. toCurry(f:(Int,Int) => Int => (Int =>Int =>Int)
    fromCurry((Int =>Int =>Int) => (Int,Int) => Int

3. compose(f,g) => x => f(g(x))
    andThen(f,g) => x =>g(f(x))

*/


abstract class MyList[+A] {
  def getHead: A

  def tail: MyList[A]

  def isEmpty: Boolean

  def add[B >: A](element: B): MyList[B]

  def printElements: String

  override def toString: String = "[" + printElements + "]"

  //Higher order functions
  def map[B](transformer: A => B): MyList[B]

  def flatMap[B](transformer: A => MyList[B]): MyList[B]

  def filter(myPredicate: A => Boolean): MyList[A]

  //concatenation
  def ++[B >: A](list: MyList[B]): MyList[B]

  //hofs
  def foreach(f: A => Unit): Unit

  def sort(compare: (A, A) => Int): MyList[A]
}

object Empty extends MyList[Nothing] {
  override def getHead: Nothing = throw new NoSuchElementException

  override def tail: MyList[Nothing] = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def add[B >: Nothing](element: B): MyList[B] = new Cons[B](element, Empty)

  override def printElements: String = ""

  override def sort(compare: (Nothing, Nothing) => Int): MyList[Nothing] = Empty


  def map[B](transformer: Nothing => B): MyList[B] = Empty

  def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = Empty

  def filter(myPredicate: Nothing => Boolean): MyList[Nothing] = Empty

  def ++[B >: Nothing](list: MyList[B]): MyList[B] = list

  override def foreach(f: Nothing => Unit): Unit = () //just return unit value
}

class Cons[+A](head: A, t: MyList[A]) extends MyList[A] {
  override def getHead: A = head

  override def tail: MyList[A] = t

  override def isEmpty: Boolean = false

  override def add[B >: A](element: B): MyList[B] = new Cons[B](element, this)

  override def printElements: String =
    if (t.isEmpty) "" + getHead
    else s"$getHead ${t.printElements}"

  override def filter(myPredicate: A => Boolean): MyList[A] =
    if (myPredicate(getHead)) new Cons(getHead, t.filter(myPredicate))
    else t.filter(myPredicate)

  override def map[B](transformer: A => B): MyList[B] =
    new Cons(transformer(getHead), t.map(transformer))

  def ++[B >: A](list: MyList[B]): MyList[B] = new Cons[B](getHead, t ++ list)

  def flatMap[B](transformer: A => MyList[B]): MyList[B] = transformer(getHead) ++ t.flatMap(transformer)

  override def foreach(f: A => Unit): Unit = {
    f(head)
    tail.foreach(f)
  }

  override def sort(compare: (A, A) => Int): MyList[A] = {
    def insert(x: A, sortedList: MyList[A]): MyList[A] = {
      if (sortedList.isEmpty) new Cons(x, Empty)
      else if (compare(x, sortedList.getHead) <= 0) new Cons(x, sortedList)
      else new Cons(sortedList.getHead, insert(x, sortedList.tail))
    }

    val sortedTail = t.sort(compare)
    insert(head, sortedTail)
  }


}

object HOFExercise extends App {
  val listInt = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val listStrings = new Cons("Malli", new Cons("Arjun", new Cons("Goki", Empty)))

  println(listInt)
  println(listStrings)

  println(listInt.map(x => x * x))
  println(listInt.filter(x => x % 2 == 0))

  println(listInt ++ listInt)

  val anotherTransformer: (Int => MyList[Int]) = elem => new Cons[Int](elem, new Cons[Int](elem + 1, Empty))
  println(listInt flatMap anotherTransformer)

  listInt.foreach(x => println(x))
  listInt.sort((x: Int, y: Int) => y - x).foreach(println)
}

