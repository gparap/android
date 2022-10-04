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
package gparap.apps.music.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SongResponseModel(
    @SerializedName("tags")
    @Expose
    val tags: TagModel,

    @SerializedName("category")
    @Expose
    val category: CategoryModel,

    @SerializedName("song_information")
    @Expose
    val songInfo: SongModel,

    @SerializedName("file_information")
    @Expose
    val fileInfo: FileModel,

    @SerializedName("links")
    @Expose
    val urls: HyperlinkModel,

    @SerializedName("licence")
    @Expose
    val licence: LicenceModel,
)