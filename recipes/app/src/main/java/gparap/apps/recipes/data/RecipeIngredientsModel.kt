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

data class RecipeIngredientsModel(
    @SerializedName("i_1")
    val ingredient1: String?,
    @SerializedName("i_2")
    val ingredient2: String?,
    @SerializedName("i_3")
    val ingredient3: String?,
    @SerializedName("i_4")
    val ingredient4: String?,
    @SerializedName("i_5")
    val ingredient5: String?,
    @SerializedName("i_6")
    val ingredient6: String?,
    @SerializedName("i_7")
    val ingredient7: String?,
    @SerializedName("i_8")
    val ingredient8: String?,
    @SerializedName("i_9")
    val ingredient9: String?,
    @SerializedName("i_0")
    val ingredient10: String?,
    @SerializedName("i_x1")
    val ingredient11: String?,
    @SerializedName("i_x2")
    val ingredient12: String?,
    @SerializedName("i_x3")
    val ingredient13: String?,
    @SerializedName("i_x4")
    val ingredient14: String?,
    @SerializedName("i_x5")
    val ingredient15: String?,
    @SerializedName("ph_i_x6")
    val placeholder1: String?,
    @SerializedName("ph_i_x7")
    val placeholder2: String?,
    @SerializedName("ph_i_x8")
    val placeholder3: String?,
    @SerializedName("ph_i_x9")
    val placeholder4: String?,
    @SerializedName("ph_i_x0")
    val placeholder5: String?,
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(ingredient1)
        parcel.writeString(ingredient2)
        parcel.writeString(ingredient3)
        parcel.writeString(ingredient4)
        parcel.writeString(ingredient5)
        parcel.writeString(ingredient6)
        parcel.writeString(ingredient7)
        parcel.writeString(ingredient8)
        parcel.writeString(ingredient9)
        parcel.writeString(ingredient10)
        parcel.writeString(ingredient11)
        parcel.writeString(ingredient12)
        parcel.writeString(ingredient13)
        parcel.writeString(ingredient14)
        parcel.writeString(ingredient15)
        parcel.writeString(placeholder1)
        parcel.writeString(placeholder2)
        parcel.writeString(placeholder3)
        parcel.writeString(placeholder4)
        parcel.writeString(placeholder5)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RecipeIngredientsModel> {
        override fun createFromParcel(parcel: Parcel): RecipeIngredientsModel {
            return RecipeIngredientsModel(parcel)
        }

        override fun newArray(size: Int): Array<RecipeIngredientsModel?> {
            return arrayOfNulls(size)
        }
    }
}
