/*
 * Copyright 2021 gparap
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
package gparap.apps.pdf_creator.utils

import android.graphics.Paint
import android.graphics.pdf.PdfDocument

object Utils {
    /** Creates and returns a PDF document with one page. */
    fun createPDF(pdfInput: String) : PdfDocument {
        val pdfDocument = PdfDocument()

        //create a page description
        val pageInfo = PdfDocument.PageInfo.Builder(100, 100, 1).create()

        //create and write a page
        val page = pdfDocument.startPage(pageInfo)
        val canvas = page.canvas
        canvas.drawText(pdfInput, 10F, 10F, Paint())
        pdfDocument.finishPage(page)

        //return the PDF
        return pdfDocument
    }
}