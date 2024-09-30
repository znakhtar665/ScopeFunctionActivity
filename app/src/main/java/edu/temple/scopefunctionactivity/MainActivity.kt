package edu.temple.scopefunctionactivity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // You can test your helper functions by  calling them from onCreate() and
        // printing their output to the Log, which is visible in the LogCat:
        // eg. Log.d("function output", getTestDataArray().toString())
        val testDataArray = getTestDataArray()
        Log.d("function output", "Sorted random list: $testDataArray")

        // Test 2: Check if average is less than median in a sample list
        val sampleList = listOf(1.0, 5.0, 8.0, 7.0, 3.0, 4.0)
        val isAverageLessThanMedian = averageLessThanMedian(sampleList)
        Log.d("function output", "Is average < median: $isAverageLessThanMedian")

        // Test 3: Recycle or create a view for a position in the collection
        val recycledView: View? = null
        val testCollection = listOf(1, 2, 3, 4, 5)
        val testView = getView(2, recycledView, testCollection, this)
        Log.d("function output", "View text at position 2: ${(testView as TextView).text}")
    }

    // Refactored helper functions using Scope Functions

    // Returns a sorted list of random integers
    private fun getTestDataArray() = MutableList(10) { Random.nextInt() }.apply { sort() }

    // Returns true if the average value in the list is less than the median, false otherwise
    private fun averageLessThanMedian(listOfNumbers: List<Double>) = listOfNumbers.run {
        val sortedList = sorted()
        val median = if (size % 2 == 0)
            (sortedList[size / 2] + sortedList[(size - 1) / 2]) / 2
        else
            sortedList[size / 2]
        average() < median
    }

    // Recycles or creates a view for a position in the collection
    private fun getView(position: Int, recycledView: View?, collection: List<Int>, context: Context): View =
        (recycledView as? TextView ?: TextView(context).apply {
            setPadding(5, 10, 10, 0)
            textSize = 22f
        }).apply {
            text = collection[position].toString()
        }

}