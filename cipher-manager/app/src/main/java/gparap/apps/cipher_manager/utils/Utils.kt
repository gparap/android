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

import org.bouncycastle.crypto.AsymmetricCipherKeyPair
import org.bouncycastle.crypto.engines.ElGamalEngine
import org.bouncycastle.crypto.engines.Salsa20Engine
import org.bouncycastle.crypto.generators.ElGamalKeyPairGenerator
import org.bouncycastle.crypto.params.ElGamalKeyGenerationParameters
import org.bouncycastle.crypto.params.ElGamalKeyParameters
import org.bouncycastle.crypto.params.ElGamalParameters
import org.bouncycastle.crypto.params.KeyParameter
import org.bouncycastle.crypto.params.ParametersWithIV
import java.math.BigInteger
import java.security.KeyFactory
import java.security.SecureRandom
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

    /* Encrypts the input value based on the ElGamal algorithm. */
    fun encryptWithElGamal(key: ElGamalKeyParameters, inputText: String): String {
        //initialize the ElGamal engine for encryption
        val engine = ElGamalEngine()
        engine.init(true, key)

        //encrypt into a byte array
        val inputBytes = inputText.toByteArray()
        val encodedBytes = engine.processBlock(inputBytes, 0, inputBytes.size)

        //return the encrypted byte array into a string using the Base64 encoding scheme
        return Base64.getEncoder().encodeToString(encodedBytes)
    }

    /* Decrypts the text input value based on the ElGamal algorithm. */
    fun decryptWithElGamal(key: ElGamalKeyParameters, inputText: String): String {
        //initialize the ElGamal engine for decryption
        val engine = ElGamalEngine()
        engine.init(false, key)

        //convert Base64 encoded cipher text string to a byte array
        val encryptedBytes = Base64.getDecoder().decode(inputText)

        //encrypt
        val decryptedBytes: ByteArray = engine.processBlock(encryptedBytes, 0, encryptedBytes.size)

        //return the decrypted byte array into a string
        return String(decryptedBytes)
    }

    /* Generates ElGamal cipher key parameters. */
    fun generateElGamalKeys() : AsymmetricCipherKeyPair  {
        //the prime modulus "p" and generator "g" of the algorithm TODO: create a keygen for p and g
        val prime = BigInteger("29402321462549794870346923930564195495056844808102479517168551703935695144527989230942403020984375357298740354856025275944700803888506019799419462561321310286311317948418891892921641523409032696495572114082942626562209992190127682280489794835835057746380776743979767755692323840044847020340844631586205886281")
        val generator = BigInteger("3")

        //generate key pair parameters
        val random = SecureRandom()
        val elGamalParams = ElGamalParameters(prime, generator)
        val keyGenParams = ElGamalKeyGenerationParameters(random, elGamalParams)

        //initialize the key pair generator
        val keyPairGenerator = ElGamalKeyPairGenerator()
        keyPairGenerator.init(keyGenParams)

        //return the generated the key pair
        return keyPairGenerator.generateKeyPair()
    }
}