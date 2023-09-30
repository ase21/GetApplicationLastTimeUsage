package com.asefactory.domain.models

import com.asefactory.getapplicationlasttimeusage.trytest.Dollar
import org.junit.Assert.*

import org.junit.Test

class ApplicationInfoWithInstallationDateTest {

    @Test
    fun testMultiplication() {
        val five = Dollar(5)
        five.times(2)
        assertEquals(10, five.amount)
        five.times(3)
        assertEquals(15, five.amount)
    }
}