package reyd;

import cn.nukkit.plugin.PluginBase;

public class SuperBanMain extends PluginBase {

    private static SuperBanMain instance;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    public static SuperBanMain getInstance() {
        return instance;
    }
}
