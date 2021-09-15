package gparap.apps.painter

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import gparap.apps.painter.canvas.CanvasView

class MainActivity : AppCompatActivity() {
    lateinit var canvasView: CanvasView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //create canvas area
        canvasView = CanvasView(this, null)
        val canvasLayout: ConstraintLayout = findViewById(R.id.main_canvas_area)
        canvasLayout.addView(canvasView)

        //get the color value from intent
        if (this.intent != null) {
            canvasView.currentColor = this.intent.getIntExtra("color_value", Color.BLACK)
        }

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
        imageViewPenSize.setOnClickListener {
            canvasView.setPaintMode()
        }

        //open color picker dialog
        val imageViewColorPicker = findViewById<ImageView>(R.id.imageViewColorPicker)
        imageViewColorPicker.setOnClickListener {
            var colorRed = 0F
            var colorGreen = 0F
            var colorBlue = 0F

            //create a dialog
            val dialog = Dialog(this)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.activity_color_picker)

            //get the view that displays the color
            val view = dialog.findViewById<View>(R.id.viewColorPicked)

            //handle red color value from changes to the SeekBar's progress level
            val seekBarRed = dialog.findViewById<SeekBar>(R.id.seekBarRedValue)
            seekBarRed.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                    colorRed = p1 * 2.55F
                    view.setBackgroundColor(
                        Color.rgb(colorRed.toInt(), colorGreen.toInt(), colorBlue.toInt())
                    )
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {
                }

                override fun onStopTrackingTouch(p0: SeekBar?) {
                }
            })

            //handle green color value from changes to the SeekBar's progress level
            val seekBarGreen = dialog.findViewById<SeekBar>(R.id.seekBarGreenValue)
            seekBarGreen.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                    colorGreen = p1 * 2.55F
                    view.setBackgroundColor(
                        Color.rgb(colorRed.toInt(), colorGreen.toInt(), colorBlue.toInt())
                    )
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {
                }

                override fun onStopTrackingTouch(p0: SeekBar?) {
                }
            })

            //handle blue color value from changes to the SeekBar's progress level
            val seekBarBlue = dialog.findViewById<SeekBar>(R.id.seekBarBlueValue)
            seekBarBlue.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                    colorBlue = p1 * 2.55F
                    view.setBackgroundColor(
                        Color.rgb(colorRed.toInt(), colorGreen.toInt(), colorBlue.toInt())
                    )
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {
                }

                override fun onStopTrackingTouch(p0: SeekBar?) {
                }
            })

            //dismiss dialog and return color value
            val buttonPickColor = dialog.findViewById<Button>(R.id.buttonPickColor)
            buttonPickColor.setOnClickListener {
                canvasView.currentColor = (view.background as ColorDrawable).color
                dialog.dismiss()
            }
            dialog.show()
        }
    }
}