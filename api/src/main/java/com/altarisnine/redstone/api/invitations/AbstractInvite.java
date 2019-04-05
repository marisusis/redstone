package com.altarisnine.redstone.api.invitations;

import lombok.Getter;

public abstract class AbstractInvite implements Invitable {
    @Getter private final Invitable sender;
    @Getter private final Invitable invited;

    public AbstractInvite(Invitable sender, Invitable invited) {
        this.sender = sender;
        this.invited = invited;
    }

}
