/*
 * Copyright 2024 gparap
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package gparap.apps.cipher_manager.utils

import org.bouncycastle.crypto.engines.Salsa20Engine
import org.bouncycastle.crypto.params.KeyParameter
import org.bouncycastle.crypto.params.ParametersWithIV
import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object Utils {

    /* Encrypts the input value based on the AES (Advanced Encryption Standard) algorithm. */
    fun encryptWithAES(publicKey: ByteArray, inputText: String) : String {
        //get a Cipher instance to implement the specified transformation
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")

        //create a secret key from the byte array input
        val secretKey = SecretKeySpec(publicKey, "AES")

        //get the Initialization Vector array and create an IvParameterSpec object
        val iv = ByteArray(cipher.blockSize)
        val ivParams = IvParameterSpec(iv)

        //setup the cipher for encryption
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParams)

        //encrypt the byte array
        val encryptedBytes = cipher.doFinal(inputText.toByteArray())

        //return the encrypted byte array into a string using the Base64 encoding scheme
        return Base64.getEncoder().encodeToString(iv + encryptedBytes)
    }

    /* Decrypts the text input value based on the AES (Advanced Encryption Standard) algorithm. */
    fun decryptWithAES(publicKey: ByteArray, inputText: String) : String {
        //get a Cipher instance to implement the specified transformation
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")

        //decode the Base64-encoded input string into an array of bytes
        val decodedBytes: ByteArray = Base64.getDecoder().decode(inputText)

        //extract the Initialization Vector array from the decoded bytes
        val ivSize = cipher.blockSize
        val iv = decodedBytes.copyOfRange(0, ivSize)

        //extract the encrypted bytes
        val encryptedBytes = decodedBytes.copyOfRange(ivSize, decodedBytes.size)

        //setup the cipher for decryption
        val secretKey = SecretKeySpec(publicKey, "AES")
        val ivParams = IvParameterSpec(iv)
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParams)

        //decrypt the byte array
        val decryptedBytes = cipher.doFinal(encryptedBytes)

        //return the decrypted byte array into a string
        return String(decryptedBytes)
    }

    /* Encrypts the input value based on the Salsa20 algorithm. */
    fun encryptWithSalsa20(publicKey: ByteArray, inputText: String): String {
        //get the Cipher instance of Salsa20
        val cipher = Salsa20Engine()

        //setup the cipher for encryption and/or decryption //TODO: randomize Initialization Vector
        val iv = ByteArray(8) //use the default Initialization Vector of 8 bytes
        val keyParam = KeyParameter(publicKey)
        val params = ParametersWithIV(keyParam, iv)
        cipher.init(true, params)

        //encrypt the byte array
        val inputBytes = inputText.toByteArray()
        val cipheredBytes = ByteArray(inputBytes.size)
        cipher.processBytes(inputBytes, 0, inputBytes.size, cipheredBytes, 0)

        //return the encrypted byte array into a string using the Base64 encoding scheme
        return Base64.getEncoder().encodeToString(cipheredBytes)
    }

    /* Decrypts the text input value based on the Salsa20 algorithm. */
    fun decryptWithSalsa20(publicKey: ByteArray, inputText: String): String {
        //get the Cipher instance of Salsa20
        val cipher = Salsa20Engine()

        //setup the cipher for encryption and/or decryption //TODO: randomize Initialization Vector
        val iv = ByteArray(8) // Default IV of 8 bytes
        val params = ParametersWithIV(KeyParameter(publicKey), iv)
        cipher.init(false, params)

        //decrypt the byte array
        val encryptedBytes = Base64.getDecoder().decode(inputText)
        val decryptedBytes = ByteArray(encryptedBytes.size)
        cipher.processBytes(encryptedBytes, 0, encryptedBytes.size, decryptedBytes, 0)

        //return the decrypted byte array into a string
        return String(decryptedBytes)
    }
}