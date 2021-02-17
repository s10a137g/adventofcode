package day6

import scala.io.{BufferedSource, Source}

object Day6 {

  lazy val f: String => Boolean = v => !v.matches("")

  def main(args: Array[String]): Unit = {

    val fileName: String   = "src/main/scala/day6/puzzle.txt"
    val s: BufferedSource = Source.fromFile(fileName)
    val list: Seq[String] = try s.getLines.toList finally s.close

    println(question1(list))
    println(question2(list))
  }

  def question1(list: Seq[String]): Int = {
    distinctCharSeq(split(list, f)).map(_.length).sum
  }

  def question2(list: Seq[String]): Int = {
    val groupAnswerSet = split(list, f).map(_.map(_.toSet))

    groupAnswerSet.map(v => v.foldLeft(v.head)((v1, v2) => v1.intersect(v2))).map(_.size).sum
  }

  /** private method */
  def split(list: Seq[String], f: String => Boolean): Seq[Seq[String]] = {
    val (a, b) = list.span(v => f(v))
    b.isEmpty match {
      case true  => Seq(a)
      case false => Seq(a) ++ split(b.tail, f)
    }
  }

  def distinctCharSeq(charSeq: Seq[Seq[String]]): Seq[Seq[Char]] = {
    val a = charSeq.map(_.flatten)
    a.map(_.distinct)
  }
//  private def convertListToMap(list: Seq[String]): Seq[Map[String, String]] = {
//    val f: String => Boolean = v => !v.matches("")
//    val splitList = split(list, f)
//  }
}
