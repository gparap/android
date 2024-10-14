package gparap.apps.personal_finances

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import gparap.apps.personal_finances.data.PersonalFinancesDatabase
import gparap.apps.personal_finances.data.TransactionModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //add test transaction value to database on section's #1 click
        findViewById<ImageView>(R.id.imageView_section_1_background).setOnClickListener {
            //create a date converter
            val sdf: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
            val dateView = findViewById<EditText>(R.id.editText_transaction_date)
            val dateText = dateView.text.toString()
            var date: Date? = Date()
            date = sdf.parse(dateText)

            //create a transaction
            val transactionModel = TransactionModel(
                type = "test", quantity = 10F, date = date.toString(), details = "Lorem ipsum..."
            )

            //create a database handler
            val roomDB = PersonalFinancesDatabase.getInstance(this)

            //add test transaction to database
            lifecycleScope.launch {
                roomDB?.transactionDao()?.addTransaction(transactionModel)
            }
        }
    }
}