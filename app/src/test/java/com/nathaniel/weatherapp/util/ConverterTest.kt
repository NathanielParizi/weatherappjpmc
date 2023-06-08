package com.nathaniel.weatherapp.util

import com.android.weatherapp.R
import junit.framework.TestCase.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class ConverterTest {

    @Test
    fun setImageResourceCorrectResource() {

        val result = Converter.setImageResource("01n")

        assertEquals(R.drawable.ic_01n,result)
    }


    @Test
    fun setImageResourceIncorrectResource() {

        val result = Converter.setImageResource("03n")

        assertNotEquals(R.drawable.ic_01n,result)
    }


    @Test
    fun setImageResourceCorrectResourceElsePart() {

        val result = Converter.setImageResource("sdasda")

        assertEquals(R.drawable.ic_01d,result)
    }

    @Test
    fun setImageResourceCorrectResourceEmptyString() {

        val result = Converter.setImageResource("")

        assertEquals(R.drawable.ic_01d,result)
    }
}