sponge.getLogger().debug("*** Dirt Punch loading");

var myEventListener = new org.spongepowered.api.event.EventListener({

    handle: function(event) {
        sponge.getLogger().debug("*** Block punched");

        var player = event.getCause().first(org.spongepowered.api.entity.living.player.Player.class).get();

        var itemInHand = player.getItemInHand().get();

        if (itemInHand.getItem().equals(org.spongepowered.api.item.ItemTypes.STICK)) {
            var punchedBlock = event.getTargetBlock();
            punchedBlock.getLocation().get().setBlockType(org.spongepowered.api.block.BlockTypes.DIRT);
        }
    }

});

sponge.registerEventListener(org.spongepowered.api.event.block.InteractBlockEvent.Secondary.class, myEventListener);

sponge.getLogger().debug("*** Dirt Punch loaded");
