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

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.github.barteksc.pdfviewer.PDFView
import gparap.apps.open_book_library.R
import java.io.File

class ReadBookFragment : Fragment() {
    //access the Fragment's arguments as an Args instance
    private val args: ReadBookFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_read_book, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //display the book details using the passed arguments of the fragment
        view.findViewById<PDFView>(R.id.pdf_view).apply {
            if (args.argsBookIsAsset) {
                //books in application assets folder
                this.fromAsset(args.argsBookAssetName).load()
            } else {
                //books in application storage folder
                val filePath = args.argsBookFilepath
                val file = File(filePath)
                this.fromFile(file).load()
            }
        }
    }
}