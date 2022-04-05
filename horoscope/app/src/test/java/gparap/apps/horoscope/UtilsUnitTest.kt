package gparap.apps.horoscope

import gparap.apps.horoscope.utils.Utils
import org.junit.Test

import org.junit.Assert.*
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
}