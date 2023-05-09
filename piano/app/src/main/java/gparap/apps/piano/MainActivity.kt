package gparap.apps.piano

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.KeyEvent.ACTION_DOWN
import android.view.KeyEvent.ACTION_UP
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    @SuppressLint("ClickableViewAccessibility")
    fun onWhiteButtonClick(view: View) {
        view.setOnTouchListener { v, event ->
            //handle the button color
            when (event?.action) {
                ACTION_DOWN -> v?.setBackgroundColor(Color.GRAY)
                ACTION_UP -> v?.setBackgroundColor(Color.WHITE)
                else -> {
                    //for the gray color no to stuck
                    v?.setBackgroundColor(Color.WHITE)
                }
            }
            v?.onTouchEvent(event) ?: true
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    fun onBlackButtonClick(view: View) {
        view.setOnTouchListener { v, event ->
            //handle the button color
            when (event?.action) {
                ACTION_DOWN -> v?.setBackgroundColor(Color.GRAY)
                ACTION_UP -> v?.setBackgroundColor(Color.BLACK)
                else -> {
                    //for the gray color no to stuck
                    v?.setBackgroundColor(Color.BLACK)
                }
            }
            v?.onTouchEvent(event) ?: true
        }
    }
}