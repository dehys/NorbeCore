package dehys.asteamoscore;

import dehys.asteamoscore.chat.ChatManager;
import dehys.asteamoscore.db.DBConnector;
import dehys.asteamoscore.economy.EconomyCommands;
import dehys.asteamoscore.economy.EconomyImplementer;
import dehys.asteamoscore.economy.VaultHook;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;


public final class AsteamosCore extends JavaPlugin {

    public static AsteamosCore getInstance;
    public EconomyImplementer economyImplementer;

    private HashMap<UUID, Double> playerBank = new HashMap<>();
    public HashMap<UUID, Double> getPlayerBank() { return playerBank;}

    private HashMap<Location, UUID> securedChestLocations = new HashMap<>();
    public HashMap<Location, UUID> getSecuredChestLocations(){ return securedChestLocations;}

    private VaultHook vaultHook;
    private EntryManager entryManager;

    @Override
    public void onEnable() {
        setupPlugin();
    }

    private void initializeDatabase(){
        if (!DBConnector.connect())
        {
            System.out.println("DATABASE CONNECTION NOT SUCCESSFUL!");
            getServer().getPluginManager().disablePlugin(this);
            getServer().shutdown();
            return;
        }
        try
        {
            entryManager.collectEconomy();
            //entryManager.collectChestSecure();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    private void initializeClasses(){
        getInstance = this;
        economyImplementer = new EconomyImplementer();
        vaultHook = new VaultHook();
        entryManager = new EntryManager();
    }

    private void setupPlugin(){
        initializeClasses();
        initializeDatabase();

        vaultHook.hook();

        Objects.requireNonNull(this.getCommand("balance")).setExecutor(new EconomyCommands());
        Objects.requireNonNull(this.getCommand("pay")).setExecutor(new EconomyCommands());

        Bukkit.getServer().getPluginManager().registerEvents(new ChatManager(), this);
    }

    @Override
    public void onDisable() {
        entryManager.saveEconomy();
        //entryManager.saveChestSecure();
        vaultHook.unhook();
    }
}
