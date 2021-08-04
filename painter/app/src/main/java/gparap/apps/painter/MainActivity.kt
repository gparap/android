package gparap.apps.painter

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import gparap.apps.painter.canvas.CanvasView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //create canvas area
        val canvasLayout: ConstraintLayout = findViewById(R.id.main_canvas_area)
        val canvasView = CanvasView(this, null)
        canvasLayout.addView(canvasView)

        //provide clearing canvas area functionality
        val imageButtonClear = findViewById<ImageView>(R.id.imageViewClearCanvas)
        imageButtonClear.setOnClickListener {
            canvasView.clearCanvas()
        }

        //provide erasing functionality
        val imageButtonErase = findViewById<ImageView>(R.id.imageViewEraser)
        imageButtonErase.setOnClickListener {
            canvasView.erase()
        }
    }
}