/*
 * Copyright (c) 2022 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.graph

import com.nrkei.training.oo.graph.Link.Companion.cost

// Understands a route from one Node to another
abstract class Path internal constructor() {

    internal open fun prepend(link: Link) {}

    abstract fun cost(): Double

    abstract fun hopCount(): Int

    class ActualPath internal constructor() : Path() {
        private val links = mutableListOf<Link>()

        override fun prepend(link: Link) {
            links.add(0, link)
        }

        override fun cost() = links.cost()

        override fun hopCount() = links.size
    }
}