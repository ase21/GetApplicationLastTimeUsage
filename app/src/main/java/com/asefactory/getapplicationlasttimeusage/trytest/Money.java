package com.asefactory.getapplicationlasttimeusage.trytest;

import androidx.annotation.Nullable;

public class Money {

    protected int amount;

    @Override
    public boolean equals(@Nullable Object obj) {
        Money money = (Money) obj;
        return amount == money.amount && getClass().equals(money.getClass());
    }
}
