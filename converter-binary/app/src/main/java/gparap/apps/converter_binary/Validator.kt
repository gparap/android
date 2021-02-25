package gparap.apps.converter_binary

/**
 * Validates binary input.
 * Created by gparap on 2021-02-25.
 */
class Validator {
    /**
     * Validates binary input.
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