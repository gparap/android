package gparap.apps.pdf_creator.utils

import android.graphics.Color
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.text.TextPaint
import org.junit.Assert.assertNotNull
import org.junit.Test

class UtilsTest {
    @Test
    fun createTextPaint() {
        val textSize = 14F

        //create a test TextPaint
        val expectedTextPaint = TextPaint()
        expectedTextPaint.color = Color.BLACK
        expectedTextPaint.textAlign = Paint.Align.LEFT
        expectedTextPaint.textSize = textSize

        val actualTextPaint = Utils.createTextPaint(textSize)

        //test
        assertNotNull(actualTextPaint)
        assert(actualTextPaint.color == expectedTextPaint.color)
        assert(actualTextPaint.textAlign == expectedTextPaint.textAlign)
        assert(actualTextPaint.textSize == expectedTextPaint.textSize)
    }

    @Test
    fun createTextLayout() {
        val pdfInput = "create static layout"
        val textPaint = TextPaint()

        //create a test PDF
        val pdfDocument = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create()
        val page = pdfDocument.startPage(pageInfo)

        val textLayout = Utils.createTextLayout(pdfInput, textPaint, page)

        assertNotNull(textLayout)

        //close PDF
        pdfDocument.finishPage(page)
        pdfDocument.close()
    }

    @Test
    fun getFilePathByApiVersion_filepathIsCorrect() {
        val filename = "test_file_name.pdf"
        val filepath = Utils.getFilePath(filename)
        assert(filepath.toString().contains(filename))
    }

    @Test
    fun getFilePathByApiVersion_isLegacyApiPathCorrect() {
        val filename = "test_file_path_legacy.pdf"
        val expectedFilepath = "/storage/emulated/0/$filename"
        val actualFilepath = Utils.getFilePath(filename)

        assert(actualFilepath.toString() == expectedFilepath) { "paths do not match" }
    }

    @Test
    fun getFilePathByApiVersion_isModernApiPathCorrect() {
        val filename = "test_file_path_mad.pdf"
        val expectedFilepath = "/storage/emulated/0/Documents/$filename"
        val actualFilepath = Utils.getFilePath(filename)

        assert(actualFilepath.toString() == expectedFilepath) { "paths do not match" }
    }
}