package day3

//import day3.Day3
import day3.Day3.{Puzzle, question1, question2}
import org.scalatest.FunSuite

import scala.io.Source

class Day3Test extends FunSuite{
  val fileName = "src/test/scala/day3/test-puzzle.txt"
  val s        = Source.fromFile(fileName)
  val list     = try s.getLines.toList finally s.close
  test("Day3.question1") {
    assertResult(7)  { question1(list) }
  }

  test("Day3.question2") {
    val slopes = List((1, 1), (3, 1), (5, 1), (7, 1), (1, 2))
    println(slopes.foldLeft(1)((v1, v2) => v1 * (question2(list, v2._1, v2._2))))
  }

}
