/*
 * Copyright 2023 gparap
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
package gparap.apps.player_video

import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.widget.ImageButton
import androidx.annotation.OptIn
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.DefaultTimeBar
import androidx.media3.ui.PlayerView
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class VideoPlayerActivity : AppCompatActivity() {
    private lateinit var playerView: PlayerView
    private lateinit var mediaPlayer: ExoPlayer
    private lateinit var defaultTimeBar: DefaultTimeBar

    @OptIn(UnstableApi::class) override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)
        playerView = findViewById(R.id.player_view)

        //get video details from intent
        val videoPath: String? = intent?.getStringExtra("video_path")

        //setup the media player and bind to view
        mediaPlayer = ExoPlayer.Builder(this).build()
        playerView.useController = false
        playerView.player = mediaPlayer

        //prepare the media player
        val mediaItem = MediaItem.fromUri(Uri.parse(videoPath))
        mediaPlayer.setMediaItem(mediaItem)
        mediaPlayer.prepare()

        //setup the default time bar of the media player
        defaultTimeBar = findViewById(R.id.default_time_bar)
        defaultTimeBar.setPlayedColor(Color.WHITE)

        //handle custom media player controller
        //PLAY
        findViewById<ImageButton>(R.id.image_button_play_video).setOnClickListener {
            if (!mediaPlayer.isPlaying) {
                if (mediaPlayer.playbackState == Player.STATE_READY) {
                    mediaPlayer.play()
                }
                if (mediaPlayer.playbackState == Player.STATE_IDLE) {
                    mediaPlayer.prepare()
                    mediaPlayer.playWhenReady = true
                }

                //handle default time bar positioning
                defaultTimeBar.setDuration(mediaPlayer.duration)
                lifecycleScope.launch {
                    while (true) {
                        delay(10L)  //give scrolling effect
                        defaultTimeBar.setPosition(mediaPlayer.currentPosition)
                    }
                }
            }
        }
        //PAUSE
        findViewById<ImageButton>(R.id.image_button_pause_video).setOnClickListener {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
            }
        }
        //STOP
        findViewById<ImageButton>(R.id.image_button_stop_video).setOnClickListener {
            mediaPlayer.stop()
            mediaPlayer.seekToDefaultPosition()
            mediaPlayer.playWhenReady = false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }
}