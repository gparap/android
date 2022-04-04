package gparap.apps.horoscope.data

import com.google.gson.annotations.SerializedName

data class TranslationResponseDataModel(
    @SerializedName("translatedText")
    val translation: String,
)
