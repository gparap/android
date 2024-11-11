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
package gparap.apps.personal_finances.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import gparap.apps.personal_finances.R
import gparap.apps.personal_finances.adapters.TransactionAdapter.TransactionViewHolder
import gparap.apps.personal_finances.data.TransactionModel
import gparap.apps.personal_finances.utils.DeleteTransactionCallback

class TransactionAdapter(private val deleteTransactionCallback: DeleteTransactionCallback) :
    RecyclerView.Adapter<TransactionViewHolder>() {
    var transactions: ArrayList<TransactionModel> = ArrayList()
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        //get context
        context = parent.context

        //create view
        val layoutInflater =
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.cardview_transaction_item, parent, false)
        return TransactionViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.type.text = transactions[position].type.toString()
        holder.quantity.text = transactions[position].quantity.toString()
        holder.date.text = transactions[position].date.toString()

        //pass a callback to delete the transaction
        holder.deleteTransaction.setOnClickListener {
            AlertDialog.Builder(context)
                .setIcon(AppCompatResources.getDrawable(context, R.drawable.ic_delete_24px))
                .setTitle(context.getString(R.string.desc_delete_transaction))
                .setMessage(context.getString(R.string.alert_delete_transaction_message))
                .setPositiveButton(context.getString(R.string.alert_delete_transaction_ok)) { dialog, _ ->
                    deleteTransactionCallback.onDeleteTransaction(
                        transactions[position].id,
                        transactions[position].quantity
                    )
                    dialog.dismiss()
                }
                .setNegativeButton(context.getString(R.string.alert_delete_transaction_cancel)) { dialog, _ ->
                    dialog.dismiss()
                }
                .create()
                .apply {
                    show()
                }
        }
    }

    override fun getItemCount(): Int {
        return transactions.size
    }

    inner class TransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var typeLabel = itemView.findViewById<TextView>(R.id.cardView_transactionType_label)
        var type = itemView.findViewById<TextView>(R.id.cardView_transactionType)
        var quantityLabel = itemView.findViewById<TextView>(R.id.cardView_transactionQuantity_label)
        var quantity = itemView.findViewById<TextView>(R.id.cardView_transactionQuantity)
        var dateLabel = itemView.findViewById<TextView>(R.id.cardView_transactionDate_label)
        var date = itemView.findViewById<TextView>(R.id.cardView_transactionDate)
        var deleteTransaction = itemView.findViewById<ImageView>(R.id.imageView_deleteTransaction)
    }
}