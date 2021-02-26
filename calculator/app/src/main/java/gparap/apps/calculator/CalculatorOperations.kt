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
package gparap.apps.calculator

import kotlin.math.pow

/**
 * Mathematical operations ("+", "-", "*", "/", "%", "^") between two numbers.
 * Created by gparap on 2021-02-11.
 */
fun addTwoNumbers(number1: Double, number2: Double): Double {
    return number1 + number2
}

fun subtractTwoNumbers(number1: Double, number2: Double): Double {
    return number1 - number2
}

fun multiplyTwoNumbers(number1: Double, number2: Double): Double {
    return number1 * number2
}

fun divideTwoNumbers(number1: Double, number2: Double): Double {
    return number1 / number2
}

fun moduloTwoNumbers(number1: Double, number2: Double): Double {
    return number1 % number2
}

fun powerTwoNumbers(number1: Double, number2: Double): Double {
    return number1.pow(number2)
}
