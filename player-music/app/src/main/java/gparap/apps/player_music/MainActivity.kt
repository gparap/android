/*
 * Copyright 2022 gparap
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
package gparap.apps.player_music

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import gparap.apps.player_music.adapters.StorageFilesAdapter
import gparap.apps.player_music.data.StorageFileModel
import gparap.apps.player_music.utils.REQUEST_CODE_READ_EXTERNAL_STORAGE

class MainActivity : AppCompatActivity() {
    private var storageFiles = ArrayList<StorageFileModel>()
    private lateinit var recyclerViewStorageFiles: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //setup recycler view with adapter for storage files
        recyclerViewStorageFiles = findViewById(R.id.recyclerViewStorageFiles)
        recyclerViewStorageFiles.layoutManager = LinearLayoutManager(this)
        recyclerViewStorageFiles.adapter = StorageFilesAdapter().apply {
            this.storageFiles = this@MainActivity.storageFiles
        }

        //determine whether the app have been granted the READ_EXTERNAL_STORAGE permission
        ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
            .apply {
                when {
                    this@apply == PackageManager.PERMISSION_GRANTED -> {
                        //get storage files from device (SDK >= 21 && <= 28)
                        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
                            storageFiles.clear()
                            getStorageFiles()
                        }

                        //get storage files from device (SDK >= 29 && < 33)
                        else if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P
                            && Build.VERSION.SDK_INT <= Build.VERSION_CODES.TIRAMISU
                        ) {
                            TODO("Not implemented yet. (SDK >= 29 && < 33)")
                        }
                    }
                    this@apply == PackageManager.PERMISSION_DENIED -> {
                        //ask for READ_EXTERNAL_STORAGE permission (SDK >= 23 && < 33)
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                            && Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU
                        ) {
                            requestPermissions(
                                arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                                REQUEST_CODE_READ_EXTERNAL_STORAGE
                            )
                        }
                        //TODO: MANAGE_EXTERNAL_STORAGE (SDK >= 33)
                        //do not ask for READ_EXTERNAL_STORAGE permission (SDK >= 33)
                        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                            TODO("Not implemented yet. (SDK >= 33)")
                        }
                    }
                }
            }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_READ_EXTERNAL_STORAGE && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //get storage files from device (SDK >= 21 && <= 28)
            storageFiles.clear()
            getStorageFiles()
        }
    }

    //get storage files from the device folders "DOWNLOADS" & "MUSIC"   TODO: Refactor code
    private fun getStorageFiles() {
        //"DOWNLOADS"
        var dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        var files = dir?.listFiles()
        files?.forEach { f ->
            //get the file name
            val filename = f.path.substring(f.path.lastIndexOf('/') + 1, f.path.length)

            //check if filename extension is of audio type (ie: mp3, ogg, etc.) and add to list
            val extension = filename.substring(filename.lastIndexOf('.') + 1, filename.length)
            if (extension.contains("mp3") || extension.contains("ogg")) {   //TODO: more
                storageFiles.add(StorageFileModel(filename))
            }
        }

        //"MUSIC"
        dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC)
        files = dir?.listFiles()
        files?.forEach { f ->
            //get the file name
            val filename = f.path.substring(f.path.lastIndexOf('/') + 1, f.path.length)

            //check if filename extension is of audio type (ie: mp3, ogg, etc.) and add to list
            val extension = filename.substring(filename.lastIndexOf('.') + 1, filename.length)
            if (extension.contains("mp3") || extension.contains("ogg")) {   //TODO: more
                storageFiles.add(StorageFileModel(filename))
            }
        }

        //update recycler view with storage files
        recyclerViewStorageFiles.adapter = StorageFilesAdapter().apply {
            this.storageFiles.clear()
            this.storageFiles = this@MainActivity.storageFiles
        }
    }
}