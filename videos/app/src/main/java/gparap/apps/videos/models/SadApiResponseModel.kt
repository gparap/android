package gparap.apps.videos.models

import com.google.gson.annotations.SerializedName

/**
 * Model class for Youtube Search and Download API response.
 */
data class SadApiResponseModel(
    @SerializedName("contents")
    val contents: List<SadApiVideoContainerModel>,
)
