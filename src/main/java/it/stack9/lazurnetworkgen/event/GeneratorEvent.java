package it.stack9.lazurnetworkgen.event;

import it.stack9.lazurnetworkgen.game.Generator;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public abstract class GeneratorEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    protected final Generator generator;

    public GeneratorEvent(Generator generator) {
        this.generator = generator;
    }

    public Generator getGenerator() {
        return generator;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
