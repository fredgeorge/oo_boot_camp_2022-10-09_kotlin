/*
 * Copyright (c) 2022 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.quantity

import com.nrkei.training.oo.order.Orderable
import com.nrkei.training.oo.quantity.Unit.Companion.EPSILON
import kotlin.math.absoluteValue

// Understands a specific measurement
open class IntervalQuantity internal constructor(amount: Number, protected val unit: Unit)
    : Orderable<IntervalQuantity> {
    protected val amount = amount.toDouble()

    override fun equals(other: Any?) = this === other || other is IntervalQuantity && this.equals(other)

    private fun equals(other: IntervalQuantity) = this isCompatible other &&
            (this.amount - convertedAmount(other)).absoluteValue < EPSILON

    private infix fun isCompatible(other: IntervalQuantity) = this.unit isCompatible other.unit

    protected fun convertedAmount(other: IntervalQuantity) = this.unit.convertedAmount(other.amount, other. unit)

    override fun hashCode() = unit.hashCode(amount)

    override fun isBetterThan(other: IntervalQuantity) =
        this.amount > convertedAmount(other)
}