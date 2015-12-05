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
import org.spongesctipt.engine.SpongeScriptEnginePlugin

import javax.script.ScriptContext
import javax.script.Bindings
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager
import javax.script.SimpleScriptContext

class JavaScriptFileCommand implements CommandExecutor {

    @Inject
    private Logger logger;
    @Inject
    private Game game;
    @Inject
    SpongeScriptEnginePlugin plugin

    @Override
    CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        def path = args.<String> getOne("file").get()
        src.sendMessage(Texts.of("file: " + path));
        File file = new File(new File("scripts"), path)
        src.sendMessage(Texts.of("file absolute: " + file.absolutePath));

        def spongeObject = [
                "game": game
        ]

        ScriptContext newContext = new SimpleScriptContext();
        Bindings engineScope = newContext.getBindings(ScriptContext.ENGINE_SCOPE);
        //engineScope.put("Sponge", spongeObject)
        //engineScope.put("commandSource", src)
        //engineScope.put("commandContext", args)
        //engineScope.put("game", game)
        //engineScope.put("spongePlugin", spongePlugin)

        ScriptEngineManager factory = new ScriptEngineManager()
        ScriptEngine engine = factory.getEngineByName("JavaScript")
        engine.put("Sponge", spongeObject)
        //def result = engine.eval(new FileReader(file), newContext)
        def result = engine.eval("load('spongescript/scripts/" + file.name + "')")
        if (result) {
            src.sendMessage(Texts.of("result: " + result));
        } else {
            src.sendMessage(Texts.of("JS result: <no result>"));
        }
        return CommandResult.success();
    }

}
