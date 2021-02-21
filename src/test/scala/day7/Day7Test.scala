package day7

import org.scalatest.FunSuite

import scala.io.{BufferedSource, Source}

class Day7Test extends FunSuite {

  test("Day7.question1") {
    val fileName: String   = "src/test/scala/day7/test-puzzle.txt"
    val s: BufferedSource = Source.fromFile(fileName)
    val list: Seq[String] = try s.getLines.toList finally s.close

    assertResult(4) {Day7.question1(list)}
  }

  test("Day7.question2") {
    val fileName: String   = "src/test/scala/day7/test-puzzle2.txt"
    val s: BufferedSource = Source.fromFile(fileName)
    val list: Seq[String] = try s.getLines.toList finally s.close
    val allBag = Bag.buildList(list)
    val target = Bag.searchBag("shiny gold", allBag)
    println(target.map(_.countBags(allBag)).getOrElse(0) - 1)

//    println(Day7.countBags(("1", "shiny", "gold"), rule))

//    assertResult(126) {Day7.question2(list)}
  }
}
