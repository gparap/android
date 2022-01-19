package gparap.apps.videos.models

import com.google.gson.annotations.SerializedName

/**
 * Model class for Youtube Search and Download API video.
 */
data class SadApiVideoModel(
    @SerializedName("thumbnails")
    val thumbnails: List<SadApiThumbnailModel>,

    @SerializedName("title")
    val title: String,

    @SerializedName("videoId")
    val id: String,

    @SerializedName("channelName")
    val channel: String,

    @SerializedName("channelId")
    val channelId: String,

    @SerializedName("publishedTimeText")
    val date: String,

    @SerializedName("lengthText")
    val length: String,

    @SerializedName("viewCountText")
    val views: String,
)
