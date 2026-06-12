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

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.ArrayMap;
import android.widget.ImageButton;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Objects;

import gparap.apps.classifieds.R;
import gparap.apps.classifieds.ui.market.categories.property.PropertyFragment;
import gparap.apps.classifieds.utils.Utils;

@RunWith(AndroidJUnit4.class)
public class PropertyFragmentInstrumentedTest {
    private final ArrayMap<String, String> categoryNames = new ArrayMap<>();
    private final ArrayMap<String, String> categoryDescriptions = new ArrayMap<>();
    FragmentScenario<PropertyFragment> fragmentScenario;

    @Before
    public void setUp() {
        fragmentScenario = FragmentScenario.launchInContainer(PropertyFragment.class);

        //init category actual values
        categoryNames.put("Commercial", "Commercial");
        categoryNames.put("Guest", "Guest");
        categoryNames.put("Land", "Land");
        categoryNames.put("Rent", "Rent");
        categoryNames.put("Sale", "Sale");
        categoryNames.put("Other", "Other");
        categoryDescriptions.put("Commercial", "Shops, offices, and business spaces.");
        categoryDescriptions.put("Guest", "Paying guest accommodations.");
        categoryDescriptions.put("Land", "Plots, farms, and vacant land.");
        categoryDescriptions.put("Rent", "Houses, apartments, and rooms for rent.");
        categoryDescriptions.put("Sale", "Properties available for purchase.");
        categoryDescriptions.put("Other", "Miscellaneous property listings.");
    }

