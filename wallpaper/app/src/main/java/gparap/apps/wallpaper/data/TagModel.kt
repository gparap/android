package gparap.apps.wallpaper.data

import com.google.gson.annotations.SerializedName

/**
 * Model class for a search filter tag.
 */
data class TagModel(
    @SerializedName("base")
    val base: String,

    @SerializedName("extra")
    val extra: String,
)
