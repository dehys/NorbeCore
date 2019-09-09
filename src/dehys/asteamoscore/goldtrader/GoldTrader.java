package dehys.asteamoscore.goldtrader;

import com.sun.org.apache.xpath.internal.operations.Bool;
import dehys.asteamoscore.AsteamosCore;
import dehys.asteamoscore.exceptions.InvalidSender;
import dehys.asteamoscore.exceptions.NoPermission;
import dehys.asteamoscore.exceptions.OversizedString;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.Listener;

import java.util.Arrays;

public class GoldTrader implements CommandExecutor, Listener {

    private AsteamosCore plugin = AsteamosCore.getInstance;

    private Villager villager;
    private String villagerName;
    private Location villagerLocation;

    private boolean removed;

    public GoldTrader(){
        //Check if the data exists for villagerName and villagerLocation
        //then spawn in the villager by the given data.

        //scheduler that refreshes the villager, check if removed = true and cancel.
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.isOp()) {
            sender.sendMessage(NoPermission.FAULT_MESSAGE);
            return true;
        }

        if(command.getName().equalsIgnoreCase("goldtrader")){
            if(args.length >= 1){
                //setVillagerPosition
                if(args[0].equalsIgnoreCase("setpos")){
                    if(!(sender instanceof Player)){
                        sender.sendMessage(InvalidSender.FAULT_MESSAGE_COMMAND);
                        return true;
                    }

                    Player player = (Player) sender;
                    Location location = player.getLocation();
                    setVillagerLocation(location);
                    return true;
                }

                if(args[0].equalsIgnoreCase("setname")){
                    String name = String.join(" ", Arrays.asList(args)).replace(args[0]+" ", "");
                    if(name.length() >= 20){
                        sender.sendMessage(OversizedString.FAULT_MESSAGE);
                        return true;
                    }
                    setVillagerName(name);
                    return true;
                }

                if(args[0].equalsIgnoreCase("refresh")){
                    removeVillager();
                    spawnVillager();
                    return true;
                }

                if(args[0].equalsIgnoreCase("remove")){
                    removeVillager();
                    this.removed = true;
                    return true;
                }

            }else{
                //info about the gold trader villager
            }
        }
        return true;
    }

    private void spawnVillager(){
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

    private void removeVillager(){
        //kill the villager spawned.

        removed = true;
    }

    private void setVillagerLocation(Location location){
        //set the villager location
        //reload data file

        villagerLocation = location;

        removeVillager();
        spawnVillager();
    }

    private void setVillagerName(String name){
        //set the villager name
        //reload data file

        villagerName = name;

        removeVillager();
        spawnVillager();
    }
}
