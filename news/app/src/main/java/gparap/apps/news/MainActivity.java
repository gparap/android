package gparap.apps.news;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by gparap on 2020-11-18.
 */
@SuppressWarnings("Convert2Lambda")
public class MainActivity extends AppCompatActivity {
    final static String URL_STORIES = "https://hacker-news.firebaseio.com/v0/newstories.json?print=pretty";
    Button buttonLoadMoreStories;
    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<String> storiesIds;
    List<Story> stories;
    List<String> storiesTitles;
    List<String> idsToRemove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initArrayLists();

        //get widgets
        buttonLoadMoreStories = findViewById(R.id.buttonLoadMoreStories);

        //init list view with adapter
        listView = findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, storiesTitles);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //visit story url
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(stories.get(position).getUrl()));
                startActivity(intent);
            }
        });
        loadAndRefreshStories();
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
    public void onButtonClick(View view) {
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
        return new FutureTask<>(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    //fetch stories
                    URL url = new URL(URL_STORIES);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.connect();

                    //read stories
                    InputStream inputStream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    String readLine;
                    readLine = reader.readLine();
                    readLine = readLine.replace("[", "");
                    readLine = readLine.replace("]", "");
                    readLine = readLine.replace(" ", "");
                    String[] stringArray = readLine.split(",");
                    reader.close();

                    //add story ids
                    storiesIds.addAll(Arrays.asList(stringArray));
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    connection.disconnect();
                }
            }
        }, null);
    }

    /**
     * Get stories to display.
     *
     * @return FutureTask
     */
    private FutureTask<Void> getStories() {
        return new FutureTask<>(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                //get data from the first 10 stories
                for (int i = 0; i < 10; i++) {
                    try {
                        URL url = new URL("https://hacker-news.firebaseio.com/v0/item/"
                                + storiesIds.get(i)
                                + ".json?print=pretty");
                        connection = (HttpURLConnection) url.openConnection();
                        connection.connect();
                        StringBuilder stringBuilder = new StringBuilder();
                        String readLine;
                        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        while ((readLine = reader.readLine()) != null) {
                            stringBuilder.append(readLine);
                        }
                        reader.close();
                        //get json object from string data and create story object
                        try {
                            JSONObject jsonObject = new JSONObject(stringBuilder.toString());
                            Story story = new Story();
                            story.setId(jsonObject.get("id").toString());
                            story.setTitle(jsonObject.get("title").toString());
                            story.setUrl(jsonObject.get("url").toString());

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
                        connection.disconnect();
                    }
                }
                adapter.notifyDataSetChanged();

                //remove ids from list
                for (String id : idsToRemove) {
                    storiesIds.remove(id);
                }
                idsToRemove.clear();
            }
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