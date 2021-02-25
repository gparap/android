package gparap.apps.converter_binary

import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import kotlin.properties.Delegates

/**
 * Created by gparap on 2021-02-25.
 */
class ParserTest {
    private var parser: Parser? = null
    private lateinit var input: String
    private lateinit var expected :String
    private lateinit var actual :String

    @Before
    fun setUp() {
        parser = Parser()
    }

    @Test
    fun parseBinary() {
        input = "000000001111111100000000"
        expected = "00000000 11111111 00000000"
        actual = parser?.parseBinary(input)!!
        assertEquals("parsing output is wrong", expected, actual)
    }

    @Test
    fun unparseBinary() {
        input = "00000000 11111111 00000000"
        expected = "000000001111111100000000"
        actual = parser?.unparseBinary(input)!!
        assertEquals("unparsing output is wrong", expected, actual)
    }
}