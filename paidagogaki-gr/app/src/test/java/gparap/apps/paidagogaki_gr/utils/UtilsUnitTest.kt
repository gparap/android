package gparap.apps.paidagogaki_gr.utils

import org.junit.Test

class UtilsUnitTest {
    @Test
    fun fixUnicodeChars_isCorrect() {
        var inputString = "&#34;&#36;&#38;&#169;&#697;&#698;&#699;&#700;&#701;&#702;&#038;&#0038;"
        var expectedString = "\u0022\u0024\u0026\u00A9\u02B9\u02BA\u02BB\u02BC\u02BD\u02BE\u0026\u0026"
        var actualString = Utils.fixUnicodeChars(inputString)
        assert(expectedString == actualString).apply { println("Row #1 OK") }

        inputString = "&#703;&#706;&#707;&#712;&#713;&#714;&#715;&#716;&#717;&#720;&#034;&#0034;"
        expectedString = "\u02BF\u02C2\u02C3\u02C8\u02C9\u02CA\u02CB\u02CC\u02CD\u02D0\u0022\u0022"
        actualString = Utils.fixUnicodeChars(inputString)
        assert(expectedString == actualString).apply { println("Row #2 OK") }

        inputString = "&#722;&#727;&#732;&#733;&#750;&#760;&#768;&#769;&#770;&#771;&#036;&#0036;"
        expectedString = "\u02D2\u02D7\u02DC\u02DD\u02EE\u02F8\u0300\u0301\u0302\u0303\u0024\u0024"
        actualString = Utils.fixUnicodeChars(inputString)
        assert(expectedString == actualString).apply { println("Row #3 OK") }

        inputString = "&#779;&#781;&#782;&#783;&#786;&#787;&#788;&#789;&#795;&#800;&#803;&#804;&#806;&#820;&#821;"
        expectedString = "\u030B\u030D\u030E\u030F\u0312\u0313\u0314\u0315\u031B\u0320\u0323\u0324\u0326\u0334\u0335"
        actualString = Utils.fixUnicodeChars(inputString)
        assert(expectedString == actualString).apply { println("Row #4 OK") }

        inputString = "&#822;&#823;&#824;&#825;&#832;&#833;&#834;&#835;&#863;&#884;&#885;&#894;&#900;&#902;&#904;&#905;&#906;&#908;&#910;&#911;&#912;&#8211;&#8220;&#8221;&#8226;&#8230;&#8259;&#8364;&#8729;"
        expectedString = "\u0336\u0337\u0338\u0339\u0340\u0341\u0342\u0343\u035F\u0374\u0375\u037E\u0384\u0386\u0388\u0389\u038A\u038C\u038E\u038F\u0390\u2013\u201C\u201D\u2022\u2026\u2043\u20AC\u2219"
        actualString = Utils.fixUnicodeChars(inputString)
        assert(expectedString == actualString).apply { println("Row #5 OK") }
    }
}