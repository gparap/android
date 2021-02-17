package gparap.apps.calculator_area

import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Area calculations for shapes:
 * Square, Rectangle, Parallelogram, Equilateral Triangle, Triangle, Trapezoid, Hexagon and Circle.
 * Created by gparap on 2021-02-12.
 */
class CalculatorOperations {
    companion object {
        @JvmStatic
        fun calculateSquare(side: Int): Int {
            return side.toDouble().pow(2).toInt()
        }

        @JvmStatic
        fun calculateRectangle(sideA: Int, sideB: Int): Int {
            return sideA.times(sideB)
        }

        @JvmStatic
        fun calculateParallelogram(side: Int, height: Int): Int {
            return side.times(height)
        }

        @JvmStatic
        fun calculateEquilateralTriangle(side: Int): Float {
            return ((sqrt(3.0f)) * (side.toFloat().pow(2))) / 4
        }

        @JvmStatic
        fun calculateTriangle(side: Int, height: Int): Float {
            return side.times(height) / 2.0f
        }

        @JvmStatic
        fun calculateTrapezoid(sideA: Int, sideB: Int, height: Int): Float {
            return 0.5f * (sideA + sideB) * height
        }

        @JvmStatic
        fun calculateHexagon(side: Int): Float {
            return (3.0f * (sqrt(3.0f)) * (side.toFloat().pow(2))) / 2.0f
        }

        @JvmStatic
        fun calculateCircle(diameter: Int): Double {
            return Math.PI * ((diameter.div(2)))
        }
    }

}



