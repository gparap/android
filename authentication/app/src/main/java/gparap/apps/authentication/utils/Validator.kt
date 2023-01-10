package gparap.apps.authentication.utils

import gparap.apps.authentication.utils.AppConstants.MAX_PASSWORD_LENGTH
import gparap.apps.authentication.utils.AppConstants.MIN_PASSWORD_LENGTH

/*
 * Copyright 2023 gparap
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
object Validator {

    /** input field is empty */
    fun isEmpty(input: String): Boolean {
        return input.isEmpty()
    }

    /** e-mail should contain `@` and `.` symbols */
    fun isEmailValid(email: String): Boolean {
        if (email.contains('@') && email.contains('.'))
            return true
        return false
    }

    /** password should be long enough */
    fun isPasswordLongEnough(password: String): Boolean {
        return password.length >= MIN_PASSWORD_LENGTH
    }

    /** password should not be too long */
    fun isPasswordTooLong(password: String): Boolean {
        return password.length <= MAX_PASSWORD_LENGTH
    }

    /** password and confirmation should match */
    fun doPasswordsMatch(password: String, confirm: String): Boolean {
        return password == confirm
    }
}