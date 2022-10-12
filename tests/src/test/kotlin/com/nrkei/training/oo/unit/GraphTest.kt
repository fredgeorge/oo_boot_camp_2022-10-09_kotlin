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
            B to A
            B to C to D to E to B to F
            C to D
            C to E
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