/*
 * Copyright (c) 2023 gparap
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
package gparap.apps.music.utils

import android.app.Activity
import android.os.Build
import android.text.Html
import android.view.View
import android.widget.TextView


object Utils {
    /** Hides an empty text view with its accompanying label when no song details are available. */
    fun hideEmptyViews(textView: TextView, field: String?, labelId: Int, activity: Activity) {
        if (field?.isEmpty() == true) {
            textView.visibility = View.GONE
            activity.findViewById<TextView>(labelId).apply {
                visibility = View.GONE
            }
        }
    }

    /** Returns a text that has markup objects containing a link. */
    fun createLink(linkHref: String?, linkText: String?): android.text.Spanned {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(
                "<font color='" + AppConstants.HTML_LINK_COLOR + "'><a href=\"" + linkHref + "\">" +
                        linkText +
                        "</a></font>", Html.FROM_HTML_MODE_LEGACY
            )
        } else {
            @Suppress("DEPRECATION")
            Html.fromHtml(
                "<a href=\"" + linkHref + "\">" +
                        linkText +
                        "</a>"
            )
        }

    }
}