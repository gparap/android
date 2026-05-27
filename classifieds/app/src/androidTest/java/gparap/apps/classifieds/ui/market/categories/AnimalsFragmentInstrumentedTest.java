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
import gparap.apps.classifieds.ui.market.categories.animals.AnimalsFragment;
import gparap.apps.classifieds.utils.Utils;

@RunWith(AndroidJUnit4.class)
public class AnimalsFragmentInstrumentedTest {
    private final ArrayMap<String, String> categoryNames = new ArrayMap<>();
    private final ArrayMap<String, String> categoryDescriptions = new ArrayMap<>();
    FragmentScenario<AnimalsFragment> fragmentScenario;

    @Before
    public void setUp() {
        fragmentScenario = FragmentScenario.launchInContainer(AnimalsFragment.class);

        //init category actual values
        categoryNames.put("Dogs", "Dogs");
        categoryNames.put("Cats", "Cats");
        categoryNames.put("Birds", "Birds");
        categoryNames.put("Fish", "Fish");
        categoryNames.put("Reptiles", "Reptiles");
        categoryNames.put("Food", "Food");
        categoryDescriptions.put("Dogs", "Puppies and adult dogs for sale or adoption.");
        categoryDescriptions.put("Cats", "Kittens and cats looking for homes.");
        categoryDescriptions.put("Birds", "Pet birds like parrots, canaries, and finches.");
        categoryDescriptions.put("Fish", "Freshwater and saltwater fish, aquariums.");
        categoryDescriptions.put("Reptiles", "Snakes, lizards, turtles, and small exotic pets.");
        categoryDescriptions.put("Food", "Pet food and treats for all animals.");
    }

