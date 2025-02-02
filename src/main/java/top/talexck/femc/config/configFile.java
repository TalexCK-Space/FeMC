package top.talexck.femc.config;

import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import dev.isxander.yacl3.platform.YACLPlatform;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import com.google.gson.*;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

import static top.talexck.femc.Version.*;
import static top.talexck.femc.utils.ChatUtils.SendMessageinChat;

public class configFile {
public static Screen configgui(Screen parent) {
    Consumer testChatUitls = fe -> SendMessageinChat(chatutilsDebugMessage);
    var yacl = YetAnotherConfigLib.createBuilder()
    .title(Text.of("FeMC "+version_fe+"+"+version_mc))
    .category(ConfigCategory.createBuilder()
            .name(Text.of("General"))
            .tooltip(Text.of("General config for the mod."))
            .group(OptionGroup.createBuilder()
                    .name(Text.of("Above All"))
                    .description(OptionDescription.of(Text.of("Most important settings")))
                    .option(Option.<Boolean>createBuilder()
                            .name(Text.of("If Enable"))
                            .description(OptionDescription.of(Text.of("Enable the mod or not.")))
                            .binding(true, () -> ifenabled, newVal -> ifenabled = newVal)
                            .controller(TickBoxControllerBuilder::create)
                            .build())
                    .build())
            .build())
    .category(ConfigCategory.createBuilder()
            .name(Text.of("Chat"))
            .tooltip(Text.of("Messages and more"))
            .group(OptionGroup.createBuilder()
                    .name(Text.of("Visual moudle"))
                    .description(OptionDescription.of(Text.of("Message Visual")))
                    .option(Option.<Boolean>createBuilder()
                            .name(Text.of("Enable Mod Message Color"))
                            .binding(true, () -> ifcoloredmodmessage, newVal -> ifcoloredmodmessage = newVal)
                            .controller(TickBoxControllerBuilder::create)
                            .build())
                    .build())
            .build())
    .category(ConfigCategory.createBuilder()
        .name(Text.of("Develop"))
        .tooltip(Text.of("Options During Development"))
            .group(OptionGroup.createBuilder()
                    .name(Text.of("Above All"))
                    .description(OptionDescription.of(Text.of("Warning: This may break your game!")))
                    .option(Option.<Boolean>createBuilder()
                            .name(Text.of("§4Developing Mode Enable"))
                            .description(OptionDescription.of(Text.of("Only turn on when developing")))
                            .binding(true, () -> ifdev, newVal -> ifdev = newVal)
                            .controller(TickBoxControllerBuilder::create)
                            .build())
                    .build())
            .group(OptionGroup.createBuilder()
                    .name(Text.of("Debug"))
                    .description(OptionDescription.of(Text.of("Debug Options. We will keep all.")))
                    .option(Option.<Boolean>createBuilder()
                            .name(Text.of("Debug Enable"))
                            .description(OptionDescription.of(Text.of("Enable the mod debug or not")))
                            .binding(true, () -> ifdebuging, newVal -> ifdebuging = newVal)
                            .controller(TickBoxControllerBuilder::create)
                            .build())
                    .option(ButtonOption.<Boolean>createBuilder()
                            .name(Text.of("ChatUtils Debug"))
                            .description(OptionDescription.of(Text.of("Send a chatutils message in chat.")))
                            .action(testChatUitls)
                            .build())
                    .build())
        .build());
    return yacl.save(configFile::saveconfig).build().generateScreen(parent);
    }

    @SerialEntry
    public static boolean ifenabled = true;
    @SerialEntry
    public static boolean ifcoloredmodmessage = true;
    @SerialEntry
    public static boolean ifdebuging = false;
    @SerialEntry
    public static boolean ifdev = false;

    public static void saveconfig(){
        configFile.HANDLER.save();;
    }

    public static ConfigClassHandler<configFile> HANDLER = ConfigClassHandler.createBuilder(configFile.class)
            .serializer(config -> GsonConfigSerializerBuilder.create(config)
                    .setPath(YACLPlatform.getConfigDir().resolve("femc.json5"))
                    .appendGsonBuilder(GsonBuilder::setPrettyPrinting) // not needed, pretty print by default
                    .setJson5(true)
                    .build())
            .build();
}