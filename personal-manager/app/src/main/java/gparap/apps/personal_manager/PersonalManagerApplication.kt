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
package gparap.apps.personal_manager

import android.app.Application
import gparap.apps.personal_manager.data.ObjectiveDatabase
import gparap.apps.personal_manager.data.ObjectiveRepository

/** Personal Manager application base class. */
class PersonalManagerApplication : Application() {

    /** Application Database. */
    private val database by lazy { ObjectiveDatabase.getInstance(this) }

    /** Data Repository. */
    val repository by lazy { ObjectiveRepository(database.objectiveDao()) }
}