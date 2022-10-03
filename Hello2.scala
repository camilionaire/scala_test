
object Hello2 {

    def main(args: Array[String]) = {

        var greeting:String = "Hello,"

        for (name <- args)
            greeting += (" " + name)
        greeting += "!"

        println(greeting)
    }
}