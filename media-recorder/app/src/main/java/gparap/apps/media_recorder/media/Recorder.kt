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

import android.content.Context
import android.media.MediaRecorder
import java.io.File

class Recorder(private val context: Context) {
    private var mediaRecorder: MediaRecorder = MediaRecorder()
    private lateinit var outputFile: File
    private var isInitialized: Boolean = false
    private var isRecording: Boolean = false

    fun isInitialized(): Boolean {
        return isInitialized
    }

    fun isRecording(): Boolean {
        return isRecording
    }

    fun init() {
        //set the audio source to be used for recording
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT)

        //set the format of the output file produced during recording
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)

        //set the output file of the recording
        outputFile = File(context.getExternalFilesDir(null), "/recording.3gp")
        mediaRecorder.setOutputFile(outputFile.absolutePath)

        //set the audio encoder to be used for recording
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)

        isInitialized = true
    }

    fun start() {
        if (!isRecording) {
            mediaRecorder.prepare()
            mediaRecorder.start()
            isRecording = true
        }
    }

    fun stop() {
        if (isRecording) {
            mediaRecorder.stop()
            mediaRecorder.release()
            isRecording = false
        }
    }
}