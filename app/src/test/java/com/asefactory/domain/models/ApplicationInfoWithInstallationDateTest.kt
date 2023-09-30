package com.asefactory.domain.models

import com.asefactory.getapplicationlasttimeusage.trytest.Dollar
import com.asefactory.getapplicationlasttimeusage.trytest.Franc
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ApplicationInfoWithInstallationDateTest {

    @Test
    fun testMultiplication() {
        val five = Dollar(5)
        assertEquals(Dollar(10), five.times(2))
        assertEquals(Dollar(15), five.times(3))
    }

    @Test
    fun testEquality(){
        assertTrue(Dollar(5).equals(Dollar(5)))
        assertFalse(Dollar(5).equals(Dollar(6)))
        assertTrue(Franc(5).equals(Franc(5)))
        assertFalse(Franc(5).equals(Franc(6)))
        assertFalse(Dollar(5).equals(Franc(5)))
    }

    @Test
    fun testFrancMultiplication() {
        val five = Franc(5)
        assertEquals(Franc(10), five.times(2))
        assertEquals(Franc(15), five.times(3))
    }
}