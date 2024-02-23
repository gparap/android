package gparap.apps.personal_manager.utils

import android.app.Application
import gparap.apps.personal_manager.PersonalManagerApplication
import gparap.apps.personal_manager.data.ObjectiveRepository

object Utils {
    /** Initializes the data repository. */
    fun getRepository(application: Application): ObjectiveRepository {
        val appContext = application as PersonalManagerApplication
        return appContext.repository
    }
}