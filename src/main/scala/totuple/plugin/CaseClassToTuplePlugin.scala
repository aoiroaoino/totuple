package totuple.plugin

import scala.tools.nsc.Global
import scala.tools.nsc.plugins.{Plugin, PluginComponent}
import scala.tools.nsc.transform.{Transform, TypingTransformers}

class CaseClassToTuplePlugin(val global: Global) extends Plugin {
  override val name = "case-class-to-tuple-plugin"
  override val description = "Generate toTuple method for case class"
  override val components: List[PluginComponent] = new CaseClassToTuplePluginComponent(global) :: Nil
}

class CaseClassToTuplePluginComponent(val global: Global)
    extends PluginComponent
    with Transform
    with TypingTransformers
{
  import global._

  override val phaseName = "case-class-2-tuple"
  override val runsAfter = "parser" :: Nil

  override def newTransformer(unit: CompilationUnit): Transformer =
    new CaseClassToTupleTransformer(unit)

  class CaseClassToTupleTransformer(unit: CompilationUnit) extends TypingTransformer(unit) {

    override def transform(tree: Tree): Tree = tree match {
      case classDef @ ClassDef(mods, name, _, impl) if mods.hasFlag(Flag.CASE) =>
        val toTuple = atOwner(impl.symbol)(
          newDefDef(impl.symbol, q"${name.toTermName}.unapply(this).get")(name = newTermName("toTuple"))
        )
        val newImpl = atPos(impl.pos)(Template(impl.parents, impl.self, toTuple :: impl.body))
        copyClassDef(classDef)(impl = newImpl)
      case _ =>
        super.transform(tree)
    }
  }
}
