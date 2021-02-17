package day6

import org.scalatest.FunSuite

import scala.io.{BufferedSource, Source}

class Day6Test extends FunSuite{

  test("Day6.question1") {
    val fileName: String   = "src/test/scala/day6/test-puzzle.txt"
    val s: BufferedSource = Source.fromFile(fileName)
    val list: Seq[String] = try s.getLines.toList finally s.close

    val f: String => Boolean = v => !v.matches("")

    println(Day6.split(list,f))
    println(Day6.distinctCharSeq(Day6.split(list,f)))
    assertResult(11)  { Day6.question1(list) }
  }

  test("Day6.question2") {
    val fileName: String   = "src/test/scala/day6/test-puzzle.txt"
    val s: BufferedSource = Source.fromFile(fileName)
    val list: Seq[String] = try s.getLines.toList finally s.close

    val f: String => Boolean = v => !v.matches("")

    assertResult(6)  { Day6.question2(list) }
  }
}
