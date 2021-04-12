/*
 * Copyright 2021 gparap
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
package gparap.apps.weather.api;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class VolleyService {
    private static VolleyService instance;
    private final VolleyServiceCallback callback;

    public static VolleyService getInstance(VolleyServiceCallback callback) {
        if (instance == null) {
            instance = new VolleyService(callback);
        }
        return instance;
    }

    private VolleyService(VolleyServiceCallback callback) {
        this.callback = callback;
    }

    public void getApiResponse(Context context, String url) {
        //init RequestQueue
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        //create request for retrieving the response body
        StringRequest stringRequest = new StringRequest(url,
                callback::onResponse,
                callback::onError);

        //add request to RequestQueue
        requestQueue.add(stringRequest);
    }
}
