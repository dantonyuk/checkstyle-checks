package com.github.dantonyuk.checkstyle.checks.coding

import com.puppycrawl.tools.checkstyle.api._
import TokenTypes._

case class Import(
  packageName: String,
  className: String,
  methodName: String):

  def extractCall(method: Import) =
    (this, method) match
      case (Import(p1, c1, "_"), Import(p2, c2, m2))
        if p1 == p2 && (c1 == c2 || c1 == "*") =>
        Option(s"$c2.$m2")
      case (Import(p1, c1, m1), Import(p2, c2, m2))
        if p1 == p2 && (c1 == c2 || c1 == "*") && (m1 == m2 || m1 == "*") =>
        Option(m2)
      case _ =>
        Option.empty

object Import:
  private val import_re = raw"(.+?)\.([^.]+)\.([^.]+)".r

  def apply(s: String) = s match
    case import_re(p, o, m) => new Import(p, o, m)
    case _ => throw IllegalStateException(s"Invalid import $s")

  def unapply(i: Import): Import = i

  def unapply(ast: DetailAST): Option[Import] =
    ast.astType match
      case STATIC_IMPORT =>
        Option(Import(collect(ast.findFirstToken(DOT))))
      case IMPORT =>
        Option(Import(collect(ast.findFirstToken(DOT)) + "._"))
      case _ =>
        Option.empty
