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
package gparap.apps.wallpaper.ui

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import gparap.apps.wallpaper.R
import gparap.apps.wallpaper.data.WallpaperModel
import gparap.apps.wallpaper.utils.Utils

/**
 * Provide details about the wallpaper image, ie. wallpaper creator, link, etc.
 */
class DetailsDialogFragment(val wallpaper: WallpaperModel) : DialogFragment() {

    /**
     * Returns a dialog that displays the wallpaper details
     */
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            AlertDialog.Builder(it)
                .setMessage(
                    Utils.getWallpaperDetailsMessage(wallpaper, requireActivity().baseContext)
                )
                .setPositiveButton(resources.getString(R.string.text_dismiss_dialog)) { dialog, _ ->
                    dialog.dismiss()
                }
                .create()
        } ?: throw Exception("FragmentActivity associated with DetailsDialogFragment is null")
    }
}