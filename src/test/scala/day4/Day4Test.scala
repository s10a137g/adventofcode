package day4

import org.scalatest.FunSuite

import scala.io.{BufferedSource, Source}
import day4.Day4.question1

class Day4Test extends FunSuite{

  val fileName:String   = "src/test/scala/day4/test-puzzle.txt"
  val s: BufferedSource = Source.fromFile(fileName)
  val list: Seq[String] = try s.getLines.toList finally s.close
  println(list)

  test("Day4.question1") {
    assertResult(2)  { question1(list) }
  }
}
