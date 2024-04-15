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

import android.content.Context
import android.widget.EditText
import android.widget.TextView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.CoreMatchers.not
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {
    private lateinit var activityScenario: ActivityScenario<MainActivity>
    private lateinit var context: Context

    @Before
    fun setUp() {
        //get the current scenario
        activityScenario = ActivityScenario.launch(MainActivity::class.java)

        //get the target context
        context = InstrumentationRegistry.getInstrumentation().targetContext
    }

    @Test
    fun isVisible_textView_privateKey_label() {
        onView(withId(R.id.textView_privateKey_label)).check(matches(not(isDisplayed())))
    }

    @Test
    fun isVisible_editText_privateKey() {
        onView(withId(R.id.editText_privateKey)).check(matches(not(isDisplayed())))
    }

    @Test
    fun isVisible_textView_publicKey_label() {
        onView(withId(R.id.textView_publicKey_label)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_editText_publicKey() {
        onView(withId(R.id.editText_publicKey)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_button_encrypt() {
        onView(withId(R.id.button_encrypt)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_button_decrypt() {
        onView(withId(R.id.button_decrypt)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_textView_cipher_value_label() {
        onView(withId(R.id.textView_cipher_value_label)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_editText_cipher_value() {
        onView(withId(R.id.editText_cipher_value)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_button_clear() {
        onView(withId(R.id.button_clear)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_textView_cipher_info() {
        onView(withId(R.id.textView_cipher_info)).check(matches(isDisplayed()))
    }

    @Test
    fun isCipherInfoTextCorrect_selectAESMenuItem() {
        //create the actual info text
        val infoText = getCipherInfoText(R.string.text_aes_long)

        //select the AES algorithm from the menu
        selectCipher(R.string.text_symmetric_ciphers, R.string.text_aes_short)

        //test here
        onView(withId(R.id.textView_cipher_info)).check(matches(withText(infoText)))
    }

    @Test
    fun isCipherInfoTextCorrect_selectARC4MenuItem() {
        //create the actual info text
        val infoText = getCipherInfoText(R.string.text_arc4_long)

        //select the ARC4 algorithm from the menu
        selectCipher(R.string.text_symmetric_ciphers, R.string.text_arc4_short)

        //test here
        onView(withId(R.id.textView_cipher_info)).check(matches(withText(infoText)))
    }

    @Test
    fun isCipherInfoTextCorrect_selectRSAMenuItem() {
        //create the actual info text
        val infoText = getCipherInfoText(R.string.text_rsa_long)

        //select the RSA algorithm from the menu
        selectCipher(R.string.text_asymmetric_ciphers, R.string.text_rsa_short)

        //test here
        onView(withId(R.id.textView_cipher_info)).check(matches(withText(infoText)))
    }

    @Test
    fun isPrivateKeyNotVisible_onAESMenuItemSelected() {
        //select the AES algorithm from the menu
        selectCipher(R.string.text_symmetric_ciphers, R.string.text_aes_short)

        //test the private key views visibility
        onView(withId(R.id.textView_privateKey_label)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editText_privateKey)).check(matches(not(isDisplayed())))
    }

    @Test
    fun isPrivateKeyVisible_onRSAMenuItemSelected() {
        //select the RSA algorithm from the menu
        selectCipher(R.string.text_asymmetric_ciphers, R.string.text_rsa_short)

        //test the private key views visibility
        onView(withId(R.id.textView_privateKey_label)).check(matches(isDisplayed()))
        onView(withId(R.id.editText_privateKey)).check(matches(isDisplayed()))
    }

    @Test
    fun isUserHintCorrect_onAESMenuItemSelected() {
        val expectedHint = context.getText(R.string.hint_cipherKey_aes)
        selectCipher(R.string.text_symmetric_ciphers, R.string.text_aes_short)
        val actualHInt = getPublicKeyViewHint()
        assertEquals(expectedHint, actualHInt)
    }

    @Test
    fun isUserHintCorrect_onARC4MenuItemSelected() {
        val expectedHint = context.getText(R.string.hint_cipherKey_arc4)
        selectCipher(R.string.text_symmetric_ciphers, R.string.text_arc4_short)
        val actualHInt = getPublicKeyViewHint()
        assertEquals(expectedHint, actualHInt)
    }

    @Test
    fun isUserHintCorrect_onBlowfishMenuItemSelected() {
        val expectedHint = context.getText(R.string.hint_cipherKey_blowfish)
        selectCipher(R.string.text_symmetric_ciphers, R.string.text_blowfish)
        val actualHInt = getPublicKeyViewHint()
        assertEquals(expectedHint, actualHInt)
    }

    @Test
    fun isUserHintCorrect_onDESMenuItemSelected() {
        val expectedHint = context.getText(R.string.hint_cipherKey_des)
        selectCipher(R.string.text_symmetric_ciphers, R.string.text_des_short)
        val actualHInt = getPublicKeyViewHint()
        assertEquals(expectedHint, actualHInt)
    }

    @Test
    fun isUserHintCorrect_onDESedeMenuItemSelected() {
        val expectedHint = context.getText(R.string.hint_cipherKey_desede)
        selectCipher(R.string.text_symmetric_ciphers, R.string.text_desede_short)
        val actualHInt = getPublicKeyViewHint()
        assertEquals(expectedHint, actualHInt)
    }

    /** Returns the text of the cipher info TextView in conjunction with the algorithm full name. */
    private fun getCipherInfoText(id: Int) : String {
        var infoText = ""
        activityScenario.onActivity {
            val textView = it.findViewById<TextView>(R.id.textView_cipher_info)
            infoText = textView.text.toString().plus(context.getText(id))
        }
        return  infoText
    }

    /** Opens the action bar menu and Selects an algorithm. */
    private fun selectCipher(cipherTypeResId: Int, cipherNameResId: Int) {
        openActionBarOverflowOrOptionsMenu(context)
        onView(withText(cipherTypeResId)).perform(click())
        onView(withText(cipherNameResId)).perform(click())
    }

    /** Returns the hint of the EditText view that accepts/displays the public key. */
    private fun getPublicKeyViewHint() : String {
        var hint = ""
        activityScenario.onActivity {
            val editText = it.findViewById<EditText>(R.id.editText_publicKey)
            hint =  editText.hint.toString()
        }
        return hint
    }
}