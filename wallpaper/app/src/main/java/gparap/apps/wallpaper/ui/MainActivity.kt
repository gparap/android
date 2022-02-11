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
package gparap.apps.wallpaper.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import gparap.apps.wallpaper.R
import gparap.apps.wallpaper.adapter.WallpaperAdapter
import gparap.apps.wallpaper.data.ApiResponseModel
import gparap.apps.wallpaper.data.WallpaperModel
import gparap.apps.wallpaper.services.ApiService
import gparap.apps.wallpaper.services.RetrofitClient
import gparap.apps.wallpaper.utils.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //setup RecyclerView with adapter
        val recyclerViewWallpapers = findViewById<RecyclerView>(R.id.recycler_view_main)
        recyclerViewWallpapers.layoutManager =
            GridLayoutManager(this, Utils.getGridLayoutSpanCount(this))
        val adapter = WallpaperAdapter()
        recyclerViewWallpapers.adapter = adapter

        //create web service and fetch all wallpapers
        RetrofitClient.build().create(ApiService::class.java).getAll()
            .enqueue(object : Callback<ApiResponseModel> {
                override fun onResponse(
                    call: Call<ApiResponseModel>,
                    response: Response<ApiResponseModel>,
                ) {
                    response.apply {
                        //populate adapter with wallpapers
                        adapter.wallpapers =
                            ((this.body() as ApiResponseModel).data) as ArrayList<WallpaperModel>
                    }
                }

                override fun onFailure(call: Call<ApiResponseModel>, t: Throwable) {
                    println(t.localizedMessage)
                }
            })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.category_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            //TODO: Display all wallpapers
            R.id.category_menu_all -> {
                showToastMessageHelper(resources.getString(R.string.text_category_all))
            }

            //TODO: Display abstract wallpapers
            R.id.category_menu_abstract -> {
                showToastMessageHelper(resources.getString(R.string.text_category_abstract))
            }

            //TODO: Display colorful wallpapers
            R.id.category_menu_colorful -> {
                showToastMessageHelper(resources.getString(R.string.text_category_colorful))
            }

            //TODO: Display mountain wallpapers
            R.id.category_menu_mountain -> {
                showToastMessageHelper(resources.getString(R.string.text_category_mountain))
            }

            //TODO: Display nature wallpapers
            R.id.category_menu_nature -> {
                showToastMessageHelper(resources.getString(R.string.text_category_nature))
            }

            //TODO: Display pattern wallpapers
            R.id.category_menu_pattern -> {
                showToastMessageHelper(resources.getString(R.string.text_category_pattern))
            }

            //TODO: Display texture wallpapers
            R.id.category_menu_texture -> {
                showToastMessageHelper(resources.getString(R.string.text_category_texture))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    //temporary - no need to test
    private fun showToastMessageHelper(category: String) {
        Toast.makeText(this, category, Toast.LENGTH_SHORT).show()
    }
}