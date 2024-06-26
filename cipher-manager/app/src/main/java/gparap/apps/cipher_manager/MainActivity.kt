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
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import gparap.apps.cipher_manager.utils.Algorithm
import gparap.apps.cipher_manager.utils.Utils

class MainActivity : AppCompatActivity() {
    private var currentAlgorithm = Algorithm.AES  //default cipher
    private var publicKey = ""
    private var privateKey = ""
    private var textToBeEncrypted = ""
    private var textToBeDecrypted = ""
    private var encryptedText = ""
    private var decryptedText = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //encrypt
        findViewById<Button>(R.id.button_encrypt).setOnClickListener {
            getSecretKeys()

            //get the text to be encrypted
            findViewById<EditText>(R.id.editText_cipher_value).apply {
                textToBeEncrypted = this.text.toString().trim()
            }

            //do not proceed with encryption if user input is invalid
            if (!validateInput()) {
                return@setOnClickListener
            }

            //perform encryption
            encryptedText = encrypt(publicKey.toByteArray(), textToBeEncrypted)

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

            //do not proceed with decryption if user input is invalid
            if (!validateInput()) {
                return@setOnClickListener
            }

            //decrypt text
            if (currentAlgorithm == Algorithm.AES || currentAlgorithm == Algorithm.ARC4
                || currentAlgorithm == Algorithm.Blowfish || currentAlgorithm == Algorithm.DES
                || currentAlgorithm == Algorithm.DESede
            ) {
                decryptedText = decrypt(publicKey.toByteArray(), encryptedText)
            } else if (currentAlgorithm == Algorithm.RSA) {
                decryptedText = decrypt(publicKey.toByteArray(), textToBeDecrypted)
            }

            //update the UI with the decrypted text
            findViewById<EditText>(R.id.editText_cipher_value).apply {
                this.setText(decryptedText)
            }
        }

        //clear input fields & update hints
        findViewById<Button>(R.id.button_clear).setOnClickListener {
            clearInput()
            when (currentAlgorithm) {
                Algorithm.AES -> findViewById<EditText>(R.id.editText_publicKey).apply {
                    this.hint = resources.getString(R.string.hint_cipherKey_aes)
                }
                Algorithm.ARC4 -> findViewById<EditText>(R.id.editText_publicKey).apply {
                    this.hint = resources.getString(R.string.hint_cipherKey_arc4)
                }
                Algorithm.Blowfish -> findViewById<EditText>(R.id.editText_publicKey).apply {
                    this.hint = resources.getString(R.string.hint_cipherKey_blowfish)
                }
                Algorithm.DES -> findViewById<EditText>(R.id.editText_publicKey).apply {
                    this.hint = resources.getString(R.string.hint_cipherKey_des)
                }
                Algorithm.DESede -> findViewById<EditText>(R.id.editText_publicKey).apply {
                    this.hint = resources.getString(R.string.hint_cipherKey_desede)
                }
                Algorithm.RSA -> {
                    TODO("Hints not implemented yet.")
                }
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
                updateInputHintText(R.id.menu_item_aes)
                currentAlgorithm = Algorithm.AES
            }

            //Rivest Cipher 4 algorithm
            R.id.menu_item_arc4 -> {
                updateCipherInfoText(R.string.text_arc4_long)
                handlePrivateKeyVisibility(R.id.menu_item_arc4)
                updateInputHintText(R.id.menu_item_arc4)
                currentAlgorithm = Algorithm.ARC4
            }

            //Blowfish algorithm
            R.id.menu_item_blowfish -> {
                updateCipherInfoText(R.string.text_blowfish)
                handlePrivateKeyVisibility(R.id.menu_item_blowfish)
                updateInputHintText(R.id.menu_item_blowfish)
                currentAlgorithm = Algorithm.Blowfish
            }

            //Data Encryption Standard algorithm
            R.id.menu_item_des -> {
                updateCipherInfoText(R.string.text_des_long)
                handlePrivateKeyVisibility(R.id.menu_item_des)
                updateInputHintText(R.id.menu_item_des)
                currentAlgorithm = Algorithm.DES
            }

            //Triple Data Encryption Standard algorithm
            R.id.menu_item_desede -> {
                updateCipherInfoText(R.string.text_desede_long)
                handlePrivateKeyVisibility(R.id.menu_item_desede)
                updateInputHintText(R.id.menu_item_desede)
                currentAlgorithm = Algorithm.DESede
            }

            //Rivest-Shamir-Adleman algorithm
            R.id.menu_item_rsa -> {
                updateCipherInfoText(R.string.text_rsa_long)
                handlePrivateKeyVisibility(R.id.menu_item_rsa)
                currentAlgorithm = Algorithm.RSA
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

            //show private key
            R.id.menu_item_rsa -> {
                findViewById<TextView>(R.id.textView_privateKey_label).apply {
                    this.visibility = View.VISIBLE
                }
                findViewById<EditText>(R.id.editText_privateKey).apply {
                    this.visibility = View.VISIBLE
                }
            }
        }
    }

    /** Updates the hint text of the the public key user input. */
    private fun updateInputHintText(id: Int) {
        var hint = ""
        when (id) {
            R.id.menu_item_aes -> hint = resources.getString(R.string.hint_cipherKey_aes)
            R.id.menu_item_arc4 -> hint = resources.getString(R.string.hint_cipherKey_arc4)
            R.id.menu_item_blowfish -> hint = resources.getString(R.string.hint_cipherKey_blowfish)
            R.id.menu_item_des -> hint = resources.getString(R.string.hint_cipherKey_des)
            R.id.menu_item_desede -> hint = resources.getString(R.string.hint_cipherKey_desede)
            else -> {}
        }.apply {
            findViewById<EditText>(R.id.editText_publicKey).apply {
                this.hint = hint
            }
        }
    }

