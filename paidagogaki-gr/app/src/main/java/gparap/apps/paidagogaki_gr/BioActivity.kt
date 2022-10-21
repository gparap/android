package gparap.apps.paidagogaki_gr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import gparap.apps.paidagogaki_gr.utils.BIO

class BioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bio)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //display the blogger's bio
        findViewById<WebView>(R.id.webViewBioContent).apply {
            this.requestFocus()
            this.loadDataWithBaseURL(null, BIO, "text/html", "UTF-8", null)
        }
    }

    //go back to home page
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}