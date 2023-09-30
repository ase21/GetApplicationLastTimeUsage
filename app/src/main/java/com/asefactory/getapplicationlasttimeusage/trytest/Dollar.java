package com.asefactory.getapplicationlasttimeusage.trytest;

import org.jetbrains.annotations.NotNull;

public class Dollar extends Money {

    public Dollar(int amount, String currency) {
        super(amount, currency);
    }

    @NotNull
    public Money times(int multiplier) {
        return Money.dollar(amount * multiplier);
    }
}
