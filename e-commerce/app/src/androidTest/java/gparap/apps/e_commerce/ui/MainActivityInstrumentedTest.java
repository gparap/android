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
import static androidx.test.espresso.Espresso.pressBackUnconditionally;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import gparap.apps.e_commerce.R;
import gparap.apps.e_commerce.data.CartRepository;
import gparap.apps.e_commerce.utils.AppConstants;

public class MainActivityInstrumentedTest {
    private final String username = "test_user";
    private View decorView;
    private Context context;
    private ActivityScenario<MainActivity> activityScenario;

    @Before
    public void setUp() {
        //create an intent to pass the username needed for main activity
        Intent intent = new Intent(ApplicationProvider.getApplicationContext(), MainActivity.class);
        intent.putExtra(AppConstants.USERNAME, username);

        //launch activity with intent
        activityScenario = ActivityScenario.launch(intent);

        //get the root view of this activity
        activityScenario.onActivity(activity -> decorView = activity.getWindow().getDecorView());

        //get the context of this activity
        context = ApplicationProvider.getApplicationContext();
    }

    @Test
    public void isVisible_customToolbar() {
        activityScenario.onActivity(activity -> {
            Toolbar toolbar = activity.findViewById(R.id.toolbar_main);
            String title = activity.getString(R.string.text_categories);
            assertEquals(toolbar.getTitle().toString(), title);
        });
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void isVisible_recycleViewCategories() {
        activityScenario.onActivity(activity -> {
            RecyclerView recyclerView = activity.findViewById(R.id.recycler_view_categories);
            assert recyclerView.getAdapter().getItemCount() > 0;
        });
    }

    @Test
    public void onMenuClickLogout_redirectToLogin() {
        //click "logout" menu item
        //!!! using try-catch because menu item may be shown as icon if there is room in the appBar
        try {
            onView(withId(R.id.menu_item_logout)).perform(click());
        } catch (NoMatchingViewException e) {
            Espresso.openActionBarOverflowOrOptionsMenu(context);
            onView(withText(R.string.text_logout)).perform(click());
        }

        //test here
        onView(withId(R.id.layout_activity_login)).check(matches(isDisplayed()));
    }

    @Test
    public void onMenuClickLogout_showGoodbyeToast() {
        //click "logout" menu item
        //!!! using try-catch because menu item may be shown as icon if there is room in the appBar
        try {
            onView(withId(R.id.menu_item_logout)).perform(click());
        } catch (NoMatchingViewException e) {
            Espresso.openActionBarOverflowOrOptionsMenu(context);
            onView(withText(R.string.text_logout)).perform(click());
        }

        //test here
        String welcomeText = context.getResources().getString(R.string.text_goodbye_user_after_logout) + username;
        onView(withText(welcomeText))
                .inRoot(withDecorView(not(decorView)))
                .check(matches(isDisplayed()));
    }

    @Test
    public void onLoad_showWelcomeToast() {
        String welcomeText = context.getResources().getString(R.string.text_welcome_user_after_login) + username;
        onView(withText(welcomeText))
                .inRoot(withDecorView(not(decorView)))
                .check(matches(isDisplayed()));
    }

    @Test
    public void addItemToCart_isCorrect() {
        AtomicInteger cartItemsCount = new AtomicInteger();

        //empty the cart first
        CartRepository repository = new CartRepository(InstrumentationRegistry.getInstrumentation().getTargetContext());
        repository.emptyCart();

        addItemToCart();

        //get the cart items count after adding a new item
        activityScenario.onActivity(activity -> cartItemsCount.set(activity.getCartItemsCount()));

        //should be different values
        assertNotEquals(cartItemsCount.get(), 0);
    }

    @Test
    public void removeItemFromCart_isCorrect() {
        AtomicInteger cartItemsCount = new AtomicInteger();

        //empty the cart first
        CartRepository repository = new CartRepository(InstrumentationRegistry.getInstrumentation().getTargetContext());
        repository.emptyCart();

        addItemToCart();

        //open cart and remove item
        onView(withId(R.id.menu_item_cart)).perform(click());
        onView(withId(R.id.button_cart_remove_item)).perform(click());
        onView(withText(R.string.text_dialog_ok)).perform(click());
        pressBackUnconditionally();

        //get the cart items count after adding a new item
        activityScenario.onActivity(activity -> cartItemsCount.set(activity.getCartItemsCount()));

        //should be different values
        assertEquals(cartItemsCount.get(), 0);
    }

    @Test
    public void addItemQuantityToCart_isCorrect() {
        //empty the cart first
        CartRepository repository = new CartRepository(InstrumentationRegistry.getInstrumentation().getTargetContext());
        repository.emptyCart();

        addItemToCart();

        //open cart and add more of this item
        onView(withId(R.id.menu_item_cart)).perform(click());
        onView(withId(R.id.button_cart_add_one)).perform(click());

        //check if the item's quantity changed
        onView(withText("2")).check(matches(isDisplayed()));
    }

    @Test
    public void subtractItemQuantityFromCart_isCorrect() {
        //empty the cart first
        CartRepository repository = new CartRepository(InstrumentationRegistry.getInstrumentation().getTargetContext());
        repository.emptyCart();

        addItemToCart();

        //open cart and remove quantity from this item
        onView(withId(R.id.menu_item_cart)).perform(click());
        onView(withId(R.id.button_cart_remove_one)).perform(click());

        //check if the item's quantity changed
        onView(withText("0")).check(matches(isDisplayed()));
    }

    //open the first category, select the first item and add it to cart
    private void addItemToCart() {
        onView(withId(R.id.recycler_view_categories)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.recycler_view_products)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.fab_add_to_cart_product_details)).perform(click());
        onView(withText(R.string.text_dialog_ok)).perform(click());
        Espresso.pressBackUnconditionally();
    }
}