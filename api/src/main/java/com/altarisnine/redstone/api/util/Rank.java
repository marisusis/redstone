package com.altarisnine.redstone.api.util;

import lombok.Getter;

public enum Rank {
    ADMIN("admin", new DisplayPrefix(Color.RED, "[ADMIN] "), 6),
    SRMOD("srmod", new DisplayPrefix(Color.GOLD, "[SRMOD] "), 5),
    MOD("mod", new DisplayPrefix(Color.DARK_GREEN, "[MOD] "), 4),
    TRAINEE("trainee", new DisplayPrefix(Color.DARK_AQUA, "[TRAINEE] "), 3),
    BUILDER("builder", new DisplayPrefix(Color.DARK_PURPLE, "[BUILDER] "), 2),
    BETA("beta", new DisplayPrefix(Color.DARK_GRAY, "[BETA] "), 1),
    DEFAULT("default", new DisplayPrefix(Color.GRAY, ""), 0),
    ERROR("error", new DisplayPrefix(Color.GREEN, "[ERROR] "), 0);

    @Getter private final String groupName;
    @Getter private final DisplayPrefix prefix;
    @Getter private final int weight;

    Rank(String groupName, DisplayPrefix prefix, int weight) {
        this.groupName = groupName;
        this.prefix = prefix;
        this.weight = weight;
    }

    public static Rank getRankFromGroup(String group) {
        return Rank.valueOf(group.toUpperCase());
//        switch (group) {
//            case "admin":
//                return ADMIN;
//            case "srmod":
//                return SRMOD;
//            case "mod":
//                return MOD;
//            case "trainee":
//                return TRAINEE;
//            case "builder":
//                return BUILDER;
//            case "beta":
//                return BETA_TESTER;
//            case "default":
//                return DEFAULT;
//            default:
//                return ERROR;
//        }
    }

    public boolean isLowerThan(Rank rank) {
        return weight < rank.weight;
    }

    public boolean isHigherThan(Rank rank) {
        return weight > rank.weight;
    }

    public boolean isEqualTo(Rank rank) {
        return weight == rank.weight;
    }

    public boolean hasClearanceOf(Rank rank) {
        return weight >= rank.weight;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public DisplayPrefix getPrefix() {
        return this.prefix;
    }
}
