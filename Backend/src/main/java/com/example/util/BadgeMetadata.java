package com.example.util;

import com.example.entity.*;
import java.util.Map;

public class BadgeMetadata {

    public static class BadgeInfo {
        public final String title;
        public final String description;
        public final String iconName;

        public BadgeInfo(String title, String description, String iconName) {
            this.title = title;
            this.description = description;
            this.iconName = iconName;
        }
    }

    private static final Map<BadgeType, BadgeInfo> metadata = Map.ofEntries(
        Map.entry(BadgeType.STREAK_10, new BadgeInfo("Rising Flame", "You’ve maintained a 10-day streak. Consistency sparks transformation.", "flame_10.png")),
        Map.entry(BadgeType.STREAK_50, new BadgeInfo("Burning Commitment", "50 days of unwavering dedication to yourself.", "flame_50.png")),
        Map.entry(BadgeType.STREAK_100, new BadgeInfo("Eternal Ember", "100 days — your light never fades.", "flame_100.png")),
        Map.entry(BadgeType.FIRST_LOGIN, new BadgeInfo("Dawn Walker", "Your journey has begun.", "first_login.png")),
        Map.entry(BadgeType.FIRST_CHECKIN, new BadgeInfo("Soul Step", "You’ve taken your first mindful step.", "checkin.png")),
        Map.entry(BadgeType.SELF_CARE_5, new BadgeInfo("Seedling", "Planted your first five acts of self-kindness.", "leaf_5.png")),
        Map.entry(BadgeType.SELF_CARE_10, new BadgeInfo("Grove", "Nurtured growth through 10 self-care actions.", "leaf_10.png")),
        Map.entry(BadgeType.SELF_CARE_30, new BadgeInfo("Blossom", "A blooming dedication to your well-being.", "flower_30.png")),
        Map.entry(BadgeType.SELF_CARE_50, new BadgeInfo("Sanctuary", "You've built a safe space through 50 self-care moments.", "tree_50.png")),
        Map.entry(BadgeType.SELF_CARE_75, new BadgeInfo("Phoenix", "Resilience reborn through 75 self-care rituals.", "phoenix_75.png")),
        Map.entry(BadgeType.SELF_CARE_100, new BadgeInfo("Harmony", "A perfect symphony of 100 mindful actions.", "lotus_100.png"))
    );

    public static BadgeInfo get(BadgeType badgeType) {
        return metadata.get(badgeType);
    }
}
