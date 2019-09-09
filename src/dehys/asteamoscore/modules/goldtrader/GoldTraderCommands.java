package dehys.asteamoscore.modules.goldtrader;

import dehys.asteamoscore.AsteamosCore;
import dehys.asteamoscore.exceptions.InvalidSender;
import dehys.asteamoscore.exceptions.NoPermission;
import dehys.asteamoscore.exceptions.OversizedString;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.Arrays;

public class GoldTraderCommands implements CommandExecutor, Listener {

    private AsteamosCore plugin = AsteamosCore.getInstance;

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
                    plugin.goldTraderHandler.setVillagerLocation(location);
                    return true;
                }

                if(args[0].equalsIgnoreCase("setname")){
                    String name = String.join(" ", Arrays.asList(args)).replace(args[0]+" ", "");
                    if(name.length() >= 20){
                        sender.sendMessage(OversizedString.FAULT_MESSAGE);
                        return true;
                    }
                    plugin.goldTraderHandler.setVillagerName(name);
                    return true;
                }

                if(args[0].equalsIgnoreCase("refresh")){
                    plugin.goldTraderHandler.removeVillager();
                    plugin.goldTraderHandler.spawnVillager();
                    return true;
                }

                if(args[0].equalsIgnoreCase("remove")){
                    plugin.goldTraderHandler.removeVillager();
                    plugin.goldTraderHandler.removed = true;
                    return true;
                }

            }else{



            }
        }
        return true;
    }
}
