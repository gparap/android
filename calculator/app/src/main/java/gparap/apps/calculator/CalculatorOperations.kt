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
