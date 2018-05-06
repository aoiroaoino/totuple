package totuple.plugin

case class Foo()
case class Bar(i: Int)
case class Baz(c: Char, s: String)
case class Qux(c: Char, d: Double)(s: String)
case class Quux(
    p1: Int,
    p2: Int,
    p3: Int,
    p4: Int,
    p5: Int,
    p6: Int,
    p7: Int,
    p8: Int,
    p9: Int,
    p10: Int,
    p11: Int,
    p12: Int,
    p13: Int,
    p14: Int,
    p15: Int,
    p16: Int,
    p17: Int,
    p18: Int,
    p19: Int,
    p20: Int,
    p21: Int,
    p22: Int,
    p23: Int
)

object Test {

  val bar = Bar(0)
  assert(bar.toTuple == Tuple1(0))

  val baz = Baz('a', "bc")
  assert(baz.toTuple == ('a', "bc"))

  val qux = Qux('a', 1.1)("bc")
  assert(qux.toTuple == ('a', 1.1))
}
