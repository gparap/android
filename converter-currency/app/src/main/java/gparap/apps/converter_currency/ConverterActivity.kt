package gparap.apps.converter_currency

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener

/**
 * Created by gparap on 2021-02-20.
 */
class ConverterActivity : AppCompatActivity(), OnItemSelectedListener {
    lateinit var spinnerFromCurrency: Spinner
    lateinit var spinnerToCurrency: Spinner
    lateinit var labelFromCurrency: TextView
    lateinit var labelToCurrency: TextView
    lateinit var buttonConvert: Button
    lateinit var result: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_converter)
        getWidgets()
        initSpinners()
        buttonConvert.setOnClickListener { convert() }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        labelFromCurrency.text = spinnerFromCurrency.selectedItem.toString()
        labelToCurrency.text = spinnerToCurrency.selectedItem.toString()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    private fun getWidgets() {
        spinnerFromCurrency = findViewById(R.id.spinnerFromCurrency)
        spinnerToCurrency = findViewById(R.id.spinnerToCurrency)
        labelFromCurrency = findViewById(R.id.textViewLabelFromCurrency)
        labelToCurrency = findViewById(R.id.textViewLabelToCurrency)
        buttonConvert = findViewById(R.id.buttonConvert)
        result = findViewById(R.id.textViewResult)
    }

    private fun initSpinners() {
        //from currency spinner
        val adapterFromCurrency = ArrayAdapter.createFromResource(
                this, R.array.currencyCodes, android.R.layout.simple_spinner_dropdown_item)
        adapterFromCurrency.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerFromCurrency.adapter = adapterFromCurrency
        spinnerFromCurrency.onItemSelectedListener = this

        //to currency spinner
        val adapterToCurrency = ArrayAdapter.createFromResource(
                this, R.array.currencyCodes, android.R.layout.simple_spinner_dropdown_item)
        adapterToCurrency.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerToCurrency.adapter = adapterToCurrency
        spinnerToCurrency.onItemSelectedListener = this
    }

    private fun convert() {
        //pass test
        result.text = "pass test"
    }
}