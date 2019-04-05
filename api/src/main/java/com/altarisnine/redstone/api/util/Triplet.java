package com.altarisnine.redstone.api.util;

import lombok.Getter;
import lombok.Setter;

public class Triplet<T, U, V> {
    @Getter @Setter private T A;
    @Getter @Setter private U B;
    @Getter @Setter private V C;

    public Triplet(T a, U b, V c) {
        A = a;
        B = b;
        C = c;
    }
}
