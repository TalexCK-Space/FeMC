package top.talexck.femc.command.subcommands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;

import static top.talexck.femc.Version.version_fe;
import static top.talexck.femc.Version.version_mc;
import static top.talexck.femc.utils.ChatUtils.SendMessageinChat;


public class Help {
    public static LiteralArgumentBuilder<FabricClientCommandSource> register(LiteralArgumentBuilder<FabricClientCommandSource> command) {
        command
                .then(ClientCommandManager.literal("help")
                        .executes(ctx -> {
                            SendMessageinChat("&6This is FeMC mod version "+version_fe+" with Minecraft "+version_mc+"\n&9Author:TalexCK\n" +
                                    "&c/fe &bhelp &f: &bSend this help message.");
                            return 1;
                        }));
        return command;
    }
}
