package gparap.apps.converter_binary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

/**
 * Created by gparap on 2021-02-19.
 */
class MainActivity : AppCompatActivity() {
    private lateinit var editTextResult :EditText
    private lateinit var buttonConvertToBinary: Button
    private lateinit var buttonConvertToText: Button
    private lateinit var buttonClear:Button
    private val converter: Converter = Converter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //get widgets
        editTextResult = findViewById(R.id.editTextResult)
        buttonConvertToBinary= findViewById(R.id.buttonConvertToBinary)
        buttonConvertToText= findViewById(R.id.buttonConvertToText)
        buttonClear= findViewById(R.id.buttonClear)

        //clear text
        buttonClear.setOnClickListener { editTextResult.text.clear() }

        //convert from binary to text
        buttonConvertToBinary.setOnClickListener {
            editTextResult.setText(converter.convertTextToBinary(editTextResult.text.toString()))
        }

        //convert from text to binary
        buttonConvertToText.setOnClickListener {
            editTextResult.setText(converter.convertBinaryToText(editTextResult.text.toString()))
        }
    }
}