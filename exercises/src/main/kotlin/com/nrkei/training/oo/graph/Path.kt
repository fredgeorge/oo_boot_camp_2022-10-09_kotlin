/*
 * Copyright (c) 2022 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.graph

import com.nrkei.training.oo.graph.Link.Companion.cost

// Understands a route from one Node to another
class Path internal constructor(private val destination: Node) {
    companion object {
        internal fun List<Path>.filterBy(destination: Node) = this.filter { it.destination == destination }
    }

    private val links = mutableListOf<Link>()

    internal fun prepend(link: Link) = links.add(0, link)

    fun cost() = links.cost()

    fun hopCount() = links.size
}

typealias PathStrategy = (Path) -> Number
