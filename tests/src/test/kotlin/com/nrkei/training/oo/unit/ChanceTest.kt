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

    companion object {
        private val IMPOSSIBLE = Chance(0)
        private val UNLIKELY = Chance(0.25)
        private val EQUALLY_LIKELY = Chance(0.5)
        private val LIKELY = Chance(0.75)
        private val CERTAIN = Chance(1)
    }

    @Test
    fun equality() {
        assertEquals(Chance(0.75), LIKELY)
        assertNotEquals(LIKELY, UNLIKELY)
        assertNotEquals(LIKELY, Any())
        assertNotEquals(LIKELY, null)
    }

    @Test
    fun `Chance in sets`() {
        assertTrue(Chance(0.75) in hashSetOf(LIKELY))
        assertEquals(1, hashSetOf(LIKELY, Chance(0.75)).size)
    }

    @Test fun hash() {
        assertEquals(LIKELY.hashCode(), Chance(0.75).hashCode())
    }
}