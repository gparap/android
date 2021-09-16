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
package gparap.apps.painter.color_picker

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.SeekBar
import gparap.apps.painter.R

class ColorPickerDialog(context: Context) : Dialog(context) {
    private var colorRed = 0
    private var colorGreen = 0
    private var colorBlue = 0
    private var view: View
    private lateinit var colorPickerListener: ColorPickerListener

    init {
        //configure dialog
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setCancelable(false)
        setContentView(R.layout.dialog_color_picker)

        //initialize the view that previews the color
        view = findViewById(R.id.viewColorPicked)
        view.setBackgroundColor(Color.BLACK)

        //handle the red color value from changes to the SeekBar's progress level
        val seekBarRed = findViewById<SeekBar>(R.id.seekBarRedValue)
        seekBarRed.max = 255
        seekBarRed.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                colorRed = p1
                view.setBackgroundColor(Color.rgb(colorRed, colorGreen, colorBlue))
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })

        //handle the green color value from changes to the SeekBar's progress level
        val seekBarGreen = findViewById<SeekBar>(R.id.seekBarGreenValue)
        seekBarGreen.max = 255
        seekBarGreen.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                colorGreen = p1
                view.setBackgroundColor(Color.rgb(colorRed, colorGreen, colorBlue))
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })

        //handle the blue color value from changes to the SeekBar's progress level
        val seekBarBlue = findViewById<SeekBar>(R.id.seekBarBlueValue)
        seekBarBlue.max = 255
        seekBarBlue.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                colorBlue = p1
                view.setBackgroundColor(Color.rgb(colorRed, colorGreen, colorBlue))
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })

        //handle pick color button and dismiss dialog
        val buttonPickColor = findViewById<Button>(R.id.buttonPickColor)
        buttonPickColor.setOnClickListener {
            colorPickerListener.onPickColorButtonClick()
            dismiss()
        }
    }

    fun setColorPickerListener(listener: ColorPickerListener) {
        colorPickerListener = listener
    }

    /** Returns the color picked */
    fun getPickedColor(): Int {
        return (view.background as ColorDrawable).color
    }
}