    /* Gets the public and/or private key from the user. */
    private fun getSecretKeys() {
        //get the public key (symmetric)
        if (currentAlgorithm == Algorithm.AES || currentAlgorithm == Algorithm.ARC4 ||
            currentAlgorithm == Algorithm.Blowfish || currentAlgorithm == Algorithm.DES
            || currentAlgorithm == Algorithm.DESede
        ) {
            findViewById<EditText>(R.id.editText_publicKey).apply {
                publicKey = this.text.toString().trim()
            }
        }

        //get the public & private keys (assymetric)
        else if (currentAlgorithm == Algorithm.RSA) {
            findViewById<EditText>(R.id.editText_privateKey).apply {
                privateKey = this.text.toString().trim()
            }
            findViewById<EditText>(R.id.editText_publicKey).apply {
                publicKey = this.text.toString().trim()
            }
        }
    }

    /* Validates the public and/or private key from the user
     & Informs them of what is invalid in their input. */
    private fun validateInput(): Boolean {
        if (currentAlgorithm == Algorithm.AES || currentAlgorithm == Algorithm.ARC4
            || currentAlgorithm == Algorithm.Blowfish || currentAlgorithm == Algorithm.DES
            || currentAlgorithm == Algorithm.DESede
        ) {
            //validate input keys
            val validationResult =
                Utils.areSecretKeysValid(publicKey, privateKey = null, currentAlgorithm)
            if (!validationResult.first) {
                val errorMessage = validationResult.second

                //check if key is empty
                if (errorMessage.contains("empty")) {
                    Toast.makeText(
                        this,
                        this.resources.getString(R.string.toast_cipherKey_empty),
                        Toast.LENGTH_LONG
                    ).show().apply {
                        return false
                    }
                }

                //get the toast message for the current algorithm's key length to inform the user
                when (currentAlgorithm) {
                    Algorithm.AES -> Toast.makeText(
                        this,
                        this.resources.getString(R.string.toast_cipherKey_aes),
                        Toast.LENGTH_SHORT
                    ).show()

                    Algorithm.ARC4 -> Toast.makeText(
                        this,
                        this.resources.getString(R.string.toast_cipherKey_arc4),
                        Toast.LENGTH_SHORT
                    ).show()

                    Algorithm.Blowfish -> Toast.makeText(
                        this,
                        this.resources.getString(R.string.toast_cipherKey_blowfish),
                        Toast.LENGTH_SHORT
                    ).show()

                    Algorithm.DES -> Toast.makeText(
                        this,
                        this.resources.getString(R.string.toast_cipherKey_des),
                        Toast.LENGTH_SHORT
                    ).show()

                    Algorithm.DESede -> Toast.makeText(
                        this,
                        this.resources.getString(R.string.toast_cipherKey_desede),
                        Toast.LENGTH_SHORT
                    ).show()

                    else -> {}
                }

                //validation failed
                return false
            }
        } else if (currentAlgorithm == Algorithm.RSA) {
            //validate input keys
            val validationResult =
                Utils.areSecretKeysValid(publicKey, privateKey, currentAlgorithm)
            if (!validationResult.first) {
                val errorMessage = validationResult.second

                //check if key is empty
                if (errorMessage.contains("empty")) {
                    Toast.makeText(
                        this,
                        this.resources.getString(R.string.toast_cipherKeys_empty),
                        Toast.LENGTH_LONG
                    ).show().apply {
                        return false
                    }
                }

                //display the toast message for the validity of the secret keys to the user
                Toast.makeText(
                    this,
                    this.resources.getString(R.string.toast_cipherKeys_rsa),
                    Toast.LENGTH_SHORT
                ).show()

                //validation failed
                return false
            }
        }

        //validation succeeded
        return true
    }

    /* Encrypts the input value based on the current algorithm. */
    private fun encrypt(key: ByteArray, value: String): String {
        val encryptedText = when (currentAlgorithm) {
            Algorithm.AES -> Utils.encryptWithAES(key, value)
            Algorithm.ARC4 -> Utils.encryptWithARC4(key, value)
            Algorithm.Blowfish -> Utils.encryptWithBlowfish(key, value)
            Algorithm.DES -> Utils.encryptWithDES(key, value)
            Algorithm.DESede -> Utils.encryptWithTripleDES(key, value)
            Algorithm.RSA -> Utils.encryptWithRSA(publicKey, value)
        }

        return encryptedText
    }

    /* Decrypts the input value based on the current algorithm. */
    private fun decrypt(key: ByteArray, value: String): String {
        val decryptedText = when (currentAlgorithm) {
            Algorithm.AES -> Utils.decryptWithAES(key, value)
            Algorithm.ARC4 -> Utils.decryptWithARC4(key, value)
            Algorithm.Blowfish -> Utils.decryptWithBlowfish(key, value)
            Algorithm.DES -> Utils.decryptWithDES(key, value)
            Algorithm.DESede -> Utils.decryptWithTripleDES(key, value)
            Algorithm.RSA -> Utils.decryptWithRSA(privateKey, value)
        }

        return decryptedText
    }

    /* Clears the input fields of secret keys & message. */
    private fun clearInput() {
        findViewById<EditText>(R.id.editText_privateKey).apply {
            this.text.clear()
        }
        findViewById<EditText>(R.id.editText_publicKey).apply {
            this.text.clear()
        }
        findViewById<EditText>(R.id.editText_cipher_value).apply {
            this.text.clear()
        }
    }
}