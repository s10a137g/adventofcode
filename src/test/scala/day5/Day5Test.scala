package day5

import org.scalatest.FunSuite

class Day5Test extends FunSuite {

  test("Day5.question1") {
    assertResult(357)  { Day5.getSeatId("FBFBBFFRLR") }
    assertResult(567)  { Day5.getSeatId("BFFFBBFRRR") }
    assertResult(119)  { Day5.getSeatId("FFFBBBFRRR") }
    assertResult(820)  { Day5.getSeatId("BBFFBBFRLL") }
  }
}
