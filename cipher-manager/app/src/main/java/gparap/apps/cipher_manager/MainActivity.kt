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
package gparap.apps.cipher_manager

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import gparap.apps.cipher_manager.utils.Algorithm
import gparap.apps.cipher_manager.utils.Utils
import org.bouncycastle.crypto.AsymmetricCipherKeyPair
import org.bouncycastle.crypto.params.ElGamalKeyParameters

class MainActivity : AppCompatActivity() {
    private var currentAlgorithm = Algorithm.AES  //default cipher
    private var publicKey = ""
    private var privateKey = ""
    private var textToBeEncrypted = ""
    private var textToBeDecrypted = ""
    private var encryptedText = ""
    private var decryptedText = ""
    private lateinit var publicKeyElGamal: ElGamalKeyParameters
    private lateinit var privateKeyElGamal: ElGamalKeyParameters

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //encrypt
        findViewById<Button>(R.id.button_encrypt).setOnClickListener {
            getSecretKeys()

            //get the text to be encrypted TODO: validate input
            findViewById<EditText>(R.id.editText_cipher_value).apply {
                textToBeEncrypted = this.text.toString().trim()
            }

            //encrypt text
            if (currentAlgorithm == Algorithm.AES) {
                encryptedText = encrypt(publicKey.toByteArray(), textToBeEncrypted)
            }else if (currentAlgorithm == Algorithm.Salsa20) {
                encryptedText = encrypt(publicKey.toByteArray(), textToBeEncrypted)
            }
            else if (currentAlgorithm == Algorithm.RSA) {
                encryptedText = encrypt(publicKey.toByteArray(), textToBeEncrypted)
            }else if (currentAlgorithm == Algorithm.ElGamal){
                //we're not using the byte array parameter  //TODO: fix
                encryptedText = encrypt(ByteArray(0), textToBeEncrypted)
            }

            //update the UI with the encrypted text
            findViewById<EditText>(R.id.editText_cipher_value).apply {
                this.setText(encryptedText)
            }
        }

        //decrypt
        findViewById<Button>(R.id.button_decrypt).setOnClickListener {
            getSecretKeys()

            //get the text to be decrypted TODO: validate input
            findViewById<EditText>(R.id.editText_cipher_value).apply {
                textToBeDecrypted = this.text.toString().trim()
            }

            //decrypt text
            if (currentAlgorithm == Algorithm.AES) {
                decryptedText = decrypt(publicKey.toByteArray(), encryptedText)
            }
            else if (currentAlgorithm == Algorithm.Salsa20) {
                decryptedText = decrypt(publicKey.toByteArray(), textToBeEncrypted)
            }
            else if (currentAlgorithm == Algorithm.RSA) {
                decryptedText = decrypt(publicKey.toByteArray(), textToBeDecrypted)
            } else if (currentAlgorithm == Algorithm.ElGamal){
                //we're not using the byte array parameter  //TODO: fix
                decryptedText = decrypt(ByteArray(0), textToBeDecrypted)
            }

            //update the UI with the decrypted text
            findViewById<EditText>(R.id.editText_cipher_value).apply {
                this.setText(decryptedText)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            //Advanced Encryption Standard algorithm
            R.id.menu_item_aes -> {
                updateCipherInfoText(R.string.text_aes_long)
                handlePrivateKeyVisibility(R.id.menu_item_aes)
                currentAlgorithm = Algorithm.AES
            }

            //Salsa20 algorithm
            R.id.menu_item_salsa20 -> {
                updateCipherInfoText(R.string.text_salsa20)
                handlePrivateKeyVisibility(R.id.menu_item_salsa20)
                currentAlgorithm = Algorithm.Salsa20
            }

            //Rivest-Shamir-Adleman algorithm
            R.id.menu_item_rsa -> {
                updateCipherInfoText(R.string.text_rsa_long)
                handlePrivateKeyVisibility(R.id.menu_item_rsa)
                currentAlgorithm = Algorithm.RSA
            }

            //ElGamal
            R.id.menu_item_elgamal -> {
                updateCipherInfoText(R.string.text_elgamal)
                handlePrivateKeyVisibility(R.id.menu_item_elgamal)
                currentAlgorithm = Algorithm.ElGamal
            }
        }
        return super.onOptionsItemSelected(item)
    }

