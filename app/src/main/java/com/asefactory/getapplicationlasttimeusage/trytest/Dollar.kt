package com.asefactory.getapplicationlasttimeusage.trytest

class Dollar(amount: Int) {
    var amount: Int

    init {
        this.amount = amount
    }

    fun times(multiplier: Int) {
        amount *= multiplier
    }
}