/*
 * Copyright 2021 gparap
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
package gparap.apps.password.ui.evaluator

import android.content.Context
import gparap.apps.password.R
import java.util.regex.Matcher
import java.util.regex.Pattern

class Evaluator(private val context: Context) {
    /**
     * Evaluates password's strength based on:
     *  -containing upper case characters,
     *  -containing digits,
     *  -containing special characters,
     *  -password length.
     */
    fun evaluatePassword(password: String): String {
        var passwordStrength = 0
        if (password.matchesRegularExpression("[A-Z]")) {
            passwordStrength += 1
        }
        if (password.matchesRegularExpression("[0-9]")) {
            passwordStrength += 1
        }
        if (password.matchesRegularExpression("[!@#$%]")) { //TODO: put all special chars
            passwordStrength += 1
        }
        if (password.length >= 8) {
            passwordStrength += 1
            if (password.length >= 16) {
                passwordStrength += 1
            }
        }
        return getPasswordStrengthAsString(passwordStrength)
    }

    private fun getPasswordStrengthAsString(passwordStrength: Int): String {
        when (passwordStrength) {
            1 -> return context.getString(R.string.password_evaluation_weakest)
            2 -> return context.getString(R.string.password_evaluation_weak)
            3 -> return context.getString(R.string.password_evaluation_medium)
            4 -> return context.getString(R.string.password_evaluation_strong)
            5 -> return context.getString(R.string.password_evaluation_strongest)
        }
        return context.getString(R.string.password_evaluation_weakest)
    }

    private fun String.matchesRegularExpression(regex: String): Boolean {
        val pattern: Pattern = Pattern.compile(regex)
        val matcher: Matcher = pattern.matcher(this)
        return matcher.find()
    }
}
