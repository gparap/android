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
package gparap.apps.media_recorder.media

import android.media.MediaPlayer
import android.util.Log
import java.io.IOException

class Player {
    private lateinit var mediaPlayer: MediaPlayer
    private var isPlaying: Boolean = false

    fun isPlaying(): Boolean {
        return isPlaying
    }

    fun init(dataSource: String) {
        mediaPlayer = MediaPlayer()

        //set the data source file-path
        try {
            mediaPlayer.setDataSource(dataSource)
        } catch (e: IllegalArgumentException) {
            Log.i("GPARAP_MEDIA_RECORDER", e.toString())
        } catch (e: IOException) {
            Log.i("GPARAP_MEDIA_RECORDER", e.toString())
        }
    }

    fun start() {
        if (!isPlaying) {
            mediaPlayer.prepare()
            mediaPlayer.start()
            isPlaying = true
        }
    }

    fun stop() {
        mediaPlayer.stop()
        mediaPlayer.release()
        isPlaying = false;
    }
}