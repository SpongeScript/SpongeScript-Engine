package com.latibro.sponge.scriptengine

import com.google.inject.Inject
import org.slf4j.Logger
import org.spongepowered.api.Game

class PluginManager {

    @Inject
    private Logger logger;
    @Inject
    private Game game;
    @Inject
    SpongeScriptEnginePlugin plugin

/*
 * On load all plugins
 * - Scan plugins in folders
 * -- Load found plugin
 */
    void loadPlugins() {
        File pluginDirectory = new File("scriptengine/plugins")

        logger.debug("Plugins directory: " + pluginDirectory.absolutePath)

        pluginDirectory.eachFile { File file ->
            logger.debug("Plugin: " + file.absolutePath)

            if (!file.isDirectory()) {
                logger.debug("Not a plugin directory: " + file.absolutePath)
                return
            }

            File scriptFile = file.listFiles().find {
                it.name ==~ /$file.name\.\w+/
            }
            if (!scriptFile?.exists()) {
                logger.debug("Plugin file not found: " + scriptFile.name)
                return
            }

        }
    }

    /*
    * On load plugin
    * - Register event listeners
    * - Register commands
     */

    /*
     * On unload all plugins
     * - Unload each plugin
     */

    /*
     * On unload plugin
     * - Unregister event listeners
     * - Unregister commands
     */

    /*
    * On reload plugin
    * - Unload plugin
    * - Load plugin
     */

}