    /** Update the text of the view that displays information about the full algorithm name. */
    private fun updateCipherInfoText(resId: Int) {
        findViewById<TextView>(R.id.textView_cipher_info).apply {
            this.text = getString(R.string.text_current_cipher_algorithm).plus(
                getString(resId)
            )
        }
    }

    /** Shows and Hides the private key's label and input text based on algorithm selected. */
    private fun handlePrivateKeyVisibility(id: Int) {
        when (id) {
            //hide private key
            R.id.menu_item_aes -> {
                findViewById<TextView>(R.id.textView_privateKey_label).apply {
                    this.visibility = View.GONE
                }
                findViewById<EditText>(R.id.editText_privateKey).apply {
                    this.visibility = View.GONE
                }
            }

            //hide private key
            R.id.menu_item_salsa20 -> {
                findViewById<TextView>(R.id.textView_privateKey_label).apply {
                    this.visibility = View.GONE
                }
                findViewById<EditText>(R.id.editText_privateKey).apply {
                    this.visibility = View.GONE
                }
            }

            //show private key
            R.id.menu_item_rsa -> {
                findViewById<TextView>(R.id.textView_privateKey_label).apply {
                    this.visibility = View.VISIBLE
                }
                findViewById<EditText>(R.id.editText_privateKey).apply {
                    this.visibility = View.VISIBLE
                }
            }

            //show private key
            R.id.menu_item_elgamal -> {
                findViewById<TextView>(R.id.textView_privateKey_label).apply {
                    this.visibility = View.VISIBLE
                }
                findViewById<EditText>(R.id.editText_privateKey).apply {
                    this.visibility = View.VISIBLE
                }
            }
        }
    }

    /* Gets the public and/or private key from the user TODO: validate keys */
    private fun getSecretKeys() {
        if (currentAlgorithm == Algorithm.AES || currentAlgorithm == Algorithm.Salsa20) {
            findViewById<EditText>(R.id.editText_publicKey).apply {
                publicKey = this.text.toString().trim() //16 chars (key length: 128/192/256 bits)
            }
        }else if (currentAlgorithm == Algorithm.RSA){
            findViewById<EditText>(R.id.editText_privateKey).apply {
                privateKey = this.text.toString().trim()    //2048-bit RSA key (Base64 formatting)
            }
            findViewById<EditText>(R.id.editText_publicKey).apply {
                publicKey = this.text.toString().trim()     //2048-bit RSA key (Base64 formatting)
            }
        }
        else if (currentAlgorithm == Algorithm.ElGamal) {
            //generate keys for ElGamal algorithm
            val akp: AsymmetricCipherKeyPair = Utils.generateElGamalKeys()
            publicKeyElGamal = akp.public as ElGamalKeyParameters
            privateKeyElGamal = akp.private as ElGamalKeyParameters
        }
    }

    /* Encrypts the input value based on the current algorithm. */
    private fun encrypt(key: ByteArray, value: String): String {
        var encryptedText = ""

        if (currentAlgorithm == Algorithm.AES) {
            encryptedText = Utils.encryptWithAES(key, value)
        }else if(currentAlgorithm == Algorithm.Salsa20) {
            encryptedText = Utils.encryptWithSalsa20(key, value)
        }else if(currentAlgorithm == Algorithm.RSA) {
            encryptedText = Utils.encryptWithRSA(publicKey, value)
        }else if(currentAlgorithm == Algorithm.ElGamal) {
            encryptedText = Utils.encryptWithElGamal(publicKeyElGamal, value)
        }

        return encryptedText
    }

    /* Decrypts the input value based on the current algorithm. */
    private fun decrypt(key: ByteArray, value: String): String {
        var decryptedText = ""

        if (currentAlgorithm == Algorithm.AES) {
            decryptedText = Utils.decryptWithAES(key, value)
        }else if(currentAlgorithm == Algorithm.Salsa20) {
            decryptedText = Utils.decryptWithSalsa20(key, value)
        }
        else if(currentAlgorithm == Algorithm.RSA) {
            decryptedText = Utils.decryptWithRSA(privateKey, value)
        }else if(currentAlgorithm == Algorithm.ElGamal) {
            decryptedText = Utils.decryptWithElGamal(privateKeyElGamal, encryptedText)
        }

        return decryptedText
    }
}