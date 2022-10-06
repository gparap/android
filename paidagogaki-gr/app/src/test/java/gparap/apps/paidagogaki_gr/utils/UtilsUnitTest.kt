package gparap.apps.paidagogaki_gr.utils

import org.junit.Test

class UtilsUnitTest {
    @Test
    fun fixUnicodeChars_isCorrect() {
        val inputString = "Hello &#8211; &#8220; World! &#8221;"
        val expectedString = "Hello \u2013 \u201C World! \u201D"
        val actualString = Utils.fixUnicodeChars(inputString)
        assert(expectedString == actualString)
    }
}