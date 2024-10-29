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
import gparap.apps.personal_finances.utils.Utils
import kotlinx.coroutines.launch

class TopUpTransactionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_top_up_transactions)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Utils.updateAppSectionTitle(this@TopUpTransactionsActivity)

        //setup recycler view with adapter
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView_topUpTransactions)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = TransactionAdapter()
        recyclerView.adapter = adapter

        //display top-up transactions
        val roomDb = PersonalFinancesDatabase.getInstance(this)
        lifecycleScope.launch {
            val transactions = roomDb?.transactionDao()?.getTopUpTransactions()
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
}