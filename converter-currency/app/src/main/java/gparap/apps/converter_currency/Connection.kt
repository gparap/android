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

/**
 * Created by gparap on 2021-03-01.
 */
class Connection {
    companion object {
        @JvmStatic
        fun fetchRates(stringURL: String) {
            val url = URL(stringURL)
            val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
            var inputStream: InputStream? = null
            try {
                val executor = Executors.newSingleThreadExecutor()

                //get latest exchange rates asynchronously
                executor.submit(Callable {
                    //read api data
                    inputStream = connection.inputStream
                    val bufferedReader = BufferedReader(InputStreamReader(inputStream))
                    val data = bufferedReader.readText()
                    bufferedReader.close()

                    //create a new JSONObject to hold the exchange rates
                    var exchangeRates = JSONObject(data)
                })
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                //release resources
                inputStream?.close()
                connection.disconnect()
            }
        }
    }
}