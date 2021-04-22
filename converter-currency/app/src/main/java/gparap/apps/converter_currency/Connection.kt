/*
 * Copyright 2021 gparap
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
package gparap.apps.converter_currency

import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Callable
import java.util.concurrent.Executors

class Connection {
    companion object {
        @JvmStatic
        var latestExchangeRates: JSONObject? = null

        @JvmStatic
        fun fetchRates(stringURL: String): String? {
            val url = URL(stringURL)
            val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
            val androidMobileUserAgents =
                "Mozilla/5.0 AppleWebKit/537.36 Chrome/62.0.3202.84 Mobile Safari/537.36"
            connection.setRequestProperty("User-Agent", androidMobileUserAgents)
            var inputStream: InputStream? = null
            var baseCurrency: String? = null
            try {
                val executor = Executors.newSingleThreadExecutor()

                //fetch exchange rates asynchronously
                executor.submit(Callable {
                    //read api data
                    inputStream = connection.inputStream
                    val bufferedReader = BufferedReader(InputStreamReader(inputStream))
                    val data = bufferedReader.readText()
                    bufferedReader.close()

                    //create JSONObject to hold latest exchange rates
                    if (connection.responseCode == 200) {
                        val exchangeRates = JSONObject(data)
                        latestExchangeRates = exchangeRates

                        //for unit testing
                        baseCurrency = exchangeRates.getString("base")
                    }
                }).get()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                //release resources
                inputStream?.close()
                connection.disconnect()
            }
            return baseCurrency
        }
    }
}