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

/**
 * A class that parses JSON API http response.
 * Created by gparap on 2021-03-02.
 */
class Parser(data: JSONObject) {
    private var data: JSONObject? = null
    private var rates: JSONObject? = null

    init {
        this.data = data
    }

    fun getRates() : JSONObject{
        return data?.getJSONObject("rates") as JSONObject
    }

    fun getRate(currency: String): Double {
        //base currency always 1
        if (currency == "EUR") {
            return 1.toDouble()
        }

        //get currency exchange rate
        return getRates().getDouble(currency)
    }
}