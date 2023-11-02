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
}
