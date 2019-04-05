package com.altarisnine.networkcore.api.guard.region;

import com.altarisnine.networkcore.api.guard.flag.PermissibleFlag;
import com.altarisnine.networkcore.api.guard.session.Session;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class RegionSet extends HashSet<Region> {

    public RegionSet() {
    }

    public RegionSet(@NotNull Collection<? extends Region> c) {
        super(c);
    }

    public boolean testState(Session target, PermissibleFlag flag) {
        Iterator<Region> iterator = this.iterator();
        while (this.iterator().hasNext()) { // TODO loops only once, should return based on hierarchy of regions, child regions, etc.
            Region region = iterator.next();
            return region.testFlagFor(target.getPlayer(), flag);
        }
        return true;
    }
}
