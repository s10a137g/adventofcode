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
    var seq1: Seq[String] = Seq()
    var seq2: Seq[String] = Seq()
    for (row <- list) {
      if (!row.matches("")) {
        seq2 :+= row
      } else {
        seq1 :+= seq2.foldLeft("")((v1, v2) => v1.concat(" " + v2))
        seq2 = Seq()
      }
    }
    convertListToMap(seq1).map(v => isValid(v)).count(_ == true)

  }
  def convertListToMap(list: Seq[String]): Seq[Map[String, String]] = {
    list.map(v1=> {
      v1.split(' ').map { v => {
        (v.split(":")(0), v.split(":")(1))
      }}.toMap
    })
  }

  def isValid(target: Map[String, String]): Boolean = target.size == 8 || (target.size == 7 && target.contains("cid"))
}
