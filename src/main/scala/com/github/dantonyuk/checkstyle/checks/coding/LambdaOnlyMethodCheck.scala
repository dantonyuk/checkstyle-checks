package com.github.dantonyuk.checkstyle.checks.coding

import com.puppycrawl.tools.checkstyle.api._
import TokenTypes._

val DEFAULT_METHODS = "java.util.Objects.isNull,java.util.Objects.nonNull"

class LambdaOnlyMethodCheck(_methods: String) extends AbstractCheck:

  def this() = this(DEFAULT_METHODS)

  private val methods = _methods.split(raw"\s*,\s*").map(Import(_))
  private val matchingCalls = new scala.collection.mutable.HashSet[String]

  override def beginTree(rootAst: DetailAST) =
    matchingCalls.clear()

  override def visitToken(ast: DetailAST) =
    ast match
      case Import(i) =>
        matchingCalls ++= methods.flatMap(i.extractCall(_).toSeq)
      case MethodCall(m) if matchingCalls(m) =>
        log(ast.line, ast.column, s"Method $m should be used as method reference only");
      case _ =>

  override def getAcceptableTokens: Array[Int] =
    getRequiredTokens

  override def getDefaultTokens: Array[Int] =
    getRequiredTokens

  override def getRequiredTokens: Array[Int] =
    Array(METHOD_CALL, IMPORT, STATIC_IMPORT)
