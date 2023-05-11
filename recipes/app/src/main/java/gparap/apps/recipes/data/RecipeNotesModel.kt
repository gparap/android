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

data class RecipeNotesModel(
    @SerializedName("n_1")
    val note1: String?,
    @SerializedName("n_2")
    val note2: String?,
    @SerializedName("n_3")
    val note3: String?,
    @SerializedName("n_4")
    val note4: String?,
    @SerializedName("n_5")
    val note5: String?,
    @SerializedName("n_6")
    val note6: String?,
    @SerializedName("n_7")
    val note7: String?,
    @SerializedName("n_8")
    val note8: String?,
    @SerializedName("n_9")
    val note9: String?,
    @SerializedName("ph_n_x0")
    val placeholder0: String?,
    @SerializedName("ph_n_x1")
    val placeholder1: String?,
    @SerializedName("ph_n_x2")
    val placeholder2: String?,
) : Parcelable {
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
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(note1)
        parcel.writeString(note2)
        parcel.writeString(note3)
        parcel.writeString(note4)
        parcel.writeString(note5)
        parcel.writeString(note6)
        parcel.writeString(note7)
        parcel.writeString(note8)
        parcel.writeString(note9)
        parcel.writeString(placeholder0)
        parcel.writeString(placeholder1)
        parcel.writeString(placeholder2)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RecipeNotesModel> {
        override fun createFromParcel(parcel: Parcel): RecipeNotesModel {
            return RecipeNotesModel(parcel)
        }

        override fun newArray(size: Int): Array<RecipeNotesModel?> {
            return arrayOfNulls(size)
        }
    }
}
