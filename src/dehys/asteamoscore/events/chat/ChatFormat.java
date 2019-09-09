package dehys.asteamoscore.events.chat;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatFormat {
    public ChatFormat(AsyncPlayerChatEvent e){
        String eventMessage = e.getMessage();
        Player eventPlayer = e.getPlayer();

        String format = ChatColor.GRAY+"<player>: "+ChatColor.WHITE+"<message>";
        format = format.replace("<player>", "%1$s");
        format = format.replace("<message>", "%2$s");
        e.setFormat(format);
    }
}