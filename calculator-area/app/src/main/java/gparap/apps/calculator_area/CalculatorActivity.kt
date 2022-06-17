/*
 * Copyright (c) 2022 gparap
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
package gparap.apps.calculator_area

import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.isVisible
import kotlin.properties.Delegates

class CalculatorActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var spinner2d: Spinner
    private lateinit var editTextSideA: EditText
    private lateinit var editTextSideB: EditText
    private lateinit var editTextHeight: EditText
    private lateinit var editTextRadius: EditText
    private lateinit var editTextDiagonal1: EditText
    private lateinit var editTextDiagonal2: EditText
    private lateinit var result: TextView
    private lateinit var buttonCalculate: Button
    private lateinit var imageViewShape2d: ImageView
    private var visibleFields: ArrayList<EditText>? = ArrayList()

    //used for helping in clearing input fields (spinner's onItemSelected)
    // when orientation changes occur and need to save/restore state
    companion object {
        var previousItemPosition by Delegates.notNull<Int>()

        init {
            previousItemPosition = 0
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        //get widgets
        editTextSideA = findViewById(R.id.editTextSideA)
        editTextSideB = findViewById(R.id.editTextSideB)
        editTextHeight = findViewById(R.id.editTextHeight)
        editTextRadius = findViewById(R.id.editTextRadius)
        editTextDiagonal1 = findViewById(R.id.editTextDiagonal1)
        editTextDiagonal2 = findViewById(R.id.editTextDiagonal2)
        result = findViewById(R.id.textViewResult)
        buttonCalculate = findViewById(R.id.buttonCalculate)
        imageViewShape2d = findViewById(R.id.imageViewShape2d)

        //calculate area and display the result
        buttonCalculate.setOnClickListener {
            result.text = getString(R.string.string_area)
            if (validateInputFields()) {
                calculateArea()

                //construct the result string
                result.text = getString(R.string.string_area)
                    .plus(" = ")
                    .plus(
                        //if it is a scientific number, display it as it is
                        if (result.text.toString().contains("E")) {
                            result.text.toString()

                            //if it is a non-scientific number, beautify it
                        } else {
                            Utils.beautifyResult(result.text.toString())
                        })
            }
        }

        //populate spinner with 2D shapes
        spinner2d = findViewById(R.id.spinnerShapes2D)
        val adapter2d: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
            this, R.array.shapes2D, R.layout.simple_spinner_item
        )
        adapter2d.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        spinner2d.adapter = adapter2d
        spinner2d.onItemSelectedListener = this

        //restore values after orientation changes
        if (savedInstanceState != null)
            restoreInstanceState(savedInstanceState)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        parent?.getItemAtPosition(position)

        //de-activate input fields based on spinner selection
        handleFieldsVisibility(spinner2d.selectedItem.toString())

        //clear input and output fields
        if (previousItemPosition != position) {
            clear()
        }
        previousItemPosition = position

        //show 2d geometric shape drawable based on spinner selection
        imageViewShape2d.setImageDrawable(
            Utils.getImageDrawable(spinner2d.selectedItem.toString(), resources)
        )
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        parent?.getItemAtPosition(0)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        saveInstanceState(outState)
    }

    private fun handleFieldsVisibility(selectedItem: String) {
        //clear active fields list before activation
        visibleFields?.clear()

        //change visibility of fields and add to list
        when (selectedItem) {
            getString(R.string.shape_square) -> {
                editTextSideA.isVisible = true.also { visibleFields?.add(editTextSideA) }
                editTextSideB.isVisible = false
                editTextHeight.isVisible = false
                editTextRadius.isVisible = false
                editTextDiagonal1.isVisible = false
                editTextDiagonal2.isVisible = false
                handleConstrainSet(R.id.editTextSideA)

                //handle constrain set on portrait orientation
//                if (this.resources.configuration.orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
//                    if (this.resources.configuration.orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
//                        val constraintLayout = findViewById<ConstraintLayout>(R.id.layout_main)
//                        val constraintSet = ConstraintSet()
//                        constraintSet.clone(constraintLayout)
//                        constraintSet.connect(R.id.textViewResult,
//                            ConstraintSet.TOP,
//                            R.id.editTextSideA,
//                            ConstraintSet.BOTTOM)
//                        constraintSet.applyTo(constraintLayout)
//                    }
//                }
            }
            getString(R.string.shape_rectangle) -> {
                editTextSideA.isVisible = true.also { visibleFields?.add(editTextSideA) }
                editTextSideB.isVisible = true.also { visibleFields?.add(editTextSideB) }
                editTextHeight.isVisible = false
                editTextRadius.isVisible = false
                editTextDiagonal1.isVisible = false
                editTextDiagonal2.isVisible = false
                handleConstrainSet(R.id.editTextSideB)

                //handle constrain set on portrait orientation
//                if (this.resources.configuration.orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
//                    if (this.resources.configuration.orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
//                        val constraintLayout = findViewById<ConstraintLayout>(R.id.layout_main)
//                        val constraintSet = ConstraintSet()
//                        constraintSet.clone(constraintLayout)
//
////                        constraintSet.connect(R.id.editTextSideA,
////                            ConstraintSet.TOP,
////                            R.id.spinnerShapes2D,
////                            ConstraintSet.BOTTOM)
//
//                        constraintSet.connect(R.id.textViewResult,
//                            ConstraintSet.TOP,
//                            R.id.editTextSideB,
//                            ConstraintSet.BOTTOM)
//                        constraintSet.applyTo(constraintLayout)
//                    }
//                }
            }
            getString(R.string.shape_parallelogram) -> {
                editTextSideA.isVisible = true.also { visibleFields?.add(editTextSideA) }
                editTextSideB.isVisible = false
                editTextHeight.isVisible = true.also { visibleFields?.add(editTextHeight) }
                editTextRadius.isVisible = false
                editTextDiagonal1.isVisible = false
                editTextDiagonal2.isVisible = false
                handleConstrainSet(R.id.editTextHeight)

                //handle constrain set on portrait orientation
//                if (this.resources.configuration.orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
//                    if (this.resources.configuration.orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
//                        val constraintLayout = findViewById<ConstraintLayout>(R.id.layout_main)
//                        val constraintSet = ConstraintSet()
//                        constraintSet.clone(constraintLayout)
//                        constraintSet.connect(R.id.textViewResult,
//                            ConstraintSet.TOP,
//                            R.id.editTextHeight,
//                            ConstraintSet.BOTTOM)
//                        constraintSet.applyTo(constraintLayout)
//                    }
//                }
            }
            getString(R.string.shape_rhombus) -> {
                editTextSideA.isVisible = false
                editTextSideB.isVisible = false
                editTextHeight.isVisible = false
                editTextRadius.isVisible = false
                editTextDiagonal1.isVisible = true.also { visibleFields?.add(editTextDiagonal1) }
                editTextDiagonal2.isVisible = true.also { visibleFields?.add(editTextDiagonal2) }
                handleConstrainSet(R.id.editTextDiagonal2)


//                //change constrain set on portrait orientation
//                // due to the different visibility of the diagonal fields
//                if (this.resources.configuration.orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
//                    val constraintLayout = findViewById<ConstraintLayout>(R.id.layout_main)
//                    val constraintSet = ConstraintSet()
//                    constraintSet.clone(constraintLayout)
//                    constraintSet.connect(R.id.textViewResult,
//                        ConstraintSet.TOP,
//                        R.id.editTextDiagonal2,
//                        ConstraintSet.BOTTOM,
//                        0)
//                    constraintSet.applyTo(constraintLayout)
//                }
            }
            getString(R.string.shape_equilateral_triangle) -> {
                editTextSideA.isVisible = true.also { visibleFields?.add(editTextSideA) }
                editTextSideB.isVisible = false
                editTextHeight.isVisible = false
                editTextRadius.isVisible = false
                editTextDiagonal1.isVisible = false
                editTextDiagonal2.isVisible = false
                handleConstrainSet(R.id.editTextSideA)


//                //handle constrain set on portrait orientation
//                if (this.resources.configuration.orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
//                    if (this.resources.configuration.orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
//                        val constraintLayout = findViewById<ConstraintLayout>(R.id.layout_main)
//                        val constraintSet = ConstraintSet()
//                        constraintSet.clone(constraintLayout)
//                        constraintSet.connect(R.id.textViewResult,
//                            ConstraintSet.TOP,
//                            R.id.editTextSideA,
//                            ConstraintSet.BOTTOM)
//                        constraintSet.applyTo(constraintLayout)
//                    }
//                }
            }
            getString(R.string.shape_triangle) -> {
                editTextSideA.isVisible = true.also { visibleFields?.add(editTextSideA) }
                editTextSideB.isVisible = false
                editTextHeight.isVisible = true
                editTextRadius.isVisible = false
                editTextDiagonal1.isVisible = false
                editTextDiagonal2.isVisible = false
                handleConstrainSet(R.id.editTextHeight)


//                //handle constrain set on portrait orientation
//                if (this.resources.configuration.orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
//                    if (this.resources.configuration.orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
//                        val constraintLayout = findViewById<ConstraintLayout>(R.id.layout_main)
//                        val constraintSet = ConstraintSet()
//                        constraintSet.clone(constraintLayout)
//                        constraintSet.connect(R.id.textViewResult,
//                            ConstraintSet.TOP,
//                            R.id.editTextHeight,
//                            ConstraintSet.BOTTOM)
//                        constraintSet.applyTo(constraintLayout)
//                    }
//                }
            }
            getString(R.string.shape_trapezoid) -> {
                editTextSideA.isVisible = true.also { visibleFields?.add(editTextSideA) }
                editTextSideB.isVisible = true.also { visibleFields?.add(editTextSideB) }
                editTextHeight.isVisible = true.also { visibleFields?.add(editTextHeight) }
                editTextRadius.isVisible = false
                editTextDiagonal1.isVisible = false
                editTextDiagonal2.isVisible = false
                handleConstrainSet(R.id.editTextHeight)
//
//
//                //handle constrain set on portrait orientation
//                if (this.resources.configuration.orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
//                    if (this.resources.configuration.orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
//                        val constraintLayout = findViewById<ConstraintLayout>(R.id.layout_main)
//                        val constraintSet = ConstraintSet()
//                        constraintSet.clone(constraintLayout)
//                        constraintSet.connect(R.id.textViewResult,
//                            ConstraintSet.TOP,
//                            R.id.editTextHeight,
//                            ConstraintSet.BOTTOM)
//                        constraintSet.applyTo(constraintLayout)
//                    }
//                }
            }
            getString(R.string.shape_hexagon) -> {
                editTextSideA.isVisible = true.also { visibleFields?.add(editTextSideA) }
                editTextSideB.isVisible = false
                editTextHeight.isVisible = false
                editTextRadius.isVisible = false
                editTextDiagonal1.isVisible = false
                editTextDiagonal2.isVisible = false
                handleConstrainSet(R.id.editTextSideA)

//                //handle constrain set on portrait orientation
//                if (this.resources.configuration.orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
//                    if (this.resources.configuration.orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
//                        val constraintLayout = findViewById<ConstraintLayout>(R.id.layout_main)
//                        val constraintSet = ConstraintSet()
//                        constraintSet.clone(constraintLayout)
//                        constraintSet.connect(R.id.textViewResult,
//                            ConstraintSet.TOP,
//                            R.id.editTextSideA,
//                            ConstraintSet.BOTTOM)
//                        constraintSet.applyTo(constraintLayout)
//                    }
//                }
            }
            getString(R.string.shape_circle) -> {
                editTextSideA.isVisible = false
                editTextSideB.isVisible = false
                editTextHeight.isVisible = false
                editTextRadius.isVisible = true.also { visibleFields?.add(editTextRadius) }
                editTextDiagonal1.isVisible = false
                editTextDiagonal2.isVisible = false
                handleConstrainSet(R.id.editTextRadius)


//                //handle constrain set on portrait orientation
//                if (this.resources.configuration.orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
//                    if (this.resources.configuration.orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
//                        val constraintLayout = findViewById<ConstraintLayout>(R.id.layout_main)
//                        val constraintSet = ConstraintSet()
//                        constraintSet.clone(constraintLayout)
//                        constraintSet.connect(R.id.textViewResult,
//                            ConstraintSet.TOP,
//                            R.id.editTextRadius,
//                            ConstraintSet.BOTTOM)
//                        constraintSet.applyTo(constraintLayout)
//                    }
//                }
            }
        }
    }

    private fun validateInputFields(): Boolean {
        //check if active field is empty
        for (field in visibleFields!!) {
            if (field.text.isEmpty()) {
                Toast.makeText(this, R.string.toast_EnterValue, Toast.LENGTH_SHORT).show()
                return false
            }
        }

        //check if Parallelogram's side is equal with its height
        if (spinner2d.selectedItem.toString() == getString(R.string.shape_parallelogram)) {
            if (editTextSideA.text.toString() == editTextHeight.text.toString()) {
                Toast.makeText(this, R.string.toast_EqualValues_Parallelogram, Toast.LENGTH_SHORT)
                    .show()
                return false
            }
        }

        //check if Trapezoid's sides are equal
        else if (spinner2d.selectedItem.toString() == getString(R.string.shape_trapezoid)) {
            if (editTextSideA.text.toString() == editTextSideB.text.toString()) {
                Toast.makeText(this, R.string.toast_EqualValues_Trapezoid, Toast.LENGTH_SHORT)
                    .show()
                return false
            }
        }

        return true
    }

    private fun clear() {
        editTextSideA.text.clear()
        editTextSideB.text.clear()
        editTextHeight.text.clear()
        editTextRadius.text.clear()
        editTextDiagonal1.text.clear()
        editTextDiagonal2.text.clear()
        result.text = getString(R.string.string_area)
    }

    private fun calculateArea() {
        when (spinner2d.selectedItem.toString()) {
            getString(R.string.shape_square) -> result.text = CalculatorOperations.calculateSquare(
                editTextSideA.text.toString().toDouble()
            )

            getString(R.string.shape_rectangle) -> result.text =
                CalculatorOperations.calculateRectangle(
                    editTextSideA.text.toString().toDouble(),
                    editTextSideB.text.toString().toDouble()
                )

            getString(R.string.shape_parallelogram) -> result.text =
                CalculatorOperations.calculateParallelogram(
                    editTextSideA.text.toString().toDouble(),
                    editTextHeight.text.toString().toDouble()
                )

            getString(R.string.shape_rhombus) -> result.text =
                CalculatorOperations.calculateRhombus(
                    editTextDiagonal1.text.toString().toDouble(),
                    editTextDiagonal2.text.toString().toDouble()
                )

            getString(R.string.shape_equilateral_triangle) -> result.text =
                CalculatorOperations.calculateEquilateralTriangle(
                    editTextSideA.text.toString().toDouble()
                )

            getString(R.string.shape_triangle) -> result.text =
                CalculatorOperations.calculateTriangle(
                    editTextSideA.text.toString().toDouble(),
                    editTextHeight.text.toString().toDouble()
                )

            getString(R.string.shape_trapezoid) -> result.text =
                CalculatorOperations.calculateTrapezoid(
                    editTextSideA.text.toString().toDouble(),
                    editTextSideB.text.toString().toDouble(),
                    editTextHeight.text.toString().toDouble()
                )

            getString(R.string.shape_hexagon) -> result.text =
                CalculatorOperations.calculateHexagon(editTextSideA.text.toString().toDouble())

            getString(R.string.shape_circle) -> result.text =
                CalculatorOperations.calculateCircle(editTextRadius.text.toString().toDouble())
        }
    }

    /*
     * Persists values on orientation changes
     */
    private fun saveInstanceState(outState: Bundle) {
        outState.putString("result", result.text.toString())
        outState.putString("side_a", editTextSideA.text.toString())
        outState.putString("side_b", editTextSideB.text.toString())
        outState.putString("height", editTextHeight.text.toString())
        outState.putString("radius", editTextRadius.text.toString())
        outState.putString("diagonal_1", editTextDiagonal1.text.toString())
        outState.putString("diagonal_2", editTextDiagonal2.text.toString())
    }

    /*
     * Restores values after orientation changes
     */
    private fun restoreInstanceState(savedInstanceState: Bundle?) {
        editTextSideA.setText(savedInstanceState?.get("side_a").toString())
        editTextSideB.setText(savedInstanceState?.get("side_b").toString())
        editTextHeight.setText(savedInstanceState?.get("height").toString())
        editTextRadius.setText(savedInstanceState?.get("radius").toString())
        editTextDiagonal1.setText(savedInstanceState?.get("diagonal_1").toString())
        editTextDiagonal2.setText(savedInstanceState?.get("diagonal_2").toString())
        result.text = savedInstanceState?.get("result").toString()
    }

    /**
     * Handles the constrain set of the main layout on portrait orientation
     *  due to the difference in visibility of the various input fields.
     *
     * param: The id of the input field that the result field must be constrained to the bottom of.
     */
    private fun handleConstrainSet(inputFieldId: Int) {
        if (this.resources.configuration.orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            //get the constrain set from the layout
            val constraintLayout = findViewById<ConstraintLayout>(R.id.layout_main)
            val constraintSet = ConstraintSet()
            constraintSet.clone(constraintLayout)

            //constrain the result field to the bottom of the input field
            constraintSet.connect(R.id.textViewResult,
                ConstraintSet.TOP,
                inputFieldId,
                ConstraintSet.BOTTOM)

            //apply the constrain set to the layout
            constraintSet.applyTo(constraintLayout)
        }
    }
}