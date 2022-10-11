/*
 * Copyright (c) 2022 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.order

// Understands a sequence of elements
interface Orderable<T> {
    infix fun isBetterThan(other: T): Boolean
}

fun <T: Orderable<T>> List<T>.bestOrNull(): T? = this.reduceOrNull { champion, challenger ->
    if (challenger isBetterThan champion) challenger else champion
}