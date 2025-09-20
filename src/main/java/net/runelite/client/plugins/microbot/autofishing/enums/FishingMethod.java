package net.runelite.client.plugins.microbot.autofishing.enums;

import lombok.Getter;

import java.util.List;

@Getter
public enum FishingMethod {
    NET(List.of("Net", "Small net"), List.of("Small fishing net"), 1),
    BAIT(List.of("Bait"), List.of("Fishing rod", "Fishing bait"), 5),
    BIG_NET(List.of("Big net"), List.of("Big fishing net"), 16),
    LURE(List.of("Lure"), List.of("Fly fishing rod", "Feather"), 20),
    HARPOON(List.of("Harpoon"), List.of("Harpoon"), 35),
    CAGE(List.of("Cage"), List.of("Lobster pot"), 40),
    SANDWORMS(List.of("Sandworms", "Bait"), List.of("Fishing rod", "Sandworms"), 15),
    KARAMBWAN_VESSEL(List.of("Fish"), List.of("Karambwan vessel", "Raw karambwanji"), 65),
    BARBARIAN_ROD(List.of("Use-rod"), List.of("Barbarian rod"), 48),
    OILY_ROD(List.of("Bait"), List.of("Oily fishing rod", "Fishing bait"), 53),
    INFERNAL_EEL_BAIT(List.of("Bait"), List.of("Oily fishing rod", "Fishing bait", "Hammer"), 80),
    DARK_CRAB_BAIT(List.of("Cage"), List.of("Lobster pot", "Dark fishing bait"), 85),
    SACRED_EEL_BAIT(List.of("Bait"), List.of("Fishing rod", "Fishing bait", "Knife"), 87);

    private final List<String> actions;
    private final List<String> requiredItems;
    private final int levelRequired;

    FishingMethod(List<String> actions, List<String> requiredItems, int levelRequired) {
        this.actions = actions;
        this.requiredItems = requiredItems;
        this.levelRequired = levelRequired;
    }

}