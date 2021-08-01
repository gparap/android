package gparap.apps.painter.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import gparap.apps.painter.R

class CanvasView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.canvas_area)
    }
}