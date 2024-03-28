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
                findViewById<TextView>(R.id.textView_cipher_info).apply {
                    this.text = getString(R.string.text_current_cipher_algorithm).plus(
                        getString(R.string.text_aes_long)
                    )
                }
            }

            //Salsa20 algorithm
            R.id.menu_item_salsa20 -> {
                findViewById<TextView>(R.id.textView_cipher_info).apply {
                    this.text = getString(R.string.text_current_cipher_algorithm).plus(
                        getString(R.string.text_salsa20)
                    )
                }
            }

            //Rivest-Shamir-Adleman algorithm
            R.id.menu_item_rsa -> {
                findViewById<TextView>(R.id.textView_cipher_info).apply {
                    this.text = getString(R.string.text_current_cipher_algorithm).plus(
                        getString(R.string.text_rsa_long)
                    )
                }
            }

            //Diffie–Hellman key exchange algorithm
            R.id.menu_item_dhke -> {
                findViewById<TextView>(R.id.textView_cipher_info).apply {
                    this.text = getString(R.string.text_current_cipher_algorithm).plus(
                        getString(R.string.text_dhke_long)
                    )
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}