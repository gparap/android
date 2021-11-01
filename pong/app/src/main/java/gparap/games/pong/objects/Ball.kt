package gparap.games.pong.objects

import android.graphics.Canvas
import android.graphics.Paint

class Ball(private val radius: Float, private val paint: Paint) {
    private var cx: Float
    private var cy: Float
    private var vx: Float
    private var vy: Float

    init {
        //cx, cy – The x-coordinate, y-coordinate of the center of the circle to be drawn
        cx = 0F
        cy = 0F

        //velocity (x,y)
        vx = 10F
        vy = 10F
    }

    fun draw(canvas: Canvas) {
        canvas.drawCircle(cx, cy, radius, paint)
    }

    /** Moves the ball inside the canvas */
    fun update(canvas: Canvas) {
        cx += vx
        cx += vx

        //Check for canvas boundaries and adjust the ball position
        //left
        if (cx < 0) {
            cx = 0F
        }
        //right
        if (cx + radius > canvas.width) {
            cx = canvas.width - radius
        }
        //top
        if (cy > canvas.height) {
            cy = canvas.height.toFloat()
        }
        //bottom
        if (cy + radius < 0) {
            cy = radius
        }
    }
}