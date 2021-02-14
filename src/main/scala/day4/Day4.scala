package day4

import scala.io.{BufferedSource, Source}


object Day4 {
  def main(args: Array[String]): Unit = {
    val fileName:String   = "src/main/scala/day4/puzzle.txt"
    val s: BufferedSource = Source.fromFile(fileName)
    val list: Seq[String] = try s.getLines.toList finally s.close

    println(question1(list))
    println(question2(list))

  }
  def question1(list :Seq[String]): Int = {
    convertListToMap(list).count(isValid)
  }
  def question2(list :Seq[String]): Int = {
    convertListToMap(list).count(isStrictValid)
  }
  /** private method */
  private def split(list: Seq[String], f: String => Boolean): Seq[Seq[String]] = {
    val (a, b) = list.span(v => f(v))
    b.isEmpty match {
      case true  => Seq(a)
      case false => Seq(a) ++ split(b.tail, f)
    }
  }
  private def convertListToMap(list: Seq[String]): Seq[Map[String, String]] = {
    val f: String => Boolean = v => !v.matches("")
    val splitList = split(list, f)

    splitList.map(v => {
      val target = v.foldLeft("")((v1, v2) => v1 + " " + v2).trim
      target.split(' ').map { v => {
        (v.split(":")(0), v.split(":")(1))
      }}.toMap
    })
  }

  /** validation of question1 */
  private def isValid(target: Map[String, String]): Boolean = target.size == 8 || (target.size == 7 && !target.contains("cid"))

  /** validation of question2 */
  private def isStrictValid(target:Map[String, String]): Boolean = {
    // byr (Birth Year) - four digits; at least 1920 and at most 2002.
    val byrValid = target.get("byr") match {
      case Some(v) => v.toInt >= 1920 && v.toInt <= 2002
      case _ => false
    }
    // iyr (Issue Year) - four digits; at least 2010 and at most 2020.
    val iyrValid = target.get("iyr") match {
      case Some(v) => v.toInt >= 2010 && v.toInt <= 2020
      case _ => false
    }
    // eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
    val eyrValid = target.get("eyr") match {
      case Some(v) => v.toInt >= 2020 && v.toInt <= 2030
      case _ => false
    }
    // hgt (Height) - a number followed by either cm or in:
    //   If cm, the number must be at least 150 and at most 193.
    //   If in, the number must be at least 59 and at most 76.
    val hgtValid = target.get("hgt") match {
      case Some(v) if v.matches("[0-9]+cm") => v.dropRight(2).toInt >= 150 && v.dropRight(2).toInt <= 193
      case Some(v) if v.matches("[0-9]+in") => v.dropRight(2).toInt >= 59  && v.dropRight(2).toInt <= 76
      case _ => false
    }
    // hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
    val hclValid = target.get("hcl") match {
      case Some(v)  => v.matches("#[0-9|a-f]{6}")
      case _ => false
    }
    // ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
    val eclValid = target.get("ecl") match {
      case Some(v)  => v.matches("amb|blu|brn|gry|grn|hzl|oth")
      case _ => false
    }
    // pid (Passport ID) - a nine-digit number, including leading zeroes.
    val pidValid = target.get("pid") match {
      case Some(v)  => v.matches("[0-9]{9}")
      case _ => false
    }
//    println(byrValid, iyrValid, eyrValid, hgtValid, hclValid, eclValid, pidValid)
    byrValid && iyrValid && eyrValid && hgtValid && hclValid && eclValid && pidValid
  }
}
