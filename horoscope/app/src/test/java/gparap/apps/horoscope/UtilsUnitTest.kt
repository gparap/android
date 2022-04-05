package gparap.apps.horoscope

import gparap.apps.horoscope.utils.AppConstants
import gparap.apps.horoscope.utils.Utils
import org.junit.Test

import org.junit.Assert.*
import java.net.URLDecoder
import java.util.*

class UtilsUnitTest {
    @Test
    fun isTranslationNeeded_isCorrect() {
        Locale.setDefault(Locale("el"))

        val isTranlationNeededExpected = true
        val isTranlationNeededActual = Utils.isTranslationNeeded()

        assertEquals(isTranlationNeededExpected, isTranlationNeededActual)
    }

    @Test
    fun getLanguagePair_isCorrect() {
        Locale.setDefault(Locale("el"))

        val languagePairExpected = "en|el"
        val languagePairActual = Utils.getLanguagePair()

        assertEquals(languagePairExpected, languagePairActual)
    }

    @Test
    fun test() {
        //setup query data
        val textToTranslate = "Hello World!"
        Locale.setDefault(Locale("it"))
        val queryDataExpected: MutableMap<String, String> = HashMap()
        queryDataExpected[AppConstants.MY_MEMORY_QUERY_1] = "Hello World!"
        queryDataExpected[AppConstants.MY_MEMORY_QUERY_2] = "en|it"

        //get query data from request and decode translation
        val queryDataActual = Utils.getTranslationRequestQueryData(textToTranslate)
        queryDataActual[AppConstants.MY_MEMORY_QUERY_1] =
            URLDecoder.decode(
                queryDataActual[AppConstants.MY_MEMORY_QUERY_1], AppConstants.DEFAULT_CHAR_ENCODING
            )

        assertEquals(queryDataExpected, queryDataActual)
    }
}