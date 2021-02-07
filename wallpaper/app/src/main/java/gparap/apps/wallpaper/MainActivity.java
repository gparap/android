package gparap.apps.wallpaper;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gparap on 2020-10-24.
 */
@SuppressWarnings({"Convert2Lambda", "RedundantThrows"})
public class MainActivity extends AppCompatActivity {
    final static String API_KEY = "MY_KEY_HERE";
    final static String URL_API = "https://api.pexels.com/v1/curated/?page=1&per_page=80";
    RecyclerView recyclerView;
    WallpaperAdapter adapter;
    List<Wallpaper> wallpapers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init recycler view
        wallpapers = new ArrayList<>();
        adapter = new WallpaperAdapter(MainActivity.this, wallpapers);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 3));
        recyclerView.setAdapter(adapter);

        //display wallpapers
        requestWallpapers();
    }

    private void requestWallpapers(){
        //init queue
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

        //init request
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_API,  new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("photos");

                    //loop through all photos
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject wallpaperObject = (JSONObject) jsonArray.get(i);
                        JSONObject images = wallpaperObject.getJSONObject("src");

                        //create wallpaper object and add to list
                        Wallpaper wallpaper = new Wallpaper();
                        wallpaper.setId(wallpaperObject.getInt("id"));
                        wallpaper.setURL(images.getString("large"));
                        wallpaper.setPhotographer(wallpaperObject.getString("photographer"));
                        wallpapers.add(wallpaper);
                    }
                    //update adapter with the new list
                    adapter.setWallpapers(wallpapers);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Something went wrong.", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                //provider authorization
                Map<String, String> params = new HashMap<>();
                params.put("Authorization", API_KEY);
                return params;
            }
        };

        //add request to queue
        requestQueue.add(stringRequest);
    }
}