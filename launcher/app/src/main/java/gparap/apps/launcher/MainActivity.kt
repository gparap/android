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

import android.os.Bundle
import android.widget.FrameLayout
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.PEEK_HEIGHT_AUTO
import gparap.apps.launcher.adapters.GridItemAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //setup bottom sheet
        val bottomSheetView = findViewById<FrameLayout>(R.id.frame_layout_apps)
        val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetView)
        bottomSheetBehavior.isHideable = true
        bottomSheetBehavior.peekHeight = PEEK_HEIGHT_AUTO

        //setup grid with adapter
        val gridView = findViewById<GridView>(R.id.grid_view_apps)
        gridView.adapter = GridItemAdapter(this, ArrayList())
    }
}