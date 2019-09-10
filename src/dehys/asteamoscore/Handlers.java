package dehys.asteamoscore;

import dehys.asteamoscore.modules.chestsecure.ChestSecureHandler;
import dehys.asteamoscore.modules.db.DataBaseHandler;
import dehys.asteamoscore.modules.economy.EconomyHandler;
import dehys.asteamoscore.modules.goldtrader.GoldTraderHandler;

public class Handlers {
    AsteamosCore plugin = AsteamosCore.getInstance;

    public ChestSecureHandler getChestSecureHandler(){
        return plugin.chestSecureHandler;
    }

    public DataBaseHandler getDataBaseHandler(){
        return plugin.dataBaseHandler;
    }

    public EconomyHandler getEconomyHandler(){
        return plugin.economyHandler;
    }

    public GoldTraderHandler getGoldTraderHandler() {
        return plugin.goldTraderHandler;
    }
}
