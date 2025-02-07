package com.altarisnine.redstone.api.entity.living.player;

public final class PlayerNotFoundException extends Exception {
    public PlayerNotFoundException(String name) {
        super("The player \"" + name + "\" cannot be found!");
    }
}
