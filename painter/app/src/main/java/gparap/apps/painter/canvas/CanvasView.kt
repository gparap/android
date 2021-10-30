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
package gparap.apps.painter.canvas

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import gparap.apps.painter.utils.Utils

@Suppress("PrivatePropertyName")
class CanvasView : View {
    private val PAINT_DITHER_FLAG = android.graphics.Paint.DITHER_FLAG
    private val BITMAP_POSITION_LEFT = 0.0f
    private val BITMAP_POSITION_TOP = 0.0f
    private var paint = Paint()
    private lateinit var path: Path
    private var paths: ArrayList<PaintPath> = ArrayList()
    lateinit var bitmap: Bitmap
    private lateinit var canvasWithBitmap: Canvas
    private var currentPathX: Float = 0.0f
    private var currentPathY: Float = 0.0f
    var currentColor: Int = Color.BLACK //default
    var strokeWidth: Int = 5

    constructor(context: Context?) : super(context)
    constructor(
        context: Context?,
        attrs: AttributeSet?,
        bitmap: Bitmap?,
        isBitmapLoaded: Boolean
    ) : super(context, attrs) {
        //create a canvas and a mutable bitmap to draw into
        if (!isBitmapLoaded) {
            this.bitmap = Bitmap.createBitmap(
                Utils.getDeviceWidth(context),
                Utils.getDeviceHeight(context),
                Bitmap.Config.ARGB_8888
            )
            canvasWithBitmap = Canvas(this.bitmap)
        } else {
            this.bitmap = bitmap!!.copy(Bitmap.Config.ARGB_8888, true)
            canvasWithBitmap = Canvas(this.bitmap)
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        //save canvas before drawing
        canvas?.save()

        //draw all paths
        for (p in paths) {
            paint.maskFilter = null
            paint.color = p.color
            paint.strokeWidth = p.width.toFloat()   //TODO: pen scale x10, x0.1 as option
            canvasWithBitmap.drawPath(p.path, paint)
        }

        //draw the specified bitmap to canvas
        canvas?.drawBitmap(
            bitmap,
            BITMAP_POSITION_LEFT,
            BITMAP_POSITION_TOP,
            paint.getFlag(PAINT_DITHER_FLAG)
        )

        //restore canvas after drawing
        canvas?.restore()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> startDrawingPath(event.x, event.y)
            MotionEvent.ACTION_MOVE -> moveDrawingPath(event.x, event.y)
            MotionEvent.ACTION_UP -> stopDrawingPath()
        }
        invalidate()
        return true
    }

    private fun startDrawingPath(x: Float, y: Float) {
        //create a default path
        path = Path()
        path.reset()
        path.moveTo(x, y)

        //create and setup a PaintPath
        val paintPath = PaintPath(path, currentColor, strokeWidth)
        paths.add(paintPath)

        updateCurrentPath(x, y)
    }

    private fun moveDrawingPath(x: Float, y: Float) {
        //TODO: tolerance
        path.quadTo(currentPathX, currentPathY, (x + currentPathX) / 2, (y + currentPathY) / 2)

        updateCurrentPath(x, y)
    }

    private fun stopDrawingPath() {
        //add a line from the last path to the current path
        path.lineTo(currentPathX, currentPathY)
    }

    private fun updateCurrentPath(x: Float, y: Float) {
        currentPathX = x
        currentPathY = y
    }

    fun clearCanvas() {
        canvasWithBitmap.drawColor(Color.WHITE)
        paths.clear()
        invalidate()
    }

    fun setEraseMode() {
        currentColor = Color.WHITE
    }

    fun setPaintMode() {
        currentColor = Color.BLACK //default
    }
}