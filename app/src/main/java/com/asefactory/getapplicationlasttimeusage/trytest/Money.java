package com.asefactory.getapplicationlasttimeusage.trytest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

public class Money implements Expression {

    protected int amount;
    private String currency;

    public Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    @NotNull
    public Expression times(int multiplier) {
        return new Money(amount * multiplier, currency());
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        Money money = (Money) obj;
        return amount == money.amount && currency().equals(money.currency());
    }

    @NonNull
    @Override
    public String toString() {
        return amount + " " + currency;
    }

    @NotNull
    public static Money dollar(int amount) {
        return new Money(amount, "USD");
    }

    @NotNull
    public static Money franc(int amount) {
        return new Money(amount, "CHF");
    }

    public @NotNull String currency() {
        return currency;
    }

    @NotNull
    public Expression plus(@NotNull Expression addend) {
        return new Sum(this, addend);
    }

    @Override
    public Money reduce(Bank bank, String to) {
        int rate = bank.rate(currency, to);
        return new Money(amount/rate, to);
    }
}
