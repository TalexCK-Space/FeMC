package top.talexck.femc;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.talexck.femc.command.FeMain;
import top.talexck.femc.config.configFile;

import static net.minecraft.text.Text.literal;
import static top.talexck.femc.config.configFile.configgui;


public class FeMC implements ModInitializer{

    public static final MinecraftClient MC = MinecraftClient.getInstance();

    public static KeyBinding menuKey;
    public static final Logger LOGGER = LoggerFactory.getLogger("femc");
    @Override
    public void onInitialize() {
        LOGGER.info("FeMC is loading...");
        configFile.HANDLER.load();
        ClientLifecycleEvents.CLIENT_STOPPING.register(client -> {
            configFile.HANDLER.save();
        });
        menuKey = new KeyBinding("菜单", InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_SHIFT, "FeMC");
        KeyBindingHelper.registerKeyBinding(menuKey);
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (menuKey.wasPressed()) {
                configgui(new GameMenuScreen(true));
            }
        });
        ClientCommandRegistrationCallback.EVENT.register(FeMain::register);
    }





}
