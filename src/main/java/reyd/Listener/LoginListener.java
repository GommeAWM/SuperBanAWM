package reyd.Listener;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerPreLoginEvent;

public class LoginListener implements Listener {

    @EventHandler
    public void onPlayerPreLogin(PlayerPreLoginEvent event){
        Player player = event.getPlayer();



    }

}
