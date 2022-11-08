package gparap.apps.player_music

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import gparap.apps.player_music.utils.REQUEST_CODE_READ_EXTERNAL_STORAGE

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //determine whether the app have been granted the READ_EXTERNAL_STORAGE permission
        ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
            .apply {
                when {
                    this@apply == PackageManager.PERMISSION_GRANTED -> {
                        TODO("Not implemented yet. (SDK >= 23 && < 33)")
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
            TODO("Not implemented yet. (SDK >= 21)")
        }
    }
}