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
    /**
     * Character set name used for converting data from an array of bytes to a string
     */
    const val CHARSET_NAME = "UTF-8"

    /**
     * Quiz database and its tables
     */
    const val DATABASE_NAME = "quizDB"
    const val DATABASE_VERSION = 1
    const val DB_TABLE_ANIMALS = "animals"
    const val DB_TABLE_GEOGRAPHY = "geography"
    const val DB_TABLE_HISTORY = "history"
    const val DB_TABLE_LITERATURE = "literature"
    const val DB_TABLE_MATHS = "mathematics"

    const val QUIZ_QUESTIONS_COUNT = 10

    const val QUESTION_DIFFICULTY_PREFIX = "("
    const val QUESTION_DIFFICULTY_SUFFIX = ")"
}