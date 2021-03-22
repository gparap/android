/*
 * Copyright 2021 gparap
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package gparap.apps.multiplex_clock.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;

public class MainActivity extends AppCompatActivity {
    private ChronometerFragment chronometerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set MaterialToolbar to act as the ActionBar
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //show the clock when app starts
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentContainer, ClockFragment.class, null)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.fragment_navigation, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        //navigate to fragments
        switch (id) {

            //clock
            case R.id.menu_item_clock:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, ClockFragment.class, null)
                        .commit();
                break;

            //chronometer timer
            case R.id.menu_item_chronometer:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, ChronometerFragment.class, null)
                        .commit();
                break;

            //countdown timer
            case R.id.menu_item_countdown_timer:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, CountdownFragment.class, null)
                        .commit();
                break;

            //alarm clock
            case R.id.menu_item_alarm_clock:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, AlarmClockFragment.class, null)
                        .commit();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}