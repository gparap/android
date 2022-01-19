package gparap.apps.videos.models

import com.google.gson.annotations.SerializedName

/**
 * Model class for Youtube Search and Download API video's thumbnail.
 */
data class SadApiThumbnailModel(
    @SerializedName("width")
    val width: String,

    @SerializedName("height")
    val height: String,

    @SerializedName("url")
    val url: String,
)
