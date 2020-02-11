package edu.towson.cosc435.labsapp

import kotlin.math.round

enum class ConvertType{ F2C, C2F }

fun convertTemp(input: Double, convertType: ConvertType): Double {
    // TODO - 5. implement this function to convert from 1 unit to the other
    val result = when(convertType){
        ConvertType.F2C -> (input - 32)*5/9
        ConvertType.C2F ->  input*9/5 + 32
    }
    return result.round(4)
}

private fun Double.round(decimals: Int): Double{
    var multiplier = 1.0
    repeat(decimals) {multiplier *= 10}
    return round(this * multiplier) / multiplier
}