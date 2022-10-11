/*
 * Copyright (c) 2022 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.order

// Understands a sequence of elements
interface Orderable<T> {
    fun isBetterThan(other: T): Boolean
}

fun <T: Orderable<T>> List<T>.bestOrNull(): T? {
    if (this.isEmpty()) return null
    var champion = this.first()
    for (challenger in this) if (challenger.isBetterThan(champion)) champion = challenger
    return champion
}