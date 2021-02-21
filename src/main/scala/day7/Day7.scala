package day7

import scala.io.{BufferedSource, Source}

object Day7 {
  def main(args: Array[String]): Unit = {
    val fileName: String   = "src/main/scala/day7/puzzle.txt"
    val s: BufferedSource = Source.fromFile(fileName)
    val list: Seq[String] = try s.getLines.toList finally s.close

    println(question1(list))
    println(question2(list))
  }

  def question1(puzzle: Seq[String]): Int = {
    val allBags = Bag.buildList(puzzle)
    allBags.count(_.hasTargetBag("shiny gold", allBags))
  }

  def question2(puzzle: Seq[String]): Int = {
    val allBags = Bag.buildList(puzzle)
    val target =  Bag.searchBag("shiny gold", allBags)
    target.map(_.countBags(allBags)).getOrElse(0) - 1
  }
}
