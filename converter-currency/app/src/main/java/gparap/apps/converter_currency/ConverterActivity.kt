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

class ConverterActivity : AppCompatActivity(), OnItemSelectedListener {
    private lateinit var spinnerFromCurrency: Spinner
    private lateinit var spinnerToCurrency: Spinner
    private lateinit var editTextAmount: EditText
    private lateinit var labelFromCurrency: TextView
    private lateinit var labelToCurrency: TextView
    private lateinit var buttonConvert: Button
    private lateinit var editTextResult: TextView
    private lateinit var currencies: HashMap<String, String>
    private val baseURL = "https://api.vatcomply.com/rates"
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
    }

    private fun getWidgets() {
        spinnerFromCurrency = findViewById(R.id.spinnerFromCurrency)
        spinnerToCurrency = findViewById(R.id.spinnerToCurrency)
        editTextAmount = findViewById(R.id.editTextAmount)
        labelFromCurrency = findViewById(R.id.textViewLabelFromCurrency)
        labelToCurrency = findViewById(R.id.textViewLabelToCurrency)
        buttonConvert = findViewById(R.id.buttonConvert)
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
        if (initParser()) {
            fromCurrencyRate = getCurrencyRate(spinnerFromCurrency.selectedItem.toString())
            toCurrencyRate = getCurrencyRate(spinnerToCurrency.selectedItem.toString())
            editTextResult.text = beautifyResult(calculateConversion())
        }
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

    /**
     * Initializes Parser with currency exchange rates from API
     */
    private fun initParser(): Boolean {
        parser = if (Connection.latestExchangeRates == null) {
            try {
                Connection.fetchRates(baseURL)
                Parser(Connection.latestExchangeRates!!)
            } catch (e: Exception) {
                e.printStackTrace()

                //user has disabled wifi connection
                Toast.makeText(this, R.string.hint_wifi_disabled, Toast.LENGTH_SHORT).show()
                return false
            }
        } else {
            Parser(Connection.latestExchangeRates!!)
        }
        return true
    }

    /**
     * Gets a currency exchange rate
     */
    private fun getCurrencyRate(currency: String): Double {
        var rate: Double
        parser.getRates().also {
            rate = parser.getRate(currency)
        }
        return rate
    }

    /**
     * Converts a specific amount from one currency to another
     */
    private fun calculateConversion(): String {
        val result: Double?

        //get amount
        val amount =
            if (editTextAmount.text.isNullOrBlank() || editTextAmount.text.toString() == ".") {
                1.toDouble()
            } else {
                editTextAmount.text.toString().toDouble()
            }

        //get result
        result = toCurrencyRate?.times((amount / fromCurrencyRate!!))
        return String.format("%.2f", result)
    }

    /**
     * Beautifies result
     *  ie. "100 EUR = 120 USD", "1,000 EUR = 1,200 USD", etc.
     */
    private fun beautifyResult(conversion: String): String {
        val stringBuilder = StringBuilder()
        val space = " "

        //there is no conversion
        if (spinnerFromCurrency.selectedItem == spinnerToCurrency.selectedItem) {
            //construct the final conversion result info
            stringBuilder.append("1").append(space)
            stringBuilder.append(spinnerFromCurrency.selectedItem.toString()).append(space)
            stringBuilder.append("=").append(space)
            stringBuilder.append("1").append(space)
            stringBuilder.append(spinnerToCurrency.selectedItem.toString())
            return stringBuilder.toString()
        }

        //the "to" and "from" currency conversion outputs
        var outputTo = conversion.replace(",", ".")
        var outputFrom =
            if (editTextAmount.text.toString().isEmpty() || editTextAmount.text.toString() == ".") {
                "1"
            } else if (editTextAmount.text.toString().toDoubleOrNull() == 0.0) {
                "0"
            } else {
                editTextAmount.text.toString()
            }

        //remove trailing zeros
        val integer: Int?
        val decimal: Double?
        if (outputFrom.contains(".")) {
            integer = outputFrom.substring(0, outputFrom.indexOf(".")).toInt()
            decimal = outputFrom.substring(outputFrom.indexOf("."), outputFrom.length).toDouble()
            if (decimal == 0.0) {
                outputFrom = integer.toString()
            }
        }
        if (outputTo.endsWith(".00")) {
            outputTo = outputTo.dropLast(3)
        }

        //append commas (",") between thousands
        if (outputTo.contains(".")) {
            if (outputTo.substring(0, outputTo.indexOf(".")).length > 3)
                outputTo = beautifyThousands(outputTo)
        } else {
            if (outputTo.length > 3)
                outputTo = beautifyThousands(outputTo)
        }
        if (outputFrom.contains(".")) {
            if (outputFrom.substring(0, outputFrom.indexOf(".")).length > 3)
                outputFrom = beautifyThousands(outputFrom)
        } else {
            if (outputFrom.length > 3)
                outputFrom = beautifyThousands(outputFrom)
        }

        //remove possible "," in the start
        if (outputTo.startsWith(",")) {
            outputTo = outputTo.drop(1)
        }
        if (outputFrom.startsWith(",")) {
            outputFrom = outputFrom.drop(1)
        }

        //construct the final conversion result info
        stringBuilder.append(outputFrom).append(space)
        stringBuilder.append(spinnerFromCurrency.selectedItem.toString()).append(space)
        stringBuilder.append("=").append(space)
        stringBuilder.append(outputTo).append(space)
        stringBuilder.append(spinnerToCurrency.selectedItem.toString())

        return stringBuilder.toString()
    }

    /**
     * Appends "," between thousands ie. 1000000 -> 1,000,000
     */
    private fun beautifyThousands(inputString: String): String {
        val stringBuilder = StringBuilder()
        val array = inputString.split(".")
        if (array[0].length > 3) {
            val reversed = array[0].reversed()
            val range = array[0].length - 1
            for (i in 0..range step 3) {
                try {
                    stringBuilder.append(reversed[i]).append(reversed[i + 1])
                        .append(reversed[i + 2])
                    stringBuilder.append(",")
                } catch (e: Exception) {
                }
            }
            stringBuilder.reverse()
            if (array.size > 1) {
                stringBuilder.append(".").append(array[1]).toString()
            } else {
                stringBuilder.toString()
            }
        }
        return stringBuilder.toString()
    }

    override fun onDestroy() {
        super.onDestroy()

        //clear exchange rate cache
        Connection.latestExchangeRates = null
    }
}