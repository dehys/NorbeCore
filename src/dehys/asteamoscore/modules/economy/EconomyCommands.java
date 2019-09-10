package dehys.asteamoscore.modules.economy;

import dehys.asteamoscore.AsteamosCore;
import dehys.asteamoscore.exceptions.PlayerNotFound;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EconomyCommands implements CommandExecutor {

    private AsteamosCore plugin = AsteamosCore.getInstance;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) return true;
        Player player = (Player) sender;

        if(!plugin.getPlayerBank().containsKey(player.getUniqueId())) {
            plugin.getPlayerBank().put(player.getUniqueId(), 0.0);
        }

        //BALANCE COMMAND
        if(command.getName().equalsIgnoreCase("balance")){
            OfflinePlayer bankPlayer = player;
            String outputMessage = ChatColor.GOLD+"Balance: ";
            if(args.length >= 1){
                bankPlayer = Bukkit.getOfflinePlayer(args[0]);
                if (!(plugin.getPlayerBank().containsKey(bankPlayer.getUniqueId()))){
                    player.sendMessage(PlayerNotFound.FAULT_MESSAGE);
                    return true;
                }
                outputMessage = ChatColor.GRAY+bankPlayer.getName()+"'s "+ChatColor.GOLD+"Balance: ";
            }

            try{
                int balance = (int) plugin.getHandlers.getEconomyHandler().getBalance(bankPlayer);
                player.sendMessage(outputMessage+ChatColor.YELLOW+balance);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }

        //PAY COMMAND
        if(command.getName().equalsIgnoreCase("pay")){
            if(args.length == 2){
                try{
                    Player target = Bukkit.getPlayer(args[0]);
                    int paymentAmount = Integer.parseInt(args[1]);

                    if(target == sender) return true;

                    plugin.getHandlers.getEconomyHandler().pay(player, target, paymentAmount);

                    sender.sendMessage(ChatColor.GREEN+"Sent "+paymentAmount+ " coins to "+target.getDisplayName());
                    target.sendMessage(ChatColor.GOLD+"+ "+paymentAmount+" coins from "+player.getDisplayName());
                    if(target.isOnline()) target.playSound(target.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 6F, 1F);
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        }

        return true;
    }
}
