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
        //only natural numbers allowed
        if (editTextSideA.text.isEmpty() && editTextSideB.text.isEmpty() &&
            editTextHeight.text.isEmpty() && editTextDiameter.text.isEmpty()
        ) {
            Toast.makeText(this, R.string.toast_EnterValue, Toast.LENGTH_SHORT).show()
        } else {
            // TODO: validation based on active fields ie: see bellow
            //if    activeField is empty then toast "should not be empty"
            //else  calculate result

            calculateArea()
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
        when (selectedItem) {
            "Square" -> {
                editTextSideA.isEnabled = true
                editTextSideB.isEnabled = false
                editTextHeight.isEnabled = false
                editTextDiameter.isEnabled = false
            }
            "Rectangle" -> {
                editTextSideA.isEnabled = true
                editTextSideB.isEnabled = true
                editTextHeight.isEnabled = false
                editTextDiameter.isEnabled = false
            }
            "Parallelogram" -> {
                editTextSideA.isEnabled = true
                editTextSideB.isEnabled = false
                editTextHeight.isEnabled = true
                editTextDiameter.isEnabled = false
            }
            "Equilateral Triangle" -> {
                editTextSideA.isEnabled = true
                editTextSideB.isEnabled = false
                editTextHeight.isEnabled = false
                editTextDiameter.isEnabled = false
            }
            "Triangle" -> {
                editTextSideA.isEnabled = true
                editTextSideB.isEnabled = false
                editTextHeight.isEnabled = true
                editTextDiameter.isEnabled = false
            }
            "Trapezoid" -> {
                editTextSideA.isEnabled = true
                editTextSideB.isEnabled = true
                editTextHeight.isEnabled = true
                editTextDiameter.isEnabled = false
            }
            "Hexagon" -> {
                editTextSideA.isEnabled = true
                editTextSideB.isEnabled = false
                editTextHeight.isEnabled = false
                editTextDiameter.isEnabled = false
            }
            "Circle" -> {
                editTextSideA.isEnabled = false
                editTextSideB.isEnabled = false
                editTextHeight.isEnabled = false
                editTextDiameter.isEnabled = true
            }
        }
    }

    private fun clear() {
        editTextSideA.text.clear()
        editTextSideB.text.clear()
        editTextHeight.text.clear()
        editTextDiameter.text.clear()
        result.text = ""
    }

    private fun calculateArea() {
        when (spinner2d.selectedItem.toString()) {
            "Square" -> result.text =
                CalculatorOperations.calculateSquare(editTextSideA.text.toString().toInt())
                    .toString()

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
}