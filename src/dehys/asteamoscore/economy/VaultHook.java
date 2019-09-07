package dehys.asteamoscore.economy;

import dehys.asteamoscore.AsteamosCore;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.ServicePriority;

public class VaultHook {
    private AsteamosCore plugin = AsteamosCore.getInstance;
    private Economy provider;

    public void hook(){
        provider = plugin.economyImplementer;
        Bukkit.getServicesManager().register(Economy.class, this.provider, this.plugin, ServicePriority.Normal);
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN+"Vault hooked into "+plugin.getName()+" successfully!");
    }

    public void unhook(){
        Bukkit.getServicesManager().unregister(Economy.class, this.provider);
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN+"Vault unhooked from "+plugin.getName()+".");
    }
}
