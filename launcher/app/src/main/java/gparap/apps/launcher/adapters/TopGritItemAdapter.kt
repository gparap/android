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
package gparap.apps.launcher.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import gparap.apps.launcher.MainActivity
import gparap.apps.launcher.R
import gparap.apps.launcher.data.AppModel

class TopGritItemAdapter(
    private val context: Context,
    private val apps: ArrayList<AppModel>,  //this is the list that contains all the app launchers
) : BaseAdapter() {
    private var previousSwipeY = -1F
    private var currentSwipeY = -1F

    override fun getCount(): Int {
        return apps.size
    }

    override fun getItem(position: Int): Any {
        return apps[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        //inflate the view that displays the grid item data
        var view = convertView
        if (view == null) {
            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.grid_item, parent, false)
        }

        //find the widgets of the view that describe an app launcher model
        val launcherIcon = view?.findViewById<ImageView>(R.id.image_view_app_icon)
        val launcherTitle = view?.findViewById<TextView>(R.id.text_view_app_title)

        //bind the widgets with the dataset
        launcherIcon?.setImageDrawable(apps[position].drawable)
        launcherTitle?.text = apps[position].title

        //enable launcher icons to start activities
        launcherIcon?.setOnClickListener {
            context.startActivity(
                context.packageManager.getLaunchIntentForPackage(apps[position].id)
            )
        }

        //perform a swipe-up action on the layout to open the drawer
        view?.setOnTouchListener { _, event ->
            //set listener only if the drawer is hidden
            if (!(context as MainActivity).isBottomSheetHidden()) {
                return@setOnTouchListener false
            }

            //get the starting Y of the event
            if (event.action == MotionEvent.ACTION_DOWN) {
                previousSwipeY = event.y
                return@setOnTouchListener true
            }

            //open the drawer if there is a swipe-up action
            if (event.action == MotionEvent.ACTION_MOVE) {
                currentSwipeY = event.y
                if (currentSwipeY < previousSwipeY && context.isBottomSheetHidden()) {
                    context.showBottomSheet()
                    return@setOnTouchListener false

                }
            }

            //continue responding to touch events
            true
        }

        return view!!
    }
}