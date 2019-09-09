package dehys.asteamoscore.exceptions;

import org.bukkit.ChatColor;

public interface InvalidSender {
    String FAULT_MESSAGE = ChatColor.RED+"[!] Cannot do that";
    String FAULT_MESSAGE_COMMAND = ChatColor.RED+"[!] Command cannot be executed by this sender";

}
