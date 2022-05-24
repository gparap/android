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
package gparap.apps.quiz.utils

object AppConstants {
    /* Character set name used for converting data from an array of bytes to a string */
    const val CHARSET_NAME = "UTF-8"

    /* Quiz database */
    const val DATABASE_NAME = "quizDB"
    const val DATABASE_VERSION = 1

    /* Quiz default number of questions */
    const val QUIZ_QUESTIONS_COUNT = 10

    /* Question difficulty values */
    const val QUESTION_DIFFICULTY_PREFIX = "("
    const val QUESTION_DIFFICULTY_SUFFIX = ")"
    const val QUESTION_DIFFICULTY_EASY = "easy"
    const val QUESTION_DIFFICULTY_MEDIUM = "medium"
    const val QUESTION_DIFFICULTY_HARD = "hard"

    /**
     * Score values (based on difficulty)
     */
    const val SCORE_DIFFICULTY_EASY = 100
    const val SCORE_DIFFICULTY_MEDIUM = 200
    const val SCORE_DIFFICULTY_HARD = 300

    /**
     * Quiz question countdown
     */
    const val ONE_SECOND_INTERVAL = 1000L
    const val MAX_QUESTION_TIME = 60000L
    const val ZERO_QUESTION_TIME = "00:00"
}