def toFuzzyStringInt( i:Int ) = i match {
	case 0 => "Nada"
	case 1 => "One"
	case 2 => "A Pair"
	case 12 => "a dozen"
	case _ if i<0 => "Less that zero"
	case _ => "%,d".format(i)
}

def prettyPrint( a:Any ) = a match {
	case i:Int => "%,d".format(i)
	case s:String => "[%s]".format(s)
	case sym:Symbol => ":%s".format(sym)
	case _ => a.toString
}