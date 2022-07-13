package com.github.dantonyuk.checkstyle.checks.coding

import com.puppycrawl.tools.checkstyle.api._
import TokenTypes._

case class MethodCall(callingMethod: String)

object MethodCall:
  def unapply(ast: DetailAST): Option[String] =
    if (ast.astType != METHOD_CALL) Option.empty
    else Option(collect(ast.first))
