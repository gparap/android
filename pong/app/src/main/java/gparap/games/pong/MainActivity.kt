package gparap.games.pong

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //start playing pong
        val buttonPlay = findViewById<Button>(R.id.buttonPlay)
        buttonPlay.setOnClickListener {
            startActivity(Intent(this, PongActivity::class.java))
        }
    }
}