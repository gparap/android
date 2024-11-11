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

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import gparap.apps.personal_finances.R
import gparap.apps.personal_finances.adapters.TransactionAdapter
import gparap.apps.personal_finances.data.PersonalFinancesDatabase
import gparap.apps.personal_finances.data.TransactionModel
import gparap.apps.personal_finances.utils.AppConstants
import gparap.apps.personal_finances.utils.AppConstants.SHARED_PREF_ACCOUNT_BALANCE
import gparap.apps.personal_finances.utils.DeleteTransactionCallback
import gparap.apps.personal_finances.utils.Utils
import kotlinx.coroutines.launch

class AllTransactionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_all_transactions)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.layout_activity_all_transactions)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Utils.updateAppSectionTitle(this@AllTransactionsActivity)

        //setup recycler view with adapter
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView_allTransactions)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = TransactionAdapter(deleteTransactionCallback)
        recyclerView.adapter = adapter

        //display transactions
        val roomDb = PersonalFinancesDatabase.getInstance(this)
        lifecycleScope.launch {
            val transactions = roomDb?.transactionDao()?.getAllTransactions()
            if (transactions != null) {
                for (transaction in transactions) {
                    //add transaction object to adapter
                    adapter.transactions.add(
                        TransactionModel(
                            transaction.id,
                            transaction.type,
                            transaction.quantity,
                            transaction.date,
                            transaction.details
                        )
                    )
                }
            }
            //notify adapter that the data set has changed
            @SuppressLint("NotifyDataSetChanged")
            adapter.notifyDataSetChanged()
        }
    }

    private val deleteTransactionCallback = object : DeleteTransactionCallback {
        override fun onDeleteTransaction(id: Int, quantity: Float) {
            val roomDb = PersonalFinancesDatabase.getInstance(this@AllTransactionsActivity)
            lifecycleScope.launch {
                val rowsDeleted = roomDb?.transactionDao()?.deleteTransaction(id)
                if (rowsDeleted != null) {
                    if (rowsDeleted > 0) {
                        Toast.makeText(
                            this@AllTransactionsActivity,
                            resources.getString(R.string.toast_transaction_deletion_success),
                            Toast.LENGTH_SHORT
                        ).show()

                        //update account balance in shared preferences TODO: refactor
                        val sharedPrefs = getSharedPreferences(AppConstants.APP_SHARED_PREFERENCES, MODE_PRIVATE)
                        var accountBalance = sharedPrefs.getFloat(SHARED_PREF_ACCOUNT_BALANCE, AppConstants.ZERO_ACCOUNT_BALANCE)
                        accountBalance += quantity
                        val editor = sharedPrefs.edit()
                        editor.putFloat(SHARED_PREF_ACCOUNT_BALANCE, accountBalance)
                        editor.apply()

                    } else {
                        Toast.makeText(
                            this@AllTransactionsActivity,
                            resources.getString(R.string.toast_transaction_deletion_failure),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}