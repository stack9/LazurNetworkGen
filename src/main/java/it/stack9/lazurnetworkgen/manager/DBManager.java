package it.stack9.lazurnetworkgen.manager;

import it.stack9.lazurnetworkgen.LazurNetworkGen;
import it.stack9.lazurnetworkgen.game.Generator;
import it.stack9.lazurnetworkgen.game.GeneratorColors;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DBManager {

    private static final String DB_FILENAME = "database.db";

    private final File dbFile;

    public DBManager(LazurNetworkGen plugin) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }
        this.dbFile = new File(plugin.getDataFolder(), DB_FILENAME);
        createTables();
    }

    public void createTables() throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile.getAbsolutePath())) {
            connection.createStatement().execute("CREATE TABLE IF NOT EXISTS lazurnetwork_gens (" +
                    "id integer PRIMARY KEY," +
                    "owner varchar(32) NOT NULL," +
                    "fill_level int," +
                    "color int," +
                    "x double," +
                    "y double," +
                    "z double," +
                    "world varchar(64) NOT NULL)"
            );
        }
    }

    public void dumpGenerators(Map<Integer, Generator> generators) throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile.getAbsolutePath())) {
            for (Generator gen : generators.values()) {
                PreparedStatement stmt = connection.prepareStatement("INSERT INTO lazurnetwork_gens (id, owner, fill_level, color, x, y, z, world) VALUES (?,?,?,?,?,?,?,?)");
                stmt.setInt(1, gen.getId());
                stmt.setString(2, gen.getOwner().getUniqueId().toString());
                stmt.setInt(3, gen.getLevel());
                stmt.setInt(4, GeneratorColors.valueOf(gen.getColor()).ordinal());
                stmt.setDouble(5, gen.getOrigin().getBlockX());
                stmt.setDouble(6, gen.getOrigin().getBlockY());
                stmt.setDouble(7, gen.getOrigin().getBlockZ());
                stmt.setString(8, gen.getOrigin().getWorld().getName());
                stmt.executeUpdate();
            }
        }
    }

    public Map<Integer, Generator> readGenerators(LazurNetworkGen plugin) throws SQLException {
        Map<Integer, Generator> generators = new HashMap<>();
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile.getAbsolutePath())) {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM lazurnetwork_gens");
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                Generator gen = new Generator(
                        result.getInt(1),
                        new Location(Bukkit.getWorld(result.getString(8)), result.getDouble(5), result.getDouble(6), result.getDouble(7)),
                        Bukkit.getServer().getPlayer(result.getString(2)),
                        GeneratorColors.fromValue(result.getInt(4)).name(),
                        result.getInt(3)
                );
                gen.update();
                gen.start(plugin);
                generators.put(gen.getId(), gen);
            }
        }
        if (generators.size() > 0) {
            Generator.LAST_ID = new ArrayList<>(generators.values()).get(generators.size() - 1).getId();
        }
        return generators;
    }
}
