package com.asefactory.getapplicationlasttimeusage.trytest;

import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

public class Dollar {

    private final int amount;

    public Dollar(int amount) {
        this.amount = amount;
    }

    @NotNull
    public Dollar times(int multiplier) {
        return new Dollar(amount*multiplier);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        Dollar dollar = (Dollar) obj;
        return amount == dollar.amount;
    }
}
