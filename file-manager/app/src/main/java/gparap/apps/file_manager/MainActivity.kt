/*
 * Copyright 2024 gparap
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
package gparap.apps.file_manager

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.provider.Settings
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import gparap.apps.file_manager.adapters.FileAdapter
import gparap.apps.file_manager.data.FileModel
import java.io.File

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var fileAdapter: FileAdapter
    private val deviceFiles = mutableListOf<FileModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //setup recycler view with adapter
        recyclerView = findViewById(R.id.recyclerView_deviceFiles)
        recyclerView.layoutManager = LinearLayoutManager(this)
        fileAdapter = FileAdapter(deviceFiles)
        recyclerView.adapter = fileAdapter

        //display media files
        findViewById<Button>(R.id.button_scanMediaFiles).setOnClickListener {
            //TIRAMISU and beyond
            if (Build.VERSION.SDK_INT >= 33) {
                //check for required media files permissions and if granted, perform scan
                if (areMediaPermissionsGranted()) {
                    scanMediaFiles()
                } else {
                    requestMediaFilesPermissions()
                }
            }
            //TODO: lesser versions
        }

        //display all files
        findViewById<Button>(R.id.button_scanAllFiles).setOnClickListener {
            //RED VELVET CAKE and beyond
            if (Build.VERSION.SDK_INT >= 30) {
                //check for managed files permission internally and if granted, perform scan
                if (Environment.isExternalStorageManager()) {
                    val rootDirectory = Environment.getExternalStorageDirectory()
                    scanAllFiles(rootDirectory)
                } else {
                    requestAllFilesPermission()
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_MEDIA_FILES && areMediaPermissionsGranted()) {
            scanMediaFiles()
        } else {
            Toast.makeText(
                this,
                getString(R.string.toast_media_permissions_denied),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    @Deprecated("Method deprecated, use Activity Result API")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_ALL_FILES) {
            if (Environment.isExternalStorageManager()) {
                //check for managed files permission internally and if granted, perform scan
                val rootDirectory = Environment.getExternalStorageDirectory()
                scanAllFiles(rootDirectory)
            } else {
                Toast.makeText(
                    this,
                    getString(R.string.toast_all_files_permission_denied),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    /** Checks for media file permissions. */
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun areMediaPermissionsGranted(): Boolean {
        val readImagesGranted = ActivityCompat.checkSelfPermission(
            this,
            android.Manifest.permission.READ_MEDIA_IMAGES
        ) == PackageManager.PERMISSION_GRANTED
        val readVideosGranted = ActivityCompat.checkSelfPermission(
            this,
            android.Manifest.permission.READ_MEDIA_VIDEO
        ) == PackageManager.PERMISSION_GRANTED
        val readAudioGranted = ActivityCompat.checkSelfPermission(
            this,
            android.Manifest.permission.READ_MEDIA_AUDIO
        ) == PackageManager.PERMISSION_GRANTED

        //all 3 permissions should be granted TODO: make granular functionality
        return readImagesGranted && readVideosGranted && readAudioGranted
    }

    /** Requests media file permissions. */
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun requestMediaFilesPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                android.Manifest.permission.READ_MEDIA_IMAGES,
                android.Manifest.permission.READ_MEDIA_VIDEO,
                android.Manifest.permission.READ_MEDIA_AUDIO
            ),
            REQUEST_CODE_MEDIA_FILES
        )
    }

    /** Requests special permission to access all files of the device . */
    @RequiresApi(Build.VERSION_CODES.R)
    private fun requestAllFilesPermission() {
        //redirect the user to the settings page
        val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
        intent.setData(Uri.parse("package:$packageName"))
        @Suppress("DEPRECATION")
        startActivityForResult(intent, REQUEST_CODE_ALL_FILES)
    }

    /** Scans the device for all media files (images, video & audio). */
    @RequiresApi(Build.VERSION_CODES.Q)
    @SuppressLint("NotifyDataSetChanged")
    private fun scanMediaFiles() {
        deviceFiles.clear()

        //get the content URI for the device volume (shared)
        val uri = MediaStore.Files.getContentUri(MediaStore.VOLUME_EXTERNAL)

        //define the columns to retrieve from the MediaStore
        val projection = arrayOf(
            MediaStore.Files.FileColumns.DISPLAY_NAME,  //file name
            MediaStore.Files.FileColumns.RELATIVE_PATH  //relative path
        )

        //filter out non-media files from the selection
        val selection =
            "${MediaStore.Files.FileColumns.MEDIA_TYPE} != ${MediaStore.Files.FileColumns.MEDIA_TYPE_NONE}"

        //use ascending order for the query results
        val sortOrder = "${MediaStore.Files.FileColumns.DISPLAY_NAME} ASC"

        //query the MediaStore and add files to media files list
        val cursor = contentResolver.query(uri, projection, selection, null, sortOrder)
        cursor?.use {
            val nameColumn = it.getColumnIndex(MediaStore.Files.FileColumns.DISPLAY_NAME)
            val pathColumn = it.getColumnIndex(MediaStore.Files.FileColumns.RELATIVE_PATH)
            while (it.moveToNext()) {
                val name = it.getString(nameColumn)
                val path = it.getString(pathColumn)
                val fileUri = Uri.parse("file://$path/$name")
                deviceFiles.add(FileModel(name, fileUri))
            }
        }

        //notify the adapter that the media files list has changed
        fileAdapter.notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun scanAllFiles(rootDirectory: File, isRecursionActive: Boolean = false) {
        //!!! the adapter must be cleared only once
        if (!isRecursionActive) {
            deviceFiles.clear()
        }

        //get an array of pathnames within the root directory
        val pathnames = rootDirectory.listFiles()
        if (pathnames != null) {
            //loop over each pathname and if it is a directory, scan its contents
            for (pathname in pathnames) {
                if (pathname.isDirectory) {
                    //use recursion to scan subdirectory
                    scanAllFiles(pathname, true)
                } else {
                    //TODO: handle .thumbnail files and others that should not be included
                    deviceFiles.add(FileModel(pathname.name, Uri.parse(pathname.path)))
                }
            }
        }

        //notify the adapter that the media files list has changed
        fileAdapter.notifyDataSetChanged()
    }

    companion object {
        const val REQUEST_CODE_MEDIA_FILES = 999 /* request code for media file permissions */
        const val REQUEST_CODE_ALL_FILES = 888 /* request code for all file access permission */
    }
}