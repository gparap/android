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
package gparap.apps.personal_finances.utils

import android.app.Activity
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import gparap.apps.personal_finances.R

object Utils {
    fun updateAppSectionTitle(activity: Activity) {
        activity.findViewById<ConstraintLayout>(R.id.layout_appLogoWithSectionTitle).apply {
            this.findViewById<TextView>(R.id.textView_sectionTitle_current).apply {
                this.text = getSectionTitleByActivityName(activity)
            }
        }
    }

    fun getSectionTitleByActivityName(activity: Activity) : String {
        return when(activity.localClassName){
            "ui.AllTransactionsActivity" -> return activity.resources.getString(R.string.desc_all_transactions)
            "ui.TopUpTransactionsActivity" -> return activity.resources.getString(R.string.desc_top_up_transactions)
            "ui.ExpenseTransactionsActivity" -> return activity.resources.getString(R.string.desc_expense_transactions)
            else -> {}
        }.toString()
    }
}