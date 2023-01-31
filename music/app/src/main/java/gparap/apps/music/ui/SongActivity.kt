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
package gparap.apps.music.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import gparap.apps.music.R
import gparap.apps.music.viewmodels.SongActivityViewModel

class SongActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song)

        //get the intent that started this activity
        val intent = intent

        //create ViewModel for this activity
        val viewModel = ViewModelProvider(this)[SongActivityViewModel::class.java]

        //update appBar title from intent data
        viewModel.updateTitle(supportActionBar, intent)

        //get song extended data from intent
        viewModel.getSongData(intent)

        //display the user selected song details
        viewModel.displaySongDetails(this)
    }
}