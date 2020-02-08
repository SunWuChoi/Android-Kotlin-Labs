package edu.towson.cosc435.labsapp

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class TemperatureConvertTests {
    @Test
    fun convertF2C() {
        val result = convertTemp(32.0, ConvertType.F2C)

        assertEquals(0.0, result, 0.00001)
    }

    @Test
    fun convertF2C_boiling() {
        val result = convertTemp(212.0, ConvertType.F2C)

        assertEquals(100.0, result, 0.00001)
    }

    @Test
    fun convertC2F() {
        val result = convertTemp(0.0, ConvertType.C2F)

        assertEquals(32.0, result, 0.00001)
    }

    @Test
    fun convertC2F_boiling() {
        val result = convertTemp(100.0, ConvertType.C2F)

        assertEquals(212.0, result, 0.00001)
    }

    @Test
    fun convertC2F_random() {
        // TODO - 9. Write one more unit test to test a random conversion
    }
}
