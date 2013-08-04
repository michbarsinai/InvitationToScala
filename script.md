Invitation to Scala
======
Michael Bar-Sinai

**Tech talk at the IQSS@Harvard**
_2013-06-27_

1. Intro
	* Based in part on Scala for the impatient by Cay Horstmann, Scala for java programmers tutorial at Typesafe.com, and Mayer Goldberg's advanced programming class at BGU.
	* To get the full experience, have a scala REPL ready. It's available from the command line after you insall scala.
	* Get scala at: [http://www.scala-lang.org](http://www.scala-lang.org)

1. Presentation:
	1. 10k feet view of scala
		* clean design (e.g. map is a type of a collection)
		* No statements - just expressions
		* Statically typed ("system language"), concise and friendly syntax ("scripting language")
	1. Odersky: Stop trying to make java better, and make a better java
	1. JVM: All your favorite libs are there
	1. Collections: cf Java’s Minimum API, maximum impact (i.e. no last() on lists)
	1. Lang Features: comprehensive type system, continuations, more
	1. Java: 1996, ruby: ... 1995, Python: 1989

1. Procedural vs. Functional
	* philosophy, no wrong answers... or answer at all
	1. Procedural: Alan Turing
	1. Discourse: Data Crunching, “mechanical engineering mindset”, “Person in the Mailroom”
	1. Functional: Alonzo Church, Haskell Brooks Curry
		* a.k.a Lambda Calculus.
		* plumbing mindset
	1. State vs. concurrency, abstraction over processing unit
	1. Hype alert
	1. Question: _Where's OOP on this?_

1. REPL into
	* try: `1.to(10)`, `1 to 10`
	* `val` vs `var`: `val`s - values - can't change. `var`s - variables - can.
	* Function definitions, unicode identifiers
		`def sum( i:Int* ):Int = { return i.sum }`, we can call this function "∑"
	* Arrays are functions `Int` &rarr; `T`. Maps are similar.

			val array = Array(1,2,3)
			array(2)
			res0: Int = 3
	_Note that we don't use [] but (). This is more mathematical and less hardware oriented_.
	* Basic string operations: `*`, `format`
	* Example for clean design: String are sequences of characters. Show `size` as well as `length`, and `str(idx)`
		* `splitAt`. Present the Tuple data type

1. How long is the longest English word
	1. Get the wordset. <br/>
			`val words = io.Source.fromFile("/usr/share/dict/words").getLines.toSet` <br />
			_This is the path in OSX, on other systems it may be on a different path._
	1. Let's take a proc-ish `for` loop and morph it into functional form<br/>

			var maxLen = 0
			for ( w <- words ) {
				if ( maxLen < w.length ) {
					maxLen = w.length
				}
			}
			println( "maxLen is %d".format(maxLen) )

		* In scala:

			`words.map( (w:String) => { w.length } ).max`

		* No need for the braces, really, since it's just one line

			`words.map( (w:String) => w.length).max`

		* `words` is a set of `String`s, so clearly `w` is a string, no need to state its type explicitly

			`words.map( (w) => w.length )`

		* We only use `w` once, why name it? We can use the `_` magic char:

			`words.map( _.length ).max`

		* Note how this "tells the story better" than the `for` loop.

	1. other basic ops: `filter`, `splitAt`, `zip`
	1. cf. Java's "Min API Max Impact" design philosophy, vs Scala's "max API max Impact" approach
	1. print longest words:

	 		words.groupBy( _.length ).maxBy( _._1 )._2.foreach( println(_) )

	1. Parallel processing
		* subset the words - it's going to take a while otheriwse.

				val subset = words.filter( _ => scala.util.Random.nextDouble <= 0.10 )

		* `subset.map( w => (w, subset.filter( _.startsWith(w)) ) )`
		* parallel version: we add `.par` to allow for parallel processing. This is possible as the above process does not mutate shared state.

				subset.par.map( w => (w, subset.filter( _.startsWith(w)) ) )

		* also mention lazy `view` - returns a collection that calculates the values when requested.

	1. find word merges ("what shall we stream?")

			def unmerge( w:String ) = (1 until w.length).map( w.splitAt(_) ).
									   filter( t => (words contains t._1) && (words contains t._2) )
		words: helloworld butterfly fortoff

	1. List Actions (slide): fold, reduce, aggregate
		* Short list into of `1 :: Nil`, `"a" :: 2 :: Nil` etc. Note that ops that end with `:` are right-associative.
		So ` 1 +: list` is invoking method `+:` of `list`, with parameter `1`, as in `list.+:(1)`.
		* File: `fold.scala`
		* sample: break string into char-non-char

				import java.lang.Character._

				def break( w:String ) = w.foldLeft[List[String]](Nil)(
				    (l,c) => {
				        if ( l.isEmpty ) c.toString :: Nil
				        else if ( isLetter(l.last.last) == isLetter(c) )
				                l.dropRight(1) :+ l.last + c
				            else
				                l :+ c.toString
				        })

1. Expressions as refined story tellers
	* Code as prose (_not_ poetry!)
	* Different ways to tell the story.

1. Concise class syntax
	1. File: `classes.scala`.
	1. Differences, mutability, functions with no () for getters. Convention no () => no mutation
	1. The blurring of method invocation vs field access
	1. On 2nd slide: `name_=` has no “`=`” => `void` return type (Unit)

1. Traits and multiple inheritance
	* File: `multiple-inheritance.scala` (must)
	* Mixing and type creation on the *declaration point*
	* order matters

1. No `static`, use Objects and companion objects
	* File: `objects.scala`

1. Pattern matching
	* better way to tell the story
	* Almost Declarative

1. Option[T] - as patten matching, as a collection of one

	* REPL:

			var map = Map('a->1, 'b->2, 'c->3)

	* Show `map('a)` `map('c)` and `get()` versions.
	* Also, show `isEmpty` and `isDefined`, and `foreach`
	* Finally, `n.getOrElse(v)`

1. Function composition, Currying
	* File: `functional.scala`
	* Multi-parameter functions are syntactic sugar(!!!!)
	* example from Play!2 fwk
	* REPL:

			val mult3 = encache( logWhen( _*3, "Multiplying") )
			mult3(4)
			mult3(4)

1. _(Slides to end)_