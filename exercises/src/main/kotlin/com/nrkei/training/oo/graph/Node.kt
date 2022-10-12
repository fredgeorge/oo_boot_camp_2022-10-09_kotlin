/*
 * Copyright (c) 2022 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.graph

// Understands its neighbors
class Node {
    companion object {
        private const val UNREACHABLE = -1
    }

    private val neighbors = mutableListOf<Node>()

    infix fun to(neighbor: Node) = neighbor.also { neighbors.add(neighbor) }

    infix fun canReach(destination: Node) = hopCount(destination, noVisitedNodes) != UNREACHABLE

    infix fun hopCount(destination: Node) = hopCount(destination, noVisitedNodes).also {
        require(it != UNREACHABLE) { "Destination cannot be reached" }
    }

    private fun hopCount(destination: Node, visitedNodes: List<Node>): Int {
        if (this == destination) return 0
        if (this in visitedNodes) return UNREACHABLE
        var champion = UNREACHABLE
        for (n in neighbors) {
            val challenger = n.hopCount(destination, visitedNodes + this)
            if (challenger == UNREACHABLE) continue
            if (champion == UNREACHABLE || challenger + 1 < champion) champion = challenger + 1
        }
        return champion
    }

    private val noVisitedNodes = emptyList<Node>()
}