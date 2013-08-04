
class Person( var name:String, val id:Int ) {
	def greet = "Hello, my name is %s".format(name)
}

class Person2( name:String, val id:Int ) {
	def greet = "Hello, my name is %s".format(name)
}

class Person3( name:String, val id:Int ) {
	val greet  = "Hello, my name is %s".format(name)
}

class Person4( aName:String, anId:Int ) {
	private val id = anId
	private[this] var pName = aName

	def name = pName
	def name_=( newName:String ) { pName = newName }

	override def toString = "[Person4 id:%d name:%s]".
										format( id, name )
}

