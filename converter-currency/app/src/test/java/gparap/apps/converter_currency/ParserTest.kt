/*
 * Copyright 2021-2023 gparap
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

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class ParserTest {
    private val stringURL = "https://api.ratesapi.io/api/latest"
    @Test
    fun getRates_getJSONObjectData() {
        Connection.fetchRates(stringURL)
        val parser = Connection.latestExchangeRates?.let { Parser(it) }
        assertNotNull(parser?.getRates())
    }

    @Test
    fun getRate_getBaseCurrencyExchangeRate() {
        Connection.fetchRates(stringURL)
        val parser = Connection.latestExchangeRates?.let { Parser(it) }
        val expected = 1.toDouble()
        val actual = parser?.getRate("EUR")
        assertEquals(expected, actual)
    }

    @Test
    fun getRate_getACurrencyExchangeRate() {
        Connection.fetchRates(stringURL)
        val parser = Connection.latestExchangeRates?.let { Parser(it) }
        assertNotNull(parser?.getRate("USD"))
    }
}