    @Test
    public void isVisible_textView_marketCategory_Animals_Dogs() {
        onView(withId(R.id.textView_marketCategory_Animals_Dogs)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Animals_dogsDetails() {
        onView(withId(R.id.textView_marketCategory_Animals_dogsDetails)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageButton_marketCategory_Animals_Dogs() {
        onView(withId(R.id.imageButton_marketCategory_Animals_Dogs)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_textWithDetails_categoryDogs() {
        onView(withText(R.string.text_category_animals_dogs)).check(matches(withText(categoryNames.get("Dogs"))));
        onView(withText(R.string.text_category_animals_dogs_details)).check(matches(withText(categoryDescriptions.get("Dogs"))));
    }

    @Test
    public void isVisible_textView_marketCategory_Animals_Cats() {
        onView(withId(R.id.textView_marketCategory_Animals_Cats)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Animals_catsDetails() {
        onView(withId(R.id.textView_marketCategory_Animals_catsDetails)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageButton_marketCategory_Animals_Cats() {
        onView(withId(R.id.imageButton_marketCategory_Animals_Cats)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_textWithDetails_categoryCats() {
        onView(withText(R.string.text_category_animals_cats)).check(matches(withText(categoryNames.get("Cats"))));
        onView(withText(R.string.text_category_animals_cats_details)).check(matches(withText(categoryDescriptions.get("Cats"))));
    }

    @Test
    public void isVisible_textView_marketCategory_Animals_Birds() {
        onView(withId(R.id.textView_marketCategory_Animals_Birds)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Animals_birdsDetails() {
        onView(withId(R.id.textView_marketCategory_Animals_birdsDetails)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageButton_marketCategory_Animals_Birds() {
        onView(withId(R.id.imageButton_marketCategory_Animals_Birds)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_textWithDetails_categoryBirds() {
        onView(withText(R.string.text_category_animals_birds)).check(matches(withText(categoryNames.get("Birds"))));
        onView(withText(R.string.text_category_animals_birds_details)).check(matches(withText(categoryDescriptions.get("Birds"))));
    }

    @Test
    public void isVisible_textView_marketCategory_Animals_Fish() {
        onView(withId(R.id.textView_marketCategory_Animals_Fish)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Animals_fishDetails() {
        onView(withId(R.id.textView_marketCategory_Animals_fishDetails)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageButton_marketCategory_Animals_Fish() {
        onView(withId(R.id.imageButton_marketCategory_Animals_Fish)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_textWithDetails_categoryFish() {
        onView(withText(R.string.text_category_animals_fish)).check(matches(withText(categoryNames.get("Fish"))));
        onView(withText(R.string.text_category_animals_fish_details)).check(matches(withText(categoryDescriptions.get("Fish"))));
    }

    @Test
    public void isVisible_textView_marketCategory_Animals_Reptiles() {
        onView(withId(R.id.textView_marketCategory_Animals_Reptiles)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Animals_reptilesDetails() {
        onView(withId(R.id.textView_marketCategory_Animals_reptilesDetails)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageButton_marketCategory_Animals_Reptiles() {
        onView(withId(R.id.imageButton_marketCategory_Animals_Reptiles)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_textWithDetails_categoryReptiles() {
        onView(withText(R.string.text_category_animals_reptiles)).check(matches(withText(categoryNames.get("Reptiles"))));
        onView(withText(R.string.text_category_animals_reptiles_details)).check(matches(withText(categoryDescriptions.get("Reptiles"))));
    }

    @Test
    public void isVisible_textView_marketCategory_Animals_Food() {
        onView(withId(R.id.textView_marketCategory_Animals_Food)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Animals_foodDetails() {
        onView(withId(R.id.textView_marketCategory_Animals_foodDetails)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageButton_marketCategory_Animals_Food() {
        onView(withId(R.id.imageButton_marketCategory_Animals_Food)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_textWithDetails_categoryFood() {
        onView(withText(R.string.text_category_animals_food)).check(matches(withText(categoryNames.get("Food"))));
        onView(withText(R.string.text_category_animals_food_details)).check(matches(withText(categoryDescriptions.get("Food"))));
    }


    @Test
    public void areCorrect_imageButtons_Drawables() {   /* !!! Compact test for all images. */
        String[] assets = new String[]{"birds.jpg", "cats.jpg", "dogs.jpg", "fish.jpg", "food.jpg", "reptiles.jpg"};
        AssetManager assetManager = InstrumentationRegistry.getInstrumentation().getTargetContext().getAssets();
        String path = "market/categories/animals";
        ArrayList<Drawable> drawables = Utils.getInstance().getDrawablesFromAssets(assetManager, assets, path);

        //TODO: refactor
        fragmentScenario.onFragment(fragment -> {
            //birds.jpg
            ImageButton imageButton = Objects.requireNonNull(fragment.getView()).findViewById(R.id.imageButton_marketCategory_Animals_Birds);
            Bitmap bitmapExpected = ((BitmapDrawable) imageButton.getDrawable()).getBitmap();
            Bitmap bitmapActual = ((BitmapDrawable) drawables.get(0)).getBitmap();
            assert bitmapExpected.sameAs(bitmapActual);
            //cats.jpg
            imageButton = fragment.getView().findViewById(R.id.imageButton_marketCategory_Animals_Cats);
            bitmapExpected = ((BitmapDrawable) imageButton.getDrawable()).getBitmap();
            bitmapActual = ((BitmapDrawable) drawables.get(1)).getBitmap();
            assert bitmapExpected.sameAs(bitmapActual);
            //dogs.jpg
            imageButton = fragment.getView().findViewById(R.id.imageButton_marketCategory_Animals_Dogs);
            bitmapExpected = ((BitmapDrawable) imageButton.getDrawable()).getBitmap();
            bitmapActual = ((BitmapDrawable) drawables.get(2)).getBitmap();
            assert bitmapExpected.sameAs(bitmapActual);
            //fish.jpg
            imageButton = fragment.getView().findViewById(R.id.imageButton_marketCategory_Animals_Fish);
            bitmapExpected = ((BitmapDrawable) imageButton.getDrawable()).getBitmap();
            bitmapActual = ((BitmapDrawable) drawables.get(3)).getBitmap();
            assert bitmapExpected.sameAs(bitmapActual);
            //food.jpg
            imageButton = fragment.getView().findViewById(R.id.imageButton_marketCategory_Animals_Food);
            bitmapExpected = ((BitmapDrawable) imageButton.getDrawable()).getBitmap();
            bitmapActual = ((BitmapDrawable) drawables.get(4)).getBitmap();
            assert bitmapExpected.sameAs(bitmapActual);
            //reptiles.jpg
            imageButton = fragment.getView().findViewById(R.id.imageButton_marketCategory_Animals_Reptiles);
            bitmapExpected = ((BitmapDrawable) imageButton.getDrawable()).getBitmap();
            bitmapActual = ((BitmapDrawable) drawables.get(5)).getBitmap();
            assert bitmapExpected.sameAs(bitmapActual);
        });
    }
}
