package com.altarisnine.networkcore.api.util;

public interface Permissable {
    Rank getRank();
    boolean hasClearanceWith(Rank rank);
}
