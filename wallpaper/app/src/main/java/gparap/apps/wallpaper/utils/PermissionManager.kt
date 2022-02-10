package gparap.apps.wallpaper.utils

import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat

/**
 * Helper singleton to handle permissions
 */
object PermissionManager {
    /**
     * Determines whether the app has the permission to write to external storage
     */
    fun hasPermissionToSave(context: Context): Boolean {
        if (ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return false
        }
        return true
    }

    /**
     * Requests permission to save to external storage
     */
    fun requestPermissionToSave(activity: AppCompatActivity) {
        requestPermissions(
            activity,
            arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
            AppConstants.WRITE_EXTERNAL_STORAGE_REQUEST_CODE
        )
    }

    /**
     * Determines whether the app has been granted the permission to write to external storage
     */
    fun isPermissionToSaveGranted(requestCode: Int, grantResult: Int): Boolean {
        if (requestCode == AppConstants.WRITE_EXTERNAL_STORAGE_REQUEST_CODE &&
            grantResult == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }
}