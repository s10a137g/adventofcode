package day2

import scala.io.Source

object Day2 {
  def main(args:      Array[String]): Unit = {
    val fileName = "/Users/mori1110/Work/adventofcode/src/main/scala/day2/puzzle.txt"
    val s        = Source.fromFile(fileName)
    val list     = try s.getLines.toList finally s.close
    println(question2(list))
  }

  def question1(list: Seq[String]): Int    = {
    list.count(v => {
      val v1                                 = v.split(":")
      val (policyMin, policyMax, policyChar) = splitToPolicies(v1)
      val count      = v1(1).count(p => p.toString == policyChar)
      policyMin.toInt <= count && count <= policyMax.toInt
    })
  }

  def question2(list: Seq[String]): Int    = {
    list.count(v => {
      val v1         = v.split(":")
      val (policy1, policy2, policyChar) = splitToPolicies(v1)
      val p1         = v1(1).charAt(policy1.toInt)
      val p2         = v1(1).charAt(policy2.toInt)
      (p1.toString == policyChar && p2.toString != policyChar) ||
        (p1.toString != policyChar && p2.toString == policyChar)
    })
  }

  private def splitToPolicies(arg: Array[String]): (String, String, String) = {
    val policy     = arg(0)
    val policy1    = policy.split("-")(0)
    val policy2    = policy.split("-")(1).split(" ")(0)
    val policyChar = policy.split("-")(1).split(" ")(1)
    (policy1, policy2, policyChar)
  }
}
