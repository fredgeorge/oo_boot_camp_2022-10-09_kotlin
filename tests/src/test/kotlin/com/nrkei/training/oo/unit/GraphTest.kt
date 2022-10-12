package com.nrkei.training.oo.unit

import com.nrkei.training.oo.graph.Node
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

// Ensures that graph algorithms perform correctly
internal class GraphTest {
    companion object {
        private val A = Node()
        private val B = Node()
        private val C = Node()
        private val D = Node()
        private val E = Node()
        private val F = Node()
        private val G = Node()

        init {
            B cost 5 to A
            B cost 6 to C cost 7 to D cost 2 to E cost 3 to B cost 4 to F
            C cost 1 to D
            C cost 8 to E
        }
    }

    @Test
    fun `can reach`() {
        assertTrue(B canReach B)
        assertTrue(B canReach A)
        assertTrue(B canReach F)
        assertTrue(B canReach D)
        assertTrue(C canReach F)
        assertFalse(G canReach B)
        assertFalse(A canReach B)
        assertFalse(B canReach G)
    }

    @Test internal fun `hop count`() {
        assertEquals(0, B hopCount B)
        assertEquals(1, B hopCount A)
        assertEquals(1, B hopCount F)
        assertEquals(2, B hopCount D)
        assertEquals(3, C hopCount F)
        org.junit.jupiter.api.assertThrows<IllegalArgumentException> { G hopCount B }
        org.junit.jupiter.api.assertThrows<IllegalArgumentException> { A hopCount B }
        org.junit.jupiter.api.assertThrows<IllegalArgumentException> { B hopCount G }
    }
}