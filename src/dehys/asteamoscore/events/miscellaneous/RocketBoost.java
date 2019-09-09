package dehys.asteamoscore.events.miscellaneous;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class RocketBoost {
    public RocketBoost(PlayerInteractEvent e){
        if(e.getAction() == e.getAction().RIGHT_CLICK_BLOCK || e.getAction() == e.getAction().RIGHT_CLICK_AIR) {
            Player player = e.getPlayer();
            if(e.getItem() != null) {
                if(e.getItem().getType() == Material.FIREWORK_ROCKET) {
                    if (player.getInventory().getChestplate().getType() == Material.ELYTRA){
                        if(!player.isGliding()) {
                            e.setCancelled(true);
                            if(e.getItem().getAmount() >= 1) {
                                ItemStack itemStack = e.getItem();
                                itemStack.setAmount(itemStack.getAmount()-1);
                                e.getPlayer().getInventory().setItemInMainHand(itemStack);
                            } else {
                                e.getPlayer().getInventory().setItemInMainHand(new ItemStack(Material.AIR));
                            }
                            player.setVelocity(new Vector(0, 3, 0));
                            player.getWorld().playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 1, 1);

                        }
                    }
                }
            }
        }
    }
}
