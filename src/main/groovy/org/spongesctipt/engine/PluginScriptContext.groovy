package org.spongesctipt.engine

import org.spongepowered.api.Game;

public class PluginScriptContext extends ScriptContext {

    private PluginContainer scriptPlugin

    PluginScriptContext(Game game, SpongeScriptEnginePlugin spongePlugin, PluginContainer scriptPlugin) {
        super(game, spongePlugin)
        this.scriptPlugin = scriptPlugin
    }
}
