package com.asefactory.getapplicationlasttimeusage.trytest;

import org.jetbrains.annotations.NotNull;

import java.util.Hashtable;

public class Bank {

    private Hashtable<KeyObject, Integer> rates = new Hashtable<>();
    @NotNull
    public Money reduce(@NotNull Expression source, @NotNull String currency) {
        return source.reduce(this, currency);
    }

    public int rate(String from, String to){
        if (from.equals(to)) return 1;
        Integer rate = rates.get(new KeyObject(from, to));
        return rate.intValue();
    }

    public void addRate(@NotNull String from, @NotNull String to, int rate) {
        rates.put(new KeyObject(from, to), rate);
    }
}
