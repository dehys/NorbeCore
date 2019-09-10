package dehys.asteamoscore.configuration;

import dehys.asteamoscore.modules.db.DataBaseConfiguration;

public interface Configurations {

    DataBaseConfiguration DATABASE_CONFIGURATION = new DataBaseConfiguration();

    String DATABASE_FILE_NAME = "db-config.yml";
    String GOLDTRADER_FILE_NAME = "goldtrader-config.yml";
}
