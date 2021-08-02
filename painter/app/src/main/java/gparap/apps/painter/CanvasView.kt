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
package gparap.apps.painter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.MotionEvent
import android.view.View

class CanvasView : View {
    private var paint = Paint()
    private var paintWithFlag = Paint(Paint.DITHER_FLAG)
    private var path = Path()
    private var paths: ArrayList<Path> = ArrayList()
    private lateinit var bitmap: Bitmap
    private lateinit var canvasWithBitmap: Canvas
    private var currentPathX: Float = 0.0f
    private var currentPathY: Float = 0.0f

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        //create a new paint and initialize it with default values
        paint = Paint()
        paint.isAntiAlias = true
        paint.style = Paint.Style.STROKE
        paint.color = Color.WHITE
        paint.strokeWidth = 5f

        //get display metrics that describe the size and density of this display
        val displayMetrics = DisplayMetrics()
        (context as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)
        val width = displayMetrics.widthPixels
        val height = displayMetrics.heightPixels

        //create a canvas and a mutable bitmap to draw into
        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        canvasWithBitmap = Canvas(bitmap)
        canvasWithBitmap.drawColor(Color.BLACK)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        //save canvas before drawing
        canvas?.save()

        //draw all paths
        for (p in paths) {
            paint.maskFilter = null
            canvasWithBitmap.drawPath(p, paint)
        }

        //draw the specified bitmap to canvas
        canvas?.drawBitmap(bitmap, 0.0f, 0.0f, paintWithFlag)

        //restore canvas after drawing
        canvas?.restore()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event?.action){
            MotionEvent.ACTION_DOWN -> startDrawingPath(event.x, event.y)
            MotionEvent.ACTION_MOVE -> moveDrawingPath(event.x, event.y)
            MotionEvent.ACTION_UP -> stopDrawingPath()
        }
        invalidate()
        return true
    }

    private fun startDrawingPath(x: Float, y:Float) {
        //create and add path to list
        path = Path()
        path.reset()
        path.moveTo(x,y)
        paths.add(path)

        updateCurrentPath(x,y)
    }

    private fun moveDrawingPath(x: Float, y:Float) {
        //TODO: tolerance
        path.quadTo(currentPathX, currentPathY, (x+currentPathX)/2, (y+currentPathY)/2)

        updateCurrentPath(x,y)
    }

    private fun stopDrawingPath() {
        //add a line from the last path to the current path
        path.lineTo(currentPathX, currentPathY)
    }

    private fun updateCurrentPath(x: Float, y:Float) {
        currentPathX = x
        currentPathY = y
    }
}