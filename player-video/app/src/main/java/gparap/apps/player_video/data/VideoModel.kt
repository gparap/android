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
package gparap.apps.player_video.data

import android.net.Uri

/** Model class that describes a video. */
@Suppress("unused")
class VideoModel(
    val id: Int?,
    val creator: String?,
    val category: String?,
    val title: String?,
    val description: String?,
    val language: String?,
    val length: String?,
    val size: String?,
    val year: Int?,
    val tags: String?,
    val uri: Uri?,
    val path: String?,
) {
    /** Builder class for the video model data. */
    data class Builder(
        var id: Int? = null,
        var creator: String? = null,
        var category: String? = null,
        var title: String? = null,
        var description: String? = null,
        var language: String? = null,
        var length: String? = null,
        var size: String? = null,
        var year: Int? = null,
        var tags: String? = null,
        var uri: Uri? = null,
        var path: String? = null,
    ) {
        fun id(id: Int) = apply { this.id = id }
        fun creator(creator: String) = apply { this.creator = creator }
        fun category(category: String) = apply { this.category = category }
        fun title(title: String) = apply { this.title = title }
        fun description(description: String) = apply { this.description = description }
        fun language(language: String) = apply { this.language = language }
        fun length(length: String) = apply { this.length = length }
        fun size(size: String) = apply { this.size = size }
        fun year(year: Int) = apply { this.year = year }
        fun tags(tags: String) = apply { this.tags = tags }
        fun uri(uri: Uri) = apply { this.uri = uri }
        fun path(path: String) = apply { this.path = path }

        fun build(): VideoModel {
            return VideoModel(
                this.id,
                this.creator,
                this.category,
                this.title,
                this.description,
                this.language,
                this.length,
                this.size,
                this.year,
                this.tags,
                this.uri,
                this.path
            )
        }
    }
}