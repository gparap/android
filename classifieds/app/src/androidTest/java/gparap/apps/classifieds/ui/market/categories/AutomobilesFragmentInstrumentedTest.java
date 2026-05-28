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
import gparap.apps.classifieds.ui.market.categories.automobiles.AutomobilesFragment;
import gparap.apps.classifieds.utils.Utils;

@RunWith(AndroidJUnit4.class)
public class AutomobilesFragmentInstrumentedTest {
    private final ArrayMap<String, String> categoryNames = new ArrayMap<>();
    private final ArrayMap<String, String> categoryDescriptions = new ArrayMap<>();
    FragmentScenario<AutomobilesFragment> fragmentScenario;

    @Before
    public void setUp() {
        fragmentScenario = FragmentScenario.launchInContainer(AutomobilesFragment.class);

        //init category actual values
        categoryNames.put("Accessories", "Accessories");
        categoryNames.put("Bicycles", "Bicycles");
        categoryNames.put("Bikes", "Bikes");
        categoryNames.put("Cars", "Cars");
        categoryNames.put("Parts", "Parts");
        categoryNames.put("Utility", "Utility");
        categoryDescriptions.put("Accessories", "Accessories for all types of vehicles.");
        categoryDescriptions.put("Bicycles", "Pedal-powered and electric bicycles.");
        categoryDescriptions.put("Bikes", "Motorbikes and scooters.");
        categoryDescriptions.put("Cars", "New and used cars for sale.");
        categoryDescriptions.put("Parts", "Spare parts and replacement components.");
        categoryDescriptions.put("Utility", "Passenger or cargo vans and commercial vehicles.");
    }

