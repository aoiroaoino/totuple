package cc2t.plugin

case class User(id: Long, name: String) {
//  def toTuple: (Long, String) = User.unapply(this).get
}

object Test {
  val user = User(1, "aoiroaoino")
  assert(user.toTuple == (1, "aoiroaoino"))
}
