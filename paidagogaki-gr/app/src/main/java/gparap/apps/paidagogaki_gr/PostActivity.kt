/*
 * Copyright (c) 2022 gparap
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
package gparap.apps.paidagogaki_gr

import android.os.Bundle
import android.webkit.WebView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import gparap.apps.paidagogaki_gr.utils.Utils
import gparap.apps.paidagogaki_gr.utils.WEBKIT_TABLET_ZOOM_FACTOR

class PostActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //get clicked post details from intent
        val postTitle = intent.getStringExtra("blog_post_title")
        var postContent = intent.getStringExtra("blog_post_content")

        //update the title
        findViewById<TextView>(R.id.webViewPostTitle).apply {
            this.text = Utils.fixUnicodeChars(postTitle!!)
        }

        //update the content
        findViewById<WebView>(R.id.webViewPost).apply {
            this.requestFocus()

            //increase font size for tablets
            val isTablet = resources.getBoolean(R.bool.isSW600dp)
            if (isTablet) {
                this.settings.textZoom = this.settings.textZoom + WEBKIT_TABLET_ZOOM_FACTOR
            }

            //fix some issues with json content coming from wordpress service
            postContent = postContent!!.replace("\\\"", "\"")
            postContent = postContent!!.replace("\\n", "")

            this.loadDataWithBaseURL(null, postContent!!, "text/html", "UTF-8", null)
        }
    }

    //go back to home page
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}