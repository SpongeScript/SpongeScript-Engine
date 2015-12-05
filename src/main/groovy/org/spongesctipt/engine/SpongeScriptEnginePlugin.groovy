package org.spongesctipt.engine

import com.google.inject.Inject
import com.google.inject.Injector
import org.spongesctipt.engine.commands.JavaScriptCommand
import org.spongesctipt.engine.commands.JavaScriptFileCommand
import org.slf4j.Logger
import org.spongepowered.api.Game
import org.spongepowered.api.event.Listener
import org.spongepowered.api.event.action.InteractEvent
import org.spongepowered.api.event.game.state.GameInitializationEvent
import org.spongepowered.api.plugin.Plugin
import org.spongepowered.api.text.Texts
import org.spongepowered.api.text.sink.MessageSinks
import org.spongepowered.api.util.command.args.GenericArguments
import org.spongepowered.api.util.command.spec.CommandSpec

@Plugin(id = "SpongeScript-Engine", name = "SpongeScript Engine", version = "0.1")
public class SpongeScriptEnginePlugin {

    @Inject private Injector injector;

    @Inject private Logger logger;
    @Inject private Game game;

    @Inject private PluginManager pluginManager;

    @Listener
    public void onGameInit(GameInitializationEvent event) {
        logger.info("**** Loading spongePlugin")

        pluginManager.loadAll()

        CommandSpec jsCommandSpec = CommandSpec.builder()
                .arguments(GenericArguments.remainingJoinedStrings(Texts.of("script")))
                .executor(injector.getInstance(JavaScriptCommand))
                .build();
        game.getCommandDispatcher().register(this, jsCommandSpec, "javascript", "js");

        CommandSpec jsfCommandSpec = CommandSpec.builder()
                .arguments(GenericArguments.remainingJoinedStrings(Texts.of("file")))
                .executor(injector.getInstance(JavaScriptFileCommand))
                .build();

        game.getCommandDispatcher().register(this, jsfCommandSpec, "javascriptfile", "jsfile", "jsf");
    }

    public void test() {
        MessageSinks.toAll().sendMessage(Texts.of("Hello World"))
    }

    @Listener
    public void testEvent(InteractEvent event) {
        //event.
        //MessageSinks.toAll().sendMessage(Texts.of("Event hello"))
    }

}