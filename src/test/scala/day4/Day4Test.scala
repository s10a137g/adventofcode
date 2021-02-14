package day4

import org.scalatest.FunSuite

import scala.io.{BufferedSource, Source}
import day4.Day4.{question1, question2}

class Day4Test extends FunSuite{


  test("Day4.question1") {
    val fileName:String   = "src/test/scala/day4/test-puzzle.txt"
    val s: BufferedSource = Source.fromFile(fileName)
    val list: Seq[String] = try s.getLines.toList finally s.close
    assertResult(2)  { question1(list) }
  }
  test("Day4.question2") {
    val fileName:String   = "src/test/scala/day4/test-valid.txt"
    val s: BufferedSource = Source.fromFile(fileName)
    val list: Seq[String] = try s.getLines.toList finally s.close
    assertResult(4)  { question2(list) }
  }

}
