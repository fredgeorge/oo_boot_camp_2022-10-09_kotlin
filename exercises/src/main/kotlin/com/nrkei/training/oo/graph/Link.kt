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
        internal fun List<Link>.cost() = this.sumOf { it.cost }
    }

    internal fun path(destination: Node, visitedNodes: List<Node>, strategy: PathStrategy) =
        target.path(destination, visitedNodes, strategy).also { it.prepend(this) }
}
