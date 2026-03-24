package net.ntrdeal.ntrdeals_items.command;

import net.fabricmc.fabric.api.command.v2.ArgumentTypeRegistry;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.commands.synchronization.SingletonArgumentInfo;
import net.ntrdeal.ntrdeals_items.NTRDealsItems;

public class ModCommands {
    public static void register() {
        ModGameRules.register();

        ArgumentTypeRegistry.registerArgumentType(
                NTRDealsItems.id("refresh_type"),
                RefreshInfusion.RefreshTypeArgument.class,
                SingletonArgumentInfo.contextFree(RefreshInfusion.RefreshTypeArgument::type)
        );

        CommandRegistrationCallback.EVENT.register((dispatcher, _, _) ->
                RefreshInfusion.register(dispatcher)
        );
    }
}
