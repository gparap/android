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
package gparap.apps.news;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import gparap.apps.news.model.Story;
import gparap.apps.news.utils.Utils;

public class MainActivity extends AppCompatActivity {
    private final static String URL_STORIES = "https://hacker-news.firebaseio.com/v0/newstories.json?print=pretty";
    private ArrayAdapter<String> adapter;
    private ArrayList<String> storiesIds;
    private List<Story> stories;
    private List<String> storiesTitles;
    private List<String> idsToRemove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initArrayLists();

        //init list view with adapter
        ListView listView = findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, storiesTitles);
        listView.setAdapter(adapter);

        //visit story url
        listView.setOnItemClickListener((parent, view, position, id) ->
                Utils.getInstance().visitStoryURL(this, stories.get(position).getUrl()));

        loadAndRefreshStories();

        //load more stories
        Button buttonLoadMoreStories = findViewById(R.id.buttonLoadMoreStories);
        buttonLoadMoreStories.setOnClickListener(this::loadMoreStories);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        clearListsAndUpdateAdapter();
        loadAndRefreshStories();
        return super.onOptionsItemSelected(item);
    }

    /**
     * Fetches more stories from server asynchronously.
     *
     * @param view button
     */
    public void loadMoreStories(View view) {
        //get stories asynchronously
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        try {
            executorService.submit(getStories()).get(5, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get all stories ids.
     *
     * @return FutureTask
     */
    private FutureTask<Void> getAllStoryIds() {
        return new FutureTask<>(() -> {
            HttpURLConnection connection = null;
            try {
                //fetch stories
                URL url = new URL(URL_STORIES);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                //add story ids
                storiesIds.addAll(Utils.getInstance().getStoryIds(connection));
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                assert connection != null;
                connection.disconnect();
            }
        }, null);
    }

    /**
     * Get stories to display.
     *
     * @return FutureTask
     */
    private FutureTask<Void> getStories() {
        return new FutureTask<>(() -> {
            HttpURLConnection connection = null;
            //get data from the first 10 stories
            for (int i = 0; i < 10; i++) {
                try {
                    URL url = new URL("https://hacker-news.firebaseio.com/v0/item/"
                            + storiesIds.get(i)
                            + ".json?print=pretty");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.connect();

                    try {
                        Story story = Utils.getInstance().createStory(connection);

                        //add to stories lists if are not already there (external bug)
                        if (!storiesTitles.contains(story.getTitle())) {
                            storiesTitles.add(story.getTitle());
                            stories.add(story);
                            idsToRemove.add(story.getId());
                        }
                    } catch (Exception ignored) {
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    assert connection != null;
                    connection.disconnect();
                }
            }
            adapter.notifyDataSetChanged();

            //remove ids from list
            for (String id : idsToRemove) {
                storiesIds.remove(id);
            }
            idsToRemove.clear();
        }, null);
    }

    private void loadAndRefreshStories() {
        //Gets all stories ids asynchronously
        //Gets stories asynchronously
        ExecutorService executor = Executors.newFixedThreadPool(2);
        try {
            executor.submit(getAllStoryIds()).get(5, TimeUnit.SECONDS);
            executor.submit(getStories()).get();
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    private void clearListsAndUpdateAdapter() {
        stories.clear();
        storiesIds.clear();
        storiesTitles.clear();
        adapter.notifyDataSetChanged();
    }

    private void initArrayLists() {
        storiesIds = new ArrayList<>();
        stories = new ArrayList<>();
        storiesTitles = new ArrayList<>();
        idsToRemove = new ArrayList<>();
    }
}