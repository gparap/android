/*
 * Copyright (c) 2022 gparap
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
package gparap.apps.horoscope.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import gparap.apps.horoscope.data.HoroscopeModel
import gparap.apps.horoscope.services.AztroService
import gparap.apps.horoscope.services.RetrofitClient
import retrofit2.Call

class MainActivityViewModel : ViewModel() {
    private val requestedDay: MutableLiveData<String> = MutableLiveData()
    private val zodiacSign: MutableLiveData<String> = MutableLiveData()
    private var webService: AztroService? = null
    private var response: Call<HoroscopeModel>? = null

    fun getRequestedDay(): String? {
        return requestedDay.value
    }

    fun setRequestedDay(value: String) {
        requestedDay.value = value
    }

    fun getZodiacSign(): String? {
        return zodiacSign.value
    }

    fun setZodiacSign(value: String) {
        zodiacSign.value = value
    }

    fun createWebService() {
        webService = RetrofitClient.build().create(AztroService::class.java)
    }

    fun getWebService(): AztroService? {
        return webService
    }

    fun getApiResponse(): Call<HoroscopeModel>? {
        return response
    }

    fun setApiResponse(response: Call<HoroscopeModel>?) {
        this.response = response
    }
}