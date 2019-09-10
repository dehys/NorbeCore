package dehys.asteamoscore.modules.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseHandler {
    public static Connection con;

    static String host = "";
    static String database = "";
    static String username = "";
    static String password = "";

    public static boolean connect()
    {
        if (!isConnected()) {
            try
            {
                con = DriverManager.getConnection("jdbc:mysql://" + host + ":3306/" + database + "?autoReconnect=true", username, password);
                return true;
            }
            catch (SQLException e)
            {
                return false;
            }
        }
        return false;
    }

    private static boolean isConnected()
    {
        return con != null;
    }
}
