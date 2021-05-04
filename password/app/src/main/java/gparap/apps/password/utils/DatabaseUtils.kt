/*
 * Copyright 2021 gparap
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
package gparap.apps.password.utils

import android.content.Context
import android.widget.Toast
import gparap.apps.password.R
import gparap.apps.password.data.DatabaseManager
import gparap.apps.password.data.PasswordModel

/**
 * Utilities for common database operations.
 */
object DatabaseUtils {
    private var instance: DatabaseUtils? = null
    fun getInstance(): DatabaseUtils? {
        if (instance == null) {
            instance = DatabaseUtils
        }
        return instance
    }

    /**
     * Saves a password model (password, title) to the local database.
     */
    fun savePassword(password: String, title: String, context: Context) {
        //password validation
        if (password.isEmpty()) {
            Toast.makeText(context, R.string.toast_empty_password, Toast.LENGTH_SHORT).show()
            return
        }

        //password title validation
        if (title.isEmpty()) {
            Toast.makeText(context, R.string.toast_empty_title, Toast.LENGTH_SHORT).show()
            return
        }

        //create a password model object
        val model = PasswordModel(-1, title, password)

        //save model to database
        val databaseManager = DatabaseManager(context)
        if (databaseManager.insertPassword(model)) {
            Toast.makeText(context, R.string.toast_password_saved, Toast.LENGTH_SHORT).show()
        }
    }
}