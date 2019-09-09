package dehys.asteamoscore.modules.economy;

import dehys.asteamoscore.AsteamosCore;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class EconomyHandler {

    private AsteamosCore plugin = AsteamosCore.getInstance;

    public double getBalance(String s) {
        Player player = Bukkit.getPlayer(s);
        UUID uuid = player.getUniqueId();
        return plugin.getPlayerBank().get(uuid);
    }

    public double getBalance(OfflinePlayer offlinePlayer) {
        UUID uuid = offlinePlayer.getUniqueId();
        return plugin.getPlayerBank().get(uuid);
    }

    public double getBalance(String s, String s1) {
        Player player = Bukkit.getPlayer(s);
        UUID uuid = player.getUniqueId();
        return plugin.getPlayerBank().get(uuid);
    }

    public double getBalance(OfflinePlayer offlinePlayer, String s) {
        UUID uuid = offlinePlayer.getUniqueId();
        return plugin.getPlayerBank().get(uuid);
    }

    public boolean has(String s, double v) {
        Player player = Bukkit.getPlayer(s);
        UUID uuid = player.getUniqueId();
        return plugin.getPlayerBank().get(uuid) >= v;
    }

    public boolean has(OfflinePlayer offlinePlayer, double v) {
        UUID uuid = offlinePlayer.getUniqueId();
        return plugin.getPlayerBank().get(uuid) >= v;
    }

    public boolean has(String s, String s1, double v) {
        Player player = Bukkit.getPlayer(s);
        UUID uuid = player.getUniqueId();
        return plugin.getPlayerBank().get(uuid) >= v;
    }

    public boolean has(OfflinePlayer offlinePlayer, String s, double v) {
        UUID uuid = offlinePlayer.getUniqueId();
        return plugin.getPlayerBank().get(uuid) >= v;
    }

    public boolean pay(String s, String t, double v){
        Player player = Bukkit.getPlayer(s);
        Player target = Bukkit.getPlayer(t);

        if(!(this.has(player, v))) return false;

        this.withdrawPlayer(player, v);
        this.depositPlayer(target, v);
        return true;
    }

    public boolean pay(OfflinePlayer offlinePlayer, OfflinePlayer offlineTargetPlayer, double v){
        if(!(this.has(offlinePlayer, v))) return false;

        this.withdrawPlayer(offlinePlayer, v);
        this.depositPlayer(offlineTargetPlayer, v);
        return true;
    }

    public boolean withdrawPlayer(String s, double v) {
        Player player = Bukkit.getPlayer(s);
        UUID uuid = player.getUniqueId();
        double oldBalance = plugin.getPlayerBank().get(uuid);
        plugin.getPlayerBank().put(uuid, oldBalance - v);
        return true;
    }

    public boolean withdrawPlayer(OfflinePlayer offlinePlayer, double v) {
        UUID uuid = offlinePlayer.getUniqueId();
        double oldBalance = plugin.getPlayerBank().get(uuid);
        plugin.getPlayerBank().put(uuid, oldBalance - v);
        return true;
    }

    public boolean withdrawPlayer(String s, String s1, double v) {
        Player player = Bukkit.getPlayer(s);
        UUID uuid = player.getUniqueId();
        double oldBalance = plugin.getPlayerBank().get(uuid);
        plugin.getPlayerBank().put(uuid, oldBalance - v);
        return true;
    }

    public boolean withdrawPlayer(OfflinePlayer offlinePlayer, String s, double v) {
        UUID uuid = offlinePlayer.getUniqueId();
        double oldBalance = plugin.getPlayerBank().get(uuid);
        plugin.getPlayerBank().put(uuid, oldBalance - v);
        return true;
    }

    public boolean depositPlayer(String s, double v) {
        Player player = Bukkit.getPlayer(s);
        UUID uuid = player.getUniqueId();
        double oldBalance = plugin.getPlayerBank().get(uuid);
        plugin.getPlayerBank().put(uuid, oldBalance + v);
        return true;
    }

    public boolean depositPlayer(OfflinePlayer offlinePlayer, double v) {
        UUID uuid = offlinePlayer.getUniqueId();
        double oldBalance = plugin.getPlayerBank().get(uuid);
        plugin.getPlayerBank().put(uuid, oldBalance + v);
        return true;
    }

    public boolean depositPlayer(String s, String s1, double v) {
        Player player = Bukkit.getPlayer(s);
        UUID uuid = player.getUniqueId();
        double oldBalance = plugin.getPlayerBank().get(uuid);
        plugin.getPlayerBank().put(uuid, oldBalance + v);
        return true;
    }

    public boolean depositPlayer(OfflinePlayer offlinePlayer, String s, double v) {
        UUID uuid = offlinePlayer.getUniqueId();
        double oldBalance = plugin.getPlayerBank().get(uuid);
        plugin.getPlayerBank().put(uuid, oldBalance + v);
        return true;
    }
}
