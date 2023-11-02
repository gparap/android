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
package gparap.apps.selfie_editor.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.View
import gparap.apps.selfie_editor.utils.AppConstants.BITMAP_ON_BITMAP_SCALE_FACTOR
import gparap.apps.selfie_editor.utils.AppConstants.BITMAP_ON_DEVICE_SCALE_FACTOR

class CanvasView : View {
    private var paint = Paint()
    private lateinit var bitmap: Bitmap
    private lateinit var canvas: Canvas
    private var existingBitmap: Bitmap? = null
    private var drawingPositions: ArrayList<PointF>? = null

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?, bitmap: Bitmap?) : super(context, attrs) {
        //create a canvas and a mutable bitmap to draw into
        paint.apply {
            isAntiAlias = true
            style = Paint.Style.STROKE
            isDither = false
        }
        this.bitmap = bitmap!!.copy(Bitmap.Config.RGB_565, true)
        canvas = Canvas(this.bitmap)
    }

    constructor(
        context: Context?, attrs: AttributeSet?, bitmap: Bitmap?, existingBitmap: Bitmap?,
        drawingPositions: ArrayList<PointF>?
    ) : super(context, attrs) {
        //create a canvas and a mutable bitmap to draw into
        paint.apply {
            isAntiAlias = true
            isFilterBitmap = true
            isDither = false
        }
        this.bitmap = bitmap!!.copy(Bitmap.Config.RGB_565, true)
        canvas = Canvas(this.bitmap)
        this.existingBitmap = existingBitmap
        this.drawingPositions = drawingPositions
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        //save canvas before drawing
        canvas?.save()

        //re-draw the previous bitmap
        existingBitmap?.let { canvas?.drawBitmap(it, 0f, 0f, paint) }

        //draw the addon bitmap based on number of faces detected
        if (drawingPositions.isNullOrEmpty()) {
            canvas?.drawBitmap(bitmap, 0f, 0f, paint)
        } else {
            drawingPositions!!.forEach {
                canvas?.drawBitmap(
                    bitmap,
                    (it.x / BITMAP_ON_BITMAP_SCALE_FACTOR) / 2 - bitmap.width / BITMAP_ON_BITMAP_SCALE_FACTOR / 2,
                    (((it.y / BITMAP_ON_BITMAP_SCALE_FACTOR) / 2) * 1/BITMAP_ON_DEVICE_SCALE_FACTOR.toFloat())
                            + ((bitmap.height / BITMAP_ON_BITMAP_SCALE_FACTOR / 2)* 1/BITMAP_ON_DEVICE_SCALE_FACTOR.toFloat()),
                    paint
                )
            }
        }

        //restore canvas after drawing
        canvas?.restore()
        invalidate()
    }

    /** Returns the already drawn bitmap on canvas. */
    fun getDrawnBitmap(): Bitmap? {
        return if (existingBitmap == null)
            this.bitmap
        else existingBitmap
    }
}