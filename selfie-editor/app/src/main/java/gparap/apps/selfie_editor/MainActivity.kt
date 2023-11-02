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
package gparap.apps.selfie_editor

import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import gparap.apps.selfie_editor.utils.AppConstants
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //check for required permissions and start the camera
        if (arePermissionsGranted()) {
            startCamera()
        } else {
            requestPermissions()
        }

        //take selfie
        findViewById<ImageButton>(R.id.selfie_capture_button).setOnClickListener {
            takeSelfie()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSIONS_REQUEST_CODE && arePermissionsGranted()) {
            startCamera()
        } else {
            Toast.makeText(this, getString(R.string.toast_permissions_denied), Toast.LENGTH_SHORT)
                .show()
        }
    }

    /** Takes a selfie image and saves it to the device. */
    private fun takeSelfie() {
        //get the camera provider associated with the current process
        val cameraProvider = ProcessCameraProvider.getInstance(this).get()

        //initialize the camera preview
        val cameraPreview = Preview.Builder().build().apply {
            val previewView = findViewById<PreviewView>(R.id.camera_preview_view)
            this.setSurfaceProvider(previewView.surfaceProvider)
        }

        //select the default back facing camera
        val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

        //create content values for captured image
        val contentValues = ContentValues()
        contentValues.put(MediaStore.Images.Media.DISPLAY_NAME, Date().time.toString())
        contentValues.put(MediaStore.Images.Media.MIME_TYPE, AppConstants.IMAGE_MIME_TYPE)
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.P) {
            contentValues.put(
                MediaStore.Images.Media.RELATIVE_PATH, AppConstants.SAVE_IMAGES_RELATIVE_PATH
            )
        }

        //configure options for saving captured image
        val outputFileOptions = ImageCapture.OutputFileOptions.Builder(
            contentResolver, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues
        ).build()

        //create a setup for basic picture taking
        val imageCapture = ImageCapture.Builder()
            .setTargetRotation(cameraPreview.targetRotation)
            .build()

        //start the camera preview
        try {
            //make sure nothing is bound to the camera provider
            cameraProvider.unbindAll()

            //configure the camera for basic picture taking
            cameraProvider.bindToLifecycle(this, cameraSelector, imageCapture, cameraPreview)
        } catch (e: Exception) {
            Log.d(getString(R.string.app_name), "Camera preview failed.")
        }

        //capture selfie image
        imageCapture.apply {
            takePicture(outputFileOptions, ContextCompat.getMainExecutor(this@MainActivity),
                object : ImageCapture.OnImageSavedCallback {
                    override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                        //inform the user that the image was captured
                        Toast.makeText(
                            this@MainActivity, getString(R.string.toast_selfie_captured),
                            Toast.LENGTH_SHORT
                        ).show()

                        //ask the user if the captured image should be opened in editor activity
                        AlertDialog.Builder(this@MainActivity)
                            .setTitle(getString(R.string.dialog_title))
                            .setMessage(getString(R.string.dialog_msg))
                            .setPositiveButton(getString(R.string.dialog_yes)) { _, _ ->
                                val intent = Intent(this@MainActivity, EditorActivity::class.java)
                                intent.putExtra(
                                    AppConstants.SELFIE_SAVED_URI_INTENT_EXTRA,
                                    outputFileResults.savedUri.toString()
                                )
                                startActivity(intent)
                            }
                            .setNegativeButton(getString(R.string.dialog_no)) { dialog, _ -> dialog.dismiss() }
                            .create()
                            .apply { show() }
                    }

                    override fun onError(exception: ImageCaptureException) {
                        Toast.makeText(
                            this@MainActivity, getString(R.string.toast_selfie_failed),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
        }
    }

    /** Starts the device camera preview stream. */
    private fun startCamera() {
        ProcessCameraProvider.getInstance(this).addListener({
            //get the camera provider associated with the current process
            val cameraProvider = ProcessCameraProvider.getInstance(this).get()

            //initialize the camera preview
            val cameraPreview = Preview.Builder().build().apply {
                val previewView = findViewById<PreviewView>(R.id.camera_preview_view)
                this.setSurfaceProvider(previewView.surfaceProvider)
            }

            //select the default back facing camera
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            //start the camera preview
            try {
                //make sure nothing is bound to the camera provider
                cameraProvider.unbindAll()

                //bind the selected camera and its preview to the camera provider
                cameraProvider.bindToLifecycle(this, cameraSelector, cameraPreview)
            } catch (e: Exception) {
                Log.d(getString(R.string.app_name), "Camera preview failed.")
            }
        }, ContextCompat.getMainExecutor(this))
    }

    /** Requests permissions for for camera & external storage. */
    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            permissions.toTypedArray(),
            PERMISSIONS_REQUEST_CODE
        )
    }

    /** Checks for camera & external storage permissions. */
    private fun arePermissionsGranted(): Boolean {
        return permissions.all {
            if (ContextCompat.checkSelfPermission(
                    baseContext,
                    it
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                Log.d(getString(R.string.app_name), "Permissions granted.")
                true
            } else {
                Log.d(getString(R.string.app_name), "Permissions denied.")
                false
            }
        }
    }

    companion object {
        private var permissions = mutableListOf(android.Manifest.permission.CAMERA).also {
            if (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.P) {
                it.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }
        }
        private const val PERMISSIONS_REQUEST_CODE = AppConstants.PERMISSIONS_REQUEST_CODE
    }
}