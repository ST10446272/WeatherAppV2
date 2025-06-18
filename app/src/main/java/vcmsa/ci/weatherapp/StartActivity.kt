package vcmsa.ci.weatherapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.system.exitProcess

class StartActivity : AppCompatActivity() {

    private lateinit var start: Button
    private lateinit var exit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_start)

        start = findViewById(R.id.btnStart)
        start.setOnClickListener {
            val intent = Intent(this, InputActivity::class.java)
            startActivity(intent)
        }
        exit = findViewById(R.id.btnExit)
        exit.setOnClickListener {
            finishAffinity()
            exitProcess(0)
        }

    }
}