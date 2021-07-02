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
package gparap.apps.media_recorder

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import gparap.apps.media_recorder.media.Player
import gparap.apps.media_recorder.media.Recorder
import java.io.File
import java.io.IOException
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var recorder: Recorder
    private lateinit var player: Player
    private lateinit var record: Button
    private lateinit var stop: Button
    private lateinit var play: Button

    @Suppress("PrivatePropertyName")
    private val REQUEST_PERMISSION_RECORD_AUDIO = 999
    private var permission: Array<String> = arrayOf(Manifest.permission.RECORD_AUDIO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getWidgets()

        //create media objects
        recorder = Recorder(this)
        player = Player()

        //request permission to use media recorder
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, permission, 999)
        }

        //start recording audio
        record.setOnClickListener {
            //if media player is active shut it down
            if (player.isPlaying()) {
                player.stop()
            }

            try {
                recorder.init()
                recorder.start()
            } catch (e: IOException) {
                Log.i("GPARAP_MEDIA_RECORDER", e.toString())
            }
            Toast.makeText(this, "Recording started..", Toast.LENGTH_SHORT).show()
        }

        //stop recording/playing audio
        stop.setOnClickListener {
            if (recorder.isRecording()) {
                recorder.stop()
                Toast.makeText(this, "Recording saved..", Toast.LENGTH_SHORT).show()
            } else if (player.isPlaying()) {
                player.stop()
            }
        }

        //start playing the recording
        play.setOnClickListener {
            //if media recorder is active shut it down
            if (recorder.isRecording()) {
                recorder.stop()
            }

            val inputFile = File(this.getExternalFilesDir(null), "/recording.3gp")
            player.init(inputFile.absolutePath)
            player.start()
            Toast.makeText(this, "Player started..", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onStop() {
        super.onStop()

        //release system resources
        try {
            recorder.stop()
            player.stop()
        } catch (e: Exception) {
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        //handle record permission check result
        if (requestCode == REQUEST_PERMISSION_RECORD_AUDIO) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                finish()
            }
        }
    }

    private fun getWidgets() {
        record = findViewById(R.id.buttonRecord)
        stop = findViewById(R.id.buttonStop)
        play = findViewById(R.id.buttonPlay)
    }
}

