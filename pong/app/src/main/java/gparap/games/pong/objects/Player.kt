package gparap.games.pong.objects

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF

class Player(private val width: Float, private val height: Float, private val paint: Paint) {
    private val bounds: RectF
    private var rx: Float
    private var ry: Float

    init {
        //player rectangle boundaries for collision detection
        bounds = RectF(0F, height, width, 0F)

        //rx, ry – The x-radius, y-radius of the oval used to round the corners
        rx = 10F
        ry = 10F
    }

    fun getWidth() : Float {
        return width
    }

    fun getHeight() : Float {
        return height
    }

    fun draw(canvas: Canvas) {
        canvas.drawRoundRect(bounds, rx, ry, paint)
    }
}