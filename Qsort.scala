
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
        // could have also used low to (high - 1)
        for ( i <- Range(low, high)) {
            for ( j <- Range(low, (high - i))) {
                if (a(j) > a(j+1)) {
                    val t = a(j)
                    a(j) = a(j+1)
                    a(j+1) = t
                }
            }
        }
    }

    def quickSort(low:Int, high:Int) = {
    }

    def partition(low:Int, high:Int):Int = {
        var pi = high
        var index = low

        for (i <- low to high) {
            if (a(i) < a(pi)) {
                swapElements(i, index)
                index += 1
            }
        }
        swapElements(pi, index + 1)
        return index + 1
    }

    def swapElements(b:Int, c:Int) = {
        val temp = a(b)
        a(b) = a(c)
        a(c) = temp
    }

    def quickSortNR(low:Int, high:Int) = {
        println("This is the quicksortNR.It goes from " + low + " to " + high)
    }

    def verifyArray = {
    
        var failed = false
        for (i <- 0 to (a.length - 2)) {
            if (a(i) > a(i+1) && !failed) {
                println("FAILED: a(" +i+ ")=" +a(i)+ ", a(" +(i+1)+ ")=" +a(i+1))
                failed = true
            }
        }
        if (!failed) {
            println("Result verified!")
        }
    }


    def main(argv: Array[String]) = {
        assert(argv.length > 0)
        val N = argv(0).toInt
        assert(N > 0)

        createArray(N)
        printArray("Initial array:")
        // quickSort(0, N-1)
        // printArray("After quickSort:")
        bubbleSort(0, N-1)
        printArray("After BUBBLESORT")
        verifyArray

        createArray(N)
        printArray("Initial array:")
        // quickSortNR(0, N-1)
        // printArray("After quickSortNR:")
        bubbleSort(0, N-1)
        printArray("After BUBBLESORT")
        verifyArray
    }
}