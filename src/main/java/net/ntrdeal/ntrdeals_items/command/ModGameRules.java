package net.ntrdeal.ntrdeals_items.command;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleBuilder;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRuleCategory;
import net.ntrdeal.ntrdeals_items.NTRDealsItems;

public class ModGameRules {
    public static final GameRule<Integer> COMBAT_TICKS = GameRuleBuilder.forInteger(100).minValue(0).category(GameRuleCategory.PLAYER).buildAndRegister(NTRDealsItems.id("combat_ticks"));

    public static void register() {
    }
}
