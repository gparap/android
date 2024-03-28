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
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
            }

            //Salsa20 algorithm
            R.id.menu_item_salsa20 -> {
                updateCipherInfoText(R.string.text_salsa20)
                handlePrivateKeyVisibility(R.id.menu_item_salsa20)
            }

            //Rivest-Shamir-Adleman algorithm
            R.id.menu_item_rsa -> {
                updateCipherInfoText(R.string.text_rsa_long)
                handlePrivateKeyVisibility(R.id.menu_item_rsa)
            }

            //Diffie–Hellman key exchange algorithm
            R.id.menu_item_dhke -> {
                updateCipherInfoText(R.string.text_dhke_long)
                handlePrivateKeyVisibility(R.id.menu_item_dhke)
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
            R.id.menu_item_dhke -> {
                findViewById<TextView>(R.id.textView_privateKey_label).apply {
                    this.visibility = View.VISIBLE
                }
                findViewById<EditText>(R.id.editText_privateKey).apply {
                    this.visibility = View.VISIBLE
                }
            }
        }
    }
}