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

    const val DATABASE_NAME  = "quizDB"
    const val DATABASE_VERSION  = 1
    const val DB_TABLE_ANIMALS = "Animals"
    const val DB_TABLE_GEOGRAPHY = "Geography"
    const val DB_TABLE_HISTORY = "History"
    const val DB_TABLE_LITERATURE = "Literature"
    const val DB_TABLE_MATHS = "Mathematics"

    const val QUIZ_CATEGORIES = 5

    //json files
    const val ANIMALS_JSON = "animals.json"
    const val GEOGRAPHY_JSON = "geography.json"
    const val HISTORY_JSON = "history.json"
    const val LITERATURE_JSON = "literature.json"
    const val MATHS_JSON = "maths.json"
}