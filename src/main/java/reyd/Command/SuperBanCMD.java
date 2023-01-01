package reyd.Command;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;

public class SuperBanCMD extends Command {

    public SuperBanCMD(){
        super("sban");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {

        if (args.length != 2){
            commandSender.sendMessage("§cUsage: §7/sban <player> <reason>");
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
