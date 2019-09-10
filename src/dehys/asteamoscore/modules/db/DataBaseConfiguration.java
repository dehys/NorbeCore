package dehys.asteamoscore.modules.db;

import dehys.asteamoscore.AsteamosCore;
import dehys.asteamoscore.configuration.Configurations;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;

public class DataBaseConfiguration {

    private AsteamosCore plugin = AsteamosCore.getInstance;

    private File rawFile;
    private FileConfiguration configuration;

    public DataBaseConfiguration(){
        DataBaseHandler.host = "";
        DataBaseHandler.database = "";
        DataBaseHandler.username = "";
        DataBaseHandler.password = "";

        this.rawFile = new File(plugin.getDataFolder(), Configurations.DATABASE_FILE_NAME);

    }
}
