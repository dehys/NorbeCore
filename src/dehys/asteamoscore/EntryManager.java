package dehys.asteamoscore;

import dehys.asteamoscore.modules.db.DataBaseHandler;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class EntryManager {

    private AsteamosCore plugin = AsteamosCore.getInstance;

    public void collectEconomy(){
        try{
            PreparedStatement ps = null;
            ps = DataBaseHandler.con.prepareStatement("SELECT * FROM economy");
            ResultSet rs = ps.executeQuery();
            int i = 0;
            while (rs.next())
            {
                String unparsedUuid = rs.getString("uuid");
                String unparsedBalance = rs.getString("balance");
                UUID uuid = UUID.fromString(unparsedUuid);
                int balance = Integer.parseInt(unparsedBalance);

                plugin.getPlayerBank().put(uuid, (double) balance);
                i++;
            }
            System.out.println("Loaded " + i + " Economy-Entries");
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public void saveEconomy(){
        if (plugin.getPlayerBank().isEmpty()) {
            return;
        }
        PreparedStatement ps = null;
        try
        {
            ps = DataBaseHandler.con.prepareStatement("DELETE FROM economy");
            ps.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        for (UUID key : plugin.getPlayerBank().keySet())
        {
            int balance = plugin.getPlayerBank().get(key).intValue();
            try
            {
                PreparedStatement ps2 = DataBaseHandler.con.prepareCall("INSERT INTO economy (uuid, balance) VALUES (?,?)");
                ps2.setString(1, key.toString());
                ps2.setString(2, balance+"");
                ps2.executeUpdate();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
                System.out.println("ERROR SAVING Economy-ENTRIES!");
            }
        }
    }

    public void collectChestSecure(){
        try{
            PreparedStatement ps = null;
            ps = DataBaseHandler.con.prepareStatement("SELECT * FROM chestsecure");
            ResultSet rs = ps.executeQuery();
            int i = 0;
            while (rs.next())
            {
                String unparseduuid = rs.getString("uuid");
                String unparsedlocation = rs.getString("location");
                UUID uuid = UUID.fromString(unparseduuid);
                String[] locsplit = unparsedlocation.split(";");
                World world = Bukkit.getWorld(locsplit[0]);
                double x = Double.parseDouble(locsplit[1]);
                double y = Double.parseDouble(locsplit[2]);
                double z = Double.parseDouble(locsplit[3]);

                Location location = new Location(world, x, y, z);
                plugin.getSecuredChestLocations().put(location, uuid);
                i++;
            }
            System.out.println("Loaded " + i + " ChestSecure-Entries");
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public void saveChestSecure(){
        if (plugin.getSecuredChestLocations().isEmpty()) {
            return;
        }
        PreparedStatement ps = null;
        try
        {
            ps = DataBaseHandler.con.prepareStatement("DELETE FROM chestsecure");
            ps.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        for (Location key : plugin.getSecuredChestLocations().keySet())
        {
            String unparsedkey = key.getWorld().getName() + ";" + key.getX() + ";" + key.getY() + ";" + key.getZ();
            String uuid = ((UUID)plugin.getSecuredChestLocations().get(key)).toString();
            try
            {
                PreparedStatement ps2 = DataBaseHandler.con.prepareCall("INSERT INTO chestsecure (uuid, location) VALUES (?,?)");
                ps2.setString(1, uuid);
                ps2.setString(2, unparsedkey);
                ps2.executeUpdate();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
                System.out.println("ERROR SAVING ChestSecure-ENTRIES!");
            }
        }
    }
}