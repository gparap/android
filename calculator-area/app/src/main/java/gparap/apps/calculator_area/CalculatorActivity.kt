package gparap.apps.calculator_area

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by gparap on 2021-02-12.
 */
class CalculatorActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    lateinit var spinner2d: Spinner
    lateinit var editTextSideA: EditText
    lateinit var editTextSideB: EditText
    lateinit var editTextHeight: EditText
    lateinit var editTextDiameter: EditText
    lateinit var result: TextView
    var activeFields: ArrayList<EditText>? = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        //get widgets
        editTextSideA = findViewById(R.id.editTextSideA)
        editTextSideB = findViewById(R.id.editTextSideB)
        editTextHeight = findViewById(R.id.editTextHeight)
        editTextDiameter = findViewById(R.id.editTextDiameter)
        result = findViewById(R.id.textViewResult)

        //populate spinner with 2D shapes
        spinner2d = findViewById(R.id.spinnerShapes2D)
        val adapter2d: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
            this, R.array.shapes2D, android.R.layout.simple_spinner_item
        )
        adapter2d.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner2d.adapter = adapter2d
        spinner2d.onItemSelectedListener = this
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
        handleFieldsActivation(spinner2d.selectedItem.toString())

        //clear input and output fields
        clear()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        parent?.getItemAtPosition(0)
    }

    private fun handleFieldsActivation(selectedItem: String) {
        //clear active fields list before activation
        activeFields?.clear()

        //(de)activate fields and add to list
        when (selectedItem) {
            "Square" -> {
                editTextSideA.isEnabled = true.also { activeFields?.add(editTextSideA) }
                editTextSideB.isEnabled = false
                editTextHeight.isEnabled = false
                editTextDiameter.isEnabled = false
            }
            "Rectangle" -> {
                editTextSideA.isEnabled = true.also { activeFields?.add(editTextSideA) }
                editTextSideB.isEnabled = true.also { activeFields?.add(editTextSideB) }
                editTextHeight.isEnabled = false
                editTextDiameter.isEnabled = false
            }
            "Parallelogram" -> {
                editTextSideA.isEnabled = true.also { activeFields?.add(editTextSideA) }
                editTextSideB.isEnabled = false
                editTextHeight.isEnabled = true.also { activeFields?.add(editTextHeight) }
                editTextDiameter.isEnabled = false
            }
            "Equilateral Triangle" -> {
                editTextSideA.isEnabled = true.also { activeFields?.add(editTextSideA) }
                editTextSideB.isEnabled = false
                editTextHeight.isEnabled = false
                editTextDiameter.isEnabled = false
            }
            "Triangle" -> {
                editTextSideA.isEnabled = true.also { activeFields?.add(editTextSideA) }
                editTextSideB.isEnabled = false
                editTextHeight.isEnabled = true
                editTextDiameter.isEnabled = false
            }
            "Trapezoid" -> {
                editTextSideA.isEnabled = true.also { activeFields?.add(editTextSideA) }
                editTextSideB.isEnabled = true.also { activeFields?.add(editTextSideB) }
                editTextHeight.isEnabled = true.also { activeFields?.add(editTextHeight) }
                editTextDiameter.isEnabled = false
            }
            "Hexagon" -> {
                editTextSideA.isEnabled = true.also { activeFields?.add(editTextSideA) }
                editTextSideB.isEnabled = false
                editTextHeight.isEnabled = false
                editTextDiameter.isEnabled = false
            }
            "Circle" -> {
                editTextSideA.isEnabled = false
                editTextSideB.isEnabled = false
                editTextHeight.isEnabled = false
                editTextDiameter.isEnabled = true.also { activeFields?.add(editTextDiameter) }
            }
        }
    }

    private fun validateInputFields() :Boolean {
        for (field in activeFields!!) {
            //Check if active field is empty
            if (field.text.isEmpty()) {
                Toast.makeText(this, R.string.toast_EnterValue, Toast.LENGTH_SHORT).show()
                return false
            }
        }
        return true
    }

    private fun clear() {
        editTextSideA.text.clear()
        editTextSideB.text.clear()
        editTextHeight.text.clear()
        editTextDiameter.text.clear()
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
                CalculatorOperations.calculateCircle(editTextDiameter.text.toString().toInt())
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
}