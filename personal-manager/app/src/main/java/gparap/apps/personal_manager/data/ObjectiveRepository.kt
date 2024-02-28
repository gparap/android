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
package gparap.apps.personal_manager.data

import androidx.lifecycle.LiveData

/** Data repository. */
class ObjectiveRepository(private val objectiveDao: ObjectiveDao) : ObjectiveDao {

    override suspend fun insertObjective(objectiveModel: ObjectiveModel) {
        objectiveDao.insertObjective(objectiveModel)
    }

    override fun getObjectives(): LiveData<List<ObjectiveModel>> {
        return objectiveDao.getObjectives()
    }

    override suspend fun updateObjective(objectiveModel: ObjectiveModel) {
        objectiveDao.updateObjective(objectiveModel)
    }

    override suspend fun deleteObjective(objectiveModel: ObjectiveModel) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllObjectives() {
        objectiveDao.deleteAllObjectives()
    }
}