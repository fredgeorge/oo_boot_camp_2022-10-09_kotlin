/*
 * Copyright (c) 2022 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.graph

// Understands a connection from one Node to another
internal class Link internal constructor(
    private val cost: Double,
    private val target: Node
) {
    companion object {
        internal val LEAST_COST = { cost: Double -> cost }
        internal val FEWEST_HOPS = { _: Double -> 1.0 }

        internal fun List<Link>.cost() = this.sumOf { it.cost }
    }

    internal fun cost(destination: Node, visitedNodes: List<Node>, strategy: CostStrategy) =
        target.cost(destination, visitedNodes, strategy) + strategy(cost)

    internal fun path(destination: Node, visitedNodes: List<Node>) =
        target.path(destination, visitedNodes)?.also { it.prepend(this) }
}

typealias CostStrategy = (Double) -> Double
