package fr.girlstream.manhunts.listeners;

import fr.girlstream.manhunts.main;
import fr.girlstream.manhunts.managers.GameState;
import fr.girlstream.manhunts.managers.lang.Lang;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerListPing implements Listener {

    @EventHandler
    public void onServerListPing(ServerListPingEvent e){
        if(GameState.isState(GameState.LOBBY)){
            e.setMotd(Lang.SERVER_MOTD_LOBBY.get());
        } else {
            e.setMotd(Lang.SERVER_MOTD_INGAME.get());
        }
    }
}
