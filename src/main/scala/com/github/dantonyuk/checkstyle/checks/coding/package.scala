package com.github.dantonyuk.checkstyle.checks.coding

import com.puppycrawl.tools.checkstyle.api._
import TokenTypes._

extension (ast: DetailAST)
  inline def astType = ast.getType
  inline def line = ast.getLineNo
  inline def column = ast.getColumnNo
  inline def text = ast.getText
  inline def first = ast.getFirstChild
  inline def last = ast.getLastChild

def collect(ast: DetailAST): String =
  ast.astType match
    case DOT =>
      s"${collect(ast.first)}.${collect(ast.last)}"
    case IDENT =>
      ast.text
    case STAR =>
      "*"
    case _ =>
      throw IllegalStateException(
        s"Invalid type in ${ast.text} (${ast.line}, ${ast.column}): ${ast.astType}")
