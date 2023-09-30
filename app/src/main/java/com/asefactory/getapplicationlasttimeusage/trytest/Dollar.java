package com.asefactory.getapplicationlasttimeusage.trytest;

import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

public class Dollar extends Money{

    public Dollar(int amount) {
        this.amount = amount;
    }

    @NotNull
    public Money times(int multiplier) {
        return new Dollar(amount*multiplier);
    }
}
