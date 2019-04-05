package com.altarisnine.networkcore.api.test.util;

import com.altarisnine.networkcore.api.configuration.ConfigurationSection;
import com.altarisnine.networkcore.api.configuration.serialization.ConfigSerialization;
import com.altarisnine.networkcore.api.guard.spatial.vector.VectorI3;
import com.altarisnine.networkcore.api.util.Vector;
import com.altarisnine.networkcore.api.world.Location;
import com.altarisnine.networkcore.api.world.World;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;

import static org.mockito.Mockito.mock;

@PrepareForTest(World.class)
public class LocationTest {

    @Test
    public void testLocationToVector() {
        World mockWorld = mock(World.class);
        Location location = new Location(mockWorld, 1.0, 2.0, 3.0);

        Assertions.assertEquals(new Vector(1.0, 2.0, 3.0), location.toVector());
    }

    @Test
    public void testLocationSerialize() {
        World mockWorld = mock(World.class);
        Mockito.when(mockWorld.getName()).thenReturn("TESTworld");
        Location location = new Location(mockWorld, 1.0, 2.0, 3.0);

        System.out.println(location.serialize().getString("world"));
    }

    @Test
    public void testBlockVector3() {
        VectorI3 v = new VectorI3(1, 3, 4);
        ConfigurationSection s = v.serialize();

        System.out.println(s.getInteger("x"));

        ConfigurationSection cs = ConfigurationSection.createBlank();
        cs.set("x", 3);
        cs.set("y", 4);
        cs.set("z", 5);

        final VectorI3 vectorI3 = ConfigSerialization.deserializeWithMethod(cs, VectorI3.class);

        System.out.println(vectorI3);
    }

}
