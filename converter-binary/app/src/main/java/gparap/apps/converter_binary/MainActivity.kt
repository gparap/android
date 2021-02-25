package gparap.apps.converter_binary

import android.os.Bundle
import android.text.Editable
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
    private val validator: Validator = Validator()
    private val parser: Parser = Parser()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //get widgets
        editTextResult = findViewById(R.id.editTextResult)
        buttonConvertToBinary = findViewById(R.id.buttonConvertToBinary)
        buttonConvertToText = findViewById(R.id.buttonConvertToText)
        buttonClear = findViewById(R.id.buttonClear)

        //restore state after orientation changes
        if (savedInstanceState != null) {
            editTextResult.text = savedInstanceState.getCharSequence("result") as Editable
        }

        //clear text
        buttonClear.setOnClickListener { editTextResult.text.clear() }

        //convert from text to binary
        buttonConvertToBinary.setOnClickListener {
            if (!isInputEmpty()){
                editTextResult.setText(converter.convertTextToBinary(editTextResult.text.toString()))

                //parse result
                editTextResult.setText(parser.parseBinary(editTextResult.text.toString()))
            }
        }

        //convert from binary to text
        buttonConvertToText.setOnClickListener {
            if (!isInputEmpty()) {
                //parse result
                val unparsedResult = editTextResult.text.toString()
                val parsedResult = parser.unparseBinary(editTextResult.text.toString())

                //validate input
                if (validator.validateBinaryInput(parsedResult)) {
                    editTextResult.setText(converter.convertBinaryToText(parsedResult))
                } else {
                    editTextResult.setText(unparsedResult)
                    Toast.makeText(this, R.string.toast_WrongInput, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        //save state before orientation changes
        outState.putCharSequence("result", editTextResult.text)
    }

    private fun isInputEmpty(): Boolean {
        if (editTextResult.text.toString().isEmpty()) {
            Toast.makeText(this, R.string.toast_EmptyInput, Toast.LENGTH_SHORT).show()
            return true
        }
        return false
    }
}