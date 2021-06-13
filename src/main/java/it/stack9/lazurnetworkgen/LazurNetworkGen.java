package it.stack9.lazurnetworkgen;

import it.stack9.lazurnetworkgen.game.Generator;
import it.stack9.lazurnetworkgen.game.GeneratorColors;
import it.stack9.lazurnetworkgen.listener.WorldListener;
import it.stack9.lazurnetworkgen.manager.DBManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;

public final class LazurNetworkGen extends JavaPlugin {

    public static NamespacedKey GENERATORS_KEY;

    private Map<Integer, Generator> generators;
    private DBManager dbManager;

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onEnable() {
        GENERATORS_KEY = new NamespacedKey(this, "gen");
        try {
            dbManager = new DBManager(this);
            generators = dbManager.readGenerators(this);
        } catch (Exception e) {
            e.printStackTrace();
            setEnabled(false);
        }
        getCommand("getgen").setExecutor(this);
        getCommand("genremove").setExecutor(this);
        Bukkit.getPluginManager().registerEvents(new WorldListener(this), this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§7cNon puoi eseguire questo comando da console");
            return true;
        }
        Player player = (Player) sender;

        if (command.getName().equalsIgnoreCase("getgen") && args.length == 1) {
            ItemStack genItem = Generator.createItem(args[0]);
            if (genItem == null) {
                sender.sendMessage("§7cGeneratore di colore " + args[0] + " non disponibile");
                return true;
            }
            player.getInventory().addItem(genItem);
            return true;
        } else if (command.getName().equalsIgnoreCase("genremove") && args.length == 1) {
            int genId;
            try {
                genId = Integer.parseInt(args[0]);
                if (!generators.containsKey(genId)) {
                    throw new Exception("not found");
                }
            } catch (Exception e) {
                sender.sendMessage("§cGeneratore " + args[0] + " non trovato");
                return true;
            }
            generators.get(genId).delete();
            generators.remove(genId);
            sender.sendMessage("§7Generatore " + args[0] + " eliminato");
            return true;
        }
        return false;
    }

    @Override
    public void onDisable() {
        if (dbManager != null) {
            try {
                dbManager.dumpGenerators(generators);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void createGenerator(Player owner, Location origin, GeneratorColors color) {
        Generator gen = Generator.create(this, owner, origin, color.name());
        owner.sendMessage("§7Creato generatore #" + gen.getId());
        generators.put(gen.getId(), gen);
    }

    public Generator getGenerator(int id) {
        return generators.get(id);
    }
}
