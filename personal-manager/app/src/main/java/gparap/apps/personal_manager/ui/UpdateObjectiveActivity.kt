package gparap.apps.personal_manager.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import gparap.apps.personal_manager.R
import gparap.apps.personal_manager.data.ObjectiveModel

class UpdateObjectiveActivity : AppCompatActivity() {
    private var objective: ObjectiveModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_objective)

        //get the objective details from intent
        if (intent != null) {
            objective = ObjectiveModel(
                intent.getStringExtra("objective_title")!!,
                intent.getStringExtra("objective_description")!!,
                intent.getStringExtra("objective_due_date")!!,
                intent.getStringExtra("objective_inception_date")!!
            )
        }

        //display the objective details
        findViewById<EditText>(R.id.update_objective_title).apply { this.setText(objective?.title) }
        findViewById<EditText>(R.id.update_objective_description).apply { this.setText(objective?.description) }
        findViewById<EditText>(R.id.update_objective_due_date).apply { this.setText(objective?.dueDate) }
    }
}