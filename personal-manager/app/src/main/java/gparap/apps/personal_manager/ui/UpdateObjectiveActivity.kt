package gparap.apps.personal_manager.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
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
        findViewById<EditText>(R.id.update_objective_due_date).apply { this.setText(objectiveOld?.dueDate) }

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
            findViewById<EditText>(R.id.update_objective_due_date).apply {
                objectiveDueDate = this.text.toString()
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