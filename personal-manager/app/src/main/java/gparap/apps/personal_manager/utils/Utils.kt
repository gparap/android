package gparap.apps.personal_manager.utils

import android.app.Application
import android.content.res.Resources
import androidx.core.text.isDigitsOnly
import gparap.apps.personal_manager.PersonalManagerApplication
import gparap.apps.personal_manager.R
import gparap.apps.personal_manager.data.ObjectiveModel
import gparap.apps.personal_manager.data.ObjectiveRepository
import java.util.Date

object Utils {
    /** Initializes the data repository. */
    fun getRepository(application: Application): ObjectiveRepository {
        val appContext = application as PersonalManagerApplication
        return appContext.repository
    }

    /**
     * Validates the objective before inserting it into the database and returns a result array.
     *
     * Result Array:
     * - `result[0]`: Validation result boolean
     * - `result[1]`: Validation string message
     */
    fun validateObjective(objective: ObjectiveModel, resources: Resources): Array<Any> {
        //array holding the validation result & message
        val result: Array<Any> = arrayOf(true, "")

        //title must be not be empty
        if (objective.title.trim().isEmpty()) {
            result[0] = false
            result[1] = resources.getString(R.string.validation_title_empty)
            return result
        }
        //title must be an alphanumeric value
        if (objective.title.trim().isDigitsOnly()) {
            result[0] = false
            result[1] = resources.getString(R.string.validation_title_digits_only)
            return result
        }
        //description must be not be empty
        if (objective.description.trim().isEmpty()) {
            result[0] = false
            result[1] = resources.getString(R.string.validation_description_empty)
            return result
        }
        //description must be an alphanumeric value
        if (objective.description.trim().isDigitsOnly()) {
            result[0] = false
            result[1] = resources.getString(R.string.validation_description_digits_only)
            return result
        }
        //due date must be greater than inception date
        if (objective.dueDate.time <= objective.inceptionDate.time) {
            result[0] = false
            result[1] = resources.getString(R.string.validation_due_date_smaller)
            return result
        }
        return result
    }

    /** Returns the due date as a string in the complete form of:
     * "Deadline: (x) days and (y) hours left". */
    fun getDueDateString(dueDate: Date) : String {
        //get the date today
        val today = Date()

        //calculate how much time will pass from today to due date
        val daysLeft = dueDate.time - today.time

        //get the days left to deadline
        val leftDays = daysLeft / 1000 / 60 / 60 /24

        //get the hours left o deadline
        var leftHours = daysLeft / 1000 / 60 / 60
        if (leftHours > 24) {
            leftHours %= 24
        }

        //return the final deadline string
        return "Deadline: $leftDays days and $leftHours hours"
    }
}