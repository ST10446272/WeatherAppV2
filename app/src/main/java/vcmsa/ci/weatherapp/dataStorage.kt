package vcmsa.ci.weatherapp

import android.content.Context
import android.widget.Toast

object dataStorage {
    private val wDate = mutableListOf<String>()
    private val wMinTemp = mutableListOf<String>()
    private val wMaxTemp = mutableListOf<String>()
    private val wWeatherConditions = mutableListOf<String>()

    //Keep track of the input
    private var entryCount = 0
    private const val maxEntries = 7 // user can only input 7 times

    fun addEntry(date:String, minTemp: String, maxTemp: String, weatherConditions: String):Boolean{
     if (entryCount < maxEntries)  {
         wDate.add(date)
         wMinTemp.add(minTemp.toString())
         wMaxTemp.add(maxTemp.toString())
         wWeatherConditions.add(weatherConditions)
         entryCount++
         return true
     }
        return false
    }
    fun clearAllEntries(){
        wDate.clear()
        wMinTemp.clear()
        wMaxTemp.clear()
        wWeatherConditions.clear()
        entryCount = 0
    }
    fun getEntryCount(): Int {
        return entryCount
    }
    fun calculateAverage(): Double {
        val minTemps = wMinTemp.mapNotNull { it.toDoubleOrNull() }
        val maxTemps = wMaxTemp.mapNotNull { it.toDoubleOrNull() }

        // Combine both lists
        val allTemps = minTemps + maxTemps

        // Avoid division by zero
        return if (allTemps.isNotEmpty()) {
            allTemps.average()
        } else {
            0.0
        }
}
    fun getRecordedEntries(): List<String>{
        return wDate.mapIndexed { index, date ->
            "Date: $date \n Minimum Temp: ${wMinTemp[index]} \n Max Temp: ${wMaxTemp[index]} \n Weather Conditions: ${wWeatherConditions[index]}"
        }
    }
    fun showToast(context: Context, message: String){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}