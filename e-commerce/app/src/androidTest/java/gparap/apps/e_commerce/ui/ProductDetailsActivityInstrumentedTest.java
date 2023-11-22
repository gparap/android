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
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.not;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.TextView;

import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import gparap.apps.e_commerce.R;
import gparap.apps.e_commerce.data.CartItemsDbHelper;
import gparap.apps.e_commerce.data.ProductResponseModel;
import gparap.apps.e_commerce.utils.AppConstants;

public class ProductDetailsActivityInstrumentedTest {
    @SuppressWarnings("FieldCanBeLocal")
    private ProductResponseModel product;
    private ActivityScenario<ProductDetailsActivity> activityScenario;
    private View decorView;
    private Context context;

    @Before
    public void setUp() {
        //create a test product with details
        product = new ProductResponseModel();
        product.setId(0);
        product.setName("jumper cables");
        product.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        product.setPrice(19.99F);
        product.setStock(100);
        product.setUrl("jumper-cables.jpg");
        product.setKeywords("auto,car,cables,battery");

        //add test product to a parcelable array to be used as intent extra
        ArrayList<ProductResponseModel> productDetails = new ArrayList<>();
        productDetails.add(product);

        //create an intent to pass the product details needed for this activity
        Intent intent = new Intent(ApplicationProvider.getApplicationContext(), ProductDetailsActivity.class);
        intent.putParcelableArrayListExtra(AppConstants.EXTRAS_PRODUCT_DETAILS, productDetails);

        //launch activity with intent
        activityScenario = ActivityScenario.launch(intent);

        //get the root view of this activity
        activityScenario.onActivity(activity -> decorView = activity.getWindow().getDecorView());

        //get context
        context = InstrumentationRegistry.getInstrumentation().getTargetContext();
    }

    @Test
    public void isVisible_productImage() {
        onView(withId(R.id.image_view_product_details)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_productPrice() {
        onView(withId(R.id.text_view_price_product_details)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_productDescription() {
        onView(withId(R.id.text_view_description_product_details)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_addToCartButton() {
        onView(withId(R.id.fab_add_to_cart_product_details)).check(matches(isDisplayed()));
    }

    @Test
    public void isUpdated_appbarWithProductName() {
        activityScenario.onActivity(activity -> {
            //noinspection ConstantConditions
            assert (activity.getSupportActionBar().getTitle().toString().equals(product.getName()));
        });
    }

    @Test
    public void isCorrect_productPrice() {
        //get price prefix & suffix
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        String pricePrefix = context.getResources().getString(R.string.price_prefix);
        String priceSuffix = context.getResources().getString(R.string.price_suffix);

        activityScenario.onActivity(activity -> {
            TextView price = activity.findViewById(R.id.text_view_price_product_details);
            assert (price.getText().toString().equals(
                    pricePrefix + product.getPrice().toString() + priceSuffix
            ));
        });
    }

    @Test
    public void isCorrect_productAvailability() {
        String expectedAvailability = InstrumentationRegistry.getInstrumentation().getTargetContext()
                .getResources().getString(R.string.stock_available);

        activityScenario.onActivity(activity -> {
            TextView availability = activity.findViewById(R.id.text_view_stock_product_details);
            assert (availability.getText().toString().equals(expectedAvailability));
        });
    }

    @Test
    public void isCorrect_productDescription() {
        activityScenario.onActivity(activity -> {
            TextView desc = activity.findViewById(R.id.text_view_description_product_details);
            assert (desc.getText().toString().equals(product.getDescription()));
        });
    }

    @Test
    public void addItemToCart_displayToast() {
        deleteItemFromCart();
        addItemToCart();
        onView(withText(context.getString(R.string.toast_add_to_cart_success)))
                .inRoot(withDecorView(not(decorView)))
                .check(matches(isDisplayed()));
    }

    @Test
    public void addItemToCartFailed_displayToast() {
        deleteItemFromCart();
        addItemToCart();
        addItemToCart();
        onView(withText(context.getString(R.string.toast_add_to_cart_failure)))
                .inRoot(withDecorView(not(decorView)))
                .check(matches(isDisplayed()));
    }

    private void addItemToCart() {
        //add test item to cart
        onView(withId(R.id.fab_add_to_cart_product_details)).perform(click());
        onView(withText(context.getString(R.string.text_dialog_ok))).perform(click());
    }

    //delete previous test item from cart (if exists)
    private void deleteItemFromCart() {
        try (CartItemsDbHelper dbHelper = new CartItemsDbHelper(InstrumentationRegistry.getInstrumentation().getTargetContext())){
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            String selection = AppConstants.COLUMN_NAME_ITEM_ID + " LIKE ?";
            String[] selectionArgs = {"0"};
            db.delete(AppConstants.TABLE_NAME_CART_ITEMS, selection, selectionArgs);
        }
    }
}