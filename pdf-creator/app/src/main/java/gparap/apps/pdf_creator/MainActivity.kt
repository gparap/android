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
package gparap.apps.pdf_creator

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import gparap.apps.pdf_creator.utils.Utils

class MainActivity : AppCompatActivity() {
    @Suppress("PrivatePropertyName")
    private val REQUEST_CODE_WRITE_EXTERNAL_STORAGE = 999
    private lateinit var editTextInputPDF: EditText
    private lateinit var viewModel: MainActivityViewModel

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //get the existing ViewModel or create a new one for MainActivity class
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        //observe the pdf input text
        editTextInputPDF = findViewById(R.id.editTextInputPDF)
        viewModel.getInputPDF().observe(this, {
            editTextInputPDF.setText(it)
        })

        //create pdf
        val buttonCreate = findViewById<Button>(R.id.buttonCreatePDF)
        buttonCreate.setOnClickListener {
            //check permissions to write to storage (only for SDK <= 29)
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) {
                if (!isPermissionGranted()) {
                    requestPermissions(
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        REQUEST_CODE_WRITE_EXTERNAL_STORAGE
                    )
                }

                //permission granted
                else {
                    savePDF()
                }
            }

            //(SDK < 23 && SDK >= 30)
            else {
                savePDF()
            }
        }
    }

    //permission result to write storage (SDK <= 29)
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        //permission granted
        if (requestCode == REQUEST_CODE_WRITE_EXTERNAL_STORAGE) {
            savePDF()
        }
    }

    //check if permission to write storage is granted (SDK <= 29)
    private fun isPermissionGranted(): Boolean {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return false
        }
        return true
    }

    private fun savePDF() {
        //create PDF document
        val pdfDocument = Utils.createPDF(viewModel.getInputPDF().toString())
    }
}