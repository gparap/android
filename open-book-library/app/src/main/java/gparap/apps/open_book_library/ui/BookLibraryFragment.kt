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

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.barteksc.pdfviewer.PDFView
import com.github.barteksc.pdfviewer.util.FileUtils
import gparap.apps.open_book_library.R
import java.io.File
import java.io.InputStream

class BookLibraryFragment : Fragment() {
    private var fragmentView: View? = null
    private var bookUrl: Uri? = null
    private var bookTitle: String? = null
    private var inputStream: InputStream? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_book_library, container, false)

        //restore previously open PDF after orientation/navigation changes
        if (savedInstanceState != null
            && !(savedInstanceState.getString(BUNDLE_URI_PDF).isNullOrEmpty())
        ) {
            bookUrl = Uri.parse(savedInstanceState.getString(BUNDLE_URI_PDF))
            fragmentView?.findViewById<PDFView>(R.id.pdf_view_book_library).apply {
                this?.fromUri(bookUrl)?.load()
            }
        }

        //return the layout for this fragment
        return fragmentView
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_book_library, menu)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //select & open PDF from device
        if (item.itemId == R.id.menu_item_search_book_library) {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = MIME_TYPE_PDF
            startActivityForResult(intent, REQUEST_CODE_ACTION_GET_PDF)
        }
        //add PDF from device
        if (item.itemId == R.id.menu_item_add_book_from_book_library) {
            AddBookDialogFragment(bookTitle, bookUrl).show(childFragmentManager, null)
        }
        return super.onOptionsItemSelected(item)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_ACTION_GET_PDF) {
            bookUrl = data?.data

            //get the selected book title by accessing the application content model
            if (bookUrl != null) {
                val cursor = this.context?.contentResolver?.query(bookUrl!!, null, null, null, null)
                val columnIndex = cursor?.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                cursor?.moveToFirst()
                if (columnIndex != null) {
                    bookTitle = cursor.getString(columnIndex)
                }
                cursor?.close()
            }

            //copy book to the directory holding application files  TODO: warning dialog for copying
            val inputStreamCopy = requireContext().contentResolver.openInputStream(bookUrl!!)
            inputStream = requireContext().contentResolver.openInputStream(bookUrl!!)
            val outFilePath = requireContext().filesDir.toString() + "/" + bookTitle
            val outFile = File(outFilePath)
            FileUtils.copy(inputStreamCopy, outFile).also {
                bookUrl = Uri.parse(outFilePath)
            }

            //open book for reading
            fragmentView?.findViewById<PDFView>(R.id.pdf_view_book_library)
                ?.apply {
                    val configurator = this.fromStream(inputStream)
                    configurator.load().also {
                        configurator.onLoad {
                            inputStream?.close()
                        }
                    }
                }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        //save open PDF state for orientation/navigation changes
        if (bookUrl != null) {
            outState.putString(BUNDLE_URI_PDF, bookUrl.toString())
        }
    }

    companion object {
        const val MIME_TYPE_PDF = "application/pdf"
        const val REQUEST_CODE_ACTION_GET_PDF = 999
        const val BUNDLE_URI_PDF = "pdf_uri"
    }
}