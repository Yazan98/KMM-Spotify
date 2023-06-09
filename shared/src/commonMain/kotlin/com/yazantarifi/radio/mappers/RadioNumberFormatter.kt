package com.yazantarifi.radio.mappers

object RadioNumberFormatter {

    fun addCommasToNumber(number: String): String {
        val stringBuilder = StringBuilder(number)
        val length = stringBuilder.length

        var commaIndex = length - 3
        while (commaIndex > 0) {
            stringBuilder.insert(commaIndex, ',')
            commaIndex -= 3
        }

        return stringBuilder.toString()
    }

}