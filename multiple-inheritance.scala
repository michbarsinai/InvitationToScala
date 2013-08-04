class Animal( sound:String ) {
    def makeNoise = sound
}

trait FancyAnimal extends Animal {
    override def makeNoise = "** %s **".format( super.makeNoise )
}

trait SecretAnimal extends Animal {
    private var offset = 1
    override def makeNoise = super.makeNoise
                                  .map( _ + offset )
                                  .map( _.toChar).mkString
}

val dog = new Animal("woof")
val shhDog = new Animal("woof") with SecretAnimal
val topDog = new Animal("woof") with FancyAnimal
val shhTopDog = new Animal("woof") with SecretAnimal with FancyAnimal
val topShhDog = new Animal("woof") with FancyAnimal with SecretAnimal