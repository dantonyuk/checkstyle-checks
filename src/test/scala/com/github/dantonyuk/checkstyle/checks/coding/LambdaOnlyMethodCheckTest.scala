package com.github.dantonyuk.checkstyle.checks.coding

import org.junit.runner.RunWith
import org.junit.Test

import com.puppycrawl.tools.checkstyle.AbstractModuleTestSupport
import com.puppycrawl.tools.checkstyle.DefaultConfiguration

class LambdaOnlyMethodCheckTest extends AbstractModuleTestSupport:

  override protected[checkstyle] def getPackageLocation =
    getClass.getPackage.getName.replaceAll("\\.", "/")

  @Test
  def testSpecificImport =
    val config = AbstractModuleTestSupport.createModuleConfig(classOf[LambdaOnlyMethodCheck])
    val path = getPath("SpecificImport.java")
    val expected = Array(
      checkMsg(8, 24, "Objects.nonNull"),
      checkMsg(16, 23, "Objects.isNull"))
    verify(config, path, expected: _*)

  @Test
  def testStarImport =
    val config = AbstractModuleTestSupport.createModuleConfig(classOf[LambdaOnlyMethodCheck])
    val path = getPath("StarImport.java")
    val expected = Array(
      checkMsg(8, 24, "Objects.nonNull"),
      checkMsg(16, 23, "Objects.isNull"))
    verify(config, path, expected: _*)

  @Test
  def testSpecificStaticImport =
    val config = AbstractModuleTestSupport.createModuleConfig(classOf[LambdaOnlyMethodCheck])
    val path = getPath("SpecificStaticImport.java")
    val expected = Array(
      checkMsg(9, 16, "nonNull"),
      checkMsg(13, 15, "isNull"))
    verify(config, path, expected: _*)

  @Test
  def testStarStaticImport =
    val config = AbstractModuleTestSupport.createModuleConfig(classOf[LambdaOnlyMethodCheck])
    val path = getPath("StarStaticImport.java")
    val expected = Array(
      checkMsg(8, 16, "nonNull"),
      checkMsg(12, 15, "isNull"))
    verify(config, path, expected: _*)

  @Test
  def testNoImport =
    val config = AbstractModuleTestSupport.createModuleConfig(classOf[LambdaOnlyMethodCheck])
    val path = getPath("NoImport.java")
    val expected = Array.empty[String]
    verify(config, path, expected: _*)

  @Test
  def testFullNameCall =
    val config = AbstractModuleTestSupport.createModuleConfig(classOf[LambdaOnlyMethodCheck])
    val path = getPath("FullNameCall.java")
    val expected = Array(
      checkMsg(6, 30, "java.util.Objects.nonNull"),
      checkMsg(10, 29, "java.util.Objects.isNull"))
    verify(config, path, expected: _*)

  private[this] def checkMsg(line: Int, column: Int, method: String) =
    s"${line}:${column}: Method ${method} should be used as method reference only"
