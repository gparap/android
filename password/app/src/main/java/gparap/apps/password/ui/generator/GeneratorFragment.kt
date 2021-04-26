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

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import gparap.apps.password.R
import gparap.apps.password.data.DatabaseManager
import gparap.apps.password.data.PasswordModel

class GeneratorFragment : Fragment() {
    private lateinit var viewModel: GeneratorViewModel
    private var passwordLength = 8
    private lateinit var passwordTitle: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //inflate layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_generator, container, false)

        //create viewmodel for this fragment
        viewModel = ViewModelProvider(this).get(GeneratorViewModel::class.java)

        //generate random password
        val buttonGenerate = rootView.findViewById<Button>(R.id.buttonGeneratePassword)
        passwordTitle = rootView.findViewById(R.id.generatedPasswordTitle)
        buttonGenerate.setOnClickListener {
            viewModel.generatePassword(passwordLength)
            passwordTitle.isVisible = true
        }

        //observe generated password
        val passwordGenerated = rootView.findViewById<TextView>(R.id.textViewPasswordGenerated)
        viewModel.password.observe(viewLifecycleOwner, {
            passwordGenerated.text = it
        })

        //observe custom password length
        val customLength = rootView.findViewById<TextView>(R.id.editTextCustomLengthPassword)
        customLength.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                passwordLength = s.toString().toInt()
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        //handle password length with radio buttons
        val radioGroup = rootView.findViewById<RadioGroup>(R.id.radioGroupPasswordLength)
        radioGroup.setOnCheckedChangeListener { _, id ->
            when (id) {
                R.id.radioButton08CharsLengthPassword -> {
                    customLength.isVisible = false
                    passwordLength = 8
                }
                R.id.radioButton16CharsLengthPassword -> {
                    customLength.isVisible = false
                    passwordLength = 16
                }
                R.id.radioButtonCustomLengthPassword -> {
                    customLength.isVisible = true
                    passwordLength = 0
                    try {
                        passwordLength = customLength.text.toString().toInt()
                    } catch (e: Exception) {
                    }
                }
            }
        }

        //save generated password
        val fabSavePassword =
            rootView.findViewById<FloatingActionButton>(R.id.fabSaveGeneratedPassword)
        fabSavePassword.setOnClickListener {
            saveGeneratedPassword()
        }

        return rootView
    }

    private fun saveGeneratedPassword() {
        //password validation
        if (viewModel.password.value.isNullOrEmpty()) {
            Toast.makeText(this.context, "Password is empty!", Toast.LENGTH_SHORT).show()
            return
        }

        //password title validation
        if (passwordTitle.text.isNullOrEmpty()) {
            Toast.makeText(this.context, "Title is empty!", Toast.LENGTH_SHORT).show()
            return
        }

        //create a password object
        val password = PasswordModel(
            -1, passwordTitle.text.toString(), viewModel.password.value.toString()
        )

        //save generated password to database
        val databaseManager = DatabaseManager(this.requireContext())
        if (databaseManager.insertPassword(password)) {
            Toast.makeText(this.context, "Password saved.", Toast.LENGTH_SHORT).show()
        }
    }
}