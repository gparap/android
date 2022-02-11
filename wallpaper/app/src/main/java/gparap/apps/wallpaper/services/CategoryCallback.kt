package gparap.apps.wallpaper.services

import android.content.Context
import gparap.apps.wallpaper.R
import gparap.apps.wallpaper.adapter.WallpaperAdapter
import gparap.apps.wallpaper.data.ApiResponseModel
import gparap.apps.wallpaper.data.WallpaperModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object CategoryCallback {
    private lateinit var apiResponse: Call<ApiResponseModel>

    /**
     * Returns all the wallpapers that belong to a specific category
     */
    fun getWallpapers(category: String, context: Context, adapter: WallpaperAdapter) {
        val webService = RetrofitClient.build().create(ApiService::class.java)

        when (category) {

            //fetch all wallpapers
            context.resources.getString(R.string.text_category_all) -> {
                apiResponse = webService.getAll()
            }

            //fetch abstract wallpapers
            context.resources.getString(R.string.text_category_abstract) -> {
                TODO("Not implemented yet")
            }

            //fetch colorful wallpapers
            context.resources.getString(R.string.text_category_colorful) -> {
                TODO("Not implemented yet")
            }

            //fetch mountain wallpapers
            context.resources.getString(R.string.text_category_mountain) -> {
                TODO("Not implemented yet")
            }

            //fetch nature wallpapers
            context.resources.getString(R.string.text_category_nature) -> {
                TODO("Not implemented yet")
            }

            //fetch pattern wallpapers
            context.resources.getString(R.string.text_category_pattern) -> {
                TODO("Not implemented yet")
            }

            //fetch texture wallpapers
            context.resources.getString(R.string.text_category_texture) -> {
                TODO("Not implemented yet")
            }
            else -> {}

        }.apply {

            //handle the api response
            apiResponse.enqueue(object : Callback<ApiResponseModel> {
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
    }
}