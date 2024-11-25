/*
 * Copyright 2024 gparap
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
package gparap.apps.personal_finances.ui

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import gparap.apps.personal_finances.R
import gparap.apps.personal_finances.data.PersonalFinancesDatabase
import gparap.apps.personal_finances.data.TransactionModel
import gparap.apps.personal_finances.utils.AppConstants
import gparap.apps.personal_finances.utils.TransactionType
import gparap.apps.personal_finances.utils.Utils
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class AddTransactionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_transaction)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.layout_activity_add_transaction)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //get transaction type from intent, if any
        val transactionType: String? = intent.getStringExtra(AppConstants.INTENT_EXTRA_TRANSACTION_TYPE)

        //hide saving transaction progress
        val progressBar: ProgressBar = findViewById(R.id.progressBar_addTransaction)
        progressBar.visibility = View.INVISIBLE

        //set the date for the transaction
        findViewById<EditText>(R.id.editText_transaction_date).setOnClickListener { view ->
            //get a reference to this EditText
            val thisEditText = view as EditText

            //get the date (today)
            val dateNowTimestamp = Date()
            val calendar = Calendar.getInstance()
            calendar.time = dateNowTimestamp

            //display a dialog to select the transaction date
            DatePickerDialog(
                this@AddTransactionActivity,
                android.R.style.Widget_DeviceDefault_DatePicker,
                { _, year, month, dayOfMonth ->
                    //set the transaction date
                    val transactionDate = "$year-$month-$dayOfMonth"
                    thisEditText.setText(transactionDate)
                },
                //initialize the dialog with today's date
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE)
            ).show()
        }

        //add transaction TODO: input data validation
        findViewById<Button>(R.id.button_add_transaction).setOnClickListener {
            //get the transaction input data
            val type = findViewById<EditText>(R.id.editText_transaction_type).text.toString()
            val quantity = findViewById<EditText>(R.id.editText_transaction_quantity).text.toString()
            val date = findViewById<EditText>(R.id.editText_transaction_date).text.toString()
            val details = findViewById<EditText>(R.id.editText_transaction_details).text.toString()

            //check if target quantity is permitted, based on transaction type
            if (transactionType != TransactionType.ALL.toString()) {
                if (transactionType == TransactionType.TOP_UP.toString()) {
                    if (quantity.toFloat() < 0) {
                        Toast.makeText(
                            this@AddTransactionActivity,
                            resources.getString(R.string.toast_add_transaction_require_positive),
                            Toast.LENGTH_SHORT
                        ).show()
                        return@setOnClickListener
                    }

                } else if (transactionType == TransactionType.EXPENSES.toString()) {
                    if (quantity.toFloat() > 0) {
                        Toast.makeText(
                            this@AddTransactionActivity,
                            resources.getString(R.string.toast_add_transaction_require_negative),
                            Toast.LENGTH_SHORT
                        ).show()
                        return@setOnClickListener
                    }
                }
            }

            //create a date formatter
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.US)
            val dateFormatted: Date? = sdf.parse(date)

            //create a transaction
            val transactionModel = TransactionModel(
                type = type, quantity = quantity.toFloat(), date = dateFormatted.toString(), details = details
            )

            //create a database handler
            val roomDB = PersonalFinancesDatabase.getInstance(this)

            //add transaction to database
            lifecycleScope.launch {
                progressBar.visibility = View.VISIBLE
                try {
                    roomDB?.transactionDao()?.addTransaction(transactionModel).apply {
                        Toast.makeText(
                            this@AddTransactionActivity,
                            resources.getString(R.string.toast_add_transaction_success),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } catch (_: Exception) {
                    Toast.makeText(
                        this@AddTransactionActivity,
                        resources.getString(R.string.toast_add_transaction_failure),
                        Toast.LENGTH_SHORT
                    ).show()
                } finally {
                    progressBar.visibility = View.INVISIBLE

                    //update account balance in shared preferences
                    val sharedPrefs = getSharedPreferences(AppConstants.APP_SHARED_PREFERENCES, MODE_PRIVATE)
                    Utils.updateAccountBalance(sharedPrefs, quantity.toFloat())
                }
            }
        }
    }
}