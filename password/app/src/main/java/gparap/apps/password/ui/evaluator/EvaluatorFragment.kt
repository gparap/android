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

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import gparap.apps.password.R
import gparap.apps.password.utils.DatabaseUtils

class EvaluatorFragment : Fragment() {
    private lateinit var viewModel: EvaluatorViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //inflate layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_evaluator, container, false)

        //create viewmodel for this fragment
        viewModel = ViewModelProvider(this).get(EvaluatorViewModel::class.java)

        //get password title widget
        val passwordTitle = rootView.findViewById<EditText>(R.id.editTextEvaluatedPasswordTitle)

        //handle evaluated password
        val password = rootView.findViewById<EditText>(R.id.editTextEvaluatedPassword)
        password.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //handle password title visibility
                if (s?.isNotEmpty() == true) {
                    passwordTitle.visibility = View.VISIBLE
                }else{
                    passwordTitle.visibility = View.INVISIBLE
                }

                //evaluate password
                viewModel.setPasswordStrength(Evaluator(context!!).evaluatePassword(s.toString()))

                //show tick if the password is strongest
                val imageTick = rootView.findViewById<ImageView>(R.id.imageViewPasswordEvaluationPassed)
                val tick:String = Evaluator(context!!).evaluatePassword(s.toString())
                if (tick == getString(R.string.password_evaluation_strongest)){
                    imageTick.visibility = View.VISIBLE
                }else{
                    imageTick.visibility = View.INVISIBLE
                }

                //keep track of password length
                if (s != null) {
                    viewModel.setPasswordLength(s.length)
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        //observe evaluated password
        viewModel.password.observe(viewLifecycleOwner, {
            password.setText(it)
        })

        //observe evaluated password length
        val passwordLength = rootView.findViewById<TextView>(R.id.textViewEvaluatedPasswordLength)
        viewModel.passwordLength.observe(viewLifecycleOwner, {
            passwordLength.text = it.toString()
        })

        //observe evaluated password strength feedback
        val passwordStrength = rootView.findViewById<TextView>(R.id.textViewEvaluatedPasswordStrength)
        viewModel.passwordStrength.observe(viewLifecycleOwner, {
            passwordStrength.text = it
        })

        //observe bassword title
        viewModel.passwordTitle.observe(viewLifecycleOwner, {
            viewModel.setPasswordTitle(passwordTitle.text.toString())
        })

        //save password to database
        val buttonSave = rootView.findViewById<FloatingActionButton>(R.id.buttonSaveEvaluatedPassword)
        buttonSave.setOnClickListener {
            DatabaseUtils.getInstance()?.savePassword(
                password.text.toString(),
                passwordTitle.text.toString(),
                this.requireContext()
            )
        }

        return rootView
    }
}