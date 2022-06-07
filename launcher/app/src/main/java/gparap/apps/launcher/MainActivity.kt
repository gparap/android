/*
 * Copyright (c) 2022 gparap
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
package gparap.apps.launcher

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ResolveInfo
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import android.widget.GridView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.PEEK_HEIGHT_AUTO
import gparap.apps.launcher.adapters.BottomGridItemAdapter
import gparap.apps.launcher.adapters.TopGritItemAdapter
import gparap.apps.launcher.data.AppModel

@SuppressLint("ClickableViewAccessibility")
class MainActivity : AppCompatActivity() {
    private val bottomSheetLaunchers = ArrayList<AppModel>()
    private val homeScreenLaunchers = ArrayList<AppModel>()
    private lateinit var bottomSheetView: FrameLayout
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<FrameLayout>
    private var previousSwipeY = -1F
    private var currentSwipeY = -1F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //get the installed applications on the device
        val intent = Intent(Intent.ACTION_MAIN, null)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)
        val installedApps: List<ResolveInfo> = packageManager.queryIntentActivities(intent, 0)

        //add installed applications to list
        for (info in installedApps) {
            bottomSheetLaunchers.add(
                AppModel(
                    info.activityInfo.loadIcon(packageManager),
                    info.activityInfo.loadLabel(packageManager).toString(),
                    info.activityInfo.packageName
                )
            )
        }

        //setup bottom sheet
        bottomSheetView = findViewById(R.id.frame_layout_apps)
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetView)
        bottomSheetBehavior.isHideable = true
        bottomSheetBehavior.peekHeight = PEEK_HEIGHT_AUTO
        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                //expand when dragging
                if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {}
        })

        //setup bottom sheet grid with adapter
        val gridView = findViewById<GridView>(R.id.grid_view_apps_bottom)
        gridView.adapter = BottomGridItemAdapter(this, bottomSheetLaunchers)

        //perform a swipe-up action on the coordinator layout to open the drawer
        findViewById<CoordinatorLayout>(R.id.layout_coordinator).setOnTouchListener { _, event ->
            //set listener only if the drawer is hidden
            if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED ||
                bottomSheetBehavior.state == BottomSheetBehavior.STATE_HALF_EXPANDED
            ) {
                return@setOnTouchListener false
            }

            //get the starting Y of the event
            if (event.action == MotionEvent.ACTION_DOWN) {
                previousSwipeY = event.y
            }

            //open the drawer if there is a swipe-up action
            if (event.action == MotionEvent.ACTION_UP) {
                currentSwipeY = event.y
                if (currentSwipeY < previousSwipeY && isBottomSheetHidden()) {
                    showBottomSheet()
                    return@setOnTouchListener false
                }
            }

            //continue responding to touch events
            return@setOnTouchListener true
        }
    }

    override fun onBackPressed() {
        //if the bottom sheet is visible, hide it..
        if (!isBottomSheetHidden()) {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        }

        //..else proceed with the default behaviour
        else {
            super.onBackPressed()
        }
    }

    fun addLauncherToHomeScreen(launcher: AppModel) {
        //hide bottom sheet
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN

        //add launcher to list
        if (!homeScreenLaunchers.contains(launcher)) {
            homeScreenLaunchers.add(launcher)
        }

        //setup home screen grid with adapter
        val gridLayout = findViewById<GridView>(R.id.grid_layout_apps_top)
        gridLayout.adapter = TopGritItemAdapter(this, homeScreenLaunchers)

        //!!! important
        gridLayout.refreshDrawableState()
    }

    fun removeLauncherFromHomeScreen(launcher: AppModel) {
        AlertDialog.Builder(this)
            .setTitle(resources.getString(R.string.dialog_remove_launcher_title))
            .setMessage(resources.getString(R.string.dialog_remove_launcher_message))
            .setOnCancelListener { return@setOnCancelListener }
            .setNegativeButton(resources.getString(R.string.dialog_remove_launcher_negative)) { _, _ -> return@setNegativeButton }
            .setPositiveButton(resources.getString(R.string.dialog_remove_launcher_positive)) { _, _ ->
                //remove launcher from list
                if (homeScreenLaunchers.contains(launcher)) {
                    homeScreenLaunchers.remove(launcher)
                }

                //setup home screen grid with adapter
                val gridLayout = findViewById<GridView>(R.id.grid_layout_apps_top)
                gridLayout.adapter = TopGritItemAdapter(this, homeScreenLaunchers)

                //!!! important
                gridLayout.refreshDrawableState()
            }
            .create().apply {
                show()
            }
    }

    fun showBottomSheet() {
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
    }

    fun isBottomSheetHidden(): Boolean {
        if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_HIDDEN) {
            return true
        }
        return false
    }
}