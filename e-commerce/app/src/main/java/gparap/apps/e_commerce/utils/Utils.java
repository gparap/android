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
package gparap.apps.e_commerce.utils;

import android.content.res.Configuration;
import android.content.res.Resources;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.badge.BadgeDrawable;

import gparap.apps.e_commerce.R;

public class Utils {
    private static Utils instance = null;

    private Utils() {
    }

    public static Utils getInstance() {
        if (instance == null) {
            instance = new Utils();
        }
        return instance;
    }

    /**
     * Gets the RecyclerView spanCount based on device orientation (Default: PORTRAIT)
     */
    public int getSpanCount(int orientation) {
        int spanCount;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            spanCount = AppConstants.SPAN_COUNT_PORTRAIT;
        } else {
            spanCount = AppConstants.SPAN_COUNT_LANDSCAPE;
        }
        return spanCount;
    }

    /**
     * Creates the product's image Uri prefix based on its category.
     */
    public String createUriPrefix(int categoryId) {
        String uriPrefix = AppConstants.IMAGES_URL;
        switch (categoryId) {
            case 1:
                uriPrefix += AppConstants.PRODUCTS_AUTOS_URL;
                break;
            case 2:
                uriPrefix += AppConstants.PRODUCTS_BIKES_URL;
                break;
            case 3:
                uriPrefix += AppConstants.PRODUCTS_DRINKS_URL;
                break;
            case 4:
                uriPrefix += AppConstants.PRODUCTS_GARDEN_URL;
                break;
            case 5:
                uriPrefix += AppConstants.PRODUCTS_GYM_URL;
                break;
            case 6:
                uriPrefix += AppConstants.PRODUCTS_TECH_URL;
                break;
        }
        return uriPrefix;
    }

    /**
     * Gets a product's availability based on the items left in stock.
     */
    public String getProductAvailability(int stock, Resources res) {
        if (stock == AppConstants.PRODUCT_ITEM_STOCK_ZERO) {
            return res.getString(R.string.stock_unavailable);
        } else if (stock <= AppConstants.PRODUCT_ITEM_STOCK_LOW) {
            return res.getString(R.string.stock_low);
        } else {
            return res.getString(R.string.stock_available);
        }
    }

    /**
     * Sets up and returns a custom toolbar to act as the current activity's ActionBar.
     *
     * @param activity AppCompatActivity
     * @param resId    resourceId
     * @param title    title
     * @return Toolbar
     */
    public Toolbar setupActionBar(AppCompatActivity activity, int resId, String title) {
        Toolbar toolbar = activity.findViewById(resId);
        toolbar.setTitle(title);
        activity.setSupportActionBar(toolbar);
        return toolbar;
    }

    /**
     * Creates a badge drawable with default values to be used for the shopping cart.
     *
     * @param activity AppCompatActivity
     * @return BadgeDrawable
     */
    public BadgeDrawable createBadge(AppCompatActivity activity) {
        BadgeDrawable badgeDrawable = BadgeDrawable.create(activity);
        badgeDrawable.setVisible(true);
        badgeDrawable.setNumber(0);
        return badgeDrawable;
    }
}
