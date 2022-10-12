/*
 * Copyright (c) 2022 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.graph

// Understands its neighbors
class Node {
    private val links = mutableListOf<Link>()

    infix fun canReach(destination: Node) = path(destination, noVisitedNodes, Path::cost) != Path.None

    infix fun hopCount(destination: Node) = path(destination, Path::hopCount).hopCount()

    infix fun cost(destination: Node) = path(destination).cost()

    infix fun path(destination: Node) = path(destination, Path::cost)

    private fun path(destination: Node, strategy: PathStrategy) =
        path(destination, noVisitedNodes, strategy)
            .also { require(it != Path.None) { "Destination cannot be reached" } }

    internal fun path(destination: Node, visitedNodes: List<Node>, strategy: PathStrategy): Path {
        if (this == destination) return Path.ActualPath()
        if (this in visitedNodes) return Path.None
        return links
            .map { it.path(destination, visitedNodes + this, strategy) }
            .minByOrNull { strategy(it).toDouble() }
            ?: Path.None
    }

    private val noVisitedNodes = emptyList<Node>()

    infix fun cost(amount: Number) = LinkBuilder(amount.toDouble(), links)

    class LinkBuilder internal constructor(
        private val cost: Double,
        private val links: MutableList<Link>
    ) {
        infix fun to(neighbor: Node) = neighbor.also { links.add(Link(cost, neighbor)) }
    }
}