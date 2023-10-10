package com.asefactory.getapplicationlasttimeusage.trytest;

import java.util.Objects;

public class KeyObject {
    String from;
    String to;

    public KeyObject(String from, String to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeyObject keyObject = (KeyObject) o;
        return from.equals(keyObject.from) && to.equals(keyObject.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }
}
