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
package gparap.apps.painter

import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import gparap.apps.painter.canvas.CanvasView
import gparap.apps.painter.color_picker.ColorPickerDialog
import gparap.apps.painter.color_picker.ColorPickerListener
import gparap.apps.painter.utils.Utils
import java.io.IOException
import java.io.InputStream

@Suppress("PrivatePropertyName")
class MainActivity : AppCompatActivity(), ColorPickerListener { //TODO: Refactor code
    private lateinit var canvasLayout: ConstraintLayout
    private lateinit var canvasView: CanvasView
    private lateinit var dialog: ColorPickerDialog
    private val REQUEST_CODE_WRITE_EXTERNAL_STORAGE = 111
    private val REQUEST_CODE_ACTION_GET_CONTENT = 222
    private val REQUEST_CODE_READ_MEDIA_IMAGES = 333
    private var interstitialAd: InterstitialAd? = null

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //lock screen orientation to the one existing at app launch time
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LOCKED

        //create canvas area and add it to layout
        canvasView = CanvasView(this, null, null, false)
        canvasLayout = findViewById(R.id.main_canvas_area)
        canvasLayout.addView(canvasView)

        //get the color value from intent
        if (this.intent != null) {
            canvasView.currentColor = this.intent.getIntExtra("color_value", Color.BLACK)
        }

        //provide clearing canvas area functionality
        val imageButtonClear = findViewById<ImageView>(R.id.imageViewClearCanvas)
        imageButtonClear.setOnClickListener {
            canvasView.clearCanvas()
        }

        //provide erasing functionality
        val imageButtonErase = findViewById<ImageView>(R.id.imageViewEraser)
        imageButtonErase.setOnClickListener {
            canvasView.setEraseMode()
        }

