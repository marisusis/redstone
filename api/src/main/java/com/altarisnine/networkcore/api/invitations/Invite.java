package com.altarisnine.networkcore.api.invitations;

public interface Invite {
    Invitable getSender();
    Invitable getInvited();

    void onAccept();
    void onDeny();
    void onTimeout();
    short getTimeout();
}
