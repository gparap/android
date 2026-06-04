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
import gparap.apps.classifieds.ui.market.categories.electronics.ElectronicsFragment;
import gparap.apps.classifieds.utils.Utils;

@RunWith(AndroidJUnit4.class)
public class ElectronicsFragmentInstrumentedTest {
    private final ArrayMap<String, String> categoryNames = new ArrayMap<>();
    private final ArrayMap<String, String> categoryDescriptions = new ArrayMap<>();
    FragmentScenario<ElectronicsFragment> fragmentScenario;

    @Before
    public void setUp() {
        fragmentScenario = FragmentScenario.launchInContainer(ElectronicsFragment.class);

        //init category actual values
        categoryNames.put("Audio", "Audio");
        categoryNames.put("Cameras", "Cameras");
        categoryNames.put("Computers", "Computers");
        categoryNames.put("Gadgets", "Gadgets");
        categoryNames.put("Mobiles", "Mobiles");
        categoryNames.put("TVs", "TVs");
        categoryDescriptions.put("Audio", "Speakers, headphones, and sound systems.");
        categoryDescriptions.put("Cameras", "Digital cameras, DSLRs, and accessories.");
        categoryDescriptions.put("Computers", "Laptops, desktops, and peripherals.");
        categoryDescriptions.put("Gadgets", "Smartwatches, drones, and tech devices.");
        categoryDescriptions.put("Mobiles", "Smartphones, basic phones, and accessories.");
        categoryDescriptions.put("TVs", "Televisions, projectors, and home entertainment.");
    }

