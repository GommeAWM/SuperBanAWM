package reyd.Command;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.Config;
import reyd.SuperBanMain;

import java.io.File;
import java.util.List;
import java.util.Map;

public class SuperBansListCMD extends Command {

    public SuperBansListCMD(){
        super("sbanlist", SuperBanMain.getInstance().getConfig().getString("sban.sbanlistDescrCMD"));
        this.setPermission("reyd.sbanlist");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {

        // Permission Check
        if (!commandSender.hasPermission("reyd.sbanlist")){
            return true;
        }

        // Config Stuff
        Config bancollection_list = new Config(new File(SuperBanMain.getInstance().getDataFolder(), "/bandata.yml"), Config.YAML);
        bancollection_list.reload();

        // Map Config
        Map<String, Object> all = bancollection_list.getAll();

        // Iterate
        for (Map.Entry<String, Object> entry : all.entrySet()) {
            String get1 = entry.getKey();

            String reason = bancollection_list.getString(get1 + ".Reason");
            commandSender.sendMessage(get1 + " §d-> " + reason);

        }

        return true;
    }
}
