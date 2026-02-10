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
package gparap.apps.horoscope.data

import com.google.gson.annotations.SerializedName

/**
 * Data model for an horoscope request based on a zodiac sign.
 */
data class HoroscopeModel(
    @SerializedName("date_range")
    val dateRange: String,

    @SerializedName("current_date")
    val date: String,

    @SerializedName("description")
    val horoscope: String,

    @SerializedName("lucky_number")
    val luckyNumber: String,

    @SerializedName("lucky_time")
    val luckyTime: String,

    @SerializedName("color")
    val luckyColor: String,

    @SerializedName("compatibility")
    val pair: String,

    @SerializedName("mood")
    val mood: String,
)
