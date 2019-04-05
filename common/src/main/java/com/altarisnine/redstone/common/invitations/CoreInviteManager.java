package com.altarisnine.redstone.common.invitations;

import com.altarisnine.redstone.api.invitations.Invitable;
import com.altarisnine.redstone.api.invitations.Invite;
import com.altarisnine.redstone.api.invitations.InviteManager;
import com.altarisnine.redstone.common.RedstoneCore;

public class CoreInviteManager implements InviteManager {

    private final RedstoneCore core;

    public CoreInviteManager(RedstoneCore instance) {
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
