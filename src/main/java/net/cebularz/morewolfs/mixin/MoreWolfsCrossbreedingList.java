package net.cebularz.morewolfs.mixin;

import javax.annotation.Nullable;
import java.util.List;

public class MoreWolfsCrossbreedingList {
    @Nullable
    public static final List<String> wolfVariants = List.of(
            "morewolfs:white_spotted",   // 0: WHITESPOTTED
            "morewolfs:mountain",         // 1: MOUNTAIN
            "morewolfs:golden",           // 2: GOLDEN
            "morewolfs:patch",            // 3: PATCH
            "morewolfs:fluffy",           // 4: FLUFFY
            "morewolfs:white_fluffy",     // 5: WHITE_FLUFFY
            "morewolfs:black_fluffy",     // 6: BLACK_FLUFFY
            "morewolfs:ginger",           // 7: GINGER
            "morewolfs:tricolor"          // 8: TRICOLOR
    );


    @Nullable
    public static final List<List<String>> WolfCrossbreedList = List.of(
            // Row 0: WHITESPOTTED
            List.of("", "", "", "", "", "", "", "", ""),

            // Row 1: MOUNTAIN
            List.of("", "", "morewolfs:patch", "", "", "", "", "", ""),

            // Row 2: GOLDEN
            List.of("", "morewolfs:patch", "", "", "", "", "", "", ""),

            // Row 3: PATCH
            List.of("", "", "", "", "", "", "", "", ""),

            // Row 4: FLUFFY
            List.of("", "", "", "", "", "", "", "", ""),

            // Row 5: WHITE_FLUFFY
            List.of("", "", "", "", "", "", "", "", ""),

            // Row 6: BLACK_FLUFFY
            List.of("", "", "", "", "", "", "", "", ""),

            // Row 7: GINGER
            List.of("", "", "", "", "", "", "", "", ""),

            // Row 8: TRICOLOR
            List.of("", "", "", "", "", "", "", "", "")
    );

}
