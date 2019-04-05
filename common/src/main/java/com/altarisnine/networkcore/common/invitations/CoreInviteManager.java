package com.altarisnine.networkcore.common.invitations;

import com.altarisnine.networkcore.api.invitations.Invitable;
import com.altarisnine.networkcore.api.invitations.Invite;
import com.altarisnine.networkcore.api.invitations.InviteManager;
import com.altarisnine.networkcore.common.NetworkCore;

public class CoreInviteManager implements InviteManager {

    private final NetworkCore core;

    public CoreInviteManager(NetworkCore instance) {
        this.core = instance;
    }

    @Override
    public Invite getInviteBySender(Invitable sender) {
        return null;
    }

    @Override
    public Invite getInviteByInvited(Invitable invited) {
        return null;
    }

    @Override
    public void sendInvite(Invite invite) {

    }

    @Override
    public void acceptInvite(Class<? extends Invite> type, Invitable invited, Invitable sender) {

    }

    @Override
    public void denyInivte(Class<? extends Invite> type, Invitable invited, Invitable sender) {

    }
}
