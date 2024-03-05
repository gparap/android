/*
 * Copyright 2024 gparap
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
package gparap.apps.personal_manager.ui

import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import gparap.apps.personal_manager.R
import gparap.apps.personal_manager.data.ObjectiveModel
import gparap.apps.personal_manager.utils.Utils
import kotlinx.coroutines.launch
import java.util.Calendar

class AddObjectiveActivity : AppCompatActivity() {
    private var objectiveTitle: String = ""
    private var objectiveDescription: String = ""
    private var objectiveDueDate: String = ""
    private var objectiveInceptionDate: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_objective)

        //get the date picker widget
        val datePicker = findViewById<DatePicker>(R.id.add_objective_due_date)

        //add objective TODO: validation
        findViewById<Button>(R.id.add_objective_submit_button).setOnClickListener {
            //get the objective title & description details that the user submitted
            findViewById<EditText>(R.id.add_objective_title).apply {
                objectiveTitle = this.text.toString()
            }
            findViewById<EditText>(R.id.add_objective_description).apply {
                objectiveDescription = this.text.toString()
            }

            //get the device's calendar instance
            val calendar: Calendar = Calendar.getInstance()

            //set the inception date of the objective
            objectiveInceptionDate = calendar.time.toString()

            //set the due date of the objective
            calendar.set(datePicker.year, datePicker.month, datePicker.dayOfMonth)
            objectiveDueDate = calendar.time.toString()

            //add objective into the database
            lifecycleScope.launch {
                Utils.getRepository(application).insertObjective(
                    ObjectiveModel(
                        objectiveTitle,
                        objectiveDescription,
                        objectiveDueDate,
                        objectiveInceptionDate
                    )
                )
            }
        }
    }
}