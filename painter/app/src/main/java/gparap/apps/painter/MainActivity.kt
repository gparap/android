package gparap.apps.painter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createCanvasArea()
    }

    private fun createCanvasArea() {
        val canvasLayout: ConstraintLayout = findViewById(R.id.main_canvas_area)
        val canvasView = CanvasView(this, null)
        canvasLayout.addView(canvasView)
    }
}