package reyd.Command;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.utils.Config;
import reyd.SuperBanMain;

import java.io.File;
import java.util.Map;

public class SuperPardonCMD extends Command {

    public SuperPardonCMD(){
        super("spardon", SuperBanMain.getInstance().getConfig().getString("sban.spardonDescrCMD"));
        this.commandParameters.clear();
        this.commandParameters.put("players", new CommandParameter[]{
                CommandParameter.newType("player", CommandParamType.STRING),
        });
        this.setPermission("reyd.sban.pardon");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {

        // check arguments length
        if (args.length != 1){
            commandSender.sendMessage(SuperBanMain.getInstance().getConfig().getString("sban.spardonUsageCMD"));
            return true;
        }

        // take arguments (player nickname)
        String name = args[0];

        // Config Stuff
        Config bancollection_list = new Config(new File(SuperBanMain.getInstance().getDataFolder(), "/bandata.yml"), Config.YAML);
        bancollection_list.reload();

        // Map Config
        Map<String, Object> all = bancollection_list.getAll();

        // for SendMessage
        boolean status = false;

        // Iterate
        for (Map.Entry<String, Object> entry : all.entrySet()) {
            String get1 = entry.getKey();

            // compare
            if (get1.equals(name)){

                // remove data
                bancollection_list.set(get1, null);
                bancollection_list.save();
                bancollection_list.remove(get1);
                bancollection_list.save();

                // set status success (need for sendMessage)
                status = true;

                // message (From Config)
                commandSender.sendMessage(SuperBanMain.getInstance().getConfig().getString("sban.pardonMessage").replace("%tg%", name));
            }

        }

        // Check Status
        if (!status){

            // message (From Config)
            commandSender.sendMessage(SuperBanMain.getInstance().getConfig().getString("sban.playerNotFound").replace("%tg%", name));
        }

        return true;
    }
}
