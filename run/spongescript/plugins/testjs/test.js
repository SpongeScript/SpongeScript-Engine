
engine.put("hest", 1234);

sponge.getLogger().debug("*** hest : " + hest);


sponge.fisk("Hej");
sponge.fisk({hund: 123});
sponge.fisk(function() { return "Hej";});

sponge.getLogger().debug(sponge);
sponge.getLogger().debug(context);
sponge.getLogger().debug(engine);

//sponge.getLogger().debug(jsadapter);
sponge.getLogger().debug(JSAdapter);
//sponge.getLogger().debug(javaApi);
//sponge.getLogger().debug(JavaApi);
sponge.getLogger().debug(__FILE__);
//sponge.getLogger().debug(packages);
sponge.getLogger().debug(java);
sponge.getLogger().debug(Java);
sponge.getLogger().debug(com);
