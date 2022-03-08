/*
 * Copyright (c) 2022 gparap
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
package gparap.apps.quiz

import android.os.Bundle
import android.view.View
import android.view.View.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import gparap.apps.quiz.utils.AppConstants
import gparap.apps.quiz.viewmodels.MainActivityViewModel

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var spinner: Spinner
    private var selectedCategory = ""

    fun getViewModel(): MainActivityViewModel {
        return viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.createOrOpenDatabase()
        observeSelectedCategory()
        handleQuizCategorySelectionCallback()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.closeDatabase()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (position == 0) return

        when (parent?.getItemAtPosition(position).toString()) {

            //animals category selected
            resources.getString(R.string.category_animals) -> {
                viewModel.populateDatabaseTable(AppConstants.DB_TABLE_ANIMALS)
                viewModel.setSelectedCategory(resources.getString(R.string.category_animals))
            }

            //geography category selected
            resources.getString(R.string.category_geography) -> {
                viewModel.populateDatabaseTable(AppConstants.DB_TABLE_GEOGRAPHY)
                viewModel.setSelectedCategory(resources.getString(R.string.category_geography))
            }

            //history category selected
            resources.getString(R.string.category_history) -> {
                viewModel.populateDatabaseTable(AppConstants.DB_TABLE_HISTORY)
                viewModel.setSelectedCategory(resources.getString(R.string.category_history))
            }

            //literature category selected
            resources.getString(R.string.category_literature) -> {
                viewModel.populateDatabaseTable(AppConstants.DB_TABLE_LITERATURE)
                viewModel.setSelectedCategory(resources.getString(R.string.category_literature))
            }

            //mathematics category selected
            resources.getString(R.string.category_mathematics) -> {
                viewModel.populateDatabaseTable(AppConstants.DB_TABLE_MATHS)
                viewModel.setSelectedCategory(resources.getString(R.string.category_mathematics))
            }
        }

        //hide the current layout
        spinner.visibility = GONE
        this@MainActivity.findViewById<LinearLayout>(R.id.main_layout_intro).apply {
            visibility = INVISIBLE
        }

        //display the quiz layout
        this@MainActivity.findViewById<ConstraintLayout>(R.id.main_layout_quiz).apply {
            visibility = VISIBLE
        }

        //prepare the questions for the quiz
        viewModel.populateSelectedCategoryQuestions()
        viewModel.shuffleSelectedCategoryQuestions()

        //quiz is ready to start now
        handleStartQuizButtonCallback()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    /* Registers a callback to be invoked when the start quiz button has been pressed */
    private fun handleStartQuizButtonCallback() {
        findViewById<Button>(R.id.button_start_quiz).apply {
            setOnClickListener {
                visibility = GONE
                displayNextQuestion()
                handleNextQuestionButtonCallback()
            }
        }
    }

    /* Registers a callback to be invoked when the next question button has been pressed */
    private fun handleNextQuestionButtonCallback() {
        this@MainActivity.findViewById<Button>(R.id.button_next_question).apply {
            visibility = VISIBLE
            setOnClickListener {
                displayNextQuestion()
            }
        }
    }

    /* Displays the next question for the current quiz */
    private fun displayNextQuestion() {
        this@MainActivity.findViewById<TextView>(R.id.text_view_question).apply {
            this.text = viewModel.getSelectedCategoryNextQuestion()
        }
    }

    /* Registers a callback to be invoked when a quiz category has been selected */
    private fun handleQuizCategorySelectionCallback() {
        spinner = findViewById(R.id.spinner_categories)
        spinner.onItemSelectedListener = this
        spinner.setSelection(0)
    }

    /* Observes the selected category value */
    private fun observeSelectedCategory() {
        viewModel.getSelectedCategory().observe(this) {
            selectedCategory = it
        }
    }
}