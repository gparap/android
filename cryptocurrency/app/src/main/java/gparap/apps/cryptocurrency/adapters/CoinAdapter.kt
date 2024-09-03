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
package gparap.apps.cryptocurrency.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import gparap.apps.cryptocurrency.R
import gparap.apps.cryptocurrency.data.CoinModel

class CoinAdapter : RecyclerView.Adapter<CoinAdapter.CoinViewHolder>() {
    private lateinit var context: Context
    private lateinit var coins: List<CoinModel>

    @SuppressLint("NotifyDataSetChanged")
    fun setCoins(coins: List<CoinModel>) {
        this.coins = coins
        notifyDataSetChanged()
    }

    //describe the item view
    class CoinViewHolder(itemView: View) : ViewHolder(itemView) {
        var coinIcon: ImageView = itemView.findViewById(R.id.imageViewCrypto)
        var coinSymbol: TextView = itemView.findViewById(R.id.textViewCryptoSymbol)
        var coinPrice: TextView = itemView.findViewById(R.id.textViewCryptoPrice)
    }

    //return the inflated item view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(
            context.resources.getLayout(R.layout.cardview_crypto), parent, false
        )
        return CoinViewHolder(view)
    }

    //return the cryptocurrencies count
    override fun getItemCount(): Int {
        return coins.size
    }

    //display the item view details
    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        Glide.with(context).load(Uri.parse(coins[position].iconUrl)).into(holder.coinIcon)
        holder.coinSymbol.text = coins[position].symbol
        holder.coinPrice.text = coins[position].price
    }
}