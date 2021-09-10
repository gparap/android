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
package gparap.apps.news.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.List;

import gparap.apps.news.model.Story;

public class Utils {
    private static Utils instance;

    public static Utils getInstance() {
        if (instance == null) {
            instance = new Utils();
        }
        return instance;
    }

    private Utils() {
    }

    /**
     * Redirects user to the story's website.
     *
     * @param context app context
     * @param url     story url
     */
    public void visitStoryURL(Context context, String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        context.startActivity(intent);
    }

    /**
     * Returns a collection of all the stories' ids.
     *
     * @param connection URLConnection for HTTP
     * @return stories's ids as a list
     * @throws IOException a general, I/O-related error
     */
    public List<String> getStoryIds(HttpURLConnection connection) throws IOException {
        InputStream inputStream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String readLine;
        readLine = reader.readLine();
        readLine = readLine.replace("[", "");
        readLine = readLine.replace("]", "");
        readLine = readLine.replace(" ", "");
        String[] stringArray = readLine.split(",");
        reader.close();
        return Arrays.asList(stringArray);
    }

    /**
     * Returns a story as string.
     *
     * @param connection URLConnection for HTTP
     * @return story
     * @throws IOException a general, I/O-related error
     */
    public String getStory(HttpURLConnection connection) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        String readLine;
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        while ((readLine = reader.readLine()) != null) {
            stringBuilder.append(readLine);
        }
        reader.close();
        return stringBuilder.toString();
    }

    /**
     * Creates and returns the story object.
     *
     * @param connection URLConnection for HTTP
     * @return story object
     * @throws IOException   a general, I/O-related error
     * @throws JSONException a JSONException
     */
    public Story createStory(HttpURLConnection connection) throws IOException, JSONException {
        //create json object from story string data
        JSONObject jsonObject = new JSONObject(Utils.getInstance().getStory(connection));

        //create and return story object
        Story story = new Story();
        story.setId(jsonObject.get("id").toString());
        story.setTitle(jsonObject.get("title").toString());
        story.setUrl(jsonObject.get("url").toString());
        return story;
    }
}
