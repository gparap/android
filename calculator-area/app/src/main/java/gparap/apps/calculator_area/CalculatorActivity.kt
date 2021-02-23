package gparap.apps.calculator_area

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import kotlin.properties.Delegates

/**
 * Created by gparap on 2021-02-12.
 */
class CalculatorActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    lateinit var spinner2d: Spinner
    lateinit var editTextSideA: EditText
    lateinit var editTextSideB: EditText
    lateinit var editTextHeight: EditText
    lateinit var editTextRadius: EditText
    lateinit var result: TextView
    var visibleFields: ArrayList<EditText>? = ArrayList()

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
        result = findViewById(R.id.textViewResult)

        //populate spinner with 2D shapes
        spinner2d = findViewById(R.id.spinnerShapes2D)
        val adapter2d: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
            this, R.array.shapes2D, android.R.layout.simple_spinner_item
        )
        adapter2d.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner2d.adapter = adapter2d
        spinner2d.onItemSelectedListener = this

        //restore values after orientation changes
        if (savedInstanceState != null)
        restoreInstanceState(savedInstanceState)
    }

    fun onClickCalculateArea(view: View) {
        result.text = getString(R.string.string_area)
        if (validateInputFields()){
            calculateArea()
            beautifyResult()
        }
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
            "Square" -> {
                editTextSideA.isVisible = true.also { visibleFields?.add(editTextSideA) }
                editTextSideB.isVisible = false
                editTextHeight.isVisible = false
                editTextRadius.isVisible = false
            }
            "Rectangle" -> {
                editTextSideA.isVisible = true.also { visibleFields?.add(editTextSideA) }
                editTextSideB.isVisible = true.also { visibleFields?.add(editTextSideB) }
                editTextHeight.isVisible = false
                editTextRadius.isVisible = false
            }
            "Parallelogram" -> {
                editTextSideA.isVisible = true.also { visibleFields?.add(editTextSideA) }
                editTextSideB.isVisible = false
                editTextHeight.isVisible = true.also { visibleFields?.add(editTextHeight) }
                editTextRadius.isVisible = false
            }
            "Equilateral Triangle" -> {
                editTextSideA.isVisible = true.also { visibleFields?.add(editTextSideA) }
                editTextSideB.isVisible = false
                editTextHeight.isVisible = false
                editTextRadius.isVisible = false
            }
            "Triangle" -> {
                editTextSideA.isVisible = true.also { visibleFields?.add(editTextSideA) }
                editTextSideB.isVisible = false
                editTextHeight.isVisible = true
                editTextRadius.isVisible = false
            }
            "Trapezoid" -> {
                editTextSideA.isVisible = true.also { visibleFields?.add(editTextSideA) }
                editTextSideB.isVisible = true.also { visibleFields?.add(editTextSideB) }
                editTextHeight.isVisible = true.also { visibleFields?.add(editTextHeight) }
                editTextRadius.isVisible = false
            }
            "Hexagon" -> {
                editTextSideA.isVisible = true.also { visibleFields?.add(editTextSideA) }
                editTextSideB.isVisible = false
                editTextHeight.isVisible = false
                editTextRadius.isVisible = false
            }
            "Circle" -> {
                editTextSideA.isVisible = false
                editTextSideB.isVisible = false
                editTextHeight.isVisible = false
                editTextRadius.isVisible = true.also { visibleFields?.add(editTextRadius) }
            }
        }
    }

    private fun validateInputFields() :Boolean {
        //check if active field is empty
        for (field in visibleFields!!) {
            if (field.text.isEmpty()) {
                Toast.makeText(this, R.string.toast_EnterValue, Toast.LENGTH_SHORT).show()
                return false
            }
        }

        //check if Parallelogram's side is equal with its height
        if (spinner2d.selectedItem.toString() == "Parallelogram") {
            if (editTextSideA.text.toString() == editTextHeight.text.toString()) {
                Toast.makeText(this, R.string.toast_EqualValues_Parallelogram, Toast.LENGTH_SHORT).show()
                return false
            }
        }

        //check if Trapezoid's sides are equal
        else if (spinner2d.selectedItem.toString() == "Trapezoid") {
            if (editTextSideA.text.toString() == editTextSideB.text.toString()) {
                Toast.makeText(this, R.string.toast_EqualValues_Trapezoid, Toast.LENGTH_SHORT).show()
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
        result.text = getString(R.string.string_area)
    }

    private fun calculateArea() {
        when (spinner2d.selectedItem.toString()) {
            "Square" -> result.text = CalculatorOperations.calculateSquare(
                editTextSideA.text.toString().toInt()
            ).toString()

            "Rectangle" -> result.text = CalculatorOperations.calculateRectangle(
                editTextSideA.text.toString().toInt(),
                editTextSideB.text.toString().toInt()
            ).toString()

            "Parallelogram" -> result.text = CalculatorOperations.calculateParallelogram(
                editTextSideA.text.toString().toInt(),
                editTextHeight.text.toString().toInt()
            ).toString()

            "Equilateral Triangle" -> result.text =
                CalculatorOperations.calculateEquilateralTriangle(
                    editTextSideA.text.toString().toInt()
                ).toString()

            "Triangle" -> result.text = CalculatorOperations.calculateTriangle(
                editTextSideA.text.toString().toInt(),
                editTextHeight.text.toString().toInt()
            ).toString()

            "Trapezoid" -> result.text = CalculatorOperations.calculateTrapezoid(
                editTextSideA.text.toString().toInt(),
                editTextSideB.text.toString().toInt(),
                editTextHeight.text.toString().toInt()
            ).toString()

            "Hexagon" -> result.text =
                CalculatorOperations.calculateHexagon(editTextSideA.text.toString().toInt())
                    .toString()

            "Circle" -> result.text =
                CalculatorOperations.calculateCircle(editTextRadius.text.toString().toInt())
                    .toString()
        }
    }

    /**
     * Beautifies the result string
     *  ie. 500.0 -> 500, 13.46234756 -> 13.46, etc
     */
    private fun beautifyResult() {
        //keep only two (2) decimals
        val tempDouble: Double = result.text.toString().toDouble()
        var tempResult: String = "%.2f".format(tempDouble).toDouble().toString()

        //remove zero ("0.")
        if (tempResult.endsWith(".0")) {
            tempResult = tempResult.dropLast(2)
        }

        //add initial string plus equals sign ("Area = ")
        result.text = getString(R.string.string_area)
            .plus(" = ")
            .plus(tempResult)
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
    }

    /*
     * Restores values after orientation changes
     */
    private fun restoreInstanceState(savedInstanceState: Bundle?) {
        editTextSideA.setText(savedInstanceState?.get("side_a").toString())
        editTextSideB.setText(savedInstanceState?.get("side_b").toString())
        editTextHeight.setText(savedInstanceState?.get("height").toString())
        editTextRadius.setText(savedInstanceState?.get("radius").toString())
        result.text = savedInstanceState?.get("result").toString()
    }
}