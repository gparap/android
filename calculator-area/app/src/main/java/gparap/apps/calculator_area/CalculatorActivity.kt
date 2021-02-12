package gparap.apps.calculator_area

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.view.get
import java.lang.Exception

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
            //
            when (spinner2d.selectedItem.toString()) {
                "Square" -> {
                    if (editTextSideA.text.isEmpty()) {
                        Toast.makeText(this, R.string.toast_EnterValueSideA, Toast.LENGTH_SHORT).show()
                    }else {
                        result.text = CalculatorOperations.calculateSquare(editTextSideA.text.toString().toInt()).toString()
                    }
                }
            }
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        parent?.getItemAtPosition(position)

        //de-activate input fields based on spinner selection
        handleFieldsActivation(spinner2d.selectedItem.toString())
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        parent?.getItemAtPosition(0)
    }

    private fun handleFieldsActivation(selectedItem: String) {
        when (selectedItem){
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
}
/*
 FORMULAS:
 area of a square                   = a ^ 2
 area of a rectangle                = a * b
 area of a parallelogram            = a * h
 area of an equilateral triangle    = [√3 *a^2] / 4
 area of a triangle                 = a * h / 2
 area of a trapezoid                = 1/2 * (a + b) * h
 area of a hexagon                  = (3 * √3 * a^2) / 2
 area of a circle                   = π * (d / 2)
 */