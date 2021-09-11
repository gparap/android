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

import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import gparap.apps.news.model.Story;

class UtilsUnitTest {

    @org.junit.jupiter.api.Test
    @DisplayName("should return a collection of ids")
    void getStoryIds() {
        //fetch stories' ids
        ArrayList<String> storiesIds = new ArrayList<>();
        HttpURLConnection connection = null;
        try {
            URL url = new URL("https://hacker-news.firebaseio.com/v0/newstories.json?print=pretty");
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            storiesIds.addAll(Utils.getInstance().getStoryIds(connection));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert connection != null;
            connection.disconnect();
        }

        //test if ids exist in list
        for (String id : storiesIds) {
            if (id.equals("")) {
                assert false;
                return;
            }
        }
        assert true;
    }

    @org.junit.jupiter.api.Test
    @DisplayName("should return a story by its id")
    void getStory() {
        //get a story by its id
        HttpURLConnection connection = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            URL url = new URL("https://hacker-news.firebaseio.com/v0/item/28490906.json?print=pretty");
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            String readLine;
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((readLine = reader.readLine()) != null) {
                stringBuilder.append(readLine);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert connection != null;
            connection.disconnect();
        }

        //test if story is returned
        Assertions.assertNotEquals(stringBuilder.toString(), "");
    }

    @org.junit.jupiter.api.Test
    @DisplayName("should create a story object")
    void createStory() throws JSONException {
        //create expected story
        Story storyExpected = new Story();
        storyExpected.setId("28490906");
        storyExpected.setTitle("South Australia Covid Tracing System SQL Injection [video]");
        storyExpected.setUrl("https://www.youtube.com/watch?v=sxMGSNxiSoM");

        //create actual story
        HttpURLConnection connection = null;
        Story storyActual = null;
        try {
            URL url = new URL("https://hacker-news.firebaseio.com/v0/item/28490906.json?print=pretty");
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            storyActual = Utils.getInstance().createStory(connection);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert connection != null;
            connection.disconnect();
        }

        //test if story is created
        assert storyActual != null;
        Assertions.assertEquals(storyExpected.getId(), storyActual.getId());
        Assertions.assertEquals(storyExpected.getTitle(), storyActual.getTitle());
        Assertions.assertEquals(storyExpected.getUrl(), storyActual.getUrl());
    }
}