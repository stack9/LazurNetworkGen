package it.stack9.lazurnetworkgen.event;

import it.stack9.lazurnetworkgen.game.Generator;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

public class GeneratorFullEvent extends GeneratorEvent {

    private static final HandlerList handlers = new HandlerList();

    public GeneratorFullEvent(Generator generator) {
        super(generator);
    }

    public Player getOwner() {
        return generator.getOwner();
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
