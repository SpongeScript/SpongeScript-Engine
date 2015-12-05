package org.spongesctipt.engine

import com.google.common.collect.Maps
import com.google.common.collect.Sets
import com.google.inject.Inject
import groovy.json.JsonSlurper
import org.slf4j.Logger
import org.spongepowered.api.Game

import javax.script.ScriptEngine
import javax.script.ScriptEngineManager

class PluginManager {

    @Inject
    private Logger logger;
    @Inject
    private Game game;
    @Inject
    SpongeScriptEnginePlugin spongePlugin

    private final Map<String, PluginContainer> plugins = Maps.newHashMap();

    public Collection<PluginContainer> loadAll() {
        File pluginDirectory = new File("spongescript/plugins")

        logger.debug("Plugins directory: " + pluginDirectory.absolutePath)

        Set<PluginContainer> plugins = Sets.newHashSet();

        pluginDirectory.eachDir() { File dir ->
            PluginContainer plugin = load(dir)
            plugins.add(plugin)
        }

        return plugins
    }

    public PluginContainer load(File path) {
        logger.debug("Plugin dir: " + path.path)

        File pluginDescriptionFile = new File(path, "plugin.json")

        if (!pluginDescriptionFile.exists() || !pluginDescriptionFile.isFile()) {
            logger.debug("plugin.json not found: " + path.path)
            return
        }

        def pluginDescriptionMap = new JsonSlurper().parse(pluginDescriptionFile)

        logger.debug("Plugin description: " + pluginDescriptionMap)

        logger.debug("Plugin id: " + pluginDescriptionMap.id)

        //TODO check if ID is already loaded

        if (pluginDescriptionMap.autoload == false) {
            logger.debug("Autoload is false for: " + pluginDescriptionMap.id)
            return null
        }

        PluginContainer plugin = new PluginContainer()
        plugin.path = path
        plugin.id = pluginDescriptionMap.id
        plugin.name = pluginDescriptionMap.name
        plugin.version = pluginDescriptionMap.version
        plugin.engine = pluginDescriptionMap.engine
        plugin.main = new File(path, pluginDescriptionMap.main) //TODO check fil exists

        logger.debug("Plugin id: " + plugin.id)

        return register(plugin)
    }

    public PluginContainer get(String id) {
        return plugins.get(id);
    }

    public PluginContainer reload(String id) {
        PluginContainer plugin = get(id)
        return reload(plugin)
    }

    public PluginContainer reload(PluginContainer plugin) {
        unregister(plugin)
        return load(plugin.path)
    }

    public Collection<PluginContainer> reloadAll() {
        unregisterAll()
        return loadAll()
    }

    private PluginContainer register(PluginContainer plugin) {
        //TODO check not already registered
        //TODO trigger start spongePlugin event

        ScriptEngineManager factory = new ScriptEngineManager()
        ScriptEngine scriptEngine = factory.getEngineByName(plugin.engine)
        scriptEngine.put("sponge", new PluginScriptContext(game, spongePlugin, plugin))

        def result;
        if (plugin.engine.equalsIgnoreCase("JavaScript")) {
            result = scriptEngine.eval("load('" + plugin.main.path + "')")
        } else {
            result = scriptEngine.eval(new FileReader(plugin.main.path))
        }

        plugins.put(plugin.id, plugin);
        return plugin
    }

    private void unregister(PluginContainer plugin) {
        //TODO trigger stop spongePlugin event (unregister listeners, commands, etc.)
        plugins.remove(plugin.id)
    }

    private void unregister(String id) {
        PluginContainer plugin = get(id)
        unregister(plugin)
    }

    private void unregisterAll() {
        plugins.each { id, plugin ->
            unregister(plugin)
        }
    }

}
