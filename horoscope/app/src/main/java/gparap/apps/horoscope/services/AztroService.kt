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
package gparap.apps.horoscope.services

import gparap.apps.horoscope.data.HoroscopeModel
import retrofit2.Call
import retrofit2.http.*

/**
 * Web service for receiving horoscope based on zodiac sign and requested day.
 * Results are in JSON format.
 */
interface AztroService {

    /** Get the zodiac sign based today's horoscope */
    @Headers(
        "x-rapidapi-host: sameer-kumar-aztro-v1.p.rapidapi.com",
        "x-rapidapi-key: MY_API_KEY"
    )
    @POST("/?day=today")
    fun getHoroscopeForToday(@Query("sign") sign: String): Call<HoroscopeModel>

    /** Get the zodiac sign based tomorrow's horoscope */
    @Headers(
        "x-rapidapi-host: sameer-kumar-aztro-v1.p.rapidapi.com",
        "x-rapidapi-key: MY_API_KEY"
    )
    @POST("/?day=tomorrow")
    fun getHoroscopeForTomorrow(@Query("sign") sign: String): Call<HoroscopeModel>

    /** Get the zodiac sign based yesterday's horoscope */
    @Headers(
        "x-rapidapi-host: sameer-kumar-aztro-v1.p.rapidapi.com",
        "x-rapidapi-key: MY_API_KEY"
    )
    @POST("/?day=yesterday")
    fun getHoroscopeForYesterday(@Query("sign") sign: String): Call<HoroscopeModel>
}