package gparap.apps.paidagogaki_gr.utils

object Utils {
    /** Fix some issues with unicode characters coming from wordpress service */
    fun fixUnicodeChars(string: String): String {
        return string
            .replace("&#8211;", "\u2013")
            .replace("&#8220;", "\u201C")
            .replace("&#8221;", "\u201D")
    }
}