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
package gparap.apps.personal_finances.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import gparap.apps.personal_finances.R
import gparap.apps.personal_finances.adapters.TransactionAdapter
import gparap.apps.personal_finances.data.PersonalFinancesDatabase
import gparap.apps.personal_finances.data.TransactionModel
import gparap.apps.personal_finances.utils.AppConstants.SHARED_PREF_ACCOUNT_BALANCE
import kotlinx.coroutines.launch

object Utils {
    fun updateAppSectionTitle(activity: Activity) {
        activity.findViewById<ConstraintLayout>(R.id.layout_appLogoWithSectionTitle).apply {
            this.findViewById<TextView>(R.id.textView_sectionTitle_current).apply {
                this.text = getSectionTitleByActivityName(activity)
            }
        }
    }

    private fun getSectionTitleByActivityName(activity: Activity): String {
        return when (activity.localClassName) {
            "ui.AllTransactionsActivity" -> return activity.resources.getString(R.string.desc_all_transactions)
            "ui.TopUpTransactionsActivity" -> return activity.resources.getString(R.string.desc_top_up_transactions)
            "ui.ExpenseTransactionsActivity" -> return activity.resources.getString(R.string.desc_expense_transactions)
            else -> {}
        }.toString()
    }

    /** Updates account balance in shared preferences. */
    fun updateAccountBalance(sharedPrefs: SharedPreferences, quantity: Float) {
        var accountBalance = sharedPrefs.getFloat(SHARED_PREF_ACCOUNT_BALANCE, AppConstants.ZERO_ACCOUNT_BALANCE)
        accountBalance += quantity
        val editor = sharedPrefs.edit()
        editor.putFloat(SHARED_PREF_ACCOUNT_BALANCE, accountBalance)
        editor.apply()
    }

    fun deleteTransaction(transaction: TransactionModel, context: Context) {
        val roomDb = PersonalFinancesDatabase.getInstance(context)
        (context as LifecycleOwner).lifecycleScope.launch {
            val rowsDeleted = roomDb?.transactionDao()?.deleteTransaction(transaction.id)
            if (rowsDeleted != null) {
                if (rowsDeleted > 0) {
                    Toast.makeText(
                        context,
                        context.resources.getString(R.string.toast_transaction_deletion_success),
                        Toast.LENGTH_SHORT
                    ).show()

                    //update account balance in shared preferences
                    val sharedPrefs = context.getSharedPreferences(AppConstants.APP_SHARED_PREFERENCES, MODE_PRIVATE)
                    updateAccountBalance(sharedPrefs, transaction.quantity)

                } else {
                    Toast.makeText(
                        context,
                        context.resources.getString(R.string.toast_transaction_deletion_failure),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    /** Displays all the transactions of a specific type. */
    @SuppressLint("NotifyDataSetChanged")
    fun displayTransactions(context: Context, type: TransactionType, adapter: TransactionAdapter) {
        val roomDb = PersonalFinancesDatabase.getInstance(context)
        (context as LifecycleOwner).lifecycleScope.launch {
            //get transactions by type
            val transactions: List<TransactionModel>? = when (type) {
                TransactionType.ALL -> roomDb?.transactionDao()?.getAllTransactions()
                TransactionType.TOP_UP -> roomDb?.transactionDao()?.getTopUpTransactions()
                TransactionType.EXPENSES -> roomDb?.transactionDao()?.getExpenseTransactions()
            }
            //add transactions to adapter
            if (transactions != null) {
                for (transaction in transactions) {
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
                //notify adapter that the data set has changed
                adapter.notifyDataSetChanged()
            }
        }
    }
}