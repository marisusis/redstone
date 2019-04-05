package com.altarisnine.redstone.api.invitations;

public interface InviteManager {

    Invite getInviteBySender(Invitable sender);
    Invite getInviteByInvited(Invitable invited);

    void sendInvite(Invite invite);
    void acceptInvite(Class<? extends Invite> type, Invitable invited, Invitable sender);
    void denyInivte(Class<? extends Invite> type, Invitable invited, Invitable sender);
}
