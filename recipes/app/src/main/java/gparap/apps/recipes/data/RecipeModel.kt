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
import java.util.ArrayList

data class RecipeModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String?,
    @SerializedName("descr")
    val description: String?,
    @SerializedName("keywords")
    val keywords: String?,
    @SerializedName("placeholder_r0")
    val placeholder0: String?,
    @SerializedName("placeholder_r1")
    val placeholder1: String?,

    @SerializedName("img")
    val image: ArrayList<RecipeImageModel>?,

    @SerializedName("info")
    val information: ArrayList<RecipeInformationModel>?,

    @SerializedName("ingreds")
    val ingredients: ArrayList<RecipeIngredientsModel>?,

    @SerializedName("steps")
    val preparationSteps: ArrayList<RecipeStepsModel>?,

    @SerializedName("notes")
    val preparationNotes: ArrayList<RecipeNotesModel>?,
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.createTypedArrayList(RecipeImageModel),
        parcel.createTypedArrayList(RecipeInformationModel),
        parcel.createTypedArrayList(RecipeIngredientsModel),
        parcel.createTypedArrayList(RecipeStepsModel),
        parcel.createTypedArrayList(RecipeNotesModel)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(keywords)
        parcel.writeString(placeholder0)
        parcel.writeString(placeholder1)
        parcel.writeTypedList(image)
        parcel.writeTypedList(information)
        parcel.writeTypedList(ingredients)
        parcel.writeTypedList(preparationSteps)
        parcel.writeTypedList(preparationNotes)
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
