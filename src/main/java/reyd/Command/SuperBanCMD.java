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
import java.util.UUID;

public class SuperBanCMD extends Command {

    public SuperBanCMD(){
        super("sban", SuperBanMain.getInstance().getConfig().getString("sban.sbanDescrCMD"));
        this.commandParameters.clear();
        this.commandParameters.put("players", new CommandParameter[]{
                CommandParameter.newType("player", CommandParamType.TARGET),
                CommandParameter.newType("reason", CommandParamType.STRING),
        });
        this.setPermission("reyd.sban.ban");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {

        if (args.length != 2){
            commandSender.sendMessage(SuperBanMain.getInstance().getConfig().getString("sban.sbanUsageCMD"));
            return true;
        }

        if (Server.getInstance().getPlayer(args[0]) != null){

            // get Target from Arguments
            Player player = Server.getInstance().getPlayer(args[0]);

            // ban
            banProcess(player, commandSender, args[1]);

            // message
            commandSender.sendMessage(SuperBanMain.getInstance().getConfig().getString("sban.banMessage").replace("%p%", args[0]));

        } else {
            commandSender.sendMessage(SuperBanMain.getInstance().getConfig().getString("sban.playerNotFound").replace("%tg%", args[0]));
        }

        return true;
    }

    private void banProcess(Player target, CommandSender Admin, String reasonMSG){

        // Reason Message
        String reason = SuperBanMain.getInstance().getConfig().getString("sban.reasonBan").replace("%reason%", reasonMSG).replace("%tg%", target.getName()).replace("%p%", Admin.getName());

        // Create Config
        Config bancollection_list = new Config(new File(SuperBanMain.getInstance().getDataFolder(), "/bandata.yml"), Config.YAML);
        bancollection_list.reload();

        // Gain DATA
        Long clientId = target.getLoginChainData().getClientId();
        String DeviceId = target.getLoginChainData().getDeviceId();
        UUID SUUID = target.getLoginChainData().getClientUUID();

        String UUID = String.valueOf(SUUID);

        String XUID = target.getLoginChainData().getXUID();
        String name = target.getName();

        // List clientID
        ArrayList<Long> clientid_list = new ArrayList<>();
        clientid_list.add(clientId);

        // List DeviceID
        ArrayList<String> deviceid_list = new ArrayList<>();
        deviceid_list.add(DeviceId);

        // List UUID
        ArrayList<String> uuidArrayList = new ArrayList<>();
        uuidArrayList.add(UUID);

        // List XUID
        ArrayList<String> xuid_list = new ArrayList<>();
        xuid_list.add(XUID);

        // Put Data in Config

        bancollection_list.set(name + ".ClientID", clientid_list);
        bancollection_list.set(name + ".DeviceID", deviceid_list);
        bancollection_list.set(name + ".UUID", uuidArrayList);
        bancollection_list.set(name + ".XUID", xuid_list );
        bancollection_list.set(name + ".Reason", reason);
        bancollection_list.save();

        // Ban Target
        target.kick(reason);

        // Message
        for (Player helper : Server.getInstance().getOnlinePlayers().values()) {
            if (helper.hasPermission("r.sban.ntfc")) {
                helper.sendMessage(SuperBanMain.getInstance().getConfig().getString("sban.notification").replace("%p%", target.getName()).replace("%p%", Admin.getName()).replace("%reason%", reasonMSG));
            }
        }

        // ToDo Уведомление

    }

}
