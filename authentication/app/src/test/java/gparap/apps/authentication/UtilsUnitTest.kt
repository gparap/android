package gparap.apps.authentication

import gparap.apps.authentication.utils.Validator
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

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
class UtilsUnitTest {
    @Test
    fun validator_emailIsValid() {
        val email = "gp@dot.com"
        assertTrue(Validator.isEmailValid(email))
    }

    @Test
    fun validator_emailIsNotValid() {
        var email = "gpdotcom"
        assertFalse(Validator.isEmailValid(email))

        email = "gp@dotcom"
        assertFalse(Validator.isEmailValid(email))

        email = "gpdot.com"
        assertFalse(Validator.isEmailValid(email))
    }

    @Test
    fun validator_isPasswordLongEnough() {
        var password = "12345678"
        assertTrue(Validator.isPasswordLongEnough(password))

        password = "1234567"
        assertFalse(Validator.isPasswordLongEnough(password))
    }

    @Test
    fun validator_isPasswordTooLong(){
        var password = "0123456789ABCDEF"
        assertTrue(Validator.isPasswordTooLong(password))

        password = "0123456789ABCDEFx"
        assertFalse(Validator.isPasswordTooLong(password))
    }

    @Test
    fun validator_doPasswordsMatch() {
        val password = "12345678"
        var confirm = "12345678"
        assertTrue(Validator.doPasswordsMatch(password, confirm))

        confirm = "12345678x"
        assertFalse(Validator.doPasswordsMatch(password, confirm))
    }
}