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
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import java.io.File
import java.io.FileOutputStream

object Utils {
    /** Creates a PDF document with one page and saves it to the device. */
    fun savePDF(pdfInput: String) {
        val pdfDocument = PdfDocument()

        //setup meta-data that describes a PDF page
        val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1)
            .create()

        //start a PDF page for drawing
        val page = pdfDocument.startPage(pageInfo)

        //setup data for text measuring and drawing
        val textPaint = createTextPaint(24f)

        //create a Layout for the page
        val textLayout = createTextLayout(pdfInput, textPaint, page)

        //draw the page
        textLayout.draw(page.canvas)
        pdfDocument.finishPage(page)

        //set file path
        val path = getFilePath()

        //save PDF to device
        val fileOutputStream = FileOutputStream(path)
        try {
            pdfDocument.writeTo(fileOutputStream)
            fileOutputStream.flush()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            //!!! release resources
            fileOutputStream.close()
            pdfDocument.close()
        }
    }

    //setup data for text measuring and drawing
    fun createTextPaint(textSize: Float): TextPaint {
        val textPaint = TextPaint()

        textPaint.color = Color.BLACK
        textPaint.textAlign = Paint.Align.LEFT
        textPaint.textSize = textSize

        return textPaint
    }

    //create a layout for the text of the PDF's page
    fun createTextLayout(
        pdfInput: String,
        textPaint: TextPaint,
        page: PdfDocument.Page
    ): StaticLayout {
        return StaticLayout.Builder.obtain(
            pdfInput,
            0,
            pdfInput.length,
            textPaint,
            page.canvas.width
        )
            .setAlignment(Layout.Alignment.ALIGN_NORMAL)
            .setLineSpacing(0F, 1F)
            .setIncludePad(false)
            .build()
    }

    //get the file path based on API version
    //TODO: filename will be given by user
    fun getFilePath(filename: String = "temp.pdf"): File {
        val path = if (android.os.Build.VERSION.SDK_INT <= 29) {
            File(Environment.getExternalStorageDirectory(), filename)
        } else {
            File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),
                filename
            )
        }
        return path
    }
}