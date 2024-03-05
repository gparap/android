package gparap.apps.personal_manager.ui

import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import gparap.apps.personal_manager.R
import gparap.apps.personal_manager.data.ObjectiveModel
import gparap.apps.personal_manager.utils.AppConstants.INTENT_EXTRA_OBJECTIVE_DESCRIPTION
import gparap.apps.personal_manager.utils.AppConstants.INTENT_EXTRA_OBJECTIVE_DUE_DATE
import gparap.apps.personal_manager.utils.AppConstants.INTENT_EXTRA_OBJECTIVE_ID
import gparap.apps.personal_manager.utils.AppConstants.INTENT_EXTRA_OBJECTIVE_INCEPTION_DATE
import gparap.apps.personal_manager.utils.AppConstants.INTENT_EXTRA_OBJECTIVE_TITLE
import gparap.apps.personal_manager.utils.Utils
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class UpdateObjectiveActivity : AppCompatActivity() {
    private var objectiveOld: ObjectiveModel? = null
    private var objectiveUpdated: ObjectiveModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_objective)

        //get the objective details from intent
        if (intent != null) {
            objectiveOld = ObjectiveModel(
                intent.getStringExtra(INTENT_EXTRA_OBJECTIVE_TITLE)!!,
                intent.getStringExtra(INTENT_EXTRA_OBJECTIVE_DESCRIPTION)!!,
                intent.getStringExtra(INTENT_EXTRA_OBJECTIVE_DUE_DATE)!!,
                intent.getStringExtra(INTENT_EXTRA_OBJECTIVE_INCEPTION_DATE)!!
            )
            objectiveOld?.id = intent.getIntExtra(INTENT_EXTRA_OBJECTIVE_ID, 0)
        }

        //display the objective details
        findViewById<EditText>(R.id.update_objective_title).apply { this.setText(objectiveOld?.title) }
        findViewById<EditText>(R.id.update_objective_description).apply { this.setText(objectiveOld?.description) }
        val datePicker = findViewById<DatePicker>(R.id.update_objective_due_date).apply {
            //update the date picker with the due date
            val calendar = Calendar.getInstance(Locale.getDefault())
            val simpleDateFormat = SimpleDateFormat("EEE MMM dd hh:mm:ss z yyyy", Locale.getDefault())
            calendar.setTime(simpleDateFormat.parse(objectiveOld?.dueDate.toString())!!)
            this.updateDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
        }

        //update objective in the database
        findViewById<Button>(R.id.update_objective_submit_button).setOnClickListener {
            //get the new objective details, if any TODO: validation
            var objectiveTitle: String
            var objectiveDescription: String
            var objectiveDueDate: String
            findViewById<EditText>(R.id.update_objective_title).apply {
                objectiveTitle = this.text.toString()
            }
            findViewById<EditText>(R.id.update_objective_description).apply {
                objectiveDescription = this.text.toString()
            }
            findViewById<DatePicker>(R.id.update_objective_due_date).apply {
                //get the due date of the objective
                val calendar: Calendar = Calendar.getInstance()
                calendar.set(datePicker.year, datePicker.month, datePicker.dayOfMonth)
                objectiveDueDate = calendar.time.toString()
            }

            //create the updated objective
            objectiveUpdated = ObjectiveModel(
                objectiveTitle,
                objectiveDescription,
                objectiveDueDate,
                objectiveOld?.inceptionDate.toString()
            )
            objectiveUpdated?.id = objectiveOld?.id!!

            //update objective
            lifecycleScope.launch {
                objectiveUpdated?.let { Utils.getRepository(application).updateObjective(it) }
            }
        }
    }
}