:paste
class Dataverse private ( val id:Int, var name:String )

object Dataverse {
	private[this] var lastId=0

	def getLastId = lastId
	def apply( name:String ) = {lastId += 1; new Dataverse(lastId, name)}
}


