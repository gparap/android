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
package gparap.apps.classifieds.ui.market.categories;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.util.ArrayMap;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import gparap.apps.classifieds.R;
import gparap.apps.classifieds.ui.market.categories.clothing.ClothingFragment;

@RunWith(AndroidJUnit4.class)
public class ClothingFragmentInstrumentedTest {
    private final ArrayMap<String, String> categoryNames = new ArrayMap<>();
    private final ArrayMap<String, String> categoryDescriptions = new ArrayMap<>();

    @Before
    public void setUp() {
        FragmentScenario.launchInContainer(ClothingFragment.class);

        //init category actual values
        categoryNames.put("Accessories", "Accessories");
        categoryNames.put("Kids", "Kids");
        categoryNames.put("Men", "Men");
        categoryNames.put("Shoes", "Shoes");
        categoryNames.put("Sportswear", "Sportswear");
        categoryNames.put("Women", "Women");
        categoryDescriptions.put("Accessories", "Bags, belts, hats, and jewelry.");
        categoryDescriptions.put("Kids", "Clothing for babies and children.");
        categoryDescriptions.put("Men", "Shirts, pants, jackets, and accessories for men.");
        categoryDescriptions.put("Shoes", "Footwear for all ages and styles.");
        categoryDescriptions.put("Sportswear", "Activewear, gym, and outdoor clothing.");
        categoryDescriptions.put("Women", "Dresses, tops, pants, and accessories for women.");
    }

    @Test
    public void isVisible_textView_marketCategory_Clothing_Accessories() {
        onView(withId(R.id.textView_marketCategory_Clothing_Accessories)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Clothing_accessoriesDetails() {
        onView(withId(R.id.textView_marketCategory_Clothing_accessoriesDetails)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageButton_marketCategory_Clothing_Accessories() {
        onView(withId(R.id.imageButton_marketCategory_Clothing_Accessories)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_textWithDetails_categoryAccessories() {
        onView(withText(R.string.text_category_clothing_accessories)).check(matches(withText(categoryNames.get("Accessories"))));
        onView(withText(R.string.text_category_clothing_accessories_details)).check(matches(withText(categoryDescriptions.get("Accessories"))));
    }

    @Test
    public void isVisible_textView_marketCategory_Clothing_Kids() {
        onView(withId(R.id.textView_marketCategory_Clothing_Kids)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Clothing_kidsDetails() {
        onView(withId(R.id.textView_marketCategory_Clothing_kidsDetails)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageButton_marketCategory_Clothing_Kids() {
        onView(withId(R.id.imageButton_marketCategory_Clothing_Kids)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_textWithDetails_categoryKids() {
        onView(withText(R.string.text_category_clothing_kids)).check(matches(withText(categoryNames.get("Kids"))));
        onView(withText(R.string.text_category_clothing_kids_details)).check(matches(withText(categoryDescriptions.get("Kids"))));
    }

    @Test
    public void isVisible_textView_marketCategory_Clothing_Men() {
        onView(withId(R.id.textView_marketCategory_Clothing_Men)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Clothing_menDetails() {
        onView(withId(R.id.textView_marketCategory_Clothing_menDetails)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageButton_marketCategory_Clothing_Men() {
        onView(withId(R.id.imageButton_marketCategory_Clothing_Men)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_textWithDetails_categoryMen() {
        onView(withText(R.string.text_category_clothing_men)).check(matches(withText(categoryNames.get("Men"))));
        onView(withText(R.string.text_category_clothing_men_details)).check(matches(withText(categoryDescriptions.get("Men"))));
    }

    @Test
    public void isVisible_textView_marketCategory_Clothing_Shoes() {
        onView(withId(R.id.textView_marketCategory_Clothing_Shoes)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Clothing_shoesDetails() {
        onView(withId(R.id.textView_marketCategory_Clothing_shoesDetails)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageButton_marketCategory_Clothing_Shoes() {
        onView(withId(R.id.imageButton_marketCategory_Clothing_Shoes)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_textWithDetails_categoryShoes() {
        onView(withText(R.string.text_category_clothing_shoes)).check(matches(withText(categoryNames.get("Shoes"))));
        onView(withText(R.string.text_category_clothing_shoes_details)).check(matches(withText(categoryDescriptions.get("Shoes"))));
    }

    @Test
    public void isVisible_textView_marketCategory_Clothing_Sportswear() {
        onView(withId(R.id.textView_marketCategory_Clothing_Sportswear)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Clothing_sportswearDetails() {
        onView(withId(R.id.textView_marketCategory_Clothing_sportswearDetails)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageButton_marketCategory_Clothing_Sportswear() {
        onView(withId(R.id.imageButton_marketCategory_Clothing_Sportswear)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_textWithDetails_categorySportswear() {
        onView(withText(R.string.text_category_clothing_sportswear)).check(matches(withText(categoryNames.get("Sportswear"))));
        onView(withText(R.string.text_category_clothing_sportswear_details)).check(matches(withText(categoryDescriptions.get("Sportswear"))));
    }

    @Test
    public void isVisible_textView_marketCategory_Clothing_Women() {
        onView(withId(R.id.textView_marketCategory_Clothing_Women)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Clothing_womenDetails() {
        onView(withId(R.id.textView_marketCategory_Clothing_womenDetails)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageButton_marketCategory_Clothing_Women() {
        onView(withId(R.id.imageButton_marketCategory_Clothing_Women)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_textWithDetails_categoryWomen() {
        onView(withText(R.string.text_category_clothing_women)).check(matches(withText(categoryNames.get("Women"))));
        onView(withText(R.string.text_category_clothing_women_details)).check(matches(withText(categoryDescriptions.get("Women"))));
    }
}
