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
import android.database.Cursor
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
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
                            getStorageFiles(Build.VERSION_CODES.P)
                        }

                        //get storage files from device (SDK >= 29 && < 33)
                        else if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P
                            && Build.VERSION.SDK_INT <= Build.VERSION_CODES.TIRAMISU
                        ) {
                            storageFiles.clear()
                            getStorageFiles(Build.VERSION_CODES.Q)
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
            getStorageFiles(Build.VERSION_CODES.P)
        }
    }

    //get audio storage files from the device folders   TODO: Refactor code
    private fun getStorageFiles(sdkVersionCode: Int) {
        //(SDK >= 21 && <= 28)
        if (sdkVersionCode <= 28) {
            //get storage files from the device folder "DOWNLOADS"
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

            //get storage files from the device folder "MUSIC"
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
        }

        //(SDK >= 29 && < 33)
        if (sdkVersionCode >= 29) {
            //define the columns that will be returned for each row
            val projection:  Array<String> = arrayOf(
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.DISPLAY_NAME
            )

            //query against the table and return a Cursor object
            val cursor: Cursor? = contentResolver.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                null,
                null,
                null
            )

            //get audio files and add to list
            while (cursor?.moveToNext() == true) {
                val index = cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME)
                val filename = cursor.getString(index)
                println(filename)
                storageFiles.add(StorageFileModel(filename))
            }

            //free up the Cursor after use
            cursor?.close()
        }

        //update recycler view with storage files
        recyclerViewStorageFiles.adapter = StorageFilesAdapter().apply {
            this.storageFiles.clear()
            this.storageFiles = this@MainActivity.storageFiles
        }
    }
}