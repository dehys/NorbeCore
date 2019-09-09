package dehys.asteamoscore.modules.goldtrader;

import dehys.asteamoscore.AsteamosCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;

public class GoldTraderHandler {

    private AsteamosCore plugin = AsteamosCore.getInstance;

    public GoldTraderHandler(){
        //Check if the data exists for villagerName and villagerLocation
        //then spawn in the villager by the given data.

        //scheduler that refreshes the villager, check if removed = true and cancel.
    }

    Villager villager;
    String villagerName;
    Location villagerLocation;

    boolean removed;

    void spawnVillager(){
        villager = (Villager) Bukkit.getWorlds().get(0).spawnEntity(villagerLocation, EntityType.VILLAGER);
        villager.setAI(false);
        villager.setProfession(Villager.Profession.LIBRARIAN);
        villager.setInvulnerable(true);
        villager.setCustomName(ChatColor.translateAlternateColorCodes('&', villagerName));
        villager.setAdult();
        villager.setAgeLock(true);
        villager.setCustomNameVisible(true);
        villager.setRemoveWhenFarAway(false);

        removed = false;
    }

    void removeVillager(){
        //kill the villager spawned.

        removed = true;
    }

    void setVillagerLocation(Location location){
        //set the villager location
        //reload data file

        villagerLocation = location;

        removeVillager();
        spawnVillager();
    }

    void setVillagerName(String name){
        //set the villager name
        //reload data file

        villagerName = name;

        removeVillager();
        spawnVillager();
    }
}
