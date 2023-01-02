package reyd;

import cn.nukkit.plugin.PluginBase;
import reyd.Command.SuperBanCMD;
import reyd.Command.SuperPardonCMD;
import reyd.Listener.LoginListener;

public class SuperBanMain extends PluginBase {

    private static SuperBanMain instance;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {

        // config
        this.saveDefaultConfig();

        // register
        getServer().getPluginManager().registerEvents(new LoginListener(), this); // listener
        getServer().getCommandMap().register("help", new SuperBanCMD()); // command
        getServer().getCommandMap().register("help", new SuperPardonCMD()); // command

        // Standard
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
