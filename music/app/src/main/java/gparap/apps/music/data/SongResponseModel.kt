/*
 * Copyright (c) 2023 gparap
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
package gparap.apps.music.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SongResponseModel(
    @SerializedName("attributes")
    @Expose
    val attributes: List<AttributeModel>,

    @SerializedName("category")
    @Expose
    val category: List<CategoryModel>,

    @SerializedName("song_information")
    @Expose
    val songInfo: List<SongModel>,

    @SerializedName("file_information")
    @Expose
    val fileInfo: List<FileModel>,

    @SerializedName("links")
    @Expose
    val urls: List<HyperlinkModel>,

    @SerializedName("licence")
    @Expose
    val licence: List<LicenceModel>,
)