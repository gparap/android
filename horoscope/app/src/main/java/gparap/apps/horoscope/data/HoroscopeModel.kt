package gparap.apps.horoscope.data

import com.google.gson.annotations.SerializedName

/**
 * Data model for an horoscope request based on a zodiac sign.
 */
data class HoroscopeModel(
    @SerializedName("date_range")
    val dateRange: String,

    @SerializedName("current_date")
    val date: String,

    @SerializedName("description")
    val horoscope: String,

    @SerializedName("lucky_number")
    val luckyNumber: String,

    @SerializedName("lucky_time")
    val luckyTime: String,

    @SerializedName("color")
    val luckyColor: String,

    @SerializedName("compatibility")
    val pair: String,

    @SerializedName("mood")
    val mood: String,
)
