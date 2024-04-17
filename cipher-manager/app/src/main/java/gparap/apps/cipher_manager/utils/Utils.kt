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

import java.security.KeyFactory
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object Utils {

    /* Encrypts the input value based on the AES (Advanced Encryption Standard) algorithm. */
    fun encryptWithAES(publicKey: ByteArray, inputText: String): String {
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
    fun decryptWithAES(publicKey: ByteArray, inputText: String): String {
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

    /* Encrypts the input value based on the ARC4 (Rivest Cipher 4) algorithm. */
    fun encryptWithARC4(publicKey: ByteArray, inputText: String): String {
        //get a Cipher instance to implement the specified transformation
        val cipher = Cipher.getInstance("RC4")

        //create a secret key from the byte array input
        val secretKey = SecretKeySpec(publicKey, "RC4")

        //setup the cipher for encryption
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)

        //encrypt the byte array
        val encryptedBytes = cipher.doFinal(inputText.toByteArray())

        //return the encrypted byte array into a string using the Base64 encoding scheme
        return Base64.getEncoder().encodeToString(encryptedBytes)
    }

    /* Decrypts the text input value based on the ARC4 (Rivest Cipher 4) algorithm. */
    fun decryptWithARC4(publicKey: ByteArray, inputText: String): String {
        //get a Cipher instance to implement the specified transformation
        val cipher = Cipher.getInstance("RC4")

        //decode the Base64-encoded input string into an array of bytes
        val decodedBytes: ByteArray = Base64.getDecoder().decode(inputText)

        //setup the cipher for decryption
        val secretKey = SecretKeySpec(publicKey, "RC4")
        cipher.init(Cipher.DECRYPT_MODE, secretKey)

        //decrypt the byte array
        val decryptedBytes = cipher.doFinal(decodedBytes)

        //return the decrypted byte array into a string
        return String(decryptedBytes)
    }

    /* Encrypts the input value based on the Blowfish algorithm. */
    fun encryptWithBlowfish(publicKey: ByteArray, inputText: String): String {
        //get a Cipher instance to implement the specified transformation
        val cipher = Cipher.getInstance("Blowfish/CBC/PKCS5Padding")

        //create a secret key from the byte array input
        val secretKey = SecretKeySpec(publicKey, "Blowfish")

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

    /* Decrypts the text input value based on the Blowfish. */
    fun decryptWithBlowfish(publicKey: ByteArray, inputText: String): String {
        //get a Cipher instance to implement the specified transformation
        val cipher = Cipher.getInstance("Blowfish/CBC/PKCS5Padding")

        //decode the Base64-encoded input string into an array of bytes
        val decodedBytes: ByteArray = Base64.getDecoder().decode(inputText)

        //extract the Initialization Vector array from the decoded bytes
        val ivSize = cipher.blockSize
        val iv = decodedBytes.copyOfRange(0, ivSize)

        //extract the encrypted bytes
        val encryptedBytes = decodedBytes.copyOfRange(ivSize, decodedBytes.size)

        //setup the cipher for decryption
        val secretKey = SecretKeySpec(publicKey, "Blowfish")
        val ivParams = IvParameterSpec(iv)
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParams)

        //decrypt the byte array
        val decryptedBytes = cipher.doFinal(encryptedBytes)

        //return the decrypted byte array into a string
        return String(decryptedBytes)
    }

    /* Encrypts the input value based on the DES (Data Encryption Standard) algorithm. */
    fun encryptWithDES(publicKey: ByteArray, inputText: String): String {
        //get a Cipher instance to implement the specified transformation
        val cipher = Cipher.getInstance("DES/CBC/PKCS5Padding")

        //create a secret key from the byte array input
        val secretKey = SecretKeySpec(publicKey, "DES")

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

    /* Decrypts the text input value based on the DES (Data Encryption Standard) algorithm. */
    fun decryptWithDES(publicKey: ByteArray, inputText: String): String {
        //get a Cipher instance to implement the specified transformation
        val cipher = Cipher.getInstance("DES/CBC/PKCS5Padding")

        //decode the Base64-encoded input string into an array of bytes
        val decodedBytes: ByteArray = Base64.getDecoder().decode(inputText)

        //extract the Initialization Vector array from the decoded bytes
        val ivSize = cipher.blockSize
        val iv = decodedBytes.copyOfRange(0, ivSize)

        //extract the encrypted bytes
        val encryptedBytes = decodedBytes.copyOfRange(ivSize, decodedBytes.size)

        //setup the cipher for decryption
        val secretKey = SecretKeySpec(publicKey, "DES")
        val ivParams = IvParameterSpec(iv)
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParams)

        //decrypt the byte array
        val decryptedBytes = cipher.doFinal(encryptedBytes)

        //return the decrypted byte array into a string
        return String(decryptedBytes)
    }

    /* Encrypts the input value based on the Triple DES (Data Encryption Standard) algorithm. */
    fun encryptWithTripleDES(publicKey: ByteArray, inputText: String): String {
        //get a Cipher instance to implement the specified transformation
        val cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding")

        //create a secret key from the byte array input
        val secretKey = SecretKeySpec(publicKey, "DESede")

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

    /* Decrypts the text input value based on the Triple DES (Data Encryption Standard) algorithm. */
    fun decryptWithTripleDES(publicKey: ByteArray, inputText: String): String {
        //get a Cipher instance to implement the specified transformation
        val cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding")

        //decode the Base64-encoded input string into an array of bytes
        val decodedBytes: ByteArray = Base64.getDecoder().decode(inputText)

        //extract the Initialization Vector array from the decoded bytes
        val ivSize = cipher.blockSize
        val iv = decodedBytes.copyOfRange(0, ivSize)

        //extract the encrypted bytes
        val encryptedBytes = decodedBytes.copyOfRange(ivSize, decodedBytes.size)

        //setup the cipher for decryption
        val secretKey = SecretKeySpec(publicKey, "DESede")
        val ivParams = IvParameterSpec(iv)
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParams)

        //decrypt the byte array
        val decryptedBytes = cipher.doFinal(encryptedBytes)

        //return the decrypted byte array into a string
        return String(decryptedBytes)
    }

    /* Encrypts the input value based on the RSA (Rivest–Shamir–Adleman) algorithm. */
    fun encryptWithRSA(key: String, inputText: String): String {
        //get the Cipher instance of RSA
        val cipher = Cipher.getInstance("RSA")

        //convert Base64 encoded public key string to a public key object
        val publicKeyBytes = Base64.getDecoder().decode(key)
        val keySpec = X509EncodedKeySpec(publicKeyBytes)
        val publicKey = KeyFactory.getInstance("RSA").generatePublic(keySpec)

        //encrypt the input text into a byte array
        cipher.init(Cipher.ENCRYPT_MODE, publicKey)
        val encryptedBytes = cipher.doFinal(inputText.toByteArray())

        //return the encrypted byte array into a string using the Base64 encoding scheme
        return Base64.getEncoder().encodeToString(encryptedBytes)
    }

    /* Decrypts the text input value based on the RSA (Rivest–Shamir–Adleman) algorithm. */
    fun decryptWithRSA(key: String, inputText: String): String {
        //get the Cipher instance of RSA
        val cipher = Cipher.getInstance("RSA")

        //convert Base64 encoded public key string to PrivateKey
        val privateKeyBytes = Base64.getDecoder().decode(key)
        val keySpec = PKCS8EncodedKeySpec(privateKeyBytes)
        val privateKey = KeyFactory.getInstance("RSA").generatePrivate(keySpec)

        //decrypt the input text into a byte array
        val encryptedBytes = Base64.getDecoder().decode(inputText)
        cipher.init(Cipher.DECRYPT_MODE, privateKey)
        val decryptedBytes = cipher.doFinal(encryptedBytes)

        //return the encrypted byte array into a string the using the UTF-8 character set
        return String(decryptedBytes)
    }

    /* Checks the validity of the public & private keys, based on the algorithm. */
    fun areSecretKeysValid(
        publicKey: String? = null,
        privateKey: String? = null,
        algorithm: Algorithm
    ): Pair<Boolean, String> {
        //1st property: valid or not
        //2nd property: any error message
        //3rd property: algorithm
        var validationResult = Pair(true, "")

        //public key must NOT be empty
        if (publicKey?.isEmpty() == true) {
            validationResult = Pair(false, "empty key")
            return validationResult
        }

        //validate the key length of each algorithm
        when (algorithm) {
            Algorithm.AES -> {
                if (publicKey?.length != 16 && publicKey?.length != 24 && publicKey?.length != 32) {
                    validationResult = Pair(false, "invalid key length: $algorithm")
                }
            }

            Algorithm.ARC4 -> {
                if (publicKey?.length!! < 5 || publicKey.length > 128) {
                    validationResult = Pair(false, "invalid key length: $algorithm")
                }
            }

            Algorithm.Blowfish -> {
                if (publicKey?.length!! > 56) {
                    validationResult = Pair(false, "invalid key length: $algorithm")
                }
            }

            Algorithm.DES -> {
                if (publicKey?.length != 8) {
                    validationResult = Pair(false, "invalid key length: $algorithm")
                }
            }

            Algorithm.DESede -> {
                if (publicKey?.length != 24) {
                    validationResult = Pair(false, "invalid key length: $algorithm")
                }
            }

            Algorithm.RSA -> {
                if (!secretKeysValidatorRSA(privateKey!!, publicKey!!)) {
                    validationResult = Pair(false, "invalid keys: $algorithm")
                }
            }
        }

        return validationResult
    }

    /* Validates the (inputted) secret keys of the RSA algorithm with a default message. */
    fun secretKeysValidatorRSA(privateKey: String, publicKey: String): Boolean {
        val textInput = "Hello World!"
        var encryptedValue = ""
        var decryptedValue = ""
        try {
            encryptedValue = encryptWithRSA(publicKey, textInput)
            decryptedValue = decryptWithRSA(privateKey, encryptedValue)
        }catch (e: Exception) {
            return false
        }
        return (textInput == decryptedValue)
    }
}