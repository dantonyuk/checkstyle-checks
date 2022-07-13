package com.github.dantonyuk.checkstyle.checks.coding

import com.puppycrawl.tools.checkstyle.api._
import TokenTypes._

val DEFAULT_METHODS = "java.util.Objects.isNull,java.util.Objects.nonNull"

class LambdaOnlyMethodCheck(_methods: String) extends AbstractCheck:

  def this() = this(DEFAULT_METHODS)

  private val matchingCalls = new scala.collection.mutable.HashSet[String]
  private val methods =
    """(?<=^|[^\w.])([\w.]+?)\.(\w+)\.(\w+)(?=[^\w.]|$)""".r
      .findAllMatchIn(_methods)
      .map(_.subgroups)
      .toArray

  override def beginTree(rootAst: DetailAST) =
    matchingCalls.clear()
    matchingCalls ++= methods.map { case List(p, c, m) => s"$p.$c.$m" }

  override def finishTree(rootAst: DetailAST) =
    println(matchingCalls)

  override def visitToken(ast: DetailAST) =
    ast.astType match
      case IMPORT =>
        val Array(ip, ic) = collect(ast.findFirstToken(DOT)).split("\\.(?=[^.]+$)")
        for (
          List(p, c, m) <- methods
          if p == ip && (c == ic || ic == "*")
        ) matchingCalls += s"$c.$m"
      case STATIC_IMPORT =>
        val Array(ip, ic, im) = collect(ast.findFirstToken(DOT)).split("\\.(?=([^.]+\\.)?[^.]+$)")
        for (
          List(p, c, m) <- methods
          if p == ip && c == ic && (m == im || im == "*")
        ) matchingCalls += m
      case METHOD_CALL =>
        val method = collect(ast.first)
        println(s"Checking $method")
        if (matchingCalls(method))
          log(ast.line, ast.column, s"Method $method should be used as method reference only");

  override def getAcceptableTokens: Array[Int] =
    getRequiredTokens

  override def getDefaultTokens: Array[Int] =
    getRequiredTokens

  override def getRequiredTokens: Array[Int] =
    Array(METHOD_CALL, IMPORT, STATIC_IMPORT)
