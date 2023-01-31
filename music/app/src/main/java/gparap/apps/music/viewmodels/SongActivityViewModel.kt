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
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.ViewModel
import gparap.apps.music.R
import gparap.apps.music.data.*
import gparap.apps.music.ui.SongActivity

class SongActivityViewModel : ViewModel() {
    private var songInfo: SongModel? = null
    private var songInfoUrls: HyperlinkModel? = null
    private var songInfoFiles: FileModel? = null
    private var songInfoAttributes: AttributeModel? = null
    private var songInfoCategory: CategoryModel? = null
    private var songInfoLicence: LicenceModel? = null

    /** Update activity's AppBar title from intent data. */
    fun updateTitle(actionBar: ActionBar?, intent: Intent) {
        actionBar?.title = intent.getStringExtra("app_bar_title")
    }

    /** Get song extended data from intent. */
    fun getSongData(intent: Intent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            songInfo = intent.getParcelableExtra("song_info", SongModel::class.java)
            songInfoUrls = intent.getParcelableExtra("song_urls", HyperlinkModel::class.java)
            songInfoFiles = intent.getParcelableExtra("song_file", FileModel::class.java)
            songInfoAttributes = intent.getParcelableExtra("song_attributes", AttributeModel::class.java)
            songInfoCategory  = intent.getParcelableExtra("song_category", CategoryModel::class.java)
            songInfoLicence = intent.getParcelableExtra("song_licence", LicenceModel::class.java)

            //DEBUG
            println(songInfo)
            println(songInfoUrls)
            println(songInfoFiles)
            println(songInfoAttributes)
            println(songInfoCategory)
            println(songInfoLicence)

        } else {
            TODO("VERSION.SDK_INT < TIRAMISU")
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
        }
        activity.findViewById<TextView>(R.id.text_view_song_information_original_author).apply {
            this.text = songInfo?.author
        }
        activity.findViewById<TextView>(R.id.text_view_song_information_modern_performer).apply {
            this.text = songInfo?.performer
        }

        //File info
        activity.findViewById<TextView>(R.id.text_view_file_information_file_name).apply {
            this.text = songInfoFiles?.name
        }
        activity.findViewById<TextView>(R.id.text_view_file_information_mime_type).apply {
            this.text = songInfoFiles?.format
        }
        activity.findViewById<TextView>(R.id.text_view_file_information_file_size).apply {
            this.text = songInfoFiles?.size
        }

        //Url info
        activity.findViewById<TextView>(R.id.text_view_links_image_link).apply {
            this.text = songInfoUrls?.imageUrl
        }
        activity.findViewById<TextView>(R.id.text_view_links_file_link).apply {
            this.text = songInfoUrls?.fileUrl
        }
        activity.findViewById<TextView>(R.id.text_view_links_download_link).apply {
            this.text = songInfoUrls?.downloadUrl
        }

        //License info
        activity.findViewById<TextView>(R.id.text_view_licence_type).apply {
            this.text = songInfoLicence?.type
        }
        activity.findViewById<TextView>(R.id.text_view_licence_attribution_html).apply {
            this.text = songInfoLicence?.attribution
        }
    }
}