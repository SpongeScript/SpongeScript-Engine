load("nashorn:mozilla_compat.js");

importClass(org.spongepowered.api.text.sink.MessageSinks);
importClass(org.spongepowered.api.text.Texts);

MessageSinks.toAll().sendMessage(Texts.of("Hello World 3"));
