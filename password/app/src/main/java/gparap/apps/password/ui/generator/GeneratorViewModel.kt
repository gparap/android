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
package gparap.apps.password.ui.generator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.StringBuilder
import kotlin.random.Random

class GeneratorViewModel : ViewModel() {
    private var passwordLiveData = MutableLiveData<String>()
    val password: LiveData<String> = passwordLiveData

    private var passwordTitleLiveData = MutableLiveData<Boolean>()
    val passwordTitleVisibility: LiveData<Boolean> = passwordTitleLiveData
    fun setPasswordTitleVisibility(visibility: Boolean) {
        passwordTitleLiveData.value = visibility
    }

    /**
     * Generates a random password using characters from ASCII code 33('!') to 126('~')
     * @param length how many characters should the password be
     */
    fun generatePassword(length: Int) {
        val stringBuilder = StringBuilder()

        for (i in 0 until length) {
            stringBuilder.append(Random.nextInt(33, 127).toChar())
        }

        passwordLiveData.value = stringBuilder.toString()
    }
}