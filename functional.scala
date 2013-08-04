def bind1( f:(Int,Int)=>Int, v:Int ) = (a:Int)=>f(a,v)

def logWhen( f:(Int)=>Int, message:String ) = (a:Int) => { println(message); f(a) }

def encache( f:(Int)=>Int ) = {
	val cache = scala.collection.mutable.Map[Int,Int]()
	(v:Int) => { if ( !(cache contains v) ) cache(v) = f(v)
	             cache(v) }
}