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

import com.android.volley.VolleyError;

public interface VolleyServiceCallback {
    /**
     * Called when a response is received.
     *
     * @param response as string
     */
    void onResponse(String response);

    /**
     * Called when an error is received.
     *
     * @param error Volley error
     */
    void onError(VolleyError error);
}
