package com.practice.eg
package com.practice.eg.part2

object ExceptionsExample extends App {

  class OverflowException extends RuntimeException

  class UnderflowException extends RuntimeException

  class DivisionByZeroException extends RuntimeException("Division by 0")

  class PocketCalculator {
    def add(x: Int, y: Int): Int = {
      val result = x + y;
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result > 0) throw new UnderflowException
      else result
    }

    def subtract(x: Int, y: Int): Int = {
      val result = x - y
      if (x < 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result

    }

    def multiply(x: Int, y: Int): Int = {
      val result = x * y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result < 0) throw new OverflowException
      else if (x > 0 && y < 0 && result > 0) throw new UnderflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result

    }

    def divide(x: Int, y: Int) = {
      if (y == 0) throw new DivisionByZeroException
      else x / y
    }

  }

  val pocketCalculator = new PocketCalculator

  println(pocketCalculator.add(Int.MaxValue, 1))
  println(pocketCalculator.subtract(Int.MaxValue, 1))
  println(pocketCalculator.divide(Int.MaxValue, 0))
  println(pocketCalculator.multiply(Int.MaxValue, 1))

}
