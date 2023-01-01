package reyd.Command;

import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;
import reyd.SuperBanMain;

public class SuperBanCMD extends Command {

    public SuperBanCMD(){
        super("sban", SuperBanMain.getInstance().getConfig().getString("sban.descriptionCMD"));
        this.commandParameters.put("player", new CommandParameter[]{
                CommandParameter.newType("player", CommandParamType.TARGET),
                CommandParameter.newType("reason", CommandParamType.STRING),
        });
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {

        if (args.length != 2){
            commandSender.sendMessage(SuperBanMain.getInstance().getConfig().getString("sban.usageCMD"));
            return true;
        }

        if (Server.getInstance().getPlayer(args[0]) != null){
            // ToDo
        } else {
            // ToDo
        }

        return true;
    }
}
