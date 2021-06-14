package it.stack9.lazurnetworkgen.listener;

import it.stack9.lazurnetworkgen.LazurNetworkGen;
import it.stack9.lazurnetworkgen.game.GeneratorColors;
import it.stack9.lazurnetworkgen.util.Builder;
import org.bukkit.Location;
import org.bukkit.entity.Player;
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
            Integer genColor = meta.getPersistentDataContainer().get(LazurNetworkGen.GENERATORS_KEY, PersistentDataType.INTEGER);
            Location origin = event.getBlockPlaced().getLocation();
            Player player = event.getPlayer();
            if (genColor != null && genColor >= 0 && genColor <= 16) {
                if (Builder.isSpaceFree(origin, 10, 16)) {
                    plugin.createGenerator(player, origin, GeneratorColors.fromValue(genColor));
                } else {
                    player.sendMessage("§cImpossibile creare un generatore qui, spazio insufficiente");
                    event.setBuild(false);
                    event.setCancelled(true);
                }
            }
        }
    }
}
