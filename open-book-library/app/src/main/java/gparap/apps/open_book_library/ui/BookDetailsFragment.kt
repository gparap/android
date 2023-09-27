/*
 * Copyright 2023 gparap
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
package gparap.apps.open_book_library.ui

import android.content.res.AssetManager
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.drawable.toDrawable
import androidx.navigation.fragment.navArgs
import gparap.apps.open_book_library.R

class BookDetailsFragment : Fragment() {
    //access the Fragment's arguments as an Args instance
    private val args: BookDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //display the book details using the passed arguments of the fragment
        view.findViewById<TextView>(R.id.text_view_book_title).apply { text =  args.argsBookTitle}
        view.findViewById<TextView>(R.id.text_view_book_author).apply { text =  args.argsBookAuthor}
        view.findViewById<TextView>(R.id.text_view_book_genre).apply { text =  args.argsBookGenre}
        view.findViewById<TextView>(R.id.text_view_book_date).apply { text =  args.argsBookDate.toString()}
        view.findViewById<TextView>(R.id.text_view_book_pages).apply { text =  args.argsBookPages.toString()}
        view.findViewById<TextView>(R.id.text_view_book_language).apply { text =  args.argsBookLanguage}
        view.findViewById<TextView>(R.id.text_view_book_country).apply { text =  args.argsBookCountry}
        view.findViewById<TextView>(R.id.text_view_book_publisher).apply { text =  args.argsBookPublisher}
        view.findViewById<ImageView>(R.id.image_view_book_cover).apply {
            //show book cover
            val assetManager: AssetManager = context.assets
            val inputStream = assetManager.open(args.argsBookCoverUrl)
            val bitmap = BitmapFactory.decodeStream(inputStream)
            setImageDrawable(bitmap.toDrawable(context.resources))
        }
        view.findViewById<TextView>(R.id.text_view_book_cover_artist).apply { text =  args.argsBookCoverArtist}
        //TODO: attribution details
    }
}