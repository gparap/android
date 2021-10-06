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

import android.graphics.Color
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.os.Environment
import java.io.File
import java.io.FileOutputStream

object Utils {
    /** Creates a PDF document with one page and saves it to the device. */
    fun savePDF(pdfInput: String) {
        val pdfDocument = PdfDocument()

        //setup and write to page
        val pageInfo = PdfDocument.PageInfo.Builder(480, 800, 1)
            .create()
        val page = pdfDocument.startPage(pageInfo)
        val canvas = page.canvas
        val paint = Paint()
        paint.textSize = 24F
        paint.color = Color.BLACK
        canvas.drawText(pdfInput, paint.textSize,paint.textSize, paint)
        pdfDocument.finishPage(page)

        //set file path (based on API version)
        val filename = "temp.pdf"
        val path = if (android.os.Build.VERSION.SDK_INT <= 29) {
            File(Environment.getExternalStorageDirectory(), filename)
        } else {
            File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),
                filename
            )
        }

        //save PDF to device
        try {
            pdfDocument.writeTo(FileOutputStream(path))
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            pdfDocument.close()
        }
    }
}