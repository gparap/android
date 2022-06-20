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

import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.core.content.res.ResourcesCompat

object Utils {
    /**
     * Beautifies the result string
     *  ie. 500.0 -> 500, 13.46234756 -> 13.46, etc
     */
    fun beautifyResult(result: String): String {
        //keep only two (2) decimals
        var beautifiesResult = try {
            result.substring(0, result.indexOf(".") + 3)
        } catch (e: Exception) {
            result
        }

        //remove zero (".0")
        if (beautifiesResult.endsWith(".0")) {
            beautifiesResult = beautifiesResult.dropLast(2)
        }

        //remove zero (".00")
        if (beautifiesResult.endsWith(".00")) {
            beautifiesResult = beautifiesResult.dropLast(3)
        }

        return beautifiesResult
    }

    /**
     * Gets an image drawable by name
     */
    fun getImageDrawable(name: String, resources: Resources): Drawable? {
        var drawable: Drawable? = null

        when (name) {
            resources.getString(R.string.shape_square) -> {
                drawable = ResourcesCompat.getDrawable(resources, R.drawable.square, null)
            }
            resources.getString(R.string.shape_rectangle) -> {
                drawable = ResourcesCompat.getDrawable(resources, R.drawable.rectangle, null)
            }
            resources.getString(R.string.shape_parallelogram) -> {
                drawable = ResourcesCompat.getDrawable(resources, R.drawable.parallelogram, null)
            }
            resources.getString(R.string.shape_rhombus) -> {
                drawable = ResourcesCompat.getDrawable(resources, R.drawable.rhombus, null)
            }
            resources.getString(R.string.shape_equilateral_triangle) -> {
                drawable =
                    ResourcesCompat.getDrawable(resources, R.drawable.equilateral_triangle, null)
            }
            resources.getString(R.string.shape_isosceles_triangle) -> {
                drawable =
                    ResourcesCompat.getDrawable(resources, R.drawable.isosceles_triangle, null)
            }
            resources.getString(R.string.shape_triangle) -> {
                drawable = ResourcesCompat.getDrawable(resources, R.drawable.triangle, null)
            }
            resources.getString(R.string.shape_isosceles_trapezoid) -> {
                drawable =
                    ResourcesCompat.getDrawable(resources, R.drawable.isosceles_trapezoid, null)
            }
            resources.getString(R.string.shape_trapezoid) -> {
                drawable = ResourcesCompat.getDrawable(resources, R.drawable.trapezoid, null)
            }
            resources.getString(R.string.shape_regular_pentagon) -> {
                drawable = ResourcesCompat.getDrawable(resources, R.drawable.regular_pentagon, null)
            }
            resources.getString(R.string.shape_hexagon) -> {
                drawable = ResourcesCompat.getDrawable(resources, R.drawable.hexagon, null)
            }
            resources.getString(R.string.shape_circle) -> {
                drawable = ResourcesCompat.getDrawable(resources, R.drawable.circle, null)
            }
            resources.getString(R.string.shape_oval) -> {
                drawable = ResourcesCompat.getDrawable(resources, R.drawable.oval, null)
            }
        }

        return drawable
    }
}