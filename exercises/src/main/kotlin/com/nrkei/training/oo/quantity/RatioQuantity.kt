/*
 * Copyright (c) 2022 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.quantity

import com.nrkei.training.oo.quantity.Unit.Companion.EPSILON
import kotlin.math.absoluteValue

// Understands a specific measurement
class RatioQuantity internal constructor(amount: Number, private val unit: Unit) {
    private val amount = amount.toDouble()

    override fun equals(other: Any?) = this === other || other is RatioQuantity && this.equals(other)

    private fun equals(other: RatioQuantity) = this isCompatible other &&
            (this.amount - convertedAmount(other)).absoluteValue < EPSILON

    private infix fun isCompatible(other: RatioQuantity) = this.unit isCompatible other.unit

    private fun convertedAmount(other: RatioQuantity) = this.unit.convertedAmount(other.amount, other. unit)

    override fun hashCode() = unit.hashCode(amount)

    operator fun unaryPlus() = this

    operator fun unaryMinus() = RatioQuantity(-amount, unit)

    operator fun plus(other: RatioQuantity) = RatioQuantity(this.amount + convertedAmount(other), unit)

    operator fun minus(other: RatioQuantity) = this + -other
}