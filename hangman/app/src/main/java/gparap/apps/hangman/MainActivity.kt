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
package gparap.apps.hangman

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainActivityViewModel
    private var letters: ArrayList<TextView> = ArrayList()
    private lateinit var alphabetLayout: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getWidgets()

        //create or retrieve the ViewModel of this Activity
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        //initialize the letters of the alphabet
        if (savedInstanceState == null) {
            initLettersByAlphabet()
        } else {
            initLetters()
        }

        //observe alphabet letters
        viewModel.getLetters().observe(this, {
            letters = it
        })
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)

        //!!! we just need outState to be not null
        outState.putString("placeholder", "placeholder")
    }

    //remove used letter from visible (available) letters
    private fun setLetterAsUsed(letter: TextView) {
        letter.visibility = View.GONE
        letters.remove(letter)
        viewModel.setLetters(letters)
    }

    //persist visible letters during orientation changes
    //  using the Activity's ViewModel
    private fun initLetters() {
        letters = viewModel.getLetters().value!!
        val tempList: ArrayList<TextView> = ArrayList()

        alphabetLayout.children.forEach { it ->
            if (it is TextView) {
                it.visibility = View.GONE

                for (l in letters) {
                    if (it.text.toString()
                            .isNotEmpty() && l.text.toString() == it.text.toString()
                    ) {
                        it.setOnClickListener {
                            setLetterAsUsed(it as TextView)
                        }
                        it.visibility = View.VISIBLE
                        tempList.add(it)
                        break
                    }
                }
            }
        }
        letters = tempList
        viewModel.setLetters(letters)
    }

    //!!! alphabets consist of a different number of letters, and so
    //!!!   all extra letter placeholders must be removed from view
    private fun initLettersByAlphabet() {
        alphabetLayout.children.forEach { it ->
            if (it is TextView) {
                letters.add(it)
                if (it.text.toString().isNotEmpty() && it.isVisible) {
                    it.setOnClickListener {
                        setLetterAsUsed(it as TextView)
                    }
                } else {
                    it.visibility = View.GONE
                }
            }
        }
    }

    private fun getWidgets() {
        alphabetLayout = findViewById(R.id.layoutAlphabet)
    }
}