    @Test
    public void isVisible_textView_marketCategory_Automobiles_Accessories() {
        onView(withId(R.id.textView_marketCategory_Automobiles_Accessories)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Automobiles_accessoriesDetails() {
        onView(withId(R.id.textView_marketCategory_Automobiles_accessoriesDetails)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageButton_marketCategory_Automobiles_Accessories() {
        onView(withId(R.id.imageButton_marketCategory_Automobiles_Accessories)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_textWithDetails_categoryAccessories() {
        onView(withText(R.string.text_category_automobiles_accessories)).check(matches(withText(categoryNames.get("Accessories"))));
        onView(withText(R.string.text_category_automobiles_accessories_details)).check(matches(withText(categoryDescriptions.get("Accessories"))));
    }

    @Test
    public void isVisible_textView_marketCategory_Automobiles_Bicycles() {
        onView(withId(R.id.textView_marketCategory_Automobiles_Bicycles)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Automobiles_bicyclesDetails() {
        onView(withId(R.id.textView_marketCategory_Automobiles_bicyclesDetails)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageButton_marketCategory_Automobiles_Bicycles() {
        onView(withId(R.id.imageButton_marketCategory_Automobiles_Bicycles)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_textWithDetails_categoryBicycles() {
        onView(withText(R.string.text_category_automobiles_bicycles)).check(matches(withText(categoryNames.get("Bicycles"))));
        onView(withText(R.string.text_category_automobiles_bicycles_details)).check(matches(withText(categoryDescriptions.get("Bicycles"))));
    }

    @Test
    public void isVisible_textView_marketCategory_Automobiles_Bikes() {
        onView(withId(R.id.textView_marketCategory_Automobiles_Bikes)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Automobiles_bikesDetails() {
        onView(withId(R.id.textView_marketCategory_Automobiles_bikesDetails)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageButton_marketCategory_Automobiles_Bikes() {
        onView(withId(R.id.imageButton_marketCategory_Automobiles_Bikes)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_textWithDetails_categoryBikes() {
        onView(withText(R.string.text_category_automobiles_bikes)).check(matches(withText(categoryNames.get("Bikes"))));
        onView(withText(R.string.text_category_automobiles_bikes_details)).check(matches(withText(categoryDescriptions.get("Bikes"))));
    }

    @Test
    public void isVisible_textView_marketCategory_Automobiles_Cars() {
        onView(withId(R.id.textView_marketCategory_Automobiles_Cars)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Automobiles_carsDetails() {
        onView(withId(R.id.textView_marketCategory_Automobiles_carsDetails)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageButton_marketCategory_Automobiles_Cars() {
        onView(withId(R.id.imageButton_marketCategory_Automobiles_Cars)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_textWithDetails_categoryCars() {
        onView(withText(R.string.text_category_automobiles_cars)).check(matches(withText(categoryNames.get("Cars"))));
        onView(withText(R.string.text_category_automobiles_cars_details)).check(matches(withText(categoryDescriptions.get("Cars"))));
    }

    @Test
    public void isVisible_textView_marketCategory_Automobiles_Parts() {
        onView(withId(R.id.textView_marketCategory_Automobiles_Parts)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Automobiles_partsDetails() {
        onView(withId(R.id.textView_marketCategory_Automobiles_partsDetails)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageButton_marketCategory_Automobiles_Parts() {
        onView(withId(R.id.imageButton_marketCategory_Automobiles_Parts)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_textWithDetails_categoryParts() {
        onView(withText(R.string.text_category_automobiles_parts)).check(matches(withText(categoryNames.get("Parts"))));
        onView(withText(R.string.text_category_automobiles_parts_details)).check(matches(withText(categoryDescriptions.get("Parts"))));
    }

    @Test
    public void isVisible_textView_marketCategory_Automobiles_Utility() {
        onView(withId(R.id.textView_marketCategory_Automobiles_Utility)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Automobiles_utilityDetails() {
        onView(withId(R.id.textView_marketCategory_Automobiles_utilityDetails)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageButton_marketCategory_Automobiles_Utility() {
        onView(withId(R.id.imageButton_marketCategory_Automobiles_Utility)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_textWithDetails_categoryUtility() {
        onView(withText(R.string.text_category_automobiles_utility)).check(matches(withText(categoryNames.get("Utility"))));
        onView(withText(R.string.text_category_automobiles_utility_details)).check(matches(withText(categoryDescriptions.get("Utility"))));
    }

    @Test
    public void areCorrect_imageButtons_Drawables() {   /* !!! Compact test for all images. */
        String[] assets = new String[]{"accessories.jpg", "bicycles.jpg", "bikes.jpg", "cars.jpg", "parts.jpg", "utility.jpg"};
        AssetManager assetManager = InstrumentationRegistry.getInstrumentation().getTargetContext().getAssets();
        String path = "market/categories/automobiles";
        ArrayList<Drawable> drawables = Utils.getInstance().getDrawablesFromAssets(assetManager, assets, path);

        //TODO: refactor
        fragmentScenario.onFragment(fragment -> {
            //Accessories
            ImageButton imageButton = Objects.requireNonNull(fragment.getView()).findViewById(R.id.imageButton_marketCategory_Automobiles_Accessories);
            Bitmap bitmapExpected = ((BitmapDrawable) imageButton.getDrawable()).getBitmap();
            Bitmap bitmapActual = ((BitmapDrawable) drawables.get(0)).getBitmap();
            assert bitmapExpected.sameAs(bitmapActual);
            //Bicycles
            imageButton = fragment.getView().findViewById(R.id.imageButton_marketCategory_Automobiles_Bicycles);
            bitmapExpected = ((BitmapDrawable) imageButton.getDrawable()).getBitmap();
            bitmapActual = ((BitmapDrawable) drawables.get(1)).getBitmap();
            assert bitmapExpected.sameAs(bitmapActual);
            //Bikes
            imageButton = fragment.getView().findViewById(R.id.imageButton_marketCategory_Automobiles_Bikes);
            bitmapExpected = ((BitmapDrawable) imageButton.getDrawable()).getBitmap();
            bitmapActual = ((BitmapDrawable) drawables.get(2)).getBitmap();
            assert bitmapExpected.sameAs(bitmapActual);
            //Cars
            imageButton = fragment.getView().findViewById(R.id.imageButton_marketCategory_Automobiles_Cars);
            bitmapExpected = ((BitmapDrawable) imageButton.getDrawable()).getBitmap();
            bitmapActual = ((BitmapDrawable) drawables.get(3)).getBitmap();
            assert bitmapExpected.sameAs(bitmapActual);
            //Parts
            imageButton = fragment.getView().findViewById(R.id.imageButton_marketCategory_Automobiles_Parts);
            bitmapExpected = ((BitmapDrawable) imageButton.getDrawable()).getBitmap();
            bitmapActual = ((BitmapDrawable) drawables.get(4)).getBitmap();
            assert bitmapExpected.sameAs(bitmapActual);
            //Utility
            imageButton = fragment.getView().findViewById(R.id.imageButton_marketCategory_Automobiles_Utility);
            bitmapExpected = ((BitmapDrawable) imageButton.getDrawable()).getBitmap();
            bitmapActual = ((BitmapDrawable) drawables.get(5)).getBitmap();
            assert bitmapExpected.sameAs(bitmapActual);
        });
    }
}
