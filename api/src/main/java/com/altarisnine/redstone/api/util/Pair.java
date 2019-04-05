package com.altarisnine.redstone.api.util;

import lombok.Getter;
import lombok.Setter;

public class Pair<T, U> {
    @Getter @Setter private T A;
    @Getter @Setter private U B;

    public Pair(T a, U b) {
        A = a;
        B = b;
    }
}
