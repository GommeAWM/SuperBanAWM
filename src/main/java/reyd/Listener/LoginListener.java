package reyd.Listener;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerPreLoginEvent;
import cn.nukkit.utils.Config;
import reyd.SuperBanMain;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

public class LoginListener implements Listener {

    @EventHandler
    public void onPlayerPreLogin(PlayerPreLoginEvent event){

        Config bancollection_list = new Config(new File(SuperBanMain.getInstance().getDataFolder(), "/bandata.yml"), Config.YAML);
        bancollection_list.reload();

        /*
        *
        * GET DATA
        *
        * */
        // player
        Player target = event.getPlayer();

        // data
        Long clientId = target.getLoginChainData().getClientId();
        String DeviceId = target.getLoginChainData().getDeviceId();
        UUID SUUID = target.getLoginChainData().getClientUUID();

        String UUID = String.valueOf(SUUID);

        String XUID = target.getLoginChainData().getXUID();
        String name = target.getName();

        // map config
        Map<String, Object> all = bancollection_list.getAll();

        // iterate
        for (Map.Entry<String, Object> entry : all.entrySet()) {
            String get1 = entry.getKey();


            if (all.containsKey(name)){
                if (get1.equals(name)){

                    // List clientID
                    ArrayList<Long> clientid_list = (ArrayList<Long>) bancollection_list.getLongList(name + ".ClientID");

                    if (!clientid_list.contains(clientId)){
                        clientid_list.add(clientId);
                        bancollection_list.set(name + ".ClientID", clientid_list);
                        bancollection_list.save();
                    }

                    // List DeviceID
                    ArrayList<String> deviceid_list = (ArrayList<String>) bancollection_list.getStringList(name + ".DeviceID");

                    if (!deviceid_list.contains(DeviceId)){
                        deviceid_list.add(DeviceId);
                        bancollection_list.set(name + ".DeviceID", deviceid_list);
                        bancollection_list.save();
                    }

                    // List UUID
                    ArrayList<String> uuidArrayList = (ArrayList<String>) bancollection_list.getStringList(name + ".UUID");

                    if (!uuidArrayList.contains(UUID)){
                        uuidArrayList.add(UUID);
                        bancollection_list.set(name + ".UUID", uuidArrayList);
                        bancollection_list.save();
                    }

                    // List XUID
                    ArrayList<String> xuid_list = (ArrayList<String>) bancollection_list.getStringList(name + ".XUID");

                    if (!xuid_list.contains(XUID)){
                        xuid_list.add(XUID);
                        bancollection_list.set(name + ".XUID", xuid_list);
                        bancollection_list.save();
                    }

                    String reason = bancollection_list.getString(name + ".Reason");

                    event.setKickMessage(reason);
                    event.setCancelled(true);
                }
            } else {

                // FOR BAN
                ArrayList<String> deviceid_list_now = (ArrayList<String>) bancollection_list.getStringList(get1 + ".DeviceID");
                ArrayList<String> uuidArrayList_now = (ArrayList<String>) bancollection_list.getStringList(get1 + ".UUID");
                ArrayList<String> xuid_list_now = (ArrayList<String>) bancollection_list.getStringList(get1 + ".XUID");

                // ClientID
                ArrayList<Long> clientid_list = (ArrayList<Long>) bancollection_list.getLongList(get1 + ".ClientID");

                if (clientid_list.contains(clientId)){

                    String reason = bancollection_list.getString(get1 + ".Reason");

                    bancollection_list.set(name + ".ClientID", clientid_list);

                    if (!deviceid_list_now.contains(DeviceId)){
                        deviceid_list_now.add(DeviceId);
                        bancollection_list.set(name + ".DeviceID", deviceid_list_now);
                    } else {
                        bancollection_list.set(name + ".DeviceID", deviceid_list_now);
                    }

                    if (!uuidArrayList_now.contains(UUID)){
                        uuidArrayList_now.add(UUID);
                        bancollection_list.set(name + ".UUID", uuidArrayList_now);
                    } else {
                        bancollection_list.set(name + ".UUID", uuidArrayList_now);
                    }

                    if (!xuid_list_now.contains(XUID)){
                        xuid_list_now.add(XUID);
                        bancollection_list.set(name + ".XUID", xuid_list_now);
                    } else {
                        bancollection_list.set(name + ".XUID", xuid_list_now);
                    }

                    bancollection_list.set(name + ".Reason", reason);
                    bancollection_list.save();
                    event.setKickMessage(reason);
                    event.setCancelled(true);
                    return;
                }

                // List DeviceID
                ArrayList<String> deviceid_list = (ArrayList<String>) bancollection_list.getStringList(get1 + ".DeviceID");

                if (deviceid_list.contains(DeviceId)){

                    String reason = bancollection_list.getString(get1 + ".Reason");


                    if (!clientid_list.contains(clientId)){
                        clientid_list.add(clientId);
                        bancollection_list.set(name + ".ClientID", clientid_list);
                    } else {
                        bancollection_list.set(name + ".DeviceID", clientid_list);
                    }

                    bancollection_list.set(name + ".DeviceID", deviceid_list);

                    if (!uuidArrayList_now.contains(UUID)){
                        uuidArrayList_now.add(UUID);
                        bancollection_list.set(name + ".UUID", uuidArrayList_now);
                    } else {
                        bancollection_list.set(name + ".UUID", uuidArrayList_now);
                    }

                    if (!xuid_list_now.contains(XUID)){
                        xuid_list_now.add(XUID);
                        bancollection_list.set(name + ".XUID", xuid_list_now);
                    } else {
                        bancollection_list.set(name + ".XUID", xuid_list_now);
                    }



                    bancollection_list.set(name + ".Reason", reason);

                    bancollection_list.save();
                    event.setKickMessage(reason);
                    event.setCancelled(true);
                    return;
                }

                // List UUID
                ArrayList<String> uuidArrayList = (ArrayList<String>) bancollection_list.getStringList(get1 + ".UUID");

                if (uuidArrayList.contains(UUID)){

                    String reason = bancollection_list.getString(get1 + ".Reason");

                    if (!clientid_list.contains(clientId)){
                        clientid_list.add(clientId);
                        bancollection_list.set(name + ".ClientID", clientid_list);
                    } else {
                        bancollection_list.set(name + ".DeviceID", clientid_list);
                    }

                    if (!deviceid_list_now.contains(DeviceId)){
                        deviceid_list_now.add(DeviceId);
                        bancollection_list.set(name + ".DeviceID", deviceid_list_now);
                    } else {
                        bancollection_list.set(name + ".DeviceID", deviceid_list_now);
                    }

                    bancollection_list.set(name + ".UUID", uuidArrayList);

                    if (!xuid_list_now.contains(XUID)){
                        xuid_list_now.add(XUID);
                        bancollection_list.set(name + ".XUID", xuid_list_now);
                    } else {
                        bancollection_list.set(name + ".XUID", xuid_list_now);
                    }



                    bancollection_list.set(name + ".Reason", reason);
                    bancollection_list.save();
                    event.setKickMessage(reason);
                    event.setCancelled(true);
                    return;
                }

                // List XUID
                ArrayList<String> xuid_list = (ArrayList<String>) bancollection_list.getStringList(get1 + ".XUID");

                if (xuid_list.contains(XUID)){

                    String reason = bancollection_list.getString(get1 + ".Reason");

                    if (!clientid_list.contains(clientId)){
                        clientid_list.add(clientId);
                        bancollection_list.set(name + ".ClientID", clientid_list);
                    } else {
                        bancollection_list.set(name + ".DeviceID", clientid_list);
                    }

                    if (!deviceid_list_now.contains(DeviceId)){
                        deviceid_list_now.add(DeviceId);
                        bancollection_list.set(name + ".DeviceID", deviceid_list_now);
                    } else {
                        bancollection_list.set(name + ".DeviceID", deviceid_list_now);
                    }

                    if (!uuidArrayList_now.contains(UUID)){
                        uuidArrayList_now.add(UUID);
                        bancollection_list.set(name + ".UUID", uuidArrayList_now);
                    } else {
                        bancollection_list.set(name + ".UUID", uuidArrayList_now);
                    }

                    bancollection_list.set(name + ".XUID", xuid_list);

                    bancollection_list.set(name + ".Reason", reason);
                    bancollection_list.save();
                    event.setKickMessage(reason);
                    event.setCancelled(true);
                    return;

                }

            }

        }

    }

}
