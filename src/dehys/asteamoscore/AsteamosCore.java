package dehys.asteamoscore;

import dehys.asteamoscore.db.DBConnector;
import dehys.asteamoscore.modules.chestsecure.ChestSecureHandler;
import dehys.asteamoscore.modules.economy.EconomyCommands;
import dehys.asteamoscore.modules.economy.EconomyHandler;
import dehys.asteamoscore.events.EventManager;
import dehys.asteamoscore.modules.goldtrader.GoldTraderHandler;
import org.bukkit.*;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;
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
        setupServer();
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

    private void setupServer(){
        List<World> worlds = Bukkit.getServer().getWorlds();
        for(World w : worlds){
            w.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
            w.setGameRule(GameRule.MOB_GRIEFING, true);
            w.setGameRule(GameRule.DO_ENTITY_DROPS, true);
            w.setGameRule(GameRule.LOG_ADMIN_COMMANDS, true);
            w.setGameRule(GameRule.RANDOM_TICK_SPEED, 20);
            w.setGameRule(GameRule.SHOW_DEATH_MESSAGES, true);

            w.setDifficulty(Difficulty.HARD);
        }
    }

    @Override
    public void onDisable() {
        entryManager.saveEconomy();
        //entryManager.saveChestSecure();
    }
}
