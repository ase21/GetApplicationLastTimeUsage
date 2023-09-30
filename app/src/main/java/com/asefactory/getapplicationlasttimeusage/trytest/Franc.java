package com.asefactory.getapplicationlasttimeusage.trytest;

import org.jetbrains.annotations.NotNull;

public class Franc extends Money {

    public Franc(int amount) {
        this.amount = amount;
    }

    @NotNull
    public Money times(int multiplier) {
        return new Franc(amount * multiplier);
    }
}
