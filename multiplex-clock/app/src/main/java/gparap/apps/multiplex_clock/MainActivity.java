/*
 * Copyright 2021-2023 gparap
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
package gparap.apps.multiplex_clock;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;

import gparap.apps.multiplex_clock.ui.AlarmClockFragment;
import gparap.apps.multiplex_clock.ui.ChronometerFragment;
import gparap.apps.multiplex_clock.ui.ClockFragment;
import gparap.apps.multiplex_clock.ui.CountdownFragment;

public class MainActivity extends AppCompatActivity {
    private int currentFragmentID;
    MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set MaterialToolbar to act as the ActionBar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //show the clock when app starts
        if (savedInstanceState == null) {
            currentFragmentID = 1;
            toolbar.setTitle(getString(R.string.menu_item_clock));
        } else {
            //restore state
            toolbar.setTitle(savedInstanceState.getString("toolbar_title"));
        }

        addCurrentFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.fragment_navigation, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        //navigate to clock fragment
        if (id == R.id.menu_item_clock) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, ClockFragment.class, null)
                    .commitNow();
            currentFragmentID = 1;
            toolbar.setTitle(getString(R.string.menu_item_clock));
        }

        //navigate to chronometer timer fragment
        if (id == R.id.menu_item_chronometer) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, ChronometerFragment.class, null)
                    .commitNow();
            currentFragmentID = 2;
            toolbar.setTitle(getString(R.string.menu_item_chronometer));
        }

        //navigate to countdown timer fragment
        if (id == R.id.menu_item_countdown_timer) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, CountdownFragment.class, null)
                    .commit();
            currentFragmentID = 3;
            toolbar.setTitle(getString(R.string.menu_item_countdown_timer));
        }

        //navigate to alarm clock fragment
        if (id == R.id.menu_item_alarm_clock) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, AlarmClockFragment.class, null)
                    .commit();
            currentFragmentID = 4;
            toolbar.setTitle(getString(R.string.menu_item_alarm_clock));
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        //save state
        if (getSupportActionBar() != null) {
            outState.putString("toolbar_title", (String) getSupportActionBar().getTitle());
        } else {
            outState.putString("toolbar_title", getString(R.string.app_name));
        }
    }

    /**
     * Adds the current fragment after orientation changes.
     */
    private void addCurrentFragment() {
        switch (currentFragmentID) {
            //show the clock
            case 1:
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragmentContainer, ClockFragment.class, null)
                        .commitNow();
                currentFragmentID = 1;
                break;

            //show the chronometer timer
            case 2:
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragmentContainer, ChronometerFragment.class, null)
                        .commitNow();
                currentFragmentID = 2;
                break;

            //show the countdown timer
            case 3:
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragmentContainer, CountdownFragment.class, null)
                        .commitNow();
                currentFragmentID = 3;
                break;

            //show the alarm clock
            case 4:
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragmentContainer, AlarmClockFragment.class, null)
                        .commitNow();
                currentFragmentID = 4;
                break;
        }
    }
}