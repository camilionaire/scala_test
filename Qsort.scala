
object Qsort {
    // thest two are just copied over
    val MINSIZE = 10 // threshold for switch to bub sort
    var a:Array[Int] = _ // the array, I guess just empty?

    def createArray(N:Int) = {
        val r = scala.util.Random

        a = Array[Int]()
        for (i <- (0 to (N-1))) {
            a = a :+ r.nextInt(N)
        }

    }

    def printArray(msg:String) = {
        var fullmsg = msg
        
        for (num <- a) {
            fullmsg += (" " + num + ",")
        }

        println(fullmsg)
    }

    def bubbleSort(low:Int, high:Int) = {
        println("This is the bubblesort.")
        println("It goes from " + low + " to " + high)
    }

    def quickSort(low:Int, high:Int) = {
        println("This is the quicksort.It goes from " + low + " to " + high)
    }

    def quickSortNR(low:Int, high:Int) = {
        println("This is the quicksortNR.It goes from " + low + " to " + high)
    }

    def verifyArray = {
        println("Here we are verifying the array!!!")
    }


    def main(argv: Array[String]) = {
        assert(argv.length > 0)
        val N = argv(0).toInt
        assert(N > 0)

        createArray(N)
        printArray("Initial array:")
        quickSort(0, N-1)
        printArray("After quickSort:")
        verifyArray

        createArray(N)
        printArray("Initial array:")
        quickSortNR(0, N-1)
        printArray("After quickSortNR:")
        verifyArray
    }
}