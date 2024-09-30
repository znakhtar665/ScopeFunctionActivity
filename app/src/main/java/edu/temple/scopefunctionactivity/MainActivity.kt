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

        val testDataArray = getTestDataArray()
        Log.d("MainActivity", "Sorted random list: $testDataArray")


        val sampleList = listOf(1.0, 5.0, 8.0, 7.0, 3.0, 4.0)
        val isAverageLessThanMedian = averageLessThanMedian(sampleList)
        Log.d("MainActivity", "Is average < median: $isAverageLessThanMedian")


        val recycledView: View? = null
        val testCollection = listOf(1, 2, 3, 4, 5)
        val testView = getView(2, recycledView, testCollection, this)
        Log.d("MainActivity", "View text at position 2: ${(testView as TextView).text}")
    }




    private fun getTestDataArray() = MutableList(10) { Random.nextInt() }.apply { sort() }


    private fun averageLessThanMedian(listOfNumbers: List<Double>) = listOfNumbers.run {
        val sortedList = sorted()
        val median = if (size % 2 == 0)
            (sortedList[size / 2] + sortedList[(size - 1) / 2]) / 2
        else
            sortedList[size / 2]
        average() < median
    }


    private fun getView(position: Int, recycledView: View?, collection: List<Int>, context: Context): View =
        (recycledView as? TextView ?: TextView(context).apply {
            setPadding(5, 10, 10, 0)
            textSize = 22f
        }).apply {
            text = collection[position].toString()
        }

}