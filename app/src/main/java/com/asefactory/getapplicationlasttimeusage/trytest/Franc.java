package com.asefactory.getapplicationlasttimeusage.trytest;

import org.jetbrains.annotations.NotNull;

public class Franc extends Money {

    public Franc(int amount, String currency) {
        super(amount, currency);
    }

    @NotNull
    public Money times(int multiplier) {
        return Money.franc(amount * multiplier);
    }
}
