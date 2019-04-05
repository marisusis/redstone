package com.altarisnine.redstone.api.guard.flag;

public class FlagNotFoundException extends Exception {
    public FlagNotFoundException(String flagName) {
        super(String.format("Flag [%s] has not been registered or does not exist!", flagName));
    }
}
