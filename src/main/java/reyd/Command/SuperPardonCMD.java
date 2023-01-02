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
import java.util.ArrayList;
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

        // Permission Check
        if (!commandSender.hasPermission("reyd.spardon")){
            return true;
        }

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

                // List clientID
                ArrayList<Long> clientid_list = (ArrayList<Long>) bancollection_list.getLongList(name + ".ClientID");

                // List DeviceID
                ArrayList<String> deviceid_list = (ArrayList<String>) bancollection_list.getStringList(name + ".DeviceID");

                // List UUID
                ArrayList<String> uuidArrayList = (ArrayList<String>) bancollection_list.getStringList(name + ".UUID");

                // List XUID
                ArrayList<String> xuid_list = (ArrayList<String>) bancollection_list.getStringList(name + ".XUID");

                // Iterate Config
                for (Map.Entry<String, Object> entry1 : all.entrySet()) {
                    String data = entry1.getKey();

                    // List clientID of target
                    ArrayList<Long> clientid_listTarget = (ArrayList<Long>) bancollection_list.getLongList(data + ".ClientID");

                    // Iterate client ID list
                    for (long value : clientid_list){
                        if (clientid_listTarget.contains(value)){

                            // remove data
                            bancollection_list.set(data, null);
                            bancollection_list.save();
                            bancollection_list.remove(data);
                            bancollection_list.save();

                        }
                    }

                    // List DeviceID
                    ArrayList<String> deviceid_listTarget = (ArrayList<String>) bancollection_list.getStringList(data + ".DeviceID");

                    // Iterate client ID list
                    for (String value : deviceid_list){
                        if (deviceid_listTarget.contains(value)){

                            // remove data
                            bancollection_list.set(data, null);
                            bancollection_list.save();
                            bancollection_list.remove(data);
                            bancollection_list.save();

                        }
                    }

                    // List UUID
                    ArrayList<String> uuidArrayListTarget = (ArrayList<String>) bancollection_list.getStringList(data + ".UUID");

                    // Iterate client ID list
                    for (String value : uuidArrayList){
                        if (uuidArrayListTarget.contains(value)){

                            // remove data
                            bancollection_list.set(data, null);
                            bancollection_list.save();
                            bancollection_list.remove(data);
                            bancollection_list.save();

                        }
                    }

                    // List XUID
                    ArrayList<String> xuid_listTarget = (ArrayList<String>) bancollection_list.getStringList(data + ".XUID");

                    // Iterate client ID list
                    for (String value : xuid_list){
                        if (xuid_listTarget.contains(value)){

                            // remove data
                            bancollection_list.set(data, null);
                            bancollection_list.save();
                            bancollection_list.remove(data);
                            bancollection_list.save();

                        }
                    }


                }

                // remove data
                bancollection_list.set(get1, null);
                bancollection_list.save();
                bancollection_list.remove(get1);
                bancollection_list.save();

                // set status success (need for sendMessage)
                status = true;

                // message (From Config)
                commandSender.sendMessage(SuperBanMain.getInstance().getConfig().getString("sban.pardonMessage").replace("%tg%", name));
                break;
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