    @Test
    public void isVisible_textView_marketCategory_Property_Commercial() {
        onView(withId(R.id.textView_marketCategory_Property_Commercial)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Property_commercialDetails() {
        onView(withId(R.id.textView_marketCategory_Property_commercialDetails)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageButton_marketCategory_Property_Commercial() {
        onView(withId(R.id.imageButton_marketCategory_Property_Commercial)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_textWithDetails_categoryCommercial() {
        onView(withText(R.string.text_category_property_commercial)).check(matches(withText(categoryNames.get("Commercial"))));
        onView(withText(R.string.text_category_property_commercial_details)).check(matches(withText(categoryDescriptions.get("Commercial"))));
    }

    @Test
    public void isVisible_textView_marketCategory_Property_Guest() {
        onView(withId(R.id.textView_marketCategory_Property_Guest)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Property_guestDetails() {
        onView(withId(R.id.textView_marketCategory_Property_guestDetails)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageButton_marketCategory_Property_Guest() {
        onView(withId(R.id.imageButton_marketCategory_Property_Guest)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_textWithDetails_categoryGuest() {
        onView(withText(R.string.text_category_property_guest)).check(matches(withText(categoryNames.get("Guest"))));
        onView(withText(R.string.text_category_property_guest_details)).check(matches(withText(categoryDescriptions.get("Guest"))));
    }

    @Test
    public void isVisible_textView_marketCategory_Property_Land() {
        onView(withId(R.id.textView_marketCategory_Property_Land)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Property_landDetails() {
        onView(withId(R.id.textView_marketCategory_Property_landDetails)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageButton_marketCategory_Property_Land() {
        onView(withId(R.id.imageButton_marketCategory_Property_Land)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_textWithDetails_categoryLand() {
        onView(withText(R.string.text_category_property_land)).check(matches(withText(categoryNames.get("Land"))));
        onView(withText(R.string.text_category_property_land_details)).check(matches(withText(categoryDescriptions.get("Land"))));
    }

    @Test
    public void isVisible_textView_marketCategory_Property_Rent() {
        onView(withId(R.id.textView_marketCategory_Property_Rent)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Property_rentDetails() {
        onView(withId(R.id.textView_marketCategory_Property_rentDetails)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageButton_marketCategory_Property_Rent() {
        onView(withId(R.id.imageButton_marketCategory_Property_Rent)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_textWithDetails_categoryRent() {
        onView(withText(R.string.text_category_property_rent)).check(matches(withText(categoryNames.get("Rent"))));
        onView(withText(R.string.text_category_property_rent_details)).check(matches(withText(categoryDescriptions.get("Rent"))));
    }

    @Test
    public void isVisible_textView_marketCategory_Property_Sale() {
        onView(withId(R.id.textView_marketCategory_Property_Sale)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Property_saleDetails() {
        onView(withId(R.id.textView_marketCategory_Property_saleDetails)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageButton_marketCategory_Property_Sale() {
        onView(withId(R.id.imageButton_marketCategory_Property_Sale)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_textWithDetails_categorySale() {
        onView(withText(R.string.text_category_property_sale)).check(matches(withText(categoryNames.get("Sale"))));
        onView(withText(R.string.text_category_property_sale_details)).check(matches(withText(categoryDescriptions.get("Sale"))));
    }

    @Test
    public void isVisible_textView_marketCategory_Property_other() {
        onView(withId(R.id.textView_marketCategory_Property_Other)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Property_otherDetails() {
        onView(withId(R.id.textView_marketCategory_Property_otherDetails)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageButton_marketCategory_Property_other() {
        onView(withId(R.id.imageButton_marketCategory_Property_Other)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_textWithDetails_category_other() {
        onView(withText(R.string.text_category_property_other)).check(matches(withText(categoryNames.get("Other"))));
        onView(withText(R.string.text_category_property_other_details)).check(matches(withText(categoryDescriptions.get("Other"))));
    }

    @Test
    public void areCorrect_imageButtons_Drawables() {   /* !!! Compact test for all images. */
        String[] assets = new String[]{"commercial.jpg", "guest.jpg", "land.jpg", "other.jpg", "rent.jpg", "sale.jpg"};
        AssetManager assetManager = InstrumentationRegistry.getInstrumentation().getTargetContext().getAssets();
        String path = "market/categories/property";
        ArrayList<Drawable> drawables = Utils.getInstance().getDrawablesFromAssets(assetManager, assets, path);

        //TODO: refactor
        fragmentScenario.onFragment(fragment -> {
            //freelance
            ImageButton imageButton = Objects.requireNonNull(fragment.getView()).findViewById(R.id.imageButton_marketCategory_Property_Commercial);
            Bitmap bitmapExpected = ((BitmapDrawable) imageButton.getDrawable()).getBitmap();
            Bitmap bitmapActual = ((BitmapDrawable) drawables.get(0)).getBitmap();
            assert bitmapExpected.sameAs(bitmapActual);
            //full_time
            imageButton = fragment.getView().findViewById(R.id.imageButton_marketCategory_Property_Guest);
            bitmapExpected = ((BitmapDrawable) imageButton.getDrawable()).getBitmap();
            bitmapActual = ((BitmapDrawable) drawables.get(1)).getBitmap();
            assert bitmapExpected.sameAs(bitmapActual);
            //internship
            imageButton = fragment.getView().findViewById(R.id.imageButton_marketCategory_Property_Land);
            bitmapExpected = ((BitmapDrawable) imageButton.getDrawable()).getBitmap();
            bitmapActual = ((BitmapDrawable) drawables.get(2)).getBitmap();
            assert bitmapExpected.sameAs(bitmapActual);
            //other
            imageButton = fragment.getView().findViewById(R.id.imageButton_marketCategory_Property_Other);
            bitmapExpected = ((BitmapDrawable) imageButton.getDrawable()).getBitmap();
            bitmapActual = ((BitmapDrawable) drawables.get(3)).getBitmap();
            assert bitmapExpected.sameAs(bitmapActual);
            //part_time
            imageButton = fragment.getView().findViewById(R.id.imageButton_marketCategory_Property_Rent);
            bitmapExpected = ((BitmapDrawable) imageButton.getDrawable()).getBitmap();
            bitmapActual = ((BitmapDrawable) drawables.get(4)).getBitmap();
            assert bitmapExpected.sameAs(bitmapActual);
            //remote
            imageButton = fragment.getView().findViewById(R.id.imageButton_marketCategory_Property_Sale);
            bitmapExpected = ((BitmapDrawable) imageButton.getDrawable()).getBitmap();
            bitmapActual = ((BitmapDrawable) drawables.get(5)).getBitmap();
            assert bitmapExpected.sameAs(bitmapActual);
        });
    }
}
