package gparap.apps.converter_binary

/**
 * Converts from binary to text and vice versa.
 * Created by gparap on 2021-02-19.
 */
class Converter {

    /**
     * Break the text string to chars
     *  and converts them to binary using their byte value
     */
    fun convertTextToBinary(textString: String) : String {
        val stringBuffer = StringBuffer()

        val charArray: CharArray = textString.toCharArray()
        for (char: Char in charArray) {
            val ascii: Short = char.toShort()
            val binary: String = ascii.toString(2)
            stringBuffer.append(String.format("%08d", binary.toInt()))  //restores missing zeros in
        }                                                               // the front from conversion
        return stringBuffer.toString()
    }

    /**
     * Makes substrings from the text string in steps of 8
     *  and converts them to char using their byte value
     */
    fun convertBinaryToText(binaryString: String): String {
        val stringBuffer = StringBuffer()

        val range = binaryString.length-1
        for (i in 0..range step 8) {
            val byte = Integer.parseInt(binaryString.substring(i, i+8), 2)//convert to byte
            val char: Char = byte.toChar()                                      //convert to char
            stringBuffer.append(char)
        }

        return stringBuffer.toString()
    }

    /**
     * Validates binary input
     *  -contains only "1" and/or "0"?
     *  -is length dividable by 8?
     */
    fun validateBinaryInput(binaryString: String): Boolean {
        //check if input consists only from "1" and "0"
        if (binaryString.contains(Regex("[23456789.,-[+]]"))) { return false }
        try { binaryString.toDouble() } catch (e:Exception) { return false }

        //check if input length is divisable with 8
        if (binaryString.length.rem(8) != 0) { return false }

        //validation passes
        return true
    }
}