////////////////////////////////////////////////////////////////////////////////
// Class: CS558 Programming Languages, Fall 2022
// Assignment: Exercise 1
// Name: Camilo Schaser-Hughes
// Date: October 4, 2022
// Program which creates two array and then quick sorts either recursively or
// iteratively.
////////////////////////////////////////////////////////////////////////////////

// got this from: https://www.geeksforgeeks.org/stack-in-scala/
import scala.collection.mutable.Stack

////////////////////////////////////////////////////////////////////////////////
// Main Object Declaration
////////////////////////////////////////////////////////////////////////////////

object Qsort {
    // thest two are just copied over
    val MINSIZE = 10 // threshold for switch to bub sort
    var a:Array[Int] = _ // the array, just empty

    def createArray(N:Int) = {
        val r = scala.util.Random

        a = Array[Int]()
        for (i <- (0 to (N-1))) {
            a = a :+ r.nextInt(N)
        }

    }

////////////////////////////////////////////////////////////////////////////////
// This strings together all the elements of the array, and then prints
// the msg along with the array in String form.
////////////////////////////////////////////////////////////////////////////////

    def printArray(msg:String) = {
        var fullmsg = msg
        
        for (num <- a) {
            fullmsg += (" " + num + ",")
        }

        println(fullmsg)
    }

////////////////////////////////////////////////////////////////////////////////
// This bubble sorts a segment of an array.
////////////////////////////////////////////////////////////////////////////////

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
// These are two helper functions, partition and swap, works within class.
////////////////////////////////////////////////////////////////////////////////

    def partition(low:Int, high:Int):Int = {
        val pivot = a(high)
        var i = low-1 // so -1 for full array

        for (j <- Range(low, high)) {
            // if an element is less than the pivot
            // it gets put to the left. if i!=j
            // then it switches with a smaller one.
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
            if (high - low < MINSIZE) {
                bubbleSort(low, high)
            } else {
                val pivIndex = partition(low, high)
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

        // starts with the low / high on the stack.
        s.push(low)
        s.push(high)

        // while we're not done
        while (! s.isEmpty) {
            // we pop the current span of the array
            val h = s.pop
            val l = s.pop

            // we partition it
            val p = partition(l, h)

            // if not the "base case"
            if ((p-1) > l) {
                // push the new span
                s.push(l)
                s.push(p-1)
            }

            if ((p+1) < h) {
                s.push(p+1)
                s.push(h)
            }
        }
    }

////////////////////////////////////////////////////////////////////////////////
// This is the verify function, it just iterates through the array and checks
// to make sure the element afterwards is not smaller.
////////////////////////////////////////////////////////////////////////////////

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

////////////////////////////////////////////////////////////////////////////////
// This is the main function, it checks / asserts the argument.
// It then creates a random array of that size, prints it, quick sorts it.
// Then creates and prints another array to non-recursively quick sort it and
// print it again.
////////////////////////////////////////////////////////////////////////////////

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