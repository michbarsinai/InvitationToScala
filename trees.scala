sealed abstract class Tree
case class Sum( l:Tree, r:Tree ) extends Tree
case class Var( n:String ) extends Tree
case class Con( v:Int ) extends Tree

def evalTree( t:Tree, e:Map[String,Int] ): Int = t match {
	case Sum( l , r ) => evalTree(l, e) + evalTree( r, e )
	case Var( n )     => e(n)
	case Con( i )     => i
}

val env = Map("a"->10, "b"->20)