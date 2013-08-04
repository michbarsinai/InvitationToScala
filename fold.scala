/// fold for breaking lines.
import java.lang.Character._

def break( w:String ) = w.foldLeft[List[String]](Nil)(
    (l,c) => if ( l.isEmpty ) c.toString :: Nil
            else if ( isLetter(l.last.last) == isLetter(c) )
                l.dropRight(1) :+ l.last + c
            else
                l :+ c.toString)
