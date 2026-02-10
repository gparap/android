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
    private var webServiceAztro: AztroService? = null
    private var responseAztroApi: Call<HoroscopeModel>? = null

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

    fun createAztroService() {
        webServiceAztro = RetrofitClient.build().create(AztroService::class.java)
    }

    fun getAztroService(): AztroService? {
        return webServiceAztro
    }

    fun getAztroApiResponse(): Call<HoroscopeModel>? {
        return responseAztroApi
    }

    fun setAztroApiResponse(response: Call<HoroscopeModel>?) {
        this.responseAztroApi = response
    }
}