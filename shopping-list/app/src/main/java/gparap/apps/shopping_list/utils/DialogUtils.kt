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
package gparap.apps.shopping_list.utils

import android.content.Context
import android.view.View
import androidx.appcompat.app.AlertDialog
import gparap.apps.shopping_list.R

class DialogUtils(private val context: Context) {

    /** Creates a custom alert dialog */
    fun createDialog(title: String, view: View, callback: DialogCallback): AlertDialog {
        return AlertDialog.Builder(context)
            .setTitle(title)
            .setPositiveButton(context.resources.getString(R.string.button_add_category_ok)) { _, _ ->
                callback.onPositiveButtonClickListener()
            }
            .setNegativeButton(context.resources.getString(R.string.button_add_category_cancel)) { _, _ ->
                callback.onNegativeButtonClickListener()
            }
            .setView(view)
            .create()
    }

    /** Callback for listening to button dialog clicks */
    interface DialogCallback {
        fun onPositiveButtonClickListener()
        fun onNegativeButtonClickListener()
    }
}

