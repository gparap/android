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

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.View.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import gparap.apps.quiz.utils.AppConstants
import gparap.apps.quiz.utils.CountDownTimerListener
import gparap.apps.quiz.viewmodels.MainActivityViewModel
import java.util.*

class MainActivity
    : AppCompatActivity(), AdapterView.OnItemSelectedListener, CountDownTimerListener {
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var spinner: Spinner
    private var selectedCategory = ""
    private var spinnerVisibility = VISIBLE
    private var buttonStartVisibility = VISIBLE
    private var layoutIntroVisibility = VISIBLE
    private var layoutQuizVisibility = GONE

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
        observeSpinnerVisibility()
        observeButtonStartVisibility()
        observeLayoutIntroVisibility()
        observeLayoutQuizVisibility()
        createQuestionTimer()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.closeDatabase()
        viewModel.stopQuestionTimer()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (position == 0) return

        //setup the selected category and its data
        viewModel.populateDatabaseTable(parent?.getItemAtPosition(position).toString().lowercase())
        viewModel.setSelectedCategory(parent?.getItemAtPosition(position).toString())

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
        val radioGroup: RadioGroup? = findViewById(R.id.radio_group_choices)

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
            displayNextQuestion(true)
            displayMultipleChoices()
            displayDifficulty()
            updateQuestionCounter()

            //when reach the end of the quiz get answer and swap its layout with the results one
        } else {
            //get answer
            radioGroup?.clearCheck()
            viewModel.addUserAnswer(answer)

            //swap layouts
            findViewById<ConstraintLayout>(R.id.main_layout_quiz).apply {
                viewModel.setLayoutQuizVisibility(GONE)
            }
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

    /* Apply a red/green color to the radio buttons describing wrong/right answers of a question */
    private fun highlightQuizAnswers() {
        findViewById<RadioGroup>(R.id.radio_group_choices).apply {
            //restore the neutral color to the radio button before applying red/green
            this.setBackgroundColor(Color.WHITE)

            //check radio button to indicate the user answer
            when (viewModel.getQuizQuestionUserAnswerIndex()) {
                //user did not select an answer or counter expired
                -1 -> findViewById<RadioGroup?>(R.id.radio_group_choices).apply { clearCheck() }

                //user did select an answer
                0 -> check(R.id.radio_button_choice_one)
                1 -> check(R.id.radio_button_choice_two)
                2 -> check(R.id.radio_button_choice_three)
                3 -> check(R.id.radio_button_choice_four)
            }
        }

        //apply a red/green color to the radio buttons describing wrong/right answers
        when (viewModel.getQuizQuestionRightAnswerIndex()) {
            0 -> applyRadioButtonsColors(Color.GREEN, Color.RED, Color.RED, Color.RED)
            1 -> applyRadioButtonsColors(Color.RED, Color.GREEN, Color.RED, Color.RED)
            2 -> applyRadioButtonsColors(Color.RED, Color.RED, Color.GREEN, Color.RED)
            3 -> applyRadioButtonsColors(Color.RED, Color.RED, Color.RED, Color.GREEN)
        }
    }

    /* Apply colors to the radio buttons holding the multiple choices (in default order always) */
    private fun applyRadioButtonsColors(color1: Int, color2: Int, color3: Int, color4: Int) {
        findViewById<RadioButton>(R.id.radio_button_choice_one).apply {
            this.setBackgroundColor(color1)
        }
        findViewById<RadioButton>(R.id.radio_button_choice_two).apply {
            this.setBackgroundColor(color2)
        }
        findViewById<RadioButton>(R.id.radio_button_choice_three).apply {
            this.setBackgroundColor(color3)
        }
        findViewById<RadioButton>(R.id.radio_button_choice_four).apply {
            this.setBackgroundColor(color4)
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

        //clear question timer
        viewModel.stopQuestionTimer()
        viewModel.removeQuestionTimerListener()
        findViewById<TextView>(R.id.text_view_timer).apply {
            text = AppConstants.ZERO_QUESTION_TIME
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
    private fun displayNextQuestion(isQuizRunning: Boolean) {
        this@MainActivity.findViewById<TextView>(R.id.text_view_question).apply {
            this.text = viewModel.getSelectedCategoryNextQuestion()
        }
        if (isQuizRunning) {
            resetQuestionTimer()
        }
    }

    /* Registers a callback to be invoked when the user presses the button to return to categories */
    private fun handleReturnToCategoriesCallback() {
        findViewById<Button>(R.id.button_back_to_categories).apply {
            //hide current layout
            this@MainActivity.findViewById<ConstraintLayout>(R.id.main_layout_quiz).apply {
                viewModel.setLayoutQuizVisibility(GONE)
            }
            this.visibility = GONE
            this@MainActivity.findViewById<ImageButton>(R.id.button_next_question)
                .apply { visibility = GONE }
            this@MainActivity.findViewById<ImageButton>(R.id.button_prev_question)
                .apply { visibility = GONE }

            //activate submit button
            this@MainActivity.findViewById<Button>(R.id.button_submit_answer).apply {
                isActivated = true
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    setBackgroundColor(resources.getColor(R.color.purple_500, null))
                } else {
                    setBackgroundColor(ContextCompat.getColor(this@MainActivity,
                        R.color.purple_500))
                }
            }

            //uncheck and restore the color of radio buttons
            this@MainActivity.findViewById<RadioGroup>(R.id.radio_group_choices).apply {
                clearCheck()
            }
            this@MainActivity.findViewById<RadioButton>(R.id.radio_button_choice_one).apply {
                this.setBackgroundColor(Color.WHITE)
            }
            this@MainActivity.findViewById<RadioButton>(R.id.radio_button_choice_two).apply {
                this.setBackgroundColor(Color.WHITE)
            }
            this@MainActivity.findViewById<RadioButton>(R.id.radio_button_choice_three).apply {
                this.setBackgroundColor(Color.WHITE)
            }
            this@MainActivity.findViewById<RadioButton>(R.id.radio_button_choice_four).apply {
                this.setBackgroundColor(Color.WHITE)
            }

            //display the starting layout
            resetSpinner()
            this@MainActivity.findViewById<Button>(R.id.button_start_quiz).apply {
                viewModel.setButtonStartVisibility(VISIBLE)
            }
            this@MainActivity.findViewById<LinearLayout>(R.id.main_layout_intro).apply {
                viewModel.setLayoutIntroVisibility(VISIBLE)
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

    /* Registers a callback to be invoked when the user presses the button to check their answers */
    private fun handleCheckAnswersCallback() {
        findViewById<Button>(R.id.button_check_answers).setOnClickListener {
            //hide current layout
            findViewById<ConstraintLayout>(R.id.main_layout_results).apply { visibility = GONE }

            //display the quiz layout
            this@MainActivity.findViewById<ConstraintLayout>(R.id.main_layout_quiz).apply {
                viewModel.setLayoutQuizVisibility(VISIBLE)

                //deactivate submit button
                this.findViewById<Button>(R.id.button_submit_answer).apply {
                    this.isClickable = false
                    this.setBackgroundColor(Color.LTGRAY)
                }

                //enable image buttons (arrows)
                this.findViewById<ImageButton>(R.id.button_next_question).apply {
                    visibility = VISIBLE
                }
                this.findViewById<ImageButton>(R.id.button_prev_question).apply {
                    visibility = VISIBLE
                }
            }

            //handle the return to categories
            this@MainActivity.findViewById<Button>(R.id.button_back_to_categories).apply {
                visibility = VISIBLE
                setOnClickListener { handleReturnToCategoriesCallback() }
            }

            //reset the counter for questions
            viewModel.resetQuestionCounter()

            //display questions & answers
            displayNextQuestion(false)
            displayMultipleChoices()
            highlightQuizAnswers()
            displayDifficulty()
            updateQuestionCounter()

            //enable the arrows to go back and forth the quiz questions
            handleNextQuestionButtonCallback()
            handlePreviousQuestionButtonCallback()
        }
    }

    /* Registers a callback to be invoked when user presses the button to restart quiz */
    private fun handleRestartQuizCallback() {
        findViewById<Button>(R.id.button_restart_quiz).setOnClickListener {
            //hide current layout
            findViewById<ConstraintLayout>(R.id.main_layout_results).apply { visibility = GONE }

            //display the quiz layout
            this@MainActivity.findViewById<ConstraintLayout>(R.id.main_layout_quiz).apply {
                viewModel.setLayoutQuizVisibility(VISIBLE)
            }

            //reset the counter for questions
            viewModel.resetQuestionCounter()

            //display and handle questions & answers
            displayNextQuestion(true)
            displayMultipleChoices()
            displayDifficulty()
            updateQuestionCounter()
            handleSubmitAnswerButtonCallback()
        }
    }

    /* Registers a callback to be invoked when user presses the button to change category */
    private fun handleChangeCategoryCallback() {
        findViewById<Button>(R.id.button_change_category).setOnClickListener {
            //hide current layout
            findViewById<ConstraintLayout>(R.id.main_layout_results).apply { visibility = GONE }

            //display the starting layout
            resetSpinner()
            findViewById<Button>(R.id.button_start_quiz).apply {
                viewModel.setButtonStartVisibility(VISIBLE)
            }
            findViewById<LinearLayout>(R.id.main_layout_intro).apply {
                viewModel.setLayoutIntroVisibility(VISIBLE)
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

                if (viewModel.getSelectedCategory().value?.isEmpty()!!) return@setOnClickListener

                //hide the current layout
                viewModel.setButtonStartVisibility(GONE)
                viewModel.setSpinnerVisibility(GONE).apply {
                    spinner.visibility = viewModel.getSpinnerVisibility().value!!
                }

                this@MainActivity.findViewById<LinearLayout>(R.id.main_layout_intro).apply {
                    viewModel.setLayoutIntroVisibility(INVISIBLE)
                }

                //display the quiz layout
                this@MainActivity.findViewById<ConstraintLayout>(R.id.main_layout_quiz).apply {
                    viewModel.setLayoutQuizVisibility(VISIBLE)
                }

                //display and handle questions & answers
                displayNextQuestion(true)
                displayMultipleChoices()
                displayDifficulty()
                updateQuestionCounter()
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
                highlightQuizAnswers()
                updateQuestionCounter()
            }
        }
    }

    /* Registers a callback to be invoked when the next question button has been pressed */
    private fun handleNextQuestionButtonCallback() {
        this@MainActivity.findViewById<ImageButton>(R.id.button_next_question).apply {
            setOnClickListener {
                displayNextQuestion(false)
                displayMultipleChoices()
                highlightQuizAnswers()
                updateQuestionCounter()
            }
        }
    }

    /* Registers a callback to be invoked when a quiz category has been selected */
    private fun handleQuizCategorySelectionCallback() {
        spinner = findViewById(R.id.spinner_categories)
        spinner.onItemSelectedListener = this
        spinner.setSelection(0)

        if (viewModel.getSpinnerVisibility().value != null) {
            spinner.visibility = viewModel.getSpinnerVisibility().value!!
        }

    }

    /* Observes the selected category value */
    private fun observeSelectedCategory() {
        viewModel.getSelectedCategory().observe(this) {
            selectedCategory = it
        }
    }

    /* Observes the visibility of the spinner */
    private fun observeSpinnerVisibility() {
        viewModel.getSpinnerVisibility().observe(this) {
            spinnerVisibility = it
        }
    }

    /* Initializes the spinner */
    private fun resetSpinner() {
        viewModel.setSpinnerVisibility(VISIBLE).apply {
            spinner.visibility = viewModel.getSpinnerVisibility().value!!
            spinner.setSelection(0)
        }
    }

    /* Observes the visibility of the start quiz button */
    private fun observeButtonStartVisibility() {
        viewModel.getButtonStartVisibility().observe(this) {
            buttonStartVisibility = it
            findViewById<Button>(R.id.button_start_quiz).apply {
                visibility = buttonStartVisibility
            }
        }
    }

    /* Observes the visibility of the introductory layout */
    private fun observeLayoutIntroVisibility() {
        viewModel.getLayoutIntroVisibility().observe(this) {
            layoutIntroVisibility = it
            this@MainActivity.findViewById<LinearLayout>(R.id.main_layout_intro).apply {
                visibility = layoutIntroVisibility
            }
        }
    }

    /* Observes the visibility of the quiz layout */
    private fun observeLayoutQuizVisibility() {
        viewModel.getLayoutQuizVisibility().observe(this) {
            layoutQuizVisibility = it
            this@MainActivity.findViewById<ConstraintLayout>(R.id.main_layout_quiz).apply {
                visibility = layoutQuizVisibility
            }
        }
    }

    /* Creates a countdown timer for a quiz questions */
    private fun createQuestionTimer() {
        this@MainActivity.findViewById<TextView>(R.id.text_view_timer).apply {
            viewModel.createQuestionTimer(this, this@MainActivity)
        }
        viewModel.addQuestionTimerListener(this)
    }

    /* Starts the question count down from the beginning */
    private fun resetQuestionTimer() {
        if (viewModel.isQuestionTimerRunning() == true) {
            viewModel.stopQuestionTimer()
        }
        viewModel.startQuestionTimer()
    }

    override fun onCountDownTimerFinished() {
        submitAnswer()
        viewModel.removeQuestionTimerListener()
    }
}