    @Test
    public void isVisible_textView_marketCategory_Electronics_Audio() {
        onView(withId(R.id.textView_marketCategory_Electronics_Audio)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Electronics_audioDetails() {
        onView(withId(R.id.textView_marketCategory_Electronics_Audio)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageButton_marketCategory_Electronics_Audio() {
        onView(withId(R.id.imageButton_marketCategory_Electronics_Audio)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_textWithDetails_categoryAudio() {
        onView(withText(R.string.text_category_electronics_audio)).check(matches(withText(categoryNames.get("Audio"))));
        onView(withText(R.string.text_category_electronics_audio_details)).check(matches(withText(categoryDescriptions.get("Audio"))));
    }

    @Test
    public void isVisible_textView_marketCategory_Electronics_Cameras() {
        onView(withId(R.id.textView_marketCategory_Electronics_Cameras)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Electronics_camerasDetails() {
        onView(withId(R.id.textView_marketCategory_Electronics_camerasDetails)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageButton_marketCategory_Electronics_Cameras() {
        onView(withId(R.id.imageButton_marketCategory_Electronics_Cameras)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_textWithDetails_categoryCameras() {
        onView(withText(R.string.text_category_electronics_cameras)).check(matches(withText(categoryNames.get("Cameras"))));
        onView(withText(R.string.text_category_electronics_cameras_details)).check(matches(withText(categoryDescriptions.get("Cameras"))));
    }

    @Test
    public void isVisible_textView_marketCategory_Electronics_Computers() {
        onView(withId(R.id.textView_marketCategory_Electronics_Computers)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Electronics_computersDetails() {
        onView(withId(R.id.textView_marketCategory_Electronics_computersDetails)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageButton_marketCategory_Electronics_Computers() {
        onView(withId(R.id.imageButton_marketCategory_Electronics_Computers)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_textWithDetails_categoryComputers() {
        onView(withText(R.string.text_category_electronics_computers)).check(matches(withText(categoryNames.get("Computers"))));
        onView(withText(R.string.text_category_electronics_computers_details)).check(matches(withText(categoryDescriptions.get("Computers"))));
    }

    @Test
    public void isVisible_textView_marketCategory_Electronics_Gadgets() {
        onView(withId(R.id.textView_marketCategory_Electronics_Gadgets)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Electronics_gadgetsDetails() {
        onView(withId(R.id.textView_marketCategory_Electronics_gadgetsDetails)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageButton_marketCategory_Electronics_Gadgets() {
        onView(withId(R.id.imageButton_marketCategory_Electronics_Gadgets)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_textWithDetails_categoryGadgets() {
        onView(withText(R.string.text_category_electronics_gadgets)).check(matches(withText(categoryNames.get("Gadgets"))));
        onView(withText(R.string.text_category_electronics_gadgets_details)).check(matches(withText(categoryDescriptions.get("Gadgets"))));
    }

    @Test
    public void isVisible_textView_marketCategory_Electronics_Mobiles() {
        onView(withId(R.id.textView_marketCategory_Electronics_Mobiles)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Electronics_mobilesDetails() {
        onView(withId(R.id.textView_marketCategory_Electronics_mobilesDetails)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageButton_marketCategory_Electronics_Mobiles() {
        onView(withId(R.id.imageButton_marketCategory_Electronics_Mobiles)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_textWithDetails_categoryMobiles() {
        onView(withText(R.string.text_category_electronics_mobiles)).check(matches(withText(categoryNames.get("Mobiles"))));
        onView(withText(R.string.text_category_electronics_mobiles_details)).check(matches(withText(categoryDescriptions.get("Mobiles"))));
    }

    @Test
    public void isVisible_textView_marketCategory_Electronics_tvs() {
        onView(withId(R.id.textView_marketCategory_Electronics_tvs)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Electronics_tvsDetails() {
        onView(withId(R.id.textView_marketCategory_Electronics_tvsDetails)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageButton_marketCategory_Electronics_tvs() {
        onView(withId(R.id.imageButton_marketCategory_Electronics_tvs)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_textWithDetails_category_tvs() {
        onView(withText(R.string.text_category_electronics_tvs)).check(matches(withText(categoryNames.get("TVs"))));
        onView(withText(R.string.text_category_electronics_tvs_details)).check(matches(withText(categoryDescriptions.get("TVs"))));
    }

    @Test
    public void areCorrect_imageButtons_Drawables() {   /* !!! Compact test for all images. */
        String[] assets = new String[]{"audio.jpg", "cameras.jpg", "computers.jpg", "gadgets.jpg", "mobiles.jpg", "tvs.jpg"};
        AssetManager assetManager = InstrumentationRegistry.getInstrumentation().getTargetContext().getAssets();
        String path = "market/categories/electronics";
        ArrayList<Drawable> drawables = Utils.getInstance().getDrawablesFromAssets(assetManager, assets, path);

        //TODO: refactor
        fragmentScenario.onFragment(fragment -> {
            //audio
            ImageButton imageButton = Objects.requireNonNull(fragment.getView()).findViewById(R.id.imageButton_marketCategory_Electronics_Audio);
            Bitmap bitmapExpected = ((BitmapDrawable) imageButton.getDrawable()).getBitmap();
            Bitmap bitmapActual = ((BitmapDrawable) drawables.get(0)).getBitmap();
            assert bitmapExpected.sameAs(bitmapActual);
            //cameras
            imageButton = fragment.getView().findViewById(R.id.imageButton_marketCategory_Electronics_Cameras);
            bitmapExpected = ((BitmapDrawable) imageButton.getDrawable()).getBitmap();
            bitmapActual = ((BitmapDrawable) drawables.get(1)).getBitmap();
            assert bitmapExpected.sameAs(bitmapActual);
            //computers
            imageButton = fragment.getView().findViewById(R.id.imageButton_marketCategory_Electronics_Computers);
            bitmapExpected = ((BitmapDrawable) imageButton.getDrawable()).getBitmap();
            bitmapActual = ((BitmapDrawable) drawables.get(2)).getBitmap();
            assert bitmapExpected.sameAs(bitmapActual);
            //gadgets
            imageButton = fragment.getView().findViewById(R.id.imageButton_marketCategory_Electronics_Gadgets);
            bitmapExpected = ((BitmapDrawable) imageButton.getDrawable()).getBitmap();
            bitmapActual = ((BitmapDrawable) drawables.get(3)).getBitmap();
            assert bitmapExpected.sameAs(bitmapActual);
            //mobiles
            imageButton = fragment.getView().findViewById(R.id.imageButton_marketCategory_Electronics_Mobiles);
            bitmapExpected = ((BitmapDrawable) imageButton.getDrawable()).getBitmap();
            bitmapActual = ((BitmapDrawable) drawables.get(4)).getBitmap();
            assert bitmapExpected.sameAs(bitmapActual);
            //tvs
            imageButton = fragment.getView().findViewById(R.id.imageButton_marketCategory_Electronics_tvs);
            bitmapExpected = ((BitmapDrawable) imageButton.getDrawable()).getBitmap();
            bitmapActual = ((BitmapDrawable) drawables.get(5)).getBitmap();
            assert bitmapExpected.sameAs(bitmapActual);
        });
    }
}
