package day5

import scala.annotation.tailrec
import scala.io.{BufferedSource, Source}

object Day5 {

  def main(args: Array[String]): Unit = {
    val fileName: String   = "src/main/scala/day5/puzzle.txt"
    val s: BufferedSource = Source.fromFile(fileName)
    val list: Seq[String] = try s.getLines.toList finally s.close

    println(question1(list))
    println(list.length)
    println(question2(list))

  }

  def question1(list: Seq[String]): Int = {
    list.map(v => getSeatId(v)).max
  }

  def question2(list: Seq[String]): Seq[Int] = {
    val seatList = list.map(v => getSeatId(v))
    val low = seatList.min
    val high = seatList.max
    println(low, high)

    (low to high).filter(v => !seatList.contains(v))
  }

  def getSeatId(value: String): Int = {
    val rowOpt = getHighAndLow(value.take(7), "F", "B", 0 to 127)
    val colOpt = getHighAndLow(value.drop(7), "L", "R", 0 to 7)

    rowOpt.flatMap(row => colOpt.map(col => row * 8 + col)).getOrElse(0)
  }

  @tailrec
  def getHighAndLow(value: String, high: String, low: String, range: Seq[Int]): Option[Int] = {
    // 対象の文字列の先頭を取得
    val x = value.take(1) match {
      case v if v == high => range.take(range.length / 2)
      case v if v == low  => range.drop(range.length / 2)
    }
    if (value.drop(1) == "") x.headOption else getHighAndLow(value.drop(1), high, low, x)
  }

}
