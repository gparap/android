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
import java.util.*

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

        //remove welcome texts and show the high score for this quiz category
        findViewById<TextView>(R.id.text_view_welcome).apply { visibility = GONE }
        findViewById<TextView>(R.id.text_view_choose_category).apply { visibility = GONE }
        findViewById<TextView>(R.id.text_view_selected_category_high_score).apply {
            visibility = VISIBLE
            text = resources.getString(R.string.text_selected_category_high_score)
                .plus(viewModel.getUserHighScore().toString())
        }

        //prepare the questions for the quiz
        viewModel.populateSelectedCategoryQuestions()
        viewModel.shuffleSelectedCategoryQuestions()

        //quiz is ready to start now
        handleStartQuizButtonCallback()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    /* The user submits an answer for the current quiz question */
    private fun submitAnswer() {
        //check if one radio button is selected
        val radioGroup: RadioGroup? = findViewById<RadioGroup>(R.id.radio_group_choices).apply {
            if (checkedRadioButtonId == -1) {
                Toast.makeText(this@MainActivity,
                    resources.getString(R.string.toast_select_answer_error),
                    Toast.LENGTH_SHORT)
                    .show()
                return
            }
        }

        //get the user answer from the radio button text
        var answer = ""
        when (radioGroup?.checkedRadioButtonId) {
            R.id.radio_button_choice_one ->
                answer = findViewById<RadioButton>(R.id.radio_button_choice_one).text.toString()
            R.id.radio_button_choice_two ->
                answer = findViewById<RadioButton>(R.id.radio_button_choice_two).text.toString()
            R.id.radio_button_choice_three ->
                answer = findViewById<RadioButton>(R.id.radio_button_choice_three).text.toString()
            R.id.radio_button_choice_four ->
                answer = findViewById<RadioButton>(R.id.radio_button_choice_four).text.toString()
        }

        //get answer and continue the quiz unless we reach the end
        if (viewModel.getQuestionsCounter() != AppConstants.QUIZ_QUESTIONS_COUNT) {
            radioGroup?.clearCheck()
            viewModel.addUserAnswer(answer)
            displayNextQuestion()
            displayMultipleChoices()
            displayDifficulty()
            updateQuestionCounter()

            //when reach the end of the quiz get answer and swap its layout with the results one
        } else {
            //get answer
            radioGroup?.clearCheck()
            viewModel.addUserAnswer(answer)

            //swap layouts
            findViewById<ConstraintLayout>(R.id.main_layout_quiz).apply { visibility = GONE }
            findViewById<ConstraintLayout>(R.id.main_layout_results).apply { visibility = VISIBLE }
            displayQuizResults()
        }
    }

    /* Update the text of the view that displays the questions counter ie. "Question 1..10 of 10" */
    private fun updateQuestionCounter() {
        findViewById<TextView>(R.id.text_view_questions_counter).apply {
            text = this@MainActivity.resources.getString(R.string.text_questions_counter_prefix)
                .plus(viewModel.getQuestionsCounter())
                .plus(this@MainActivity.resources.getString(R.string.text_questions_counter_suffix))
                .plus(AppConstants.QUIZ_QUESTIONS_COUNT)
        }
    }

    /* Displays the results of the current quiz to the user */
    private fun displayQuizResults() {
        //update category field
        findViewById<TextView>(R.id.text_view_outro_category).apply {
            text = viewModel.getSelectedCategory().value.toString()
        }
        //update average difficulty field
        findViewById<TextView>(R.id.text_view_outro_difficulty).apply {
            text = viewModel.getQuizDifficulty()
        }
        //update user right answers field
        findViewById<TextView>(R.id.text_view_outro_answers).apply {
            text = viewModel.getUserRightAnswersToQuiz().toString()
                .plus(resources.getString(R.string.text_questions_counter_suffix))
                .plus(AppConstants.QUIZ_QUESTIONS_COUNT)
        }
        //update user score field
        findViewById<TextView>(R.id.text_view_outro_score).apply {
            text = viewModel.getUserScore().toString()
        }
        //update user high score field
        findViewById<TextView>(R.id.text_view_outro_high_score).apply {
            //check if high score should be updated
            val score = viewModel.getUserScore()
            val highScore = viewModel.getUserHighScore()
            if (highScore < score) {
                viewModel.setUserHighScore(score)
            }

            text = viewModel.getUserHighScore().toString()
        }

        //handle the buttons callbacks in quiz results screen
        handleCheckAnswersCallback()
        handleRestartQuizCallback()
        handleChangeCategoryCallback()
    }

    /* Update the text of the view that displays the question's difficulty ie. (EASY) */
    private fun displayDifficulty() {
        val difficulty = viewModel.getQuestionDifficulty()
        findViewById<TextView>(R.id.text_view_question_difficulty).apply {
            var string =
                AppConstants.QUESTION_DIFFICULTY_PREFIX
                    .plus(difficulty)
                    .plus(AppConstants.QUESTION_DIFFICULTY_SUFFIX)
            string = string.uppercase(Locale.getDefault())
            text = string
        }
    }

    /* Displays multiple choices for a question - one of them is the right answer */
    private fun displayMultipleChoices() {
        val choices = viewModel.getMultipleChoices()

        findViewById<RadioButton>(R.id.radio_button_choice_one).apply { text = choices[0] }
        findViewById<RadioButton>(R.id.radio_button_choice_two).apply { text = choices[1] }
        findViewById<RadioButton>(R.id.radio_button_choice_three).apply { text = choices[2] }
        findViewById<RadioButton>(R.id.radio_button_choice_four).apply { text = choices[3] }
    }

    /* Displays the previous question for the current quiz */
    private fun displayPreviousQuestion() {
        this@MainActivity.findViewById<TextView>(R.id.text_view_question).apply {
            this.text = viewModel.getSelectedCategoryPreviousQuestion()
        }
    }

    /* Displays the next question for the current quiz */
    private fun displayNextQuestion() {
        this@MainActivity.findViewById<TextView>(R.id.text_view_question).apply {
            this.text = viewModel.getSelectedCategoryNextQuestion()
        }
    }

    /* Registers a callback to be invoked when the user presses the button to check their answers */
    private fun handleCheckAnswersCallback() {
    }

    /* Registers a callback to be invoked when user presses the button to restart quiz */
    private fun handleRestartQuizCallback() {
    }

    /* Registers a callback to be invoked when user presses the button to change category */
    private fun handleChangeCategoryCallback() {
        findViewById<Button>(R.id.button_change_category).setOnClickListener {
            //hide current layout
            findViewById<ConstraintLayout>(R.id.main_layout_results).apply { visibility = GONE }

            //display the starting layout
            findViewById<Spinner>(R.id.spinner_categories).apply {
                visibility = VISIBLE
                setSelection(0)
            }
            findViewById<Button>(R.id.button_start_quiz).apply { visibility = VISIBLE }
            findViewById<LinearLayout>(R.id.main_layout_intro).apply {
                visibility = VISIBLE
                this.findViewById<TextView>(R.id.text_view_choose_category).apply {
                    visibility = VISIBLE
                }
                this.findViewById<TextView>(R.id.text_view_selected_category_high_score).apply {
                    visibility = GONE
                }
            }

            //reset the current quiz
            viewModel.resetQuiz()
        }
    }

    /* Registers a callback to be invoked when the submit answer button has been pressed */
    private fun handleSubmitAnswerButtonCallback() {
        findViewById<Button>(R.id.button_submit_answer).setOnClickListener {
            submitAnswer()
        }
    }

    /* Registers a callback to be invoked when the start quiz button has been pressed */
    private fun handleStartQuizButtonCallback() {
        findViewById<Button>(R.id.button_start_quiz).apply {
            setOnClickListener {

                //hide the current layout
                this.visibility = GONE
                spinner.visibility = GONE
                this@MainActivity.findViewById<LinearLayout>(R.id.main_layout_intro).apply {
                    visibility = INVISIBLE
                }

                //display the quiz layout
                this@MainActivity.findViewById<ConstraintLayout>(R.id.main_layout_quiz).apply {
                    visibility = VISIBLE
                }

                //display and handle questions & answers
                displayNextQuestion()
                displayMultipleChoices()
                displayDifficulty()
                handleNextQuestionButtonCallback()
                handlePreviousQuestionButtonCallback()
                handleSubmitAnswerButtonCallback()
            }
        }
    }

    /* Registers a callback to be invoked when the previous question button has been pressed */
    private fun handlePreviousQuestionButtonCallback() {
        this@MainActivity.findViewById<ImageButton>(R.id.button_prev_question).apply {
            setOnClickListener {
                displayPreviousQuestion()
                displayMultipleChoices()
                updateQuestionCounter()
            }
        }
    }

    /* Registers a callback to be invoked when the next question button has been pressed */
    private fun handleNextQuestionButtonCallback() {
        this@MainActivity.findViewById<ImageButton>(R.id.button_next_question).apply {
            setOnClickListener {
                displayNextQuestion()
                displayMultipleChoices()
                updateQuestionCounter()
            }
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