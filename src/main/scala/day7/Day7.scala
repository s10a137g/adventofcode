package day7

import scala.::
import scala.io.{BufferedSource, Source}

object Day7 {
  def main(args: Array[String]): Unit = {
    val fileName: String   = "src/main/scala/day7/puzzle.txt"
    val s: BufferedSource = Source.fromFile(fileName)
    val list: Seq[String] = try s.getLines.toList finally s.close

    println(question1(list))
//    println(question2(list))
  }

  def question1(puzzle: Seq[String]): Int = {
    search(convert(puzzle))
  }

  def convert(list: Seq[String]): Map[(String, String), Seq[(String, String)]] = {
    val f: (String, String) => Seq[String] = (v, p) => v.split(p).map(_.trim)
    val a = list.map(f(_, "contain")).map(v =>  (v(0), v(1).replace(".", "")))
    val b = a.map(v => ((f(v._1, " ")(0), f(v._1, " ")(1)), f(v._2, ","))).toMap
    b.map(v => (v._1, v._2.map(v1 => (f(v1, " ")(1), f(v1, " ")(2)))))
  }

  def convert2(list: Seq[String]): Map[(String, String), Seq[(String, String, String)]] = {
    val f: (String, String) => Seq[String] = (v, p) => v.split(p).map(_.trim)
    val a = list.map(f(_, "contain")).map(v =>  (v(0), v(1).replace(".", "")))
    val b = a.map(v => ((f(v._1, " ")(0), f(v._1, " ")(1)), f(v._2, ","))).toMap
    b.map(v => (v._1, v._2.map(v1 => (f(v1, " ")(0), f(v1, " ")(1), f(v1, " ")(2)))))
  }
  def search(rule: Map[(String, String), Seq[(String, String)]]): Int = {
    rule.values.count(recursiveSearch(_, rule))
  }

  def recursiveSearch(target: Seq[(String, String)], rule: Map[(String, String), Seq[(String, String)]]): Boolean = {
    if (target.contains(("shiny", "gold"))) {
      true
    } else {
      val f = target.map(v => rule.get(v) match {
        case Some(v) => recursiveSearch(v, rule)
        case _ => false
      })
      f.contains(true)
    }
  }

  def recursiveSearch2(target: Seq[(String, String, String)], rule: map[(string, string), seq[(string, string, string)]]): Int= {
    target.map(v =>{
      v match {
        case
      }
    })
  }

  def search2(target: (String, String, String), rule: map[(string, string), seq[(string, string, string)]]): Int = {
    target match {
      case
    }
  }
  def question2(puzzle: Seq[String]): Int = ???

}
