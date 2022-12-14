/*
 * Copyright (c) 2022 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.unit

import com.nrkei.training.oo.quantity.IntervalQuantity
import com.nrkei.training.oo.quantity.Unit.Companion.celsius
import com.nrkei.training.oo.quantity.Unit.Companion.chains
import com.nrkei.training.oo.quantity.Unit.Companion.cups
import com.nrkei.training.oo.quantity.Unit.Companion.fahrenheit
import com.nrkei.training.oo.quantity.Unit.Companion.fathoms
import com.nrkei.training.oo.quantity.Unit.Companion.feet
import com.nrkei.training.oo.quantity.Unit.Companion.furlongs
import com.nrkei.training.oo.quantity.Unit.Companion.gallons
import com.nrkei.training.oo.quantity.Unit.Companion.gasMark
import com.nrkei.training.oo.quantity.Unit.Companion.inches
import com.nrkei.training.oo.quantity.Unit.Companion.kelvin
import com.nrkei.training.oo.quantity.Unit.Companion.leagues
import com.nrkei.training.oo.quantity.Unit.Companion.miles
import com.nrkei.training.oo.quantity.Unit.Companion.ounces
import com.nrkei.training.oo.quantity.Unit.Companion.pints
import com.nrkei.training.oo.quantity.Unit.Companion.quarts
import com.nrkei.training.oo.quantity.Unit.Companion.rankine
import com.nrkei.training.oo.quantity.Unit.Companion.tablespoons
import com.nrkei.training.oo.quantity.Unit.Companion.teaspoons
import com.nrkei.training.oo.quantity.Unit.Companion.yards
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

// Ensures Quantity works correctly
internal class QuantityTest {

    @Test fun `equality of like Units`() {
        assertEquals(8.tablespoons, 8.tablespoons)
        assertNotEquals(8.tablespoons, 6.tablespoons)
        assertNotEquals(8.tablespoons, Any())
        assertNotEquals(8.tablespoons, null)
    }

    @Test fun `equality of different Units`() {
        assertEquals(8.tablespoons, 0.5.cups)
        assertEquals(768.teaspoons, 1.gallons)
        assertNotEquals(8.tablespoons, 8.pints)
        assertEquals(1.miles, (12 * 5280).inches)
        assertEquals(1.5.leagues, 36.furlongs)
        assertEquals(22.fathoms, 2.chains)
    }

    @Test fun `Chance in sets`() {
        assertTrue(8.tablespoons in hashSetOf(8.tablespoons))
        assertEquals(1, hashSetOf(8.tablespoons, 8.tablespoons).size)
    }

    @Test fun hash() {
        assertEquals(8.tablespoons.hashCode(), 8.tablespoons.hashCode())
        assertEquals(8.tablespoons.hashCode(), 0.5.cups.hashCode())
        assertEquals(18.inches.hashCode(), 0.5.yards.hashCode())
        assertEquals(325.fahrenheit.hashCode(), 3.gasMark.hashCode())
    }

    @Test fun arithmetic() {
        assertEquals(0.5.quarts, +6.tablespoons + 13.ounces)
        assertEquals((-6).tablespoons, -6.tablespoons)
        assertEquals((-0.5).pints, 10.tablespoons - 13.ounces)
        assertEquals(-4.feet, 24.inches - 2.yards)
    }

    @Test fun `cross metric types`() {
        assertNotEquals(1.inches, 1.teaspoons)
        assertNotEquals(4.ounces, 2.feet)
    }

    @Test fun `incompatible units`() {
        org.junit.jupiter.api.assertThrows<IllegalArgumentException> { 3.yards - 4.tablespoons }
    }

    @Test internal fun temperatures() {
        assertBidirectionalEquality(0.celsius, 32.fahrenheit)
        assertBidirectionalEquality(10.celsius, 50.fahrenheit)
        assertBidirectionalEquality(100.celsius, 212.fahrenheit)
        assertBidirectionalEquality((-40).celsius, (-40).fahrenheit)
        assertBidirectionalEquality(325.fahrenheit, 3.gasMark)
        assertBidirectionalEquality(0.celsius, 273.15.kelvin)
        assertBidirectionalEquality(50.fahrenheit, 283.15.kelvin)
        assertBidirectionalEquality(50.fahrenheit, 509.67.rankine)
    }

    @Test
    internal fun temperatureArithmetic() {
        // The following should not compile!
//        10.celsius + 32.fahrenheit
    }

    private fun assertBidirectionalEquality(left: IntervalQuantity, right: IntervalQuantity) {
        assertEquals(left, right)
        assertEquals(right, left)
    }
}