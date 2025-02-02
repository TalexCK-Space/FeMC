package top.talexck.femc.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.command.CommandRegistryAccess;
import top.talexck.femc.command.subcommands.Help;

public class FeMain {
    public static void register (CommandDispatcher<FabricClientCommandSource> dispatcher, CommandRegistryAccess registry) {
        LiteralArgumentBuilder<FabricClientCommandSource> command = ClientCommandManager.literal("fe");
        command = Help.register(command);
        dispatcher.register(command);

    }
}
