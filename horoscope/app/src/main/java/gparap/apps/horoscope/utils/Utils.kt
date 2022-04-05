package gparap.apps.horoscope.utils

import android.content.Context
import gparap.apps.horoscope.R
import java.util.*

object Utils {
    /**
     * Returns a drawable resource id based on the name of a zodiac sign
     */
    fun getZodiacSignDrawableResourceIdByName(context: Context, name: String): Int {
        return when (name) {
            //Aries
            context.resources.getString(R.string.text_zodiac_Aries) -> R.drawable.aries

            //Taurus
            context.resources.getString(R.string.text_zodiac_Taurus) -> R.drawable.taurus

            //Gemini
            context.resources.getString(R.string.text_zodiac_Gemini) -> R.drawable.gemini

            //Cancer
            context.resources.getString(R.string.text_zodiac_Cancer) -> R.drawable.cancer

            //Leo
            context.resources.getString(R.string.text_zodiac_Leo) -> R.drawable.leo

            //Virgo
            context.resources.getString(R.string.text_zodiac_Virgo) -> R.drawable.virgo

            //Libra
            context.resources.getString(R.string.text_zodiac_Libra) -> R.drawable.libra

            //Scorpio
            context.resources.getString(R.string.text_zodiac_Scorpio) -> R.drawable.scorpio

            //Sagittarius
            context.resources.getString(R.string.text_zodiac_Sagittarius) -> R.drawable.sagittarius

            //Capricorn
            context.resources.getString(R.string.text_zodiac_Capricorn) -> R.drawable.capricorn

            //Aquarius
            context.resources.getString(R.string.text_zodiac_Aquarius) -> R.drawable.aquarius

            //Pisces
            context.resources.getString(R.string.text_zodiac_Pisces) -> R.drawable.pisces

            //wtf
            else -> {
                -1
            }
        }
    }

    /**
     * Returns a string resource identifier based on the name of a zodiac sign
     */
    fun getZodiacSignDateRangeStringByName(context: Context, name: String): Int {
        return when (name) {
            //Aries
            context.resources.getString(R.string.text_zodiac_Aries) -> R.string.date_range_Aries

            //Taurus
            context.resources.getString(R.string.text_zodiac_Taurus) -> R.string.date_range_Taurus

            //Gemini
            context.resources.getString(R.string.text_zodiac_Gemini) -> R.string.date_range_Gemini

            //Cancer
            context.resources.getString(R.string.text_zodiac_Cancer) -> R.string.date_range_Cancer

            //Leo
            context.resources.getString(R.string.text_zodiac_Leo) -> R.string.date_range_Leo

            //Virgo
            context.resources.getString(R.string.text_zodiac_Virgo) -> R.string.date_range_Virgo

            //Libra
            context.resources.getString(R.string.text_zodiac_Libra) -> R.string.date_range_Libra

            //Scorpio
            context.resources.getString(R.string.text_zodiac_Scorpio) -> R.string.date_range_Scorpio

            //Sagittarius
            context.resources.getString(R.string.text_zodiac_Sagittarius) -> R.string.date_range_Sagittarius

            //Capricorn
            context.resources.getString(R.string.text_zodiac_Capricorn) -> R.string.date_range_Capricorn

            //Aquarius
            context.resources.getString(R.string.text_zodiac_Aquarius) -> R.string.date_range_Aquarius

            //Pisces
            context.resources.getString(R.string.text_zodiac_Pisces) -> R.string.date_range_Pisces

            //wtf
            else -> {
                -1
            }
        }
    }

    /**
     * Returns the requested day for the horoscope as a string based on the selected menu item id
     */
    fun getRequestedDayById(context: Context, itemId: Int): String {
        return when (itemId) {
            R.id.menu_item_today -> context.resources.getString(R.string.text_today)
            R.id.menu_item_tomorrow -> context.resources.getString(R.string.text_tomorrow)
            R.id.menu_item_yesterday -> context.resources.getString(R.string.text_yesterday)

            //wtf
            else -> {
                ""
            }
        }
    }

    /**
     * Checks the user's locale and decides if the horoscope should be translated or not
     *
     * (default language of horoscope api is "en")
     */
    fun isTranslationNeeded() : Boolean {
        return Locale.getDefault().language != Locale.ENGLISH.language
    }

    /**
     * Returns the language pair for translation in the form of "en|<translation_language>"
     */
    fun getLanguagePair() : String {
        return Locale.ENGLISH.language
            .plus("|")
            .plus(Locale.getDefault().language)
    }
}