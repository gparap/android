/*
 * Copyright 2023 gparap
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
package gparap.apps.e_commerce.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static org.junit.Assert.assertEquals;

import android.content.Intent;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;

import org.junit.Before;
import org.junit.Test;

import gparap.apps.e_commerce.R;
import gparap.apps.e_commerce.utils.AppConstants;

public class ProductActivityInstrumentedTest {
    @SuppressWarnings("FieldCanBeLocal")
    private final int categoryId = 1;
    private ActivityScenario<ProductActivity> activityScenario;

    @Before
    public void setUp() {
        //create an intent to pass the category id needed for this activity
        Intent intent = new Intent(ApplicationProvider.getApplicationContext(), ProductActivity.class);
        intent.putExtra(AppConstants.CATEGORY_ID, categoryId);

        //launch activity with intent
        activityScenario = ActivityScenario.launch(intent);
    }

    @Test
    public void isVisible_customToolbar() {
        activityScenario.onActivity(activity -> {
            Toolbar toolbar = activity.findViewById(R.id.toolbar_products);
            String title = activity.getString(R.string.text_products);
            assertEquals(toolbar.getTitle().toString(), title);
        });
    }

    @Test
    public void isVisible_recycleViewProducts() {
        onView(withId(R.id.recycler_view_products)).check(matches(isDisplayed()));
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void hasProducts_recycleViewProducts() throws InterruptedException {
        //wait for the products to load...
        Thread.sleep(1667);

        activityScenario.onActivity(activity -> {
            RecyclerView recyclerView = activity.findViewById(R.id.recycler_view_products);
            assert recyclerView.getAdapter().getItemCount() > 0;
        });
    }
}