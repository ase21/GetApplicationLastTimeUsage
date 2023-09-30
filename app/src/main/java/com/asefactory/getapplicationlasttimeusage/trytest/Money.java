package com.asefactory.getapplicationlasttimeusage.trytest;

import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

public abstract class Money {

    protected int amount;
    protected String currency;

    public Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public abstract Money times(int multiplier);

    @Override
    public boolean equals(@Nullable Object obj) {
        Money money = (Money) obj;
        return amount == money.amount && getClass().equals(money.getClass());
    }

    @NotNull
    public static Money dollar(int amount) {
        return new Dollar(amount, "USD");
    }

    @NotNull
    public static Money franc(int amount) {
        return new Franc(amount, "CHF");
    }

    public @NotNull String currency() {
        return currency;
    }
}
