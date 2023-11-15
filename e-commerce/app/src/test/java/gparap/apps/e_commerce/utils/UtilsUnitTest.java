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

import static org.junit.Assert.assertEquals;

import android.content.res.Configuration;

import org.junit.Test;

import java.util.ArrayList;

import gparap.apps.e_commerce.data.CartItemModel;

public class UtilsUnitTest {
    @Test
    public void getSpanCount_isCorrect() {
        int orientation = Configuration.ORIENTATION_PORTRAIT;
        int expectedSpanCount = 2;
        int actualSpanCount = Utils.getInstance().getSpanCount(orientation);
        assertEquals(expectedSpanCount, actualSpanCount);
    }

    @Test
    public void createUriPrefix_isCorrect() {
        int categoryId = 1;
        String expectedUriPrefix = AppConstants.IMAGES_URL + AppConstants.PRODUCTS_AUTOS_URL;
        String actualUriPrefix = Utils.getInstance().createUriPrefix(categoryId);
        assertEquals(expectedUriPrefix, actualUriPrefix);
    }

    @Test
    public void getCartTotalCost() {
        //add items to cart
        ArrayList<CartItemModel> items = new ArrayList<>();
        items.add(new CartItemModel(1,1, "item1", 50.99f, 0, "", 1));
        items.add(new CartItemModel(2,1, "item2", 10.99f, 0, "", 2));

        float expectedCost = 72.97f;
        float actualCost = Utils.getInstance().getCartTotalCost(items);
        assertEquals(expectedCost, actualCost, 0);
    }

    @Test
    public void getCartFinalCost() {
        //add items to cart
        ArrayList<CartItemModel> items = new ArrayList<>();
        items.add(new CartItemModel(1,1, "item1", 19.99f, 5, "", 1));
        items.add(new CartItemModel(2,1, "item2", 59.99f, 15, "", 2));

        float expectedCost = 129.972f;
        float actualCost = Utils.getInstance().getCartFinalCost(items);
        assertEquals(expectedCost, actualCost, 0);
    }

    @Test
    public void getCartDiscount() {
        //add items to cart
        ArrayList<CartItemModel> items = new ArrayList<>();
        items.add(new CartItemModel(1,1, "item1", 19.99f, 5, "", 3));
        items.add(new CartItemModel(1,1, "item1", 7.49f, 0, "", 1));
        items.add(new CartItemModel(2,1, "item2", 59.99f, 15, "", 2));

        int expectedDiscount = 5;
        int actualDiscount = Utils.getInstance().getCartDiscount(items);
        assertEquals(expectedDiscount, actualDiscount);
    }

    @Test
    public void formatPrice() {
        String expectedPrice = "59.97€";
        String actualPrice = Utils.getInstance().formatPrice((49.99f + 2 * 4.99f), "€");
        assertEquals(expectedPrice, actualPrice);
    }
}
