package com.practice.eg
package com.practice.eg.part4

object PatternMatchingEx extends App {
  /*
  simple function uses PM
  takes and expression => human readable form

  Sum(Number(2),Number(3)) => 2 + 3
  Sum(Number(2),Number(3), nUMBER(4)) => 2 + 3 + 4
  Prod(Sum(Number(2), Number(1)), Number(3)) => (2+1)*3
  Sum(Prod(Number(2), Number(1)), Number(3)) => 2 * 1 + 3
  */
  trait Expr

  case class Number(n: Int) extends Expr

  private case class Sum(e1: Expr, e2: Expr) extends Expr

  private case class Prod(e1: Expr, e2: Expr) extends Expr

  private def show(exp: Expr): String = exp match {
    case Number(n) => s"$n"
    case Sum(e1, e2) => show(e1) + " + " + show(e2)
    case Prod(e1, e2) =>
      def maybeShowParentheses(ex: Expr) = ex match {
        case Prod(_, _) => show(ex)
        case Number(_) => show(ex)
        case _ => s"(${show(ex)})"
      }

      maybeShowParentheses(e1) + " * " + maybeShowParentheses(e2)
  }

  println(show(Sum(Number(2), Number(3))))
  println(show(Sum(Sum(Number(2), Number(3)), Number(4))))
  println(show(Prod(Sum(Number(2), Number(3)), Number(10))))
  println(show(Sum(Prod(Number(2), Number(3)), Number(10))))
  println(show(Prod(Sum(Number(2), Number(3)), Prod(Number(10), Number(5)))))

}
