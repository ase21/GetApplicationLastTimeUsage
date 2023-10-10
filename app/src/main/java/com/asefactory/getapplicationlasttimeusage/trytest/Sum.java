package com.asefactory.getapplicationlasttimeusage.trytest;

import org.jetbrains.annotations.NotNull;

public class Sum implements Expression {
    public Expression augend;
    public Expression addend;

    public Sum(Expression augend, Expression addend) {
        this.augend = augend;
        this.addend = addend;
    }

    public Money reduce(Bank bank, String currency) {
        int amount = augend.reduce(bank, currency).amount + addend.reduce(bank, currency).amount;
        return new Money(amount, currency);
    }

    @Override
    public @NotNull Expression plus(@NotNull Expression addent) {
        return new Sum(this, addend);
    }

    @Override
    public @NotNull Expression times(int multiplier) {
        return new Sum(augend.times(multiplier), addend.times(multiplier));
    }
}
