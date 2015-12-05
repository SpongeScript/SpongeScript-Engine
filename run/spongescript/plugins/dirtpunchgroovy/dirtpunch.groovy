import org.spongepowered.api.block.BlockTypes
import org.spongepowered.api.entity.living.player.Player
import org.spongepowered.api.event.block.InteractBlockEvent
import org.spongepowered.api.item.ItemTypes

sponge.getLogger().debug("*** Dirt Punch loading");

def myEventListener = new org.spongepowered.api.event.EventListener<InteractBlockEvent>() {

    @Override
    void handle(InteractBlockEvent event) throws Exception {
        sponge.getLogger().debug("*** Block punched");

        def player = event.getCause().first(Player).get()

        def itemInHand = player.getItemInHand().get()

        if (itemInHand.getItem().equals(ItemTypes.STICK)) {
            def punchedBlock = event.getTargetBlock();
            punchedBlock.getLocation().get().setBlockType(BlockTypes.DIRT);
        }

    }

}

sponge.registerEventListener(InteractBlockEvent.Secondary.class, myEventListener);

sponge.getLogger().debug("*** Dirt Punch loaded");
