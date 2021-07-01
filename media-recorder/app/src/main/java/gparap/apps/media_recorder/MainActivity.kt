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
import android.view.Menu
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import gparap.apps.media_recorder.media.Recorder
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var recorder: Recorder
    private lateinit var start: Button
    private lateinit var stop: Button
    private lateinit var play: Button

    @Suppress("PrivatePropertyName")
    private val REQUEST_PERMISSION = 999
    private var isPermissionAccepted = false
    private var permissions: Array<String> = arrayOf(Manifest.permission.RECORD_AUDIO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getWidgets()

        recorder = Recorder(this)
        recorder.init()

        //start recording audio
        start.setOnClickListener {
            //ask for permissions or proceed if granted
            if (ActivityCompat.checkSelfPermission(
                    this, Manifest.permission.RECORD_AUDIO
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(this, permissions, 999)
            } else {
                try {
                    recorder.start()
                } catch (e: IOException) {
                    TODO("missing exception")
                }
            }
            it.isEnabled = false
            stop.isEnabled = true
            play.isEnabled = false
        }

        //stop recording audio
        stop.setOnClickListener {
            recorder.stop()
            it.isEnabled = false
            start.isEnabled = true
            play.isEnabled = true
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
        isPermissionAccepted = if (requestCode == REQUEST_PERMISSION) {
            grantResults[0] == PackageManager.PERMISSION_GRANTED
        } else {
            false
        }
        if (!isPermissionAccepted) {
            finish()
        }
    }

    private fun getWidgets() {
        start = findViewById(R.id.buttonRecord)
        stop = findViewById(R.id.buttonStop)
        play = findViewById(R.id.buttonPlay)
    }
}