        //provide changing the pen size functionality
        val textViewPenSize = findViewById<TextView>(R.id.textViewPenSize)
        val seekBarPenSize = findViewById<SeekBar>(R.id.seekBarPenSize)
        seekBarPenSize.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                textViewPenSize.text = p1.toString()
                canvasView.strokeWidth = p1
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })

        //restore paint mode with previous color
        val imageViewPenSize = findViewById<ImageView>(R.id.imageViewPenSize)
        imageViewPenSize.setOnClickListener {
            canvasView.setPaintMode()
        }

        //open color picker dialog
        val imageViewColorPicker = findViewById<ImageView>(R.id.imageViewColorPicker)
        imageViewColorPicker.setOnClickListener {
            dialog = ColorPickerDialog(this)
            dialog.setColorPickerListener(this)
            dialog.show()
        }

        //save painting to a .png file on a top-level shared storage directory
        val imageViewSave = findViewById<ImageView>(R.id.imageViewSave)
        imageViewSave.setOnClickListener {
            //we should always request permissions if we encounter a legacy API
            if (Build.VERSION.SDK_INT < 29) {
                if (!isPermissionGranted()) {
                    requestPermissions(
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        REQUEST_CODE_WRITE_EXTERNAL_STORAGE
                    )

                } else {
                    savePainting()  //permission granted
                }
            }

            //no need permissions when saving with MediaStore on modern APIs
            else {
                savePainting(this)
            }
        }

        //open existing painting from filesystem
        val imageViewOpen = findViewById<ImageView>(R.id.imageViewOpen)
        imageViewOpen.setOnClickListener {
            //!!! API >=33
            if (Build.VERSION.SDK_INT >= 33) {
                if (isPermissionGranted33Plus()) {
                    val intent = Intent(Intent.ACTION_PICK).apply {
                        type = "image/*"
                        putExtra(Intent.EXTRA_MIME_TYPES, arrayOf("image/jpeg", "image/jpg", "image/png"))
                    }
                    startActivityForResult(intent, REQUEST_CODE_READ_MEDIA_IMAGES)
                } else {
                    //request permissions to read image files
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.READ_MEDIA_IMAGES),
                        REQUEST_CODE_READ_MEDIA_IMAGES
                    )
                }
            }

            //!!! API <33
            else {
                //create an action to open image files
                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.type = "image/*"
                startActivityForResult(
                    Intent.createChooser(intent, resources.getString(R.string.title_file_picker)),
                    REQUEST_CODE_ACTION_GET_CONTENT
                )
            }
        }

        //initialize the Google Mobile Ads SDK
        MobileAds.initialize(this) { }

        //load an interstitial ad
        InterstitialAd.load(
            this,
            "ca-app-pub-4227032191105066/6370721282",
            AdRequest.Builder().build(),
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    Log.d(TAG, adError.message)
                    interstitialAd = null
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    Log.d(TAG, "Ad was loaded.")
                    this@MainActivity.interstitialAd = interstitialAd

                    //show the ad
                    if (this@MainActivity.interstitialAd != null) {
                        this@MainActivity.interstitialAd?.show(this@MainActivity)

                    } else {
                        Log.d("TAG", "The interstitial ad wasn't ready yet.")
                    }
                }
            })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)
        if (resultCode != RESULT_OK) {
            return
        }
        if (requestCode == REQUEST_CODE_ACTION_GET_CONTENT || requestCode == REQUEST_CODE_READ_MEDIA_IMAGES) {
            //get file path and decode file to bitmap
            val uri = intentData?.data
            val inputStream: InputStream? = contentResolver.openInputStream(uri!!)
            val mBitmap = BitmapFactory.decodeStream(inputStream)
            inputStream?.close()

            //create canvas area and add it to layout
            canvasView = CanvasView(this, null, mBitmap!!, true)
            canvasLayout.removeAllViews()
            canvasLayout.addView(canvasView)
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_CODE_WRITE_EXTERNAL_STORAGE -> {
                //if request is cancelled, the result arrays are empty
                if ((grantResults.isNotEmpty() &&
                            grantResults[0] == PackageManager.PERMISSION_GRANTED)
                ) {
                    //permission to save to device storage is granted
                    savePainting()
                }
                return
            }

            REQUEST_CODE_READ_MEDIA_IMAGES -> {
                //if request is cancelled, the result arrays are empty
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    val intent = Intent(Intent.ACTION_PICK).apply {
                        type = "image/*"
                        putExtra(Intent.EXTRA_MIME_TYPES, arrayOf("image/jpeg", "image/jpg", "image/png"))
                    }
                    startActivityForResult(intent, REQUEST_CODE_READ_MEDIA_IMAGES)
                }
                return
            }
        }
    }

    //TODO: Refactor isPermissionGranted methods
    //check if permission to write storage is granted (for API < 29)
    private fun isPermissionGranted(): Boolean {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

    //check if permission to read media images is granted (for API >= 33)
    private fun isPermissionGranted33Plus(): Boolean {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_MEDIA_IMAGES
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

    //TODO: Refactor savePainting methods
    //save painting to device's public directory, "pictures"
    private fun savePainting() {
        Utils.writeDataToFile(
            Utils.createNewFile(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                    .toString(),
                Utils.generateFilenameSuffix("yyMMddHHmmss")
            ), Utils.getByteArray(canvasView.bitmap)
        )
        //notify user
        Toast.makeText(this, resources.getString(R.string.text_file_saved), Toast.LENGTH_SHORT)
            .show()
    }

    //save painting to device's public directory, "pictures" using the MediaStore API
    private fun savePainting(context: Context) {
        //bitmap
        val imageByteArray = Utils.getByteArray(canvasView.bitmap)

        //image name
        val imageName = "painting".plus(Utils.generateFilenameSuffix("yyMMddHHmmss")).plus(".png")

        //prepare the content values for the image
        val values = ContentValues()
        values.put(MediaStore.Images.Media.DISPLAY_NAME, imageName)
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/png")
        values.put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures")

        //insert the image into MediaStore and get the URI
        val uri = context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        if (uri != null) {
            try {
                context.contentResolver.openOutputStream(uri).use { outputStream ->
                    //write the byte array (image data) to the output stream
                    outputStream?.write(imageByteArray)
                }
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                //notify user
                Toast.makeText(this, resources.getString(R.string.text_file_saved), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    //pick a color as the current color
    override fun onPickColorButtonClick() {
        canvasView.currentColor = dialog.getPickedColor()
    }
}