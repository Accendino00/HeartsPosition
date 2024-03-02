package net.hearts.position;

import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.hearts.position.event.KeyInputHandler;
import me.shedaniel.autoconfig.AutoConfig;
import net.hearts.position.config.AutoConfigInitializer;


public class HeartsPositionClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		KeyInputHandler.registerKeys();
		AutoConfig.register(AutoConfigInitializer.class, GsonConfigSerializer::new);
	}

}