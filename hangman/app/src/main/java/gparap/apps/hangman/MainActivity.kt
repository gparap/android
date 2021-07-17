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
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import gparap.apps.hangman.utils.Utils
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainActivityViewModel
    private var letters: ArrayList<TextView> = ArrayList()
    private lateinit var alphabetLayout: ConstraintLayout
    private lateinit var buttonStart: Button
    private lateinit var textViewWordToFind: TextView
    private lateinit var wordToFind: String
    private var underscoredWord = StringBuilder()
    private var currentLetter: Char? = null

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

        //perform initial setup
        setupWordsToFind()

        //starts a new round of hangman
        buttonStart.setOnClickListener {
            startNewHangman()
        }
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)

        //!!! we just need outState to be not null
        outState.putString("placeholder", "placeholder")
    }

    private fun startNewHangman() {
        letters.clear()
        alphabetLayout.children.forEach { it ->
            if (it is TextView) {
                letters.add(it)
                if (it.text.toString().isNotEmpty()) {
                    it.visibility = View.VISIBLE
                    it.setOnClickListener {
                        setLetterAsUsed(it as TextView)
                        searchLetter()
                    }
                } else {
                    it.visibility = View.GONE
                }
            }
        }
        setupWordsToFind()
    }

    //perform initial setup
    private fun setupWordsToFind() {
        wordToFind = Utils.getRandomWord()
        underscoredWord = Utils.getUnderscoredWords(wordToFind)
        textViewWordToFind.text = underscoredWord
    }

    //update the requested word with selected letters (if exist)
    private fun searchLetter() {
        textViewWordToFind.text = underscoredWord
        val indices = mutableListOf<Int>()

        wordToFind.toCharArray().forEach {
            //letter exists in requested word
            if (it == currentLetter) {
                //get all indices inside the requested word
                wordToFind.toCharArray().forEachIndexed { index, char ->
                    if (char.equals(it, true)) {
                        indices.add(index)
                    }
                }

                //set the found position inside the requested word
                for (i in indices) {
                    underscoredWord[i] = it
                    textViewWordToFind.text = underscoredWord
                }
            }
        }
    }

    //remove used letter from visible (available) letters and
    //  set current letter to find
    private fun setLetterAsUsed(letter: TextView) {
        letter.visibility = View.GONE
        letters.remove(letter)
        viewModel.setLetters(letters)
        currentLetter = letter.text.toString().single()
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
                            searchLetter()
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
                        searchLetter()
                    }
                } else {
                    it.visibility = View.GONE
                }
            }
        }
    }

    private fun getWidgets() {
        alphabetLayout = findViewById(R.id.layoutAlphabet)
        textViewWordToFind = findViewById(R.id.textViewWordToFind)
        buttonStart = findViewById(R.id.buttonStart)
    }
}
