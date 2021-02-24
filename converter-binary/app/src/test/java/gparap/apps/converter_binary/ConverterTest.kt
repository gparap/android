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

    @Test
    fun validateBinaryInput() {
        val correctInput = "011001110111000001100001011100100110000101110000"
        val errorInputIsNotBinary = "a01100111011100000110000101110010011000010111000"  //has "a" typed
        val errorInputMissingDigits = "01100111011100000110000101110010011000010111000" //last 0 is missing

        assertEquals("something is wrong, correct input not working?",
            converter?.validateBinaryInput(correctInput), true)

        assertEquals("input value is not binary",
            converter?.validateBinaryInput(errorInputIsNotBinary), false)

        assertEquals("missing digit(s)",
            converter?.validateBinaryInput(errorInputMissingDigits), false)
    }
}