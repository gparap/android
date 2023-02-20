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
package gparap.apps.music.viewmodels

import android.content.Context
import android.content.Intent
import android.os.Build
import android.text.method.LinkMovementMethod
import android.webkit.WebView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import gparap.apps.music.R
import gparap.apps.music.data.*
import gparap.apps.music.ui.SongActivity
import gparap.apps.music.utils.AppConstants
import gparap.apps.music.utils.Utils

class SongActivityViewModel : ViewModel() {
    private var songInfo: SongModel? = null
    private var songInfoUrls: HyperlinkModel? = null
    private var songInfoFiles: FileModel? = null
    private var songInfoAttributes: AttributeModel? = null
    private var songInfoCategory: CategoryModel? = null
    private var songInfoLicence: LicenceModel? = null

    /** Update activity's AppBar title from intent data. */
    fun updateTitle(actionBar: ActionBar?, intent: Intent) {
        actionBar?.title = intent.getStringExtra(AppConstants.EXTRAS_APPBAR_TITLE)
    }

    /** Get song extended data from intent. */
    @Suppress("DEPRECATION")
    fun getSongData(intent: Intent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            songInfo = intent.getParcelableExtra(AppConstants.EXTRAS_SONG_INFO, SongModel::class.java)
            songInfoUrls = intent.getParcelableExtra(AppConstants.EXTRAS_SONG_URLS, HyperlinkModel::class.java)
            songInfoFiles = intent.getParcelableExtra(AppConstants.EXTRAS_SONG_FILE, FileModel::class.java)
            songInfoAttributes =
                intent.getParcelableExtra(AppConstants.EXTRAS_SONG_ATTRIBUTES, AttributeModel::class.java)
            songInfoCategory = intent.getParcelableExtra(AppConstants.EXTRAS_SONG_CATEGORY, CategoryModel::class.java)
            songInfoLicence = intent.getParcelableExtra(AppConstants.EXTRAS_SONG_LICENCE, LicenceModel::class.java)

        } else {
            songInfo = intent.getParcelableExtra(AppConstants.EXTRAS_SONG_INFO)
            songInfoUrls = intent.getParcelableExtra(AppConstants.EXTRAS_SONG_URLS)
            songInfoFiles = intent.getParcelableExtra(AppConstants.EXTRAS_SONG_FILE)
            songInfoAttributes = intent.getParcelableExtra(AppConstants.EXTRAS_SONG_ATTRIBUTES)
            songInfoCategory = intent.getParcelableExtra(AppConstants.EXTRAS_SONG_CATEGORY)
            songInfoLicence = intent.getParcelableExtra(AppConstants.EXTRAS_SONG_LICENCE)
        }
    }

    /** */
    fun displaySongDetails(context: Context) {
        val activity = (context as SongActivity)

        //Category info
        activity.findViewById<TextView>(R.id.text_view_category_music_genre).apply {
            this.text = songInfoCategory?.genre
        }
        activity.findViewById<TextView>(R.id.text_view_category_time_period).apply {
            this.text = songInfoCategory?.period
            Utils.hideEmptyViews(
                this, songInfoCategory?.period, R.id.label_category_time_period, activity
            )
        }

        //Song info
        activity.findViewById<TextView>(R.id.text_view_song_information_song_title).apply {
            this.text = songInfo?.title
        }
        activity.findViewById<TextView>(R.id.text_view_song_information_song_duration).apply {
            this.text = songInfo?.duration
        }
        activity.findViewById<TextView>(R.id.text_view_song_information_song_description).apply {
            this.text = songInfo?.description
        }
        activity.findViewById<TextView>(R.id.text_view_song_information_song_date).apply {
            this.text = songInfo?.date
            Utils.hideEmptyViews(
                this, songInfo?.date, R.id.label_song_information_song_date, activity
            )
        }
        activity.findViewById<TextView>(R.id.text_view_song_information_original_author).apply {
            this.text = songInfo?.author
            Utils.hideEmptyViews(
                this, songInfo?.author, R.id.label_song_information_original_author, activity
            )
        }
        activity.findViewById<TextView>(R.id.text_view_song_information_modern_performer).apply {
            this.text = songInfo?.performer
            Utils.hideEmptyViews(
                this, songInfo?.performer, R.id.label_song_information_modern_performer, activity
            )
        }

        //File info
        activity.findViewById<TextView>(R.id.text_view_file_information_mime_type).apply {
            this.text = songInfoFiles?.format
        }
        activity.findViewById<TextView>(R.id.text_view_file_information_file_size).apply {
            this.text = songInfoFiles?.size
            Utils.hideEmptyViews(
                this, songInfoFiles?.size, R.id.label_file_information_file_size, activity
            )
        }

        //Url info
        activity.findViewById<TextView>(R.id.text_view_links_image_link).apply {
            Utils.hideEmptyViews(
                this, songInfoUrls?.imageUrl, R.id.label_links_image_link, activity
            )
            //display as link
            this.text = Utils.createLink(songInfoUrls?.imageUrl, songInfoUrls?.imageUrl)
            this.movementMethod = LinkMovementMethod.getInstance()
        }
        activity.findViewById<TextView>(R.id.text_view_links_download_link).apply {
            //display as link
            this.text = Utils.createLink(songInfoUrls?.downloadUrl, songInfoUrls?.downloadUrl)
            this.movementMethod = LinkMovementMethod.getInstance()
        }

        //Attribution info
        activity.findViewById<WebView>(R.id.text_view_licence_attribution_html).apply {
            var htmlText = songInfoLicence?.attribution!!

            //change the background color and the text color if we are on dark mode
            val uiMode = resources.configuration.uiMode.and(android.content.res.Configuration.UI_MODE_NIGHT_MASK)
            if (uiMode == android.content.res.Configuration.UI_MODE_NIGHT_YES) {
                this.setBackgroundColor(ContextCompat.getColor(context, com.google.android.material.R.color.material_on_background_disabled))
                htmlText = "<font color='white'" +  songInfoLicence?.attribution!! +  "</font>"
            }

            this.loadDataWithBaseURL(
                null, htmlText, AppConstants.MIME_TYPE, AppConstants.ENCODING, null
            )
        }
    }
}