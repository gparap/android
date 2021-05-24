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

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule

class GeneratorViewModelUnitTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Test
    fun generatePassword_isGeneratedPasswordLengthCorrect() {
        val expectedLength = 5

        val viewModel = GeneratorViewModel()
        viewModel.generatePassword(expectedLength)
        val actualLength = viewModel.password.value?.length

        assertEquals("Password length is not correct", expectedLength, actualLength)
    }
}