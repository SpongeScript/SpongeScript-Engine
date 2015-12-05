var eventManager = game.getEventManager();

//var handler = new org.spongepowered.api.event.EventHandler();
var handler = {
    handle: function(event) {
        logger.info("***** hey du, kan du så lade være");
    }
};

eventManager.registerListener(plugin, org.spongepowered.api.event.action.InteractEvent.class, new org.spongepowered.api.event.EventListener(handler));