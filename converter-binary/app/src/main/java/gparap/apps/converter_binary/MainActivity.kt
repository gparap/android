package gparap.apps.converter_binary

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


/**
 * Created by gparap on 2021-02-19.
 */
class MainActivity : AppCompatActivity() {
    private lateinit var editTextResult: EditText
    private lateinit var buttonConvertToBinary: Button
    private lateinit var buttonConvertToText: Button
    private lateinit var buttonClear: Button
    private val converter: Converter = Converter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //get widgets
        editTextResult = findViewById(R.id.editTextResult)
        buttonConvertToBinary = findViewById(R.id.buttonConvertToBinary)
        buttonConvertToText = findViewById(R.id.buttonConvertToText)
        buttonClear = findViewById(R.id.buttonClear)

        //clear text
        buttonClear.setOnClickListener { editTextResult.text.clear() }

        //convert from text to binary
        buttonConvertToBinary.setOnClickListener {
            if (!isInputEmpty()){
                editTextResult.setText(converter.convertTextToBinary(editTextResult.text.toString()))
            }
        }

        //convert from binary to text
        buttonConvertToText.setOnClickListener {
            if (!isInputEmpty()) {
                //validate input and act accordingly
                if (converter.validateBinaryInput(editTextResult.text.toString())) {
                    editTextResult.setText(converter.convertBinaryToText(editTextResult.text.toString()))
                } else {
                    Toast.makeText(this, R.string.toast_WrongInput, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun isInputEmpty(): Boolean {
        if (editTextResult.text.toString().isEmpty()) {
            Toast.makeText(this, R.string.toast_EmptyInput, Toast.LENGTH_SHORT).show()
            return true
        }
        return false
    }
}