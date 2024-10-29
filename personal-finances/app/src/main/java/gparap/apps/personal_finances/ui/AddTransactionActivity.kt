package gparap.apps.personal_finances.ui

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
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
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

        //hide saving transaction progress
        val progressBar: ProgressBar = findViewById(R.id.progressBar_addTransaction)
        progressBar.visibility = View.INVISIBLE

        //add transaction TODO: input data validation
        findViewById<Button>(R.id.button_add_transaction).setOnClickListener {
            //get the transaction input data
            val type = findViewById<EditText>(R.id.editText_transaction_type).text.toString()
            val quantity = findViewById<EditText>(R.id.editText_transaction_quantity).text.toString()
            val date = findViewById<EditText>(R.id.editText_transaction_date).text.toString()
            val details = findViewById<EditText>(R.id.editText_transaction_details).text.toString()

            //create a date formatter TODO: use a date/calendar widget
            val sdf: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
            var dateFormatted: Date? = Date()
            dateFormatted = sdf.parse(date)

            //create a transaction
            val transactionModel = TransactionModel(
                type = type, quantity = quantity.toFloat(), date = dateFormatted.toString(), details = details
            )

            //create a database handler
            val roomDB = PersonalFinancesDatabase.getInstance(this)

            //add test transaction to database
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
                }
            }
        }
    }
}