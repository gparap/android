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

import gparap.apps.horoscope.data.TranslationModel
import retrofit2.Call
import retrofit2.http.*

/**
 * Web service for translating horoscope based on Translation Memory technology.
 * Results are in JSON format.
 */
interface MyMemoryService {
    /** Get horoscope translated */
    @Headers(
        "x-rapidapi-host: translated-mymemory---translation-memory.p.rapidapi.com",
        "x-rapidapi-key: MY_API_KEY"
    )
    @GET("/api/get?")
    fun getMemoryTranslation(@QueryMap queryData: MutableMap<String, String>): Call<TranslationModel>
}