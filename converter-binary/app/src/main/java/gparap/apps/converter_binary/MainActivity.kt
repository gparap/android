package gparap.apps.converter_binary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    lateinit var editTextResult :EditText
    lateinit var buttonConvertToBinary: Button
    lateinit var buttonConvertToText: Button
    lateinit var buttonClear:Button
    private val converter: Converter = Converter()

    @ExperimentalStdlibApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //get widgets
        editTextResult = findViewById(R.id.editTextResult)
        buttonConvertToBinary= findViewById(R.id.buttonConvertToBinary)
        buttonConvertToText= findViewById(R.id.buttonConvertToText)
        buttonClear= findViewById(R.id.buttonClear)

        //add listeners
        buttonClear.setOnClickListener { clear() }
        buttonConvertToBinary.setOnClickListener {
            editTextResult.setText(converter.convertTextToBinary(editTextResult.text.toString()))

        }
    }

    // Clears text
    private fun clear() {
        editTextResult.text.clear()
    }
}