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

data class RecipeModel(
    @SerializedName("id")
    val id: String?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("descr")
    val description: String?,

    @SerializedName("keywords")
    val keywords: String?,

    @SerializedName("img")
    val imageUrl: String?,

    @SerializedName("license")
    val imageAttribution: String?,

    @SerializedName("video")
    val videoUrl: String?,

    @SerializedName("category")
    val category: String?,

    @SerializedName("servings")
    val servings: String?,

    @SerializedName("prep_time")
    val preparationTime: String?,

    @SerializedName("difficulty")
    val difficulty: String?,

    @SerializedName("ingreds")
    val ingredients: String?,

    @SerializedName("steps")
    val preparationSteps: String?,

    @SerializedName("notes")
    val preparationNotes: String?,
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
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(keywords)
        parcel.writeString(imageUrl)
        parcel.writeString(imageAttribution)
        parcel.writeString(videoUrl)
        parcel.writeString(category)
        parcel.writeString(servings)
        parcel.writeString(preparationTime)
        parcel.writeString(difficulty)
        parcel.writeString(ingredients)
        parcel.writeString(preparationSteps)
        parcel.writeString(preparationNotes)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RecipeModel> {
        override fun createFromParcel(parcel: Parcel): RecipeModel {
            return RecipeModel(parcel)
        }

        override fun newArray(size: Int): Array<RecipeModel?> {
            return arrayOfNulls(size)
        }
    }
}