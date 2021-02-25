package gparap.apps.converter_binary

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Created by gparap on 2021-02-23.
 */
class ConverterTest {
    var converter: Converter? = null

    @Before
    fun setUp() {
        converter = Converter()
    }

    @Test
    fun convertTextToBinary() {
        val expected = "011001110111000001100001011100100110000101110000"
        val actual = converter?.convertTextToBinary("gparap")
        assertEquals(expected, actual)
    }

    @Test
    fun convertBinaryToText() {
        val expected = "gparap"
        val actual = converter?.convertBinaryToText("011001110111000001100001011100100110000101110000")
        assertEquals(expected, actual)
    }
}