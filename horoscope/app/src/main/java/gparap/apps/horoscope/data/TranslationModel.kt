package gparap.apps.horoscope.data

import com.google.gson.annotations.SerializedName

/**
 * Data model for a memory translation based on an horoscope.
 */
data class TranslationModel(
    @SerializedName("responseData")
    val response: TranslationResponseDataModel,

    @SerializedName("quotaFinished")
    val isQuotaFinished: Boolean,

    @SerializedName("responseStatus")
    val httpStatus: Int,
)
