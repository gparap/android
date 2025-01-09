/*
 * Copyright 2025 gparap
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
package gparap.apps.converter_unit;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class MainActivityInstrumentedTest {

    @Before
    public void setUp() throws Exception {
        ActivityScenario.launch(MainActivity.class);
    }

    @Test
    public void isCorrect_onOptionsItemSelected() { //TODO: break into smaller tests
        //open category #1
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText("Menu 1")).perform(click());
        onView(withText("Sub Menu 1-1")).perform(click());
        onView(withText("Sub Menu 1-1")).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText("Menu 1")).perform(click());
        onView(withText("Sub Menu 1-2")).perform(click());
        onView(withText("Sub Menu 1-2")).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText("Menu 1")).perform(click());
        onView(withText("Sub Menu 1-3")).perform(click());
        onView(withText("Sub Menu 1-3")).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText("Menu 1")).perform(click());
        onView(withText("Sub Menu 1-4")).perform(click());
        onView(withText("Sub Menu 1-4")).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText("Menu 1")).perform(click());
        onView(withText("Sub Menu 1-5")).perform(click());
        onView(withText("Sub Menu 1-5")).check(matches(isDisplayed()));

        //open category #2
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText("Menu 2")).perform(click());
        onView(withText("Sub Menu 2-1")).perform(click());
        onView(withText("Sub Menu 2-1")).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText("Menu 2")).perform(click());
        onView(withText("Sub Menu 2-2")).perform(click());
        onView(withText("Sub Menu 2-2")).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText("Menu 2")).perform(click());
        onView(withText("Sub Menu 2-3")).perform(click());
        onView(withText("Sub Menu 2-3")).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText("Menu 2")).perform(click());
        onView(withText("Sub Menu 2-4")).perform(click());
        onView(withText("Sub Menu 2-4")).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText("Menu 2")).perform(click());
        onView(withText("Sub Menu 2-5")).perform(click());
        onView(withText("Sub Menu 2-5")).check(matches(isDisplayed()));

        //open category #3
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText("Menu 3")).perform(click());
        onView(withText("Sub Menu 3-1")).perform(click());
        onView(withText("Sub Menu 3-1")).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText("Menu 3")).perform(click());
        onView(withText("Sub Menu 3-2")).perform(click());
        onView(withText("Sub Menu 3-2")).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText("Menu 3")).perform(click());
        onView(withText("Sub Menu 3-3")).perform(click());
        onView(withText("Sub Menu 3-3")).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText("Menu 3")).perform(click());
        onView(withText("Sub Menu 3-4")).perform(click());
        onView(withText("Sub Menu 3-4")).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText("Menu 3")).perform(click());
        onView(withText("Sub Menu 3-5")).perform(click());
        onView(withText("Sub Menu 3-5")).check(matches(isDisplayed()));

        //open category #4
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText("Menu 4")).perform(click());
        onView(withText("Sub Menu 4-1")).perform(click());
        onView(withText("Sub Menu 4-1")).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText("Menu 4")).perform(click());
        onView(withText("Sub Menu 4-2")).perform(click());
        onView(withText("Sub Menu 4-2")).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText("Menu 4")).perform(click());
        onView(withText("Sub Menu 4-3")).perform(click());
        onView(withText("Sub Menu 4-3")).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText("Menu 4")).perform(click());
        onView(withText("Sub Menu 4-4")).perform(click());
        onView(withText("Sub Menu 4-4")).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText("Menu 4")).perform(click());
        onView(withText("Sub Menu 4-5")).perform(click());
        onView(withText("Sub Menu 4-5")).check(matches(isDisplayed()));

        //open category #5
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText("Menu 5")).perform(click());
        onView(withText("Sub Menu 5-1")).perform(click());
        onView(withText("Sub Menu 5-1")).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText("Menu 5")).perform(click());
        onView(withText("Sub Menu 5-2")).perform(click());
        onView(withText("Sub Menu 5-2")).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText("Menu 5")).perform(click());
        onView(withText("Sub Menu 5-3")).perform(click());
        onView(withText("Sub Menu 5-3")).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText("Menu 5")).perform(click());
        onView(withText("Sub Menu 5-4")).perform(click());
        onView(withText("Sub Menu 5-4")).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText("Menu 5")).perform(click());
        onView(withText("Sub Menu 5-5")).perform(click());
        onView(withText("Sub Menu 5-5")).check(matches(isDisplayed()));

        //open category #6
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText("Menu 6")).perform(click());
        onView(withText("Sub Menu 6-1")).perform(click());
        onView(withText("Sub Menu 6-1")).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText("Menu 6")).perform(click());
        onView(withText("Sub Menu 6-2")).perform(click());
        onView(withText("Sub Menu 6-2")).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText("Menu 6")).perform(click());
        onView(withText("Sub Menu 6-3")).perform(click());
        onView(withText("Sub Menu 6-3")).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText("Menu 6")).perform(click());
        onView(withText("Sub Menu 6-4")).perform(click());
        onView(withText("Sub Menu 6-4")).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText("Menu 6")).perform(click());
        onView(withText("Sub Menu 6-5")).perform(click());
        onView(withText("Sub Menu 6-5")).check(matches(isDisplayed()));

        //open category #7
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText("Menu 7")).perform(click());
        onView(withText("Sub Menu 7-1")).perform(click());
        onView(withText("Sub Menu 7-1")).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText("Menu 7")).perform(click());
        onView(withText("Sub Menu 7-2")).perform(click());
        onView(withText("Sub Menu 7-2")).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText("Menu 7")).perform(click());
        onView(withText("Sub Menu 7-3")).perform(click());
        onView(withText("Sub Menu 7-3")).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText("Menu 7")).perform(click());
        onView(withText("Sub Menu 7-4")).perform(click());
        onView(withText("Sub Menu 7-4")).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText("Menu 7")).perform(click());
        onView(withText("Sub Menu 7-5")).perform(click());
        onView(withText("Sub Menu 7-5")).check(matches(isDisplayed()));

        //open category #8
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText("Menu 8")).perform(click());
        onView(withText("Sub Menu 8-1")).perform(click());
        onView(withText("Sub Menu 8-1")).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText("Menu 8")).perform(click());
        onView(withText("Sub Menu 8-2")).perform(click());
        onView(withText("Sub Menu 8-2")).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText("Menu 8")).perform(click());
        onView(withText("Sub Menu 8-3")).perform(click());
        onView(withText("Sub Menu 8-3")).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText("Menu 8")).perform(click());
        onView(withText("Sub Menu 8-4")).perform(click());
        onView(withText("Sub Menu 8-4")).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText("Menu 8")).perform(click());
        onView(withText("Sub Menu 8-5")).perform(click());
        onView(withText("Sub Menu 8-5")).check(matches(isDisplayed()));

        //open category #9
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText("Menu 9")).perform(click());
        onView(withText("Sub Menu 9-1")).perform(click());
        onView(withText("Sub Menu 9-1")).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText("Menu 9")).perform(click());
        onView(withText("Sub Menu 9-2")).perform(click());
        onView(withText("Sub Menu 9-2")).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText("Menu 9")).perform(click());
        onView(withText("Sub Menu 9-3")).perform(click());
        onView(withText("Sub Menu 9-3")).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText("Menu 9")).perform(click());
        onView(withText("Sub Menu 9-4")).perform(click());
        onView(withText("Sub Menu 9-4")).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText("Menu 9")).perform(click());
        onView(withText("Sub Menu 9-5")).perform(click());
        onView(withText("Sub Menu 9-5")).check(matches(isDisplayed()));

        //open category #10
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText("Menu 10")).perform(click());
        onView(withText("Sub Menu 10-1")).perform(click());
        onView(withText("Sub Menu 10-1")).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText("Menu 10")).perform(click());
        onView(withText("Sub Menu 10-2")).perform(click());
        onView(withText("Sub Menu 10-2")).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText("Menu 10")).perform(click());
        onView(withText("Sub Menu 10-3")).perform(click());
        onView(withText("Sub Menu 10-3")).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText("Menu 10")).perform(click());
        onView(withText("Sub Menu 10-4")).perform(click());
        onView(withText("Sub Menu 10-4")).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText("Menu 10")).perform(click());
        onView(withText("Sub Menu 10-5")).perform(click());
        onView(withText("Sub Menu 10-5")).check(matches(isDisplayed()));
    }
}