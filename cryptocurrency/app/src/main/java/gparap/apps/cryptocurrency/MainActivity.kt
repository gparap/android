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
package gparap.apps.cryptocurrency

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import gparap.apps.cryptocurrency.adapters.CoinAdapter
import gparap.apps.cryptocurrency.data.CoinModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = getString(R.string.txt_mainActivity_actionBar_title)

        //TODO: call the actual web service

        //create a test list of cryptocurrencies
        val coins = listOf(
            CoinModel("1", "BTC", "Bitcoin", "#f7931A", "https://cdn.pixabay.com/photo/2015/08/27/11/20/bitcoin-910307_1280.png","1164623660707", "60000.00", "1.01", "1","23342784016"),
            CoinModel("2", "BTC", "Bitcoin", "#f7931A","https://cdn.pixabay.com/photo/2015/08/27/11/20/bitcoin-910307_1280.png","1164623660707", "60000.00", "1.01", "1","23342784016"),
            CoinModel("3", "BTC", "Bitcoin", "#f7931A","https://cdn.pixabay.com/photo/2015/08/27/11/20/bitcoin-910307_1280.png","1164623660707", "60000.00", "1.01", "1","23342784016"),
            CoinModel("4", "BTC", "Bitcoin", "#f7931A","https://cdn.pixabay.com/photo/2015/08/27/11/20/bitcoin-910307_1280.png","1164623660707", "60000.00", "1.01", "1","23342784016"),
            CoinModel("5", "BTC", "Bitcoin", "#f7931A","https://cdn.pixabay.com/photo/2015/08/27/11/20/bitcoin-910307_1280.png","1164623660707", "60000.00", "1.01", "1","23342784016"),
            CoinModel("5", "BTC", "Bitcoin", "#f7931A","https://cdn.pixabay.com/photo/2015/08/27/11/20/bitcoin-910307_1280.png","1164623660707", "60000.00", "1.01", "1","23342784016"),
            CoinModel("5", "BTC", "Bitcoin", "#f7931A","https://cdn.pixabay.com/photo/2015/08/27/11/20/bitcoin-910307_1280.png","1164623660707", "60000.00", "1.01", "1","23342784016"),
            CoinModel("5", "BTC", "Bitcoin", "#f7931A","https://cdn.pixabay.com/photo/2015/08/27/11/20/bitcoin-910307_1280.png","1164623660707", "60000.00", "1.01", "1","23342784016"),
            CoinModel("5", "BTC", "Bitcoin", "#f7931A","https://cdn.pixabay.com/photo/2015/08/27/11/20/bitcoin-910307_1280.png","1164623660707", "60000.00", "1.01", "1","23342784016"),
            CoinModel("5", "BTC", "Bitcoin", "#f7931A","https://cdn.pixabay.com/photo/2015/08/27/11/20/bitcoin-910307_1280.png","1164623660707", "60000.00", "1.01", "1","23342784016"),
        )

        //initialize recycler view with adapter
        val recyclerViewCryptos: RecyclerView = findViewById(R.id.recycler_view_cryptos)
        recyclerViewCryptos.layoutManager = LinearLayoutManager(this)
        val coinAdapter = CoinAdapter()
        coinAdapter.setCoins(coins)
        recyclerViewCryptos.adapter = coinAdapter
    }
}