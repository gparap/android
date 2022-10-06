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

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import gparap.apps.paidagogaki_gr.utils.Utils

class PostActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        //get clicked post details from intent
        val postTitle = intent.getStringExtra("blog_post_title")
        var postContent = intent.getStringExtra("blog_post_content")

        //update the app bar
        supportActionBar?.title = Utils.fixUnicodeChars(postTitle!!)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //update the content
        findViewById<WebView>(R.id.webViewPost).apply {
            this.requestFocus()

            //fix some issues with json content coming from wordpress service
            postContent = postContent!!.replace("\\\"", "\"")
            postContent = postContent!!.replace("\\n", "")

            this.loadData(postContent!!, "text/html", "UTF-8")
        }
    }

    //go back to home page
    override fun onSupportNavigateUp(): Boolean {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
        return super.onSupportNavigateUp()
    }
}