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
import androidx.lifecycle.ViewModelProvider
import gparap.apps.quiz.utils.AppConstants
import gparap.apps.quiz.viewmodels.MainActivityViewModel

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.createOrOpenDatabase()
        handleQuizCategorySelection()
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
            }

            //geography category selected
            resources.getString(R.string.category_geography) -> {
                viewModel.populateDatabaseTable(AppConstants.DB_TABLE_GEOGRAPHY)
            }

            //history category selected
            resources.getString(R.string.category_history) -> {
                viewModel.populateDatabaseTable(AppConstants.DB_TABLE_HISTORY)
            }

            //literature category selected
            resources.getString(R.string.category_literature) -> {
                viewModel.populateDatabaseTable(AppConstants.DB_TABLE_LITERATURE)
            }

            //mathematics category selected
            resources.getString(R.string.category_mathematics) -> {
                viewModel.populateDatabaseTable(AppConstants.DB_TABLE_MATHS)
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    /* Registers a callback to be invoked when a quiz category has been selected */
    private fun handleQuizCategorySelection() {
        val spinner = findViewById<Spinner>(R.id.spinner_categories)
        spinner.onItemSelectedListener = this
        spinner.setSelection(0)
    }
}