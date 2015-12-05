package org.spongesctipt.engine

import org.spongepowered.api.Game
import org.spongepowered.api.util.command.CommandSource;

public class CommandScriptContext extends ScriptContext {

    private CommandSource commandSource

    CommandScriptContext(Game game, SpongeScriptEnginePlugin spongePlugin) {
        super(game, spongePlugin)
    }
}
