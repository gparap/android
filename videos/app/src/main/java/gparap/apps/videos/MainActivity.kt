package gparap.apps.videos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import gparap.apps.videos.adapters.VideoAdapter
//import gparap.apps.videos.adapters.VideoAdapter
import gparap.apps.videos.models.SadApiResponseModel
import gparap.apps.videos.services.RetrofitClient
import gparap.apps.videos.services.SadApiService
import gparap.apps.videos.utils.AppConstants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //setup RecyclerView with adapter
        val recyclerViewVideos = findViewById<RecyclerView>(R.id.recycler_view_videos)
        recyclerViewVideos.layoutManager = LinearLayoutManager(this)
        val adapter = VideoAdapter()
        recyclerViewVideos.adapter = adapter

        //create a Retrofit HTTP client instance
        val retrofit = RetrofitClient
            .build(AppConstants.BASE_URL_YTSaD)
            .create(SadApiService::class.java)

        //get trending videos from the web service
        val response: Call<SadApiResponseModel?>? = retrofit.getTrendingVideos
        response?.enqueue(object : Callback<SadApiResponseModel?>{
            override fun onResponse(
                call: Call<SadApiResponseModel?>,
                response: Response<SadApiResponseModel?>,
            ) {
                //DEBUG
                println(response.body().toString())
            }

            override fun onFailure(call: Call<SadApiResponseModel?>, t: Throwable) {
                println(t.message.toString())
            }
        })
    }
}















