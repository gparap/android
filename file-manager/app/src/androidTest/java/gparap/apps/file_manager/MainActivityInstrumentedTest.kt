/*
 * Copyright 2024 gparap
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
package gparap.apps.file_manager

import android.os.Build
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SdkSuppress
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {
    private lateinit var activityScenario: ActivityScenario<MainActivity>

    @Before
    fun setUp() {
        activityScenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("gparap.apps.file_manager", appContext.packageName)
    }

    @Test
    fun isVisible_recyclerView_mediaFiles() {
        onView(withId(R.id.recyclerView_deviceFiles)).check(matches(isDisplayed()))
    }

    @Test
    @TestInfo("!!! Make sure there are media files on device !!!")
    @SdkSuppress(minSdkVersion = Build.VERSION_CODES.TIRAMISU)
    fun onMediaFilesScanClick_recyclerViewIsNotEmpty() {
        val itemsBeforeScan = 0
        var itemsAfterScan = 0

        //grant media file permissions
        grantPermission(android.Manifest.permission.READ_MEDIA_IMAGES)
        grantPermission(android.Manifest.permission.READ_MEDIA_VIDEO)
        grantPermission(android.Manifest.permission.READ_MEDIA_AUDIO)

        //get recycler view count before scan
        activityScenario.onActivity {
            val recyclerView = it.findViewById<RecyclerView>(R.id.recyclerView_deviceFiles)
            assertEquals(recyclerView.adapter?.itemCount, itemsBeforeScan)
        }

        //scan for media files
        Espresso.openContextualActionModeOverflowMenu()
        onView(withText(R.string.text_scan_media_files)).perform(click())

        //get recycler view count after scan
        activityScenario.onActivity {
            val recyclerView = it.findViewById<RecyclerView>(R.id.recyclerView_deviceFiles)
            itemsAfterScan = recyclerView.adapter?.itemCount!!
        }

        //test here
        assertNotEquals(itemsBeforeScan, itemsAfterScan)
    }

    @Test
    @Ignore("!!! Make sure this special permission is granted manually before testing !!!")
    @TestInfo("!!! Make sure there are media files on device !!!")
    @SdkSuppress(minSdkVersion = Build.VERSION_CODES.R)
    fun onAllFilesScanClick_recyclerViewIsNotEmpty() {
        val itemsBeforeScan = 0
        var itemsAfterScan = 0

        //get recycler view count before scan
        activityScenario.onActivity {
            val recyclerView = it.findViewById<RecyclerView>(R.id.recyclerView_deviceFiles)
            assertEquals(recyclerView.adapter?.itemCount, itemsBeforeScan)
        }

        //scan for files
        Espresso.openContextualActionModeOverflowMenu()
        onView(withText(R.string.text_scan_media_files)).perform(click())

        //get recycler view count after scan
        activityScenario.onActivity {
            val recyclerView = it.findViewById<RecyclerView>(R.id.recyclerView_deviceFiles)
            itemsAfterScan = recyclerView.adapter?.itemCount!!
        }

        //test here
        assertNotEquals(itemsBeforeScan, itemsAfterScan)
    }

    //execute a shell command to grant the permission
    private fun grantPermission(permission: String?) {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val command = java.lang.String.format("pm grant %s %s", context.packageName, permission)
        InstrumentationRegistry.getInstrumentation().uiAutomation.executeShellCommand(command)
    }

    //custom annotation (to avoid comments)
    @Retention(AnnotationRetention.RUNTIME)
    @Target(AnnotationTarget.FUNCTION)
    annotation class TestInfo(val value: String)
}