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
package gparap.apps.personal_finances

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import gparap.apps.personal_finances.ui.AddTransactionActivity
import gparap.apps.personal_finances.ui.AllTransactionsActivity
import gparap.apps.personal_finances.ui.ExpenseTransactionsActivity
import gparap.apps.personal_finances.ui.TopUpTransactionsActivity
import gparap.apps.personal_finances.utils.AppConstants
import gparap.apps.personal_finances.utils.AppConstants.SHARED_PREF_ACCOUNT_BALANCE
import gparap.apps.personal_finances.utils.AppConstants.ZERO_ACCOUNT_BALANCE
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private var accountBalance: Float = ZERO_ACCOUNT_BALANCE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //initialize account balance as a shared preference, if not exists
        val sharedPreferences = getSharedPreferences(AppConstants.APP_SHARED_PREFERENCES, MODE_PRIVATE)
        if (!sharedPreferences.contains(SHARED_PREF_ACCOUNT_BALANCE)) {
            val editor = sharedPreferences.edit()
            editor.putFloat(SHARED_PREF_ACCOUNT_BALANCE, ZERO_ACCOUNT_BALANCE)
            editor.apply()
        }else{
            accountBalance = sharedPreferences.getFloat(SHARED_PREF_ACCOUNT_BALANCE, ZERO_ACCOUNT_BALANCE)
        }

        //display account balance
        findViewById<TextView>(R.id.textView_account_balance).apply {
            text = String.format(Locale.getDefault(), accountBalance.toString())
        }

        //redirect to add transaction screen
        findViewById<FloatingActionButton>(R.id.fab_addTransaction).setOnClickListener {
            startActivity(Intent(this, AddTransactionActivity::class.java))
        }

        //redirect to all transactions screen
        findViewById<ImageView>(R.id.imageView_all_transactions_background).setOnClickListener {
            startActivity(Intent(this, AllTransactionsActivity::class.java))
        }

        //redirect to top-up transactions screen
        findViewById<ImageView>(R.id.imageView_top_up_transactions_background).setOnClickListener {
            startActivity(Intent(this, TopUpTransactionsActivity::class.java))
        }

        //redirect to expense transactions screen
        findViewById<ImageView>(R.id.imageView_expense_transactions_background).setOnClickListener {
            startActivity(Intent(this, ExpenseTransactionsActivity::class.java))
        }
    }
}
