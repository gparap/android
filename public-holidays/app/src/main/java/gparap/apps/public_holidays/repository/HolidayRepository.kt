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
package gparap.apps.public_holidays.repository

import androidx.lifecycle.MutableLiveData
import gparap.apps.public_holidays.model.HolidayModel
import gparap.apps.public_holidays.service.HolidayService
import gparap.apps.public_holidays.service.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

object HolidayRepository {
    private var holidays: MutableLiveData<List<HolidayModel>> = MutableLiveData()

    /**
     * Makes an api request using Retrofit client and gets all public holidays for a given country.
     */
    fun requestPublicHolidays(countryCode: String): MutableLiveData<List<HolidayModel>>? {
        //create Retrofit client
        val apiService = RetrofitClient.instance()!!.create<HolidayService>()

        //create client request
        val apiResponse = apiService.getPublicHolidays(countryCode)
        apiResponse.enqueue(object : Callback<List<HolidayModel>> {
            override fun onResponse(
                call: Call<List<HolidayModel>>,
                response: Response<List<HolidayModel>>
            ) {
                //if we have a response
                if (response.isSuccessful && response.body() != null) {
                    holidays.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<HolidayModel>>, t: Throwable) {
                print(t.message)
            }

        })
        return holidays
    }
}