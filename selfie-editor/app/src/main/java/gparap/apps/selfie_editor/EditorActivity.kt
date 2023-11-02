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
package gparap.apps.selfie_editor

import gparap.apps.selfie_editor.utils.CanvasView
import android.content.pm.ActivityInfo
import android.graphics.*
import android.media.FaceDetector
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import gparap.apps.selfie_editor.utils.AppConstants
import gparap.apps.selfie_editor.utils.Utils
import java.io.InputStream

class EditorActivity : AppCompatActivity() {
    private lateinit var faces: Array<FaceDetector.Face?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor)

        //lock screen orientation to the one existing at app launch time
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LOCKED

        //get the resource identifier the captured image
        val uri = Uri.parse(intent.getStringExtra(AppConstants.SELFIE_SAVED_URI_INTENT_EXTRA))
//        val uri = Uri.parse("content://media/external/images/media/380")

        //create bitmap options needed for face detection
        val options = BitmapFactory.Options()
        options.inPreferredConfig = Bitmap.Config.RGB_565

        //get the byte array from image uri and decode an immutable bitmap
        val inputStream: InputStream? = contentResolver.openInputStream(uri)
        val byteArray = Utils.getByteArray(inputStream)
        inputStream?.close()
        val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size, options)

        //create a new mutable Bitmap
        val matrix = Matrix()
        matrix.postRotate(AppConstants.BITMAP_MATRIX_ROTATION_DEGREES)
        val bitmapRotated = Bitmap.createBitmap(
            bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true
        ).apply {
            //get rid of bitmap
            bitmap.recycle()
        }

        //detect the number of faces included in the captured image
        val width = bitmapRotated.width
        val height = bitmapRotated.height
        faces = arrayOfNulls(AppConstants.MAX_FACE_DETECTION)
        val faceDetector = FaceDetector(width, height, AppConstants.MAX_FACE_DETECTION)
        val detectedFaces = faceDetector.findFaces(bitmapRotated, faces)
        println("FACES: $detectedFaces")

        //get the middle points of all the detected faces
        val facesMidPoints = ArrayList<PointF>()
        for (i in 1..detectedFaces) {
            val point = PointF()
            val face = faces[i - 1]
            face?.getMidPoint(point)
            facesMidPoints.add(point)
        }

        //scale selfie bitmap
        val scaledBitmap = Bitmap.createScaledBitmap(
            bitmapRotated,
            Utils.getDeviceWidth(this),
            (Utils.getDeviceHeight(this) * AppConstants.BITMAP_ON_DEVICE_SCALE_FACTOR).toInt(),
            false
        )

        //create a canvas view and and draw the captured image as bitmap
        var canvasView = CanvasView(this, null, scaledBitmap)
        val canvasLayout = findViewById<ConstraintLayout>(R.id.selfie_canvas)
        canvasLayout.addView(canvasView)

        //edit captured selfie TODO: all images
        findViewById<ImageView>(R.id.image_view_balloon).setOnClickListener {
            val existingBitmap = canvasView.getDrawnBitmap()
            val tempBitmap = BitmapFactory.decodeResource(resources, R.drawable.balloon)
            canvasView = CanvasView(this, null, tempBitmap, existingBitmap, facesMidPoints)
            canvasLayout.removeAllViews()
            canvasLayout.addView(canvasView)
            canvasLayout.refreshDrawableState()
        }
    }
}