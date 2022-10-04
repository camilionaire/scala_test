// got this from: https://www.geeksforgeeks.org/stack-in-scala/
import scala.collection.mutable.Stack

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
        // could have also used low to (high - 1) or low until high
        for ( i <- Range(low, high)) {
            for ( j <- Range(low, (high + low - i))) {
                if (a(j) > a(j+1)) {
                    val t = a(j)
                    a(j) = a(j+1)
                    a(j+1) = t
                }
            }
        }
    }
    
////////////////////////////////////////////////////////////////////////////////
// These are two helper functions, partition and swap, works within class
////////////////////////////////////////////////////////////////////////////////

    def partition(low:Int, high:Int):Int = {
        val pivot = a(high)
        var i = low-1 // so -1?...

        for (j <- Range(low, high)) {
            if (a(j) <= pivot) {
                i += 1
                swapElements(i, j)
            }
        }
        swapElements(high, i + 1)
        return i + 1
    }

    def swapElements(b:Int, c:Int) = {
        val temp = a(b)
        a(b) = a(c)
        a(c) = temp
    }

////////////////////////////////////////////////////////////////////////////////
// This is the quicksort algorithm.  Got some help on it from:
// https://www.programiz.com/dsa/quick-sort
////////////////////////////////////////////////////////////////////////////////

    def quickSort(low:Int, high:Int):Unit = {
        if (low < high) {
            if (high - low <= MINSIZE) {
                bubbleSort(low, high)
            } else {
                val pivIndex = partition(low, high)
                // printArray("Current Arr:") // this stuff is for troubleshooting
                // println("Pivot pt: " + pivIndex)
                quickSort(low, pivIndex - 1)
                quickSort(pivIndex, high)
            }
        }
    }

////////////////////////////////////////////////////////////////////////////////
// This is the non-recursive quicksort algorithm.  Got some help on it from:
// https://www.geeksforgeeks.org/iterative-quick-sort/
////////////////////////////////////////////////////////////////////////////////
    def quickSortNR(low:Int, high:Int) = {
        var s = Stack[Int]()

        s.push(low)
        s.push(high)

        while (! s.isEmpty) {
            val h = s.pop
            val l = s.pop

            val p = partition(l, h)

            if ((p-1) > l) {
                s.push(l)
                s.push(p-1)
            }

            if ((p+1) < h) {
                s.push(p+1)
                s.push(h)
            }
        }



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