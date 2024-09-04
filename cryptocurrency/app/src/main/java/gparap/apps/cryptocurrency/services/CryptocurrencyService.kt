/*
 * Copyright (c) 2024 gparap
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
package gparap.apps.cryptocurrency.services

import gparap.apps.cryptocurrency.data.HttpResponseModel
import gparap.apps.cryptocurrency.utils.AppConstants
import retrofit2.Call
import retrofit2.http.GET

/**
 * A web service for receiving cryptocurrency price information.
 * API results are in JSON format.
 */
interface CryptocurrencyService {
    /** Gets all the cryptocurrencies. */
    @get:GET("/api/test_crypto.php?key=" + AppConstants.API_KEY)
    val getCoins: Call<HttpResponseModel?>?
}