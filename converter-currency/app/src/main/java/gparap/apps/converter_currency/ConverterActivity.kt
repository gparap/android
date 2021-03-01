/*
 * Copyright 2021 gparap
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
package gparap.apps.converter_currency

import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible

/**
 * Created by gparap on 2021-02-20.
 */
class ConverterActivity : AppCompatActivity(), OnItemSelectedListener {
    private lateinit var spinnerFromCurrency: Spinner
    private lateinit var spinnerToCurrency: Spinner
    private lateinit var amount: EditText
    private lateinit var labelFromCurrency: TextView
    private lateinit var labelToCurrency: TextView
    private lateinit var buttonConvert: Button
    private lateinit var processBar: ProgressBar
    private lateinit var result: TextView
    private lateinit var currencies: HashMap<String, String>
    private var baseURL = "https://api.ratesapi.io/api/latest"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_converter)
        initCurrencies()
        getWidgets()
        initSpinners()
        buttonConvert.setOnClickListener { convert() }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        //display full currency name
        labelFromCurrency.text = currencies[spinnerFromCurrency.selectedItem.toString()]
        labelToCurrency.text = currencies[spinnerToCurrency.selectedItem.toString()]
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    private fun getWidgets() {
        spinnerFromCurrency = findViewById(R.id.spinnerFromCurrency)
        spinnerToCurrency = findViewById(R.id.spinnerToCurrency)
        amount = findViewById(R.id.editTextAmount)
        labelFromCurrency = findViewById(R.id.textViewLabelFromCurrency)
        labelToCurrency = findViewById(R.id.textViewLabelToCurrency)
        buttonConvert = findViewById(R.id.buttonConvert)
        processBar = findViewById(R.id.progressBar)
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
        showProgressBar()
        Connection.fetchRates(baseURL)
        hideProgressBar()
    }

    /**
     * Creates a collection of currencies
     */
    private fun initCurrencies() {
        currencies = HashMap()
        val count = 33
        val codes = resources.getStringArray(R.array.currencyCodes)
        val names = resources.getStringArray(R.array.currencyNames)
        for (i in 0 until count) {
            currencies[codes[i].toString()] = names[i].toString()
        }
    }

    private fun hideProgressBar() {
        processBar.isVisible = false
    }

    private fun showProgressBar() {
        processBar.isVisible = true
    }
}