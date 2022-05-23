package gparap.apps.quiz.utils

import android.os.CountDownTimer
import android.widget.TextView
import gparap.apps.quiz.utils.AppConstants.MAX_QUESTION_TIME
import gparap.apps.quiz.utils.AppConstants.ONE_SECOND_INTERVAL
import gparap.apps.quiz.utils.AppConstants.ZERO_QUESTION_TIME

/**
 * This timer represents the maximum time that the user has to answer a question
 */
class CountDownTimer(val timerView: TextView) {
    private var millisUntilFinished = MAX_QUESTION_TIME
    private var isRunning = false
    private val timer = object : CountDownTimer(MAX_QUESTION_TIME, ONE_SECOND_INTERVAL) {
        override fun onTick(millisUntilFinished: Long) {
            this@CountDownTimer.millisUntilFinished = millisUntilFinished
            isRunning = true
            timerView.text = Utils.convertMillisToTimeString(millisUntilFinished)
        }

        override fun onFinish() {
            isRunning = false

            //!!! needed for safety on older devices
            timerView.text = ZERO_QUESTION_TIME
        }

    }

    fun start() {
        timer.start()
    }

    fun stop() {
        timer.cancel()
    }

    fun isRunning(): Boolean {
        return isRunning
    }
}