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

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import gparap.apps.personal_manager.utils.AppConstants.OBJECTIVES_COLUMN_DUE_DATE
import gparap.apps.personal_manager.utils.AppConstants.OBJECTIVES_COLUMN_INCEPTION_DATE
import gparap.apps.personal_manager.utils.AppConstants.OBJECTIVES_TABLE

/** This data class describes an objective. */
@Entity(tableName = OBJECTIVES_TABLE)
data class ObjectiveModel(
    val title: String,
    val description: String,

    //TODO: create converters for Date values
    @ColumnInfo(name = OBJECTIVES_COLUMN_DUE_DATE) val dueDate: String,
    @ColumnInfo(name = OBJECTIVES_COLUMN_INCEPTION_DATE) val inceptionDate: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
