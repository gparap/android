/*
 * Copyright 2023 gparap
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
package gparap.apps.recipes.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class RecipeImageModel(
    @SerializedName("url")
    val url: String?,
    @SerializedName("license")
    val license: String?,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(url)
        parcel.writeString(license)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RecipeImageModel> {
        override fun createFromParcel(parcel: Parcel): RecipeImageModel {
            return RecipeImageModel(parcel)
        }

        override fun newArray(size: Int): Array<RecipeImageModel?> {
            return arrayOfNulls(size)
        }
    }
}
