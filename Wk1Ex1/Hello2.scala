////////////////////////////////////////////////////////////////////////////////
// Class: CS558 Programming Languages, Fall 2022
// Assignment: Exercise 1
// Name: Camilo Schaser-Hughes
// Date: October 4, 2022
// Program which takes in however many command line arguments and then prints
// out a personalized "Hello" message with the users name / cmd line arguments.
////////////////////////////////////////////////////////////////////////////////

object Hello2 {

    def main(args: Array[String]) = {

        var greeting:String = "Hello,"

        for (name <- args)
            greeting += (" " + name)
        greeting += "!"

        println(greeting)
    }
}