package com.altarisnine.networkcore.api.entity;

import com.altarisnine.networkcore.api.util.Identifiable;
import com.altarisnine.networkcore.api.util.Vector;
import com.altarisnine.networkcore.api.world.Location;

/**
 * This is the base of every Entity.
 */
public interface Entity extends Identifiable {

    /**
     * Sets the entity's display name. This may not always work as intended, especially for entities such as {@link com.altarisnine.networkcore.api.entity.living.player.Player}
     *
     * @param displayName the display name
     */
    void setDisplayName(String displayName);

    /**
     * Gets the entity's display name. The display name is what is displayed in chat, tablist, log, etc...
     *
     * @return the display name
     */
    String getDisplayName();

    /**
     * Gets the entity's current location
     *
     * @return the location
     */
    Location getLocation();

    /**
     * Teleports the entity to the specified location. Take note that the teleport may be rejected by region restrictions.
     *
     * @param to the to
     */
    void teleport(Location to);

    void setVelocity(Vector velocity);
    Vector getVelocity();
    boolean isOnGround();
    void remove();
    boolean isDead();
    boolean isValid();
    float getFallDistance();
    void setFallDistance(float distance);
    boolean isInsideVehicle();
    boolean leaveVehicle();
    Entity getVehicle();
    void setCustomNameVisible(boolean flag);
    boolean isCustomNameVisible();
    void setGlowing(boolean flag);
    boolean isGlowing();
    void setInvulnerable(boolean flag);
    boolean isInvulnerable();
    boolean isSilent();
    void setSilent(boolean flag);
    boolean hasGravity();
    void setGravity(boolean gravity);


}
