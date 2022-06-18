/*
 * Copyright (c) 2022 gparap
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
package gparap.apps.calculator_area

import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Area calculations for shapes:
 * Square, Rectangle, Parallelogram, Equilateral Triangle, Triangle, Trapezoid, Hexagon and Circle.
 */
class CalculatorOperations {
    companion object {
        @JvmStatic
        fun calculateSquare(side: Double): String {
            return side.pow(2).toString()
        }

        @JvmStatic
        fun calculateRectangle(sideA: Double, sideB: Double): String {
            return sideA.times(sideB).toString()
        }

        @JvmStatic
        fun calculateParallelogram(side: Double, height: Double): String {
            return side.times(height).toString()
        }

        @JvmStatic
        fun calculateRhombus(diagonal1: Double, diagonal2: Double): String {
            return diagonal1.times(diagonal2).div(2).toString()
        }

        @JvmStatic
        fun calculateEquilateralTriangle(side: Double): String {
            return (((sqrt(3.0f)) * (side.pow(2))) / 4).toString()
        }

        @JvmStatic
        fun calculateTriangle(side: Double, height: Double): String {
            return (side.times(height) / 2.0f).toString()
        }

        @JvmStatic
        fun calculateTrapezoid(sideA: Double, sideB: Double, height: Double): String {
            return (0.5f * (sideA + sideB) * height).toString()
        }

        @JvmStatic
        fun calculateRegularPentagon(sideA: Double): String {
            return (0.25 * sqrt(5 * (5 + 2 * sqrt(5.0))) * sideA * sideA).toString()
        }

        @JvmStatic
        fun calculateHexagon(side: Double): String {
            return ((3.0f * (sqrt(3.0f)) * (side.pow(2))) / 2.0f).toString()
        }

        @JvmStatic
        fun calculateCircle(radius: Double): String {
            return (Math.PI * (radius.pow(2))).toString()
        }

        @JvmStatic
        fun calculateOval(r1: Double, r2: Double): String {
            return (Math.PI * r1 * r2).toString()
        }
    }
}



