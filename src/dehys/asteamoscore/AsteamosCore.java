package dehys.asteamoscore;

import dehys.asteamoscore.db.DBConnector;
import dehys.asteamoscore.modules.chestsecure.ChestSecureHandler;
import dehys.asteamoscore.modules.economy.EconomyCommands;
import dehys.asteamoscore.modules.economy.EconomyHandler;
import dehys.asteamoscore.events.EventManager;
import dehys.asteamoscore.modules.goldtrader.GoldTraderHandler;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;


public final class AsteamosCore extends JavaPlugin {

    public static AsteamosCore getInstance;

    public ChestSecureHandler chestSecureHandler;
    public EconomyHandler economyHandler;
    public GoldTraderHandler goldTraderHandler;

    private HashMap<UUID, Double> playerBank = new HashMap<>();
    public HashMap<UUID, Double> getPlayerBank() { return playerBank;}

    private HashMap<Location, UUID> securedChestLocations = new HashMap<>();
    public HashMap<Location, UUID> getSecuredChestLocations(){ return securedChestLocations;}

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

        entryManager = new EntryManager();

        //MODULES
        chestSecureHandler = new ChestSecureHandler();
        economyHandler = new EconomyHandler();
        goldTraderHandler = new GoldTraderHandler();
    }

    private void setupPlugin(){
        initializeClasses();
        initializeDatabase();

        Objects.requireNonNull(this.getCommand("coins")).setExecutor(new EconomyCommands());
        Objects.requireNonNull(this.getCommand("pay")).setExecutor(new EconomyCommands());

        Bukkit.getServer().getPluginManager().registerEvents(new EventManager(), this);
    }

    @Override
    public void onDisable() {
        entryManager.saveEconomy();
        //entryManager.saveChestSecure();
    }
}
