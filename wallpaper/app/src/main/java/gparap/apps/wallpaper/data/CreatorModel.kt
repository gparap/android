package gparap.apps.wallpaper.data

import com.google.gson.annotations.SerializedName

/**
 * Model class for the creator of the wallpaper image.
 */
data class CreatorModel(
    @SerializedName("name")
    val name: String,

    @SerializedName("link")
    val website: String,
)
