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

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
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
import androidx.core.content.ContextCompat
import gparap.apps.painter.canvas.CanvasView
import gparap.apps.painter.color_picker.ColorPickerDialog
import gparap.apps.painter.color_picker.ColorPickerListener
import gparap.apps.painter.utils.Utils

@Suppress("PrivatePropertyName")
class MainActivity : AppCompatActivity(), ColorPickerListener {
    private lateinit var canvasView: CanvasView
    private lateinit var dialog: ColorPickerDialog
    private val REQUEST_CODE_WRITE_EXTERNAL_STORAGE = 111

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

        //save painting to a .png file on the primary external filesystem
        val imageViewSave = findViewById<ImageView>(R.id.imageViewSave)
        imageViewSave.setOnClickListener {
            //we should always request permissions if we encounter a legacy API
            if (Build.VERSION.SDK_INT < 29) {
                if (!isPermissionGranted()) {
                    requestPermissions(
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        REQUEST_CODE_WRITE_EXTERNAL_STORAGE
                    )
                }
            } else {
                savePainting()
            }
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_CODE_WRITE_EXTERNAL_STORAGE -> {
                //if request is cancelled, the result arrays are empty
                if ((grantResults.isNotEmpty() &&
                            grantResults[0] == PackageManager.PERMISSION_GRANTED)
                ) {
                    //permission to save to device storage is granted
                    savePainting()
                }
                return
            }
        }
    }

    //check if permission to write storage is granted (for API < 29)
    private fun isPermissionGranted(): Boolean {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

    //save painting to device's public directory, "pictures"
    private fun savePainting() {
        Utils.writeDataToFile(
            Utils.createNewFile(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                    .toString(),
                Utils.generateFilenameSuffix("yyMMddHHmmss")
            ), Utils.getByteArray(canvasView.bitmap)
        )
    }

    //pick a color as the current color
    override fun onPickColorButtonClick() {
        canvasView.currentColor = dialog.getPickedColor()
    }
}