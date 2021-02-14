package day3

import scala.io.Source

object Day3 {

  def main(args: Array[String]): Unit = {

    val fileName = "/Users/mori1110/Work/adventofcode/src/main/scala/day3/puzzle.txt"
    val s        = Source.fromFile(fileName)
    val list     = try s.getLines.toList finally s.close
    println("question1: ", question1(list))

    val slopes = List((1, 1), (3, 1), (5, 1), (7, 1), (1, 2))
    println(question2(list, 1, 1))
    println(question2(list, 3, 1))
    println(question2(list, 5, 1))
    println(question2(list, 7, 1))
    println(question2(list, 1, 2))
    println("question2: "  + slopes.foldLeft(1L)((v1, v2) => v1 * question2(list, v2._1, v2._2)))
  }

  def question1(list: Seq[String]): Int = list.zipWithIndex.count(v => isTree(1 + v._2 * 3, list.head.length, v._1))

  def question2(list: Seq[String], right: Int, down: Int): Int = {
    list.zipWithIndex.count {
      case v if v._2 % down != 0 => false
      case v => isTree(1 + (v._2 / down) * right, list.head.length, v._1)
    }
  }

  private def isTree(pos: Int, width: Int, row: String): Boolean = {
    val tree = if (pos % width == 0) row(width - 1) else row(pos % width - 1)
    tree.toString == "#"
  }
}
