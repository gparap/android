/*
 * Copyright 2026 gparap
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
package gparap.apps.classifieds;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import gparap.apps.classifieds.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    /**
     * @noinspection FieldCanBeLocal
     */
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //create options for NavigationUI interactions with the app bar
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_market, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();

        //get NavHostFragment from container layout
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment_activity_main);

        //get NavController from NavHostFragment
        assert navHostFragment != null;
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        //handle bottom navigation item selections with special options for navigate actions
        binding.navView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            //setup navigate actions
            NavOptions navOptions = new NavOptions.Builder()
                    .setLaunchSingleTop(true)
                    .setRestoreState(true)
                    .setPopUpTo(NavGraph.findStartDestination(navController.getGraph()).getId(), false, true)
                    .build();
            navController.navigate(itemId, null, navOptions);

            return true;
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        //navigate Up within application's activity hierarchy from the action bar
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        return navController.navigateUp() || super.onSupportNavigateUp();
    }
}