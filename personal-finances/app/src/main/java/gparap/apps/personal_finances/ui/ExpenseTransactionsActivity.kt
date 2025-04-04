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
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import gparap.apps.personal_finances.R
import gparap.apps.personal_finances.adapters.TransactionAdapter
import gparap.apps.personal_finances.data.TransactionModel
import gparap.apps.personal_finances.utils.AppConstants
import gparap.apps.personal_finances.utils.DeleteTransactionCallback
import gparap.apps.personal_finances.utils.TransactionType
import gparap.apps.personal_finances.utils.Utils

@SuppressLint("NotifyDataSetChanged")
class ExpenseTransactionsActivity : AppCompatActivity() {
    private lateinit var adapter: TransactionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_expense_transactions)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Utils.updateAppSectionTitle(this@ExpenseTransactionsActivity)

        //setup recycler view with adapter
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView_expenseTransactions)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = TransactionAdapter(deleteTransactionCallback)
        recyclerView.adapter = adapter

        //redirect to add transaction screen
        findViewById<FloatingActionButton>(R.id.fab_addExpenseTransaction).setOnClickListener {
            val intent = Intent(this, AddTransactionActivity::class.java)
            intent.putExtra(AppConstants.INTENT_EXTRA_TRANSACTION_TYPE, TransactionType.EXPENSES.toString())
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()

        //display expense transactions
        adapter.transactions.clear()
        Utils.displayTransactions(this, TransactionType.EXPENSES, adapter)
    }

    private val deleteTransactionCallback = object : DeleteTransactionCallback {
        override fun onDeleteTransaction(transaction: TransactionModel) {
            Utils.deleteTransaction(transaction, this@ExpenseTransactionsActivity)
            this@ExpenseTransactionsActivity.recreate()
        }
    }
}