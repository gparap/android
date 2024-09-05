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
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import gparap.apps.cryptocurrency.adapters.CoinAdapter
import gparap.apps.cryptocurrency.data.CoinModel
import gparap.apps.cryptocurrency.data.HttpResponseModel
import gparap.apps.cryptocurrency.services.CryptocurrencyService
import gparap.apps.cryptocurrency.services.RetrofitClient
import gparap.apps.cryptocurrency.utils.AppConstants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private var coins: List<CoinModel>? = null
    private var coinAdapter = CoinAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = getString(R.string.txt_mainActivity_actionBar_title)

        //create the web service
        val webService = RetrofitClient().build(AppConstants.BASE_URL).create(CryptocurrencyService::class.java)

        //consume the web service (get cryptocurrencies)
        val httpResponse: Call<HttpResponseModel?>? = webService.getCoins
        httpResponse?.enqueue(object : Callback<HttpResponseModel?> {
            override fun onResponse(
                request: Call<HttpResponseModel?>,
                response: Response<HttpResponseModel?>
            ) {
                if (response.body()?.statusCode.equals("200") || response.body()?.statusCode.equals("success")) {
                    coins = response.body()?.cryptoData
                    coins?.let { coinAdapter.setCoins(it) }
                } else {
                    Log.d("API_MSG", "this shouldn't have happened!")
                }
            }

            override fun onFailure(request: Call<HttpResponseModel?>, response: Throwable) {
                Log.d("ERROR", response.message.toString())
            }
        })

        //initialize recycler view with adapter to display the cryptocurrencies
        val recyclerViewCryptos: RecyclerView = findViewById(R.id.recycler_view_cryptos)
        recyclerViewCryptos.layoutManager = LinearLayoutManager(this)
        coins?.let { coinAdapter.setCoins(it) }
        recyclerViewCryptos.adapter = coinAdapter
    }
}