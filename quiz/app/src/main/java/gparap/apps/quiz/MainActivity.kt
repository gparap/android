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
import android.widget.AdapterView
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import gparap.apps.quiz.data.QuizDatabase
import gparap.apps.quiz.data.QuizModel
import gparap.apps.quiz.utils.AppConstants
import gparap.apps.quiz.utils.Utils

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var database: QuizDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //create database or open if exists
        database = QuizDatabase(this, null)
        database.writableDatabase

        //handle quiz categories
        val spinner = findViewById<Spinner>(R.id.spinner_categories)
        spinner.onItemSelectedListener = this
        spinner.setSelection(0)
    }

    override fun onDestroy() {
        super.onDestroy()
        database.close()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (position == 0) return

        var quiz: List<QuizModel>? = null

        when (parent?.getItemAtPosition(position).toString()) {
            //Animals category selected
            resources.getString(R.string.category_animals) -> {
                //populate the database table if it is empty
                if (database.isTableEmpty(AppConstants.DB_TABLE_ANIMALS)) {
                    val jsonData = Utils.getJSONDataByCategory(this, AppConstants.ANIMALS_JSON)
                    if (jsonData != null) {
                        quiz = Utils.getQuizModelFromJSON(jsonData)
                    }

                    //fill in the database table the first time
                    if (quiz != null) {
                        for (q in quiz) {
                            database.populateTable(AppConstants.DB_TABLE_ANIMALS, q)
                        }
                    }
                }
            }

            //Geography category selected
            resources.getString(R.string.category_geography) -> {
                //populate the database table if it is empty
                if (database.isTableEmpty(AppConstants.DB_TABLE_GEOGRAPHY)) {
                    val jsonData = Utils.getJSONDataByCategory(this, AppConstants.GEOGRAPHY_JSON)
                    if (jsonData != null) {
                        quiz = Utils.getQuizModelFromJSON(jsonData)
                    }

                    //fill in the database table the first time
                    if (quiz != null) {
                        for (q in quiz) {
                            database.populateTable(AppConstants.DB_TABLE_GEOGRAPHY, q)
                        }
                    }
                }
            }

            //History category selected
            resources.getString(R.string.category_history) -> {
                //populate the database table if it is empty
                if (database.isTableEmpty(AppConstants.DB_TABLE_HISTORY)) {
                    val jsonData = Utils.getJSONDataByCategory(this, AppConstants.HISTORY_JSON)
                    if (jsonData != null) {
                        quiz = Utils.getQuizModelFromJSON(jsonData)
                    }

                    //fill in the database table the first time
                    if (quiz != null) {
                        for (q in quiz) {
                            database.populateTable(AppConstants.DB_TABLE_HISTORY, q)
                        }
                    }
                }
            }

            //Literature category selected
            resources.getString(R.string.category_literature) -> {
                //populate the database table if it is empty
                if (database.isTableEmpty(AppConstants.DB_TABLE_LITERATURE)) {
                    val jsonData = Utils.getJSONDataByCategory(this, AppConstants.LITERATURE_JSON)
                    if (jsonData != null) {
                        quiz = Utils.getQuizModelFromJSON(jsonData)
                    }

                    //fill in the database table the first time
                    if (quiz != null) {
                        for (q in quiz) {
                            database.populateTable(AppConstants.DB_TABLE_LITERATURE, q)
                        }
                    }
                }
            }

            //Mathematics category selected
            resources.getString(R.string.category_mathematics) -> {
                //populate the database table if it is empty
                if (database.isTableEmpty(AppConstants.DB_TABLE_MATHS)) {
                    val jsonData = Utils.getJSONDataByCategory(this, AppConstants.MATHS_JSON)
                    if (jsonData != null) {
                        quiz = Utils.getQuizModelFromJSON(jsonData)
                    }

                    //fill in the database table the first time
                    if (quiz != null) {
                        for (q in quiz) {
                            database.populateTable(AppConstants.DB_TABLE_MATHS, q)
                        }
                    }
                }
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}
}