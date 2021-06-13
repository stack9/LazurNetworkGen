package it.stack9.lazurnetworkgen.listener;

import it.stack9.lazurnetworkgen.LazurNetworkGen;
import it.stack9.lazurnetworkgen.game.GeneratorColors;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class WorldListener implements Listener {

    private final LazurNetworkGen plugin;

    public WorldListener(LazurNetworkGen plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        ItemStack item = event.getItemInHand();
        ItemMeta meta = item.getItemMeta();
        if (meta != null && meta.getPersistentDataContainer().has(LazurNetworkGen.GENERATORS_KEY, PersistentDataType.INTEGER)) {
            int genColor = meta.getPersistentDataContainer().get(LazurNetworkGen.GENERATORS_KEY, PersistentDataType.INTEGER);
            if (genColor >= 0 && genColor <= 16) {
                plugin.createGenerator(event.getPlayer(), event.getBlock().getLocation(), GeneratorColors.fromValue(genColor));
            }
        }
    }
}
