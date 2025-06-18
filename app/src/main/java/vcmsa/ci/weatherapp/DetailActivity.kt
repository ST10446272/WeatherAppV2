package vcmsa.ci.weatherapp

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.system.exitProcess

class DetailActivity : AppCompatActivity() {

    private lateinit var days: TextView
    private lateinit var average: TextView
    private lateinit var display: ListView
    private lateinit var back: Button
    private lateinit var exit: Button
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)

       /*
        ☺
        */
        days = findViewById(R.id.tvDays)
        average = findViewById(R.id.tvAverage)
        display = findViewById(R.id.lvContent)

        days.text = "Days: ${dataStorage.getEntryCount()}"
        average.text = "Average: ${dataStorage.calculateAverage()}"

        //use ArrayAdapter to link inventory data to list view
        val entiresAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, dataStorage.getRecordedEntries())
        display.adapter = entiresAdapter


        
        back = findViewById(R.id.btnBack)
        back.setOnClickListener {
            val intent = Intent(this,InputActivity::class.java)
            startActivity(intent)
        }
        exit = findViewById(R.id.btnExit)
        exit.setOnClickListener {
            finishAffinity()
            exitProcess(0)
        }


    }
}