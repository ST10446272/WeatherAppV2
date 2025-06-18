package vcmsa.ci.weatherapp

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import vcmsa.ci.weatherapp.dataStorage.clearAllEntries
import java.util.Calendar
import kotlin.system.exitProcess

class InputActivity : AppCompatActivity() {

    private lateinit var wDate: EditText
    private lateinit var wMinTemp: EditText
    private lateinit var wMaxTemp: EditText
    private lateinit var wWeatherConditions: EditText

    private lateinit var addButton: Button
    private lateinit var clearButton: Button
    private lateinit var view: Button
    private lateinit var exit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_input)

        wDate = findViewById(R.id.etDate)
        wDate.setOnClickListener{
            // the instance of our calendar.
            val c = Calendar.getInstance()

            // our day, month and year.
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            // variable for date picker dialog.
            val datePickerDialog = DatePickerDialog(
                this,
                { view, year, monthOfYear, dayOfMonth ->
                    // date to our edit text.
                    val dat = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                    wDate.setText(dat)
                },
                // are passing year, month and day for the selected date in our date picker.
                year,
                month,
                day
            )
            // call to display our date picker dialog.
            datePickerDialog.show()

        }
        wMinTemp = findViewById(R.id.etMinimum)
        wMaxTemp = findViewById(R.id.etMaximum)
        wWeatherConditions = findViewById(R.id.etConditions)

        addButton = findViewById(R.id.btnAdd)
        addButton.setOnClickListener {
            addEntry(
                //date = TODO(),
               // minTemp = TODO(),
                //maxTemp = TODO(),
                //weatherConditions = TODO()
            )
        }
        clearButton = findViewById(R.id.btnClear)
        clearButton.setOnClickListener {
            clearAllEntries()
        }

        view = findViewById(R.id.btnView)
        view.setOnClickListener {
            val intent = Intent(this,DetailActivity::class.java)
            startActivity(intent)
        }
        exit = findViewById(R.id.btnExit)
        exit.setOnClickListener {
            finishAffinity()
            exitProcess(0)
        }

    }
    private fun addEntry(){
        val date = wDate.text.toString()
        val minTemp = wMinTemp.text.toString()
        val maxTemp = wMaxTemp.text.toString()
        val weatherConditions = wWeatherConditions.text.toString()

        //error handling
        if (date.isEmpty() || minTemp.isEmpty() || maxTemp.isEmpty() || weatherConditions.isEmpty()){
            dataStorage.showToast(this, "Please fill in all the Fields.")
            return
        }
        //check number of entries
        if (dataStorage.getEntryCount() >= 7){
            dataStorage.showToast(this, "Maximum Number of Entries is 7. Please view or clear entries")
            return
        }
        //add entries using custom class
        val added = dataStorage.addEntry(date, minTemp, maxTemp, weatherConditions)
        if (added){
            dataStorage.showToast(this, "Entry added Successfully!")
            wDate.setText("")
            wMinTemp.setText("")
            wMaxTemp.setText("")
            wWeatherConditions.setText("")
        } else {
            dataStorage.showToast(this, "Could not add entry")
        }
    }
    private fun clearAllEntries(){
        dataStorage.clearAllEntries()
        wDate.text.clear()
        wMinTemp.text.clear()
        wMaxTemp.text.clear()
        wWeatherConditions.text.clear()
        dataStorage.showToast(this, "All inputs have been cleared")
    }
}