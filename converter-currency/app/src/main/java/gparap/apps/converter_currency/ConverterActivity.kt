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
import java.lang.Exception
import java.lang.StringBuilder

/**
 * Created by gparap on 2021-02-20.
 */
class ConverterActivity : AppCompatActivity(), OnItemSelectedListener {
    private lateinit var spinnerFromCurrency: Spinner
    private lateinit var spinnerToCurrency: Spinner
    private lateinit var editTextAmount: EditText
    private lateinit var labelFromCurrency: TextView
    private lateinit var labelToCurrency: TextView
    private lateinit var buttonConvert: Button
    private lateinit var processBar: ProgressBar
    private lateinit var editTextResult: TextView
    private lateinit var currencies: HashMap<String, String>
    private val baseURL = "https://api.ratesapi.io/api/latest"
    private lateinit var parser: Parser
    private var fromCurrencyRate: Double? = null
    private var toCurrencyRate: Double? = null

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
        editTextAmount = findViewById(R.id.editTextAmount)
        labelFromCurrency = findViewById(R.id.textViewLabelFromCurrency)
        labelToCurrency = findViewById(R.id.textViewLabelToCurrency)
        buttonConvert = findViewById(R.id.buttonConvert)
        processBar = findViewById(R.id.progressBar)
        editTextResult = findViewById(R.id.textViewResult)
    }

    private fun initSpinners() {
        //from currency spinner
        val adapterFromCurrency = ArrayAdapter.createFromResource(
            this, R.array.currencyCodes, android.R.layout.simple_spinner_dropdown_item
        )
        adapterFromCurrency.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerFromCurrency.adapter = adapterFromCurrency
        spinnerFromCurrency.onItemSelectedListener = this

        //to currency spinner
        val adapterToCurrency = ArrayAdapter.createFromResource(
            this, R.array.currencyCodes, android.R.layout.simple_spinner_dropdown_item
        )
        adapterToCurrency.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerToCurrency.adapter = adapterToCurrency
        spinnerToCurrency.onItemSelectedListener = this
    }

    private fun convert() {
        showProgressBar()
        initParser()
        fromCurrencyRate = getCurrencyRate(spinnerFromCurrency.selectedItem.toString())
        toCurrencyRate = getCurrencyRate(spinnerToCurrency.selectedItem.toString())
        editTextResult.text = beautifyResult(calculateConversion())
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

    /**
     * Initializes Parser with currency exchange rates from API
     */
    private fun initParser() {
        parser = if (Connection.latestExchangeRates == null){
            Connection.fetchRates(baseURL)
            Parser(Connection.latestExchangeRates!!)
        }else{
            Parser(Connection.latestExchangeRates!!)
        }
    }

    /**
     * Gets a currency exchange rate
     */
    private fun getCurrencyRate(currency: String) : Double {
        var rate: Double
        parser.getRates().also {
            rate  = parser.getRate(currency)
        }
        return rate
    }

    /**
     * Converts a specific amount from one currency to another
     */
    private fun calculateConversion() : String {
        val result: Double?
        val amount = editTextAmount.text.toString().toDouble()
        result = toCurrencyRate?.times((amount / fromCurrencyRate!!))
        return String.format("%.2f", result)
    }

    /**
     * Beautifies result
     * (like ie. "100 EUR = 120 USD", etc.)
     */
    private fun beautifyResult(conversion: String): String {
        var tempResult = conversion
        val stringBuilder = StringBuilder()

        //remove trailing zeros
        if (tempResult.endsWith(".00")) { tempResult = tempResult.dropLast(3) }

        //give a space between thousands ie. 1000000 -> 1,000,000
        val array = tempResult.split(".")
        if (array[0].length > 3) {
            val reversed = array[0].reversed()
            val range = array[0].length-1
            for (i in 0..range step 3){
                try {
                    stringBuilder.append(reversed[i]).append(reversed[i+1]).append(reversed[i+2])
                    stringBuilder.append(",")
                }catch (e:Exception){}

            }
            stringBuilder.reverse()
            tempResult = if (array.size > 1){
                stringBuilder.append(".").append(array[1]).toString()
            }else {
                stringBuilder.toString()
            }
            //remove possible "," in the start
            if (tempResult.startsWith(",")){
                 tempResult = tempResult.drop(1)
            }

        }

        //add info
        stringBuilder.clear()
        val spaceChar = " "
        stringBuilder.append(editTextAmount.text.toString()).append(spaceChar)                      //ie 100
        stringBuilder.append(spinnerFromCurrency.selectedItem.toString()).append(spaceChar)         //ie EUR
        stringBuilder.append("=").append(spaceChar)                                                 //ie EUR =
        stringBuilder.append(tempResult).append(spaceChar)                                          //ie EUR = 120
        stringBuilder.append(spinnerToCurrency.selectedItem.toString())                             //ie EUR = 120 USD

        return stringBuilder.toString()
    }
}