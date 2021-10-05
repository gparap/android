package gparap.apps.pdf_creator

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    @Suppress("PrivatePropertyName")
    private val REQUEST_CODE_WRITE_EXTERNAL_STORAGE = 999

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
        //TODO("unimplemented yet")
    }
}