/*
 * Copyright 2021 gparap
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package gparap.apps.painter

import android.content.pm.ActivityInfo
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import gparap.apps.painter.canvas.CanvasView
import gparap.apps.painter.color_picker.ColorPickerDialog
import gparap.apps.painter.color_picker.ColorPickerListener
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), ColorPickerListener {
    private lateinit var canvasView: CanvasView
    private lateinit var dialog: ColorPickerDialog

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //lock screen orientation to the one existing at app launch time
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LOCKED

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
            dialog = ColorPickerDialog(this)
            dialog.setColorPickerListener(this)
            dialog.show()

        }

        //save painting
        val imageViewSave = findViewById<ImageView>(R.id.imageViewSave)
        imageViewSave.setOnClickListener {
            //generate a formatted datetime string to append as a suffix to painting's filename
            val simpleDateFormat = SimpleDateFormat("yyMMddHHmmss")
            val suffix: String = simpleDateFormat.format(Date())

            //create a new file on external storage
            val directoryPath: String = this.getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!.absolutePath
            val filePath: String = "$directoryPath/painting$suffix.png"
            val file = File(filePath)

            //get the contents of the painting as a byte array
            val bitmap = canvasView.bitmap
            val byteArrayOutputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream)
            val byteArray = byteArrayOutputStream.toByteArray()

            //write byte array data to a file
            val fileOutputStream = FileOutputStream(file)
            fileOutputStream.write(byteArray)
            fileOutputStream.flush()
            fileOutputStream.close()
        }
    }

    //pick a color as the current color
    override fun onPickColorButtonClick() {
        canvasView.currentColor = dialog.getPickedColor()
    }
}