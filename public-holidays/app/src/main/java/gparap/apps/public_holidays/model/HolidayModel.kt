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
package gparap.apps.public_holidays.model

import com.google.gson.annotations.SerializedName

data class HolidayModel(
    @SerializedName("countryCode")
    val countryCode: String,

    @SerializedName("date")
    val date: String,

    @SerializedName("localName")
    val localName: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("fixed")
    val isDateFixed: Boolean,

    @SerializedName("global")
    val isGlobal: Boolean,

    @SerializedName("launchYear")
    val launchYear: Int?
)
