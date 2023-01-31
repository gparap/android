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

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.ViewModel
import gparap.apps.music.data.*

class SongActivityViewModel : ViewModel() {
    var songInfo: SongModel? = null
    var songInfoUrls: HyperlinkModel? = null
    var songInfoFiles: FileModel? = null
    var songInfoAttributes: AttributeModel? = null
    var songInfoCategory: CategoryModel? = null
    var songInfoLicence: LicenceModel? = null

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
}