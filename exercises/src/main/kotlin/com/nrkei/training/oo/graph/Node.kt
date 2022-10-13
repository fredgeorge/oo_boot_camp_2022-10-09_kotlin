/*
 * Copyright (c) 2022 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.graph

import com.nrkei.training.oo.graph.Path.Companion.filterBy

// Understands its neighbors
class Node {
    private val links = mutableListOf<Link>()

    infix fun canReach(destination: Node) = paths(destination).isNotEmpty()

    infix fun hopCount(destination: Node) = path(destination, Path::hopCount).hopCount()

    infix fun cost(destination: Node) = path(destination).cost()

    infix fun path(destination: Node) = path(destination, Path::cost)

    infix fun paths(destination: Node) = paths().filterBy(destination)

    fun paths() = paths(noVisitedNodes)

    internal fun paths(visitedNodes: List<Node>): List<Path> {
        if (this in visitedNodes) return emptyList()
        return links.flatMap { it.paths(visitedNodes + this) } + Path(this)
    }

    private fun path(destination: Node, strategy: PathStrategy) = paths(destination)
        .minByOrNull { strategy(it).toDouble() }
        ?: throw IllegalArgumentException("Destination cannot be reached")

    private val noVisitedNodes = emptyList<Node>()

    infix fun cost(amount: Number) = LinkBuilder(amount.toDouble(), links)

    class LinkBuilder internal constructor(
        private val cost: Double,
        private val links: MutableList<Link>
    ) {
        infix fun to(neighbor: Node) = neighbor.also { links.add(Link(cost, neighbor)) }
    }
}