package day7

import day7.Bag.searchBag

case class Bag(name: String, childBag: Seq[(Int, String)]) {

  def countBags(allBags: Seq[Bag]): Int = {
    childBag.foldLeft(1)((v1, v2) => {
      val foundBagOpt = searchBag(v2._2, allBags)
      foundBagOpt.map(foundBag => v1 + v2._1 * foundBag.countBags(allBags)).getOrElse(0)
    })
  }

  def hasTargetBag(targetName: String, allBags: Seq[Bag]): Boolean = {
    childBag.exists(_._2 == targetName) ||
    childBag.foldLeft(false)((v1, v2) => {
      val foundBagOpt = searchBag(v2._2, allBags)
      foundBagOpt match {
        case None           => false
        case Some(foundBag) => v1 || foundBag.childBag.exists(_._2 == targetName) || foundBag.hasTargetBag(targetName, allBags)
      }
    })
  }
}

object Bag {
  def buildList(list: Seq[String]): Seq[Bag] = {
    val f: (String, String) => Seq[String] = (v, p) => v.split(p).map(_.trim)

    val parseList = list
      .map(f(_, "contain"))
      .map(v => (v(0), v(1).replace(".", "")))
      .map(v => ((f(v._1, " ")(0), f(v._1, " ")(1)), f(v._2, ",")))

    parseList.map(row => {
      if (row._2 == Seq("no other bags")) {
        Bag(row._1._1 + " " + row._1._2, Nil)
      } else {
        val child = row._2.map(v => {
          val a = f(v, " ")
          (a(0).toInt, a(1) + " " + a(2))
        })
        Bag(row._1._1 + " " + row._1._2, child)
      }
    })
  }

  def searchBag(name: String, allBags: Seq[Bag]): Option[Bag] = allBags.find(_.name == name)

}
