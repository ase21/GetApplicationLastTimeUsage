package com.asefactory.getapplicationlasttimeusage.trytest;

import org.jetbrains.annotations.NotNull;

public interface Expression {

    Money reduce(Bank bank, String currency);

    @NotNull Expression plus(@NotNull Expression addent);

    @NotNull Expression times(int times);
}
