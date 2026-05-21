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
package gparap.apps.classifieds.ui.market;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Objects;

import gparap.apps.classifieds.R;
import gparap.apps.classifieds.utils.Utils;

@RunWith(AndroidJUnit4.class)
public class MarketFragmentInstrumentedTest {
    FragmentScenario<MarketFragment> fragmentScenario;

    @Before
    public void setUp() {
        fragmentScenario = FragmentScenario.launchInContainer(MarketFragment.class);
   }

    @Test
    public void isVisible_textView_marketCategory_Animals() {
        onView(withId(R.id.textView_marketCategory_Animals)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageView_marketCategory_Animals() {
        onView(withId(R.id.imageView_marketCategory_Animals)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Automobiles() {
        onView(withId(R.id.textView_marketCategory_Automobiles)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageView_marketCategory_Automobiles() {
        onView(withId(R.id.imageView_marketCategory_Automobiles)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Clothing() {
        onView(withId(R.id.textView_marketCategory_Clothing)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageView_marketCategory_Clothing() {
        onView(withId(R.id.imageView_marketCategory_Clothing)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Electronics() {
        onView(withId(R.id.textView_marketCategory_Electronics)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageView_marketCategory_Electronics() {
        onView(withId(R.id.imageView_marketCategory_Electronics)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Employment() {
        onView(withId(R.id.textView_marketCategory_Employment)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageView_marketCategory_Employment() {
        onView(withId(R.id.imageView_marketCategory_Employment)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Home() {
        onView(withId(R.id.textView_marketCategory_Home)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageView_marketCategory_Home() {
        onView(withId(R.id.imageView_marketCategory_Home)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Property() {
        onView(withId(R.id.textView_marketCategory_Property)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageView_marketCategory_Property() {
        onView(withId(R.id.imageView_marketCategory_Property)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Services() {
        onView(withId(R.id.textView_marketCategory_Services)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageView_marketCategory_Services() {
        onView(withId(R.id.imageView_marketCategory_Services)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Sports() {
        onView(withId(R.id.textView_marketCategory_Sports)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageView_marketCategory_Sports() {
        onView(withId(R.id.imageView_marketCategory_Sports)).check(matches(isDisplayed()));
    }

    @Test
    public void areCorrect_imageViews_Drawables() {   /* !!! Compact test for all images. */
        String[] assets = new String[]{"animals.png", "automobiles.png", "clothing.png", "electronics.png",
                "employment.png", "home.jpg", "property.jpg", "services.png", "sports.png"};
        AssetManager assetManager = InstrumentationRegistry.getInstrumentation().getTargetContext().getAssets();
        String path = "market/categories";
        ArrayList<Drawable> drawables = Utils.getInstance().getDrawablesFromAssets(assetManager, assets, path);

        //TODO: refactor
        fragmentScenario.onFragment(fragment -> {
            //animals.png
            ImageView imageView = Objects.requireNonNull(fragment.getView()).findViewById(R.id.imageView_marketCategory_Animals);
            Bitmap bitmapExpected = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
            Bitmap bitmapActual = ((BitmapDrawable) drawables.get(0)).getBitmap();
            assert bitmapExpected.sameAs(bitmapActual);
            //automobiles.png
            imageView = fragment.getView().findViewById(R.id.imageView_marketCategory_Automobiles);
            bitmapExpected = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
            bitmapActual = ((BitmapDrawable) drawables.get(1)).getBitmap();
            assert bitmapExpected.sameAs(bitmapActual);
            //clothing.png
            imageView = fragment.getView().findViewById(R.id.imageView_marketCategory_Clothing);
            bitmapExpected = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
            bitmapActual = ((BitmapDrawable) drawables.get(2)).getBitmap();
            assert bitmapExpected.sameAs(bitmapActual);
            //electronics.png
            imageView = fragment.getView().findViewById(R.id.imageView_marketCategory_Electronics);
            bitmapExpected = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
            bitmapActual = ((BitmapDrawable) drawables.get(3)).getBitmap();
            assert bitmapExpected.sameAs(bitmapActual);
            //employment.png
            imageView = fragment.getView().findViewById(R.id.imageView_marketCategory_Employment);
            bitmapExpected = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
            bitmapActual = ((BitmapDrawable) drawables.get(4)).getBitmap();
            assert bitmapExpected.sameAs(bitmapActual);
            //home.png
            imageView = fragment.getView().findViewById(R.id.imageView_marketCategory_Home);
            bitmapExpected = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
            bitmapActual = ((BitmapDrawable) drawables.get(5)).getBitmap();
            assert bitmapExpected.sameAs(bitmapActual);
            //property.jpg
            imageView = fragment.getView().findViewById(R.id.imageView_marketCategory_Property);
            bitmapExpected = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
            bitmapActual = ((BitmapDrawable) drawables.get(6)).getBitmap();
            assert bitmapExpected.sameAs(bitmapActual);
            //services.png
            imageView = fragment.getView().findViewById(R.id.imageView_marketCategory_Services);
            bitmapExpected = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
            bitmapActual = ((BitmapDrawable) drawables.get(7)).getBitmap();
            assert bitmapExpected.sameAs(bitmapActual);
            //sports.png
            imageView = fragment.getView().findViewById(R.id.imageView_marketCategory_Sports);
            bitmapExpected = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
            bitmapActual = ((BitmapDrawable) drawables.get(8)).getBitmap();
            assert bitmapExpected.sameAs(bitmapActual);
        });
    }
}
