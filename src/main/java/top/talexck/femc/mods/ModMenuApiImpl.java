package top.talexck.femc.mods;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.minecraft.client.gui.screen.GameMenuScreen;
import top.talexck.femc.config.configFile;

import static top.talexck.femc.config.configFile.configgui;

public class ModMenuApiImpl implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parentScreen -> configgui(parentScreen);
    }

}