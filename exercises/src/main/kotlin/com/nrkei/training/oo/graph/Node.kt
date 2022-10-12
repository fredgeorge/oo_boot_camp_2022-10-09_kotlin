/*
 * Copyright (c) 2022 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.graph

// Understands its neighbors
class Node {
    companion object {
        private const val UNREACHABLE = Double.POSITIVE_INFINITY
    }

    private val links = mutableListOf<Link>()

    infix fun to(neighbor: Node) = neighbor.also { links.add(Link(neighbor)) }

    infix fun canReach(destination: Node) = hopCount(destination, noVisitedNodes) != UNREACHABLE

    infix fun hopCount(destination: Node) = hopCount(destination, noVisitedNodes).also {
        require(it != UNREACHABLE) { "Destination cannot be reached" }
    }.toInt()

    internal fun hopCount(destination: Node, visitedNodes: List<Node>): Double {
        if (this == destination) return 0.0
        if (this in visitedNodes || links.isEmpty()) return UNREACHABLE
        return links.minOf { it.hopCount(destination, visitedNodes + this)}
    }

    private val noVisitedNodes = emptyList<Node>()
}