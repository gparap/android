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

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import gparap.apps.launcher.R
import gparap.apps.launcher.data.AppModel

class GridItemAdapter(
    private val context: Context,
    private val apps: ArrayList<AppModel>,  //this is the list that contains all the app launchers
) : BaseAdapter() {

    override fun getCount(): Int {
        return apps.size
    }

    override fun getItem(position: Int): Any {
        return apps[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        //inflate the view that displays the grid item data
        var view = convertView
        if (view == null) {
            val inflater = context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
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

        return view!!
    }
}