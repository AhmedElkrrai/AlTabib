package com.example.altabib.utils

import kotlin.math.pow

fun Float.roundToDecimal(numDecimals: Int): Float {
    val factor = 10f.pow(numDecimals)
    return (this * factor).toInt() / factor
}