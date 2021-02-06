package day2

import org.scalatest.FunSuite

class Day2Test extends FunSuite {
  test("Day2.question1") {
    val list = Seq("1-3 a: abcde", "1-3 b: cdefg", "2-9 c: ccccccccc")
    assertResult(2)  { Day2.question1(list) }
  }

  test("Day2.question2") {
    val list1 = Seq("1-3 a: abcde", "1-3 b: cdefg", "2-9 c: ccccccccc")
    val list2 = Seq("1-3 a: abcde", "1-3 b: cdefg", "2-9 c: caccccccc")
    assertResult(1)  { Day2.question2(list1) }
    assertResult(2)  { Day2.question2(list2) }
  }
}
