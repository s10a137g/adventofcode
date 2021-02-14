package day4

import scala.io.{BufferedSource, Source}


object Day4 {
  def main(args: Array[String]): Unit = {
    val fileName:String   = "src/main/scala/day4/puzzle.txt"
    val s: BufferedSource = Source.fromFile(fileName)
    val list: Seq[String] = try s.getLines.toList finally s.close

    println(question1(list))
  }
  def question1(list :Seq[String]): Int = {
    val f: String => Boolean = v => !v.matches("")
    val splitList = split(list, f)
    val a = splitList.map(v => {
      val target = v.foldLeft("")((v1, v2) => v1 + " " + v2).trim
      target.split(' ').map { v => {
        (v.split(":")(0), v.split(":")(1))
      }}.toMap
    })
    a.count(isValid)
  }
  def split(list: Seq[String], f: String => Boolean): Seq[Seq[String]] = {
    val (a, b) = list.span(v => f(v))
    b.isEmpty match {
      case true  => Seq(a)
      case false => Seq(a) ++ split(b.tail, f)
    }
  }
  def convertListToMap(list: Seq[String]): Seq[Map[String, String]] = {
    list.map(v1=> {
      v1.split(' ').map { v => {
        (v.split(":")(0), v.split(":")(1))
      }}.toMap
    })
  }

  def isValid(target: Map[String, String]): Boolean = target.size == 8 || (target.size == 7 && !target.contains("cid"))
}
