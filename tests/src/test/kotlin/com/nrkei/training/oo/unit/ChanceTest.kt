/*
 * Copyright (c) 2022 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.unit

import com.nrkei.training.oo.probability.Chance
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

// Ensures Chance operates correctly
internal class ChanceTest {

    @Test
    fun equality() {
        assertEquals(Chance(0.75), Chance(0.75))
        assertNotEquals(Chance(0.75), Chance(0.25))
        assertNotEquals(Chance(0.75), Any())
        assertNotEquals(Chance(0.75), null)
    }

    @Test
    fun `Chance in sets`() {
        assertTrue(Chance(0.75) in hashSetOf(Chance(0.75)))
        assertEquals(1, hashSetOf(Chance(0.75), Chance(0.75)).size)
    }

    @Test fun hash() {
        assertEquals(Chance(0.75).hashCode(), Chance(0.75).hashCode())
    }
}