package gparap.apps.converter_binary

/**
 * Created by gparap on 2021-02-19.
 */
class Converter {

    /**
     * Break the text string to chars
     *  and converts them to binary using their byte value
     */
    fun convertTextToBinary(textString: String) : String {
        val stringBuffer: StringBuffer = StringBuffer()

        val charArray: CharArray = textString.toCharArray()
        for (char: Char in charArray) {
            val byte = char.toByte()
            val binary = byte.toString(2)
            stringBuffer.append(binary) //.append(" ") TODO: parse result
        }
        return stringBuffer.toString()
    }
}