package dehys.asteamoscore.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    public static Connection con;

    public static boolean connect()
    {
        if (!isConnected()) {
            try
            {
                String host = "";
                String database = "";
                String username = "";
                String password = "";
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
