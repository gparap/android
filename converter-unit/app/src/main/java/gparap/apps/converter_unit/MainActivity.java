/*
 * Copyright 2025 gparap
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
package gparap.apps.converter_unit;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    //!!! temporary view for making the tests
    private TextView testingItemSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //get the testing TextView
        testingItemSelected = findViewById(R.id.testing_item_selected);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //get the id of the selected item
        int itemId = item.getItemId();

        //Handle the selected item TODO: replace with actual Unit Converter Category Items
        switch (itemId) {

            //Category #1
            case R.id.submenu_item_1_1:testingItemSelected.setText("Sub Menu 1-1");
                break;
            case R.id.submenu_item_1_2:testingItemSelected.setText("Sub Menu 1-2");
                break;
            case R.id.submenu_item_1_3:testingItemSelected.setText("Sub Menu 1-3");
                break;
            case R.id.submenu_item_1_4:testingItemSelected.setText("Sub Menu 1-4");
                break;
            case R.id.submenu_item_1_5:testingItemSelected.setText("Sub Menu 1-5");
                break;

            //Category #2
            case R.id.submenu_item_2_1:testingItemSelected.setText("Sub Menu 2-1");
                break;
            case R.id.submenu_item_2_2:testingItemSelected.setText("Sub Menu 2-2");
                break;
            case R.id.submenu_item_2_3:testingItemSelected.setText("Sub Menu 2-3");
                break;
            case R.id.submenu_item_2_4:testingItemSelected.setText("Sub Menu 2-4");
                break;
            case R.id.submenu_item_2_5:testingItemSelected.setText("Sub Menu 2-5");
                break;

            //Category #3
            case R.id.submenu_item_3_1:testingItemSelected.setText("Sub Menu 3-1");
                break;
            case R.id.submenu_item_3_2:testingItemSelected.setText("Sub Menu 3-2");
                break;
            case R.id.submenu_item_3_3:testingItemSelected.setText("Sub Menu 3-3");
                break;
            case R.id.submenu_item_3_4:testingItemSelected.setText("Sub Menu 3-4");
                break;
            case R.id.submenu_item_3_5:testingItemSelected.setText("Sub Menu 3-5");
                break;

            //Category #4
            case R.id.submenu_item_4_1:testingItemSelected.setText("Sub Menu 4-1");
                break;
            case R.id.submenu_item_4_2:testingItemSelected.setText("Sub Menu 4-2");
                break;
            case R.id.submenu_item_4_3:testingItemSelected.setText("Sub Menu 4-3");
                break;
            case R.id.submenu_item_4_4:testingItemSelected.setText("Sub Menu 4-4");
                break;
            case R.id.submenu_item_4_5:testingItemSelected.setText("Sub Menu 4-5");
                break;

            //Category #5
            case R.id.submenu_item_5_1:testingItemSelected.setText("Sub Menu 5-1");
                break;
            case R.id.submenu_item_5_2:testingItemSelected.setText("Sub Menu 5-2");
                break;
            case R.id.submenu_item_5_3:testingItemSelected.setText("Sub Menu 5-3");
                break;
            case R.id.submenu_item_5_4:testingItemSelected.setText("Sub Menu 5-4");
                break;
            case R.id.submenu_item_5_5:testingItemSelected.setText("Sub Menu 5-5");
                break;

            //Category #6
            case R.id.submenu_item_6_1:testingItemSelected.setText("Sub Menu 6-1");
                break;
            case R.id.submenu_item_6_2:testingItemSelected.setText("Sub Menu 6-2");
                break;
            case R.id.submenu_item_6_3:testingItemSelected.setText("Sub Menu 6-3");
                break;
            case R.id.submenu_item_6_4:testingItemSelected.setText("Sub Menu 6-4");
                break;
            case R.id.submenu_item_6_5:testingItemSelected.setText("Sub Menu 6-5");
                break;

            //Category #7
            case R.id.submenu_item_7_1:testingItemSelected.setText("Sub Menu 7-1");
                break;
            case R.id.submenu_item_7_2:testingItemSelected.setText("Sub Menu 7-2");
                break;
            case R.id.submenu_item_7_3:testingItemSelected.setText("Sub Menu 7-3");
                break;
            case R.id.submenu_item_7_4:testingItemSelected.setText("Sub Menu 7-4");
                break;
            case R.id.submenu_item_7_5:testingItemSelected.setText("Sub Menu 7-5");
                break;

            //Category #8
            case R.id.submenu_item_8_1:testingItemSelected.setText("Sub Menu 8-1");
                break;
            case R.id.submenu_item_8_2:testingItemSelected.setText("Sub Menu 8-2");
                break;
            case R.id.submenu_item_8_3:testingItemSelected.setText("Sub Menu 8-3");
                break;
            case R.id.submenu_item_8_4:testingItemSelected.setText("Sub Menu 8-4");
                break;
            case R.id.submenu_item_8_5:testingItemSelected.setText("Sub Menu 8-5");
                break;

                //Category #9
            case R.id.submenu_item_9_1:testingItemSelected.setText("Sub Menu 9-1");
                break;
            case R.id.submenu_item_9_2:testingItemSelected.setText("Sub Menu 9-2");
                break;
            case R.id.submenu_item_9_3:testingItemSelected.setText("Sub Menu 9-3");
                break;
            case R.id.submenu_item_9_4:testingItemSelected.setText("Sub Menu 9-4");
                break;
            case R.id.submenu_item_9_5:testingItemSelected.setText("Sub Menu 9-5");
                break;

            //Category #10
            case R.id.submenu_item_10_1:testingItemSelected.setText("Sub Menu 10-1");
                break;
            case R.id.submenu_item_10_2:testingItemSelected.setText("Sub Menu 10-2");
                break;
            case R.id.submenu_item_10_3:testingItemSelected.setText("Sub Menu 10-3");
                break;
            case R.id.submenu_item_10_4:testingItemSelected.setText("Sub Menu 10-4");
                break;
            case R.id.submenu_item_10_5:testingItemSelected.setText("Sub Menu 10-5");
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}