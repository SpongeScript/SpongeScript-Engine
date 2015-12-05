package org.spongesctipt.engine

import org.slf4j.Logger;
import org.spongepowered.api.Game
import org.spongepowered.api.event.Event
import org.spongepowered.api.event.EventListener
import org.spongepowered.api.event.Order
import org.spongepowered.api.util.command.CommandCallable
import org.spongepowered.api.util.command.CommandMapping

import java.util.function.Function

public class ScriptContext {

    private Game game
    private SpongeScriptEnginePlugin spongePlugin

    ScriptContext(Game game, SpongeScriptEnginePlugin spongePlugin) {
        this.game = game
        this.spongePlugin = spongePlugin
    }

    public Game getGame() {
        return game
    }

    public Logger getLogger() {
        return game.getPluginManager().getLogger(game.getPluginManager().fromInstance(spongePlugin).get());
    }

    // EventHandler

    public <T extends Event> void registerEventListener(Class<T> eventClass, EventListener<? super T> listener) {
        game.getEventManager().registerListener(spongePlugin, eventClass, listener)
    }

    public <T extends Event> void registerEventListener(Class<T> eventClass, Order order, EventListener<? super T> listener) {
        game.getEventManager().registerListener(spongePlugin, eventClass, order, listener)
    }

    public <T extends Event> void registerEventListener(Class<T> eventClass, Order order, boolean beforeModifications,
                                                   EventListener<? super T> listener) {
        game.getEventManager().registerListener(spongePlugin, eventClass, order, beforeModifications, listener)
    }

    public void unregisterEventListener(EventListener listener) {
        game.getEventManager().unregisterListeners(listener)
    }

    // CommandService

    public Set<CommandMapping> getOwnCommands() {
        return game.getCommandDispatcher().getOwnedBy(spongePlugin)
    }

    public Optional<CommandMapping> registerCommand(CommandCallable callable, String... alias) {
        return game.getCommandDispatcher().register(spongePlugin, callable, alias)
    }

    Optional<CommandMapping> registerCommand(CommandCallable callable, List<String> aliases) {
        return game.getCommandDispatcher().register(spongePlugin, callable, aliases)
    }

    Optional<CommandMapping> registerCommand(CommandCallable callable, List<String> aliases, Function<List<String>, List<String>> callback) {
        return game.getCommandDispatcher().register(spongePlugin, callable, aliases, callback)
    }

    Optional<CommandMapping> removeCommandMapping(CommandMapping mapping) {
        return game.getCommandDispatcher().removeMapping(mapping)
    }

    def fisk(dims) {
        print(dims)
    }

}
