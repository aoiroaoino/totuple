package totuple.plugin

import scala.tools.nsc.Global
import scala.tools.nsc.plugins.{Plugin, PluginComponent}
import scala.tools.nsc.transform.{Transform, TypingTransformers}

class CaseClassToTuplePlugin(val global: Global) extends Plugin {
  override val name = "totuple-plugin"
  override val description = "Add methods to case classes"
  override val components: List[PluginComponent] = new CaseClassToTuplePluginComponent(global) :: Nil
}

class CaseClassToTuplePluginComponent(val global: Global)
    extends PluginComponent
    with Transform
    with TypingTransformers
{
  import global._

  override val phaseName = "addtotuplemethod"
  override val description = "add methods to case classes"
  override val runsAfter = "parser" :: Nil

  override def newTransformer(unit: CompilationUnit): Transformer =
    new CaseClassToTupleTransformer(unit)

  class CaseClassToTupleTransformer(unit: CompilationUnit) extends TypingTransformer(unit) {

    override def transform(tree: Tree): Tree = tree match {
      case classDef @ ClassDef(mods, name, _, impl) if mods.hasFlag(Flag.CASE) && 0 < numOfCtorArgs(impl) && numOfCtorArgs(impl) <= 22 =>
        val rhs = numOfCtorArgs(impl) match {
          case 1 => q"Tuple1(${name.toTermName}.unapply(this).get)"
          case _ => q"${name.toTermName}.unapply(this).get"
        }
        val toTuple = atOwner(impl.symbol)(newDefDef(impl.symbol, rhs)(name = newTermName("toTuple")))
        val newImpl = atPos(impl.pos)(Template(impl.parents, impl.self, toTuple :: impl.body))
        copyClassDef(classDef)(impl = newImpl)
      case _ =>
        super.transform(tree)
    }

    // support only to the first argument group
    private def numOfCtorArgs(template: Template): Int =
      template.body
        .collectFirst { case DefDef(_, name, _, vparamss, _, _) if name string_== "<init>" => vparamss }
        .flatMap(_.headOption)
        .map(_.length)
        .getOrElse(0)
  }
}
