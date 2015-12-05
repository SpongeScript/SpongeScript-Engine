package org.spongesctipt.engine.commands

import com.google.inject.Inject
import org.slf4j.Logger
import org.spongepowered.api.Game
import org.spongepowered.api.text.Texts
import org.spongepowered.api.util.command.CommandException
import org.spongepowered.api.util.command.CommandResult
import org.spongepowered.api.util.command.CommandSource
import org.spongepowered.api.util.command.args.CommandContext
import org.spongepowered.api.util.command.spec.CommandExecutor

import javax.script.ScriptEngine
import javax.script.ScriptEngineManager

class JavaScriptCommand implements CommandExecutor {

    @Inject
    private Logger logger;
    @Inject
    private Game game;

    @Override
    CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        def script = args.<String> getOne("script").get()
        src.sendMessage(Texts.of("JS script: " + script));

        ScriptEngineManager factory = new ScriptEngineManager()
        ScriptEngine engine = factory.getEngineByName("JavaScript")
        def result = engine.eval(script)
        if (result) {
            src.sendMessage(Texts.of("JS result: " + result));
        } else {
            src.sendMessage(Texts.of("JS result: <no result>"));
        }

        return CommandResult.success();
    }

}
