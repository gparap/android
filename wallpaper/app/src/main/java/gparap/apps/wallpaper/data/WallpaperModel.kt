package gparap.apps.wallpaper.data

import com.google.gson.annotations.SerializedName

/**
 * Model class for a wallpaper (includes Creator and Tag models).
 */
data class WallpaperModel(
    @SerializedName("id")
    val id: String,

    @SerializedName("url")
    val imageUrl: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("sort")
    val sortingBy: String,

    @SerializedName("category")
    val category: String,

    @SerializedName("tags")
    val filterTags: TagModel,

    @SerializedName("artist")
    val creator: CreatorModel,
)
