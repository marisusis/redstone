package com.altarisnine.redstone.api.util;

public interface Permissable {
    Rank getRank();
    boolean hasClearanceWith(Rank rank);
}
