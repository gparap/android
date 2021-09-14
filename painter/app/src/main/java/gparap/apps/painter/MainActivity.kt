package gparap.apps.painter

import android.os.Bundle
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
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
            canvasView.setEraseMode()
        }

        //provide changing the pen size functionality
        val textViewPenSize = findViewById<TextView>(R.id.textViewPenSize)
        val seekBarPenSize = findViewById<SeekBar>(R.id.seekBarPenSize)
        seekBarPenSize.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                textViewPenSize.text = p1.toString()
                canvasView.strokeWidth = p1
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })

        //restore paint mode with previous color
        val imageViewPenSize = findViewById<ImageView>(R.id.imageViewPenSize)
        imageViewPenSize.setOnClickListener{
            canvasView.setPaintMode()
        }
    }
}