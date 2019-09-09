package dehys.asteamoscore.events;

import dehys.asteamoscore.events.chat.ChatFormat;
import dehys.asteamoscore.events.miscellaneous.RocketBoost;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class EventManager implements Listener {
    @EventHandler
    public void playerInteract(PlayerInteractEvent e){
        new RocketBoost(e);
    }

    @EventHandler
    public void asyncPlayerChat(AsyncPlayerChatEvent e){
        new ChatFormat(e);
    }
}
