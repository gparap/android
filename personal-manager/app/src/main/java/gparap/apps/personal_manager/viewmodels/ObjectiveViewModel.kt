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
package gparap.apps.personal_manager.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import gparap.apps.personal_manager.data.ObjectiveModel
import gparap.apps.personal_manager.utils.Utils
import kotlinx.coroutines.launch

class ObjectiveViewModel(application: Application) : AndroidViewModel(application) {
    private var objectivesLiveData: LiveData<List<ObjectiveModel>> = MutableLiveData()

    /** Fetches all objectives from the database. */
    fun getObjectives(): LiveData<List<ObjectiveModel>> {
        objectivesLiveData = Utils.getRepository(this.getApplication()).getObjectives()
        return objectivesLiveData
    }

    /** Deletes all objectives from the database. */
    fun deleteAllObjectives() {
        viewModelScope.launch() {
            Utils.getRepository(this@ObjectiveViewModel.getApplication()).deleteAllObjectives()
        }
    }

    /** Inserts an objective into the database. */
    fun insertObjective(objective: ObjectiveModel) {
        viewModelScope.launch() {
            Utils.getRepository(this@ObjectiveViewModel.getApplication()).insertObjective(objective)
        }
    }

    /** Updates an objective in the database. */
    fun updateObjective(objective: ObjectiveModel) {
        viewModelScope.launch() {
            Utils.getRepository(this@ObjectiveViewModel.getApplication()).updateObjective(objective)
        }
    }
}