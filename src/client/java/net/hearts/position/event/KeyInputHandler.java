package net.hearts.position.event;

import org.lwjgl.glfw.GLFW;

import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.hearts.position.config.AutoConfigInitializer;

public class KeyInputHandler {
    public static final String KEY_CATEGORY_HEARTS_POSITION = "key.categories.hearts.position";
    public static final String KEY_BINDING_HEARTS_POSITION = "key.binding.hearts.position";
    public static final String KEY_BINDING_HEARTS_POSITION_X_UP = "key.binding.hearts.position.x.up";
    public static final String KEY_BINDING_HEARTS_POSITION_Y_UP = "key.binding.hearts.position.y.up";
    public static final String KEY_BINDING_HEARTS_POSITION_X_DOWN = "key.binding.hearts.position.x.down";
    public static final String KEY_BINDING_HEARTS_POSITION_Y_DOWN = "key.binding.hearts.position.y.down";

    public static KeyBinding positionKey;
    public static KeyBinding xUpKey;
    public static KeyBinding xDownKey;
    public static KeyBinding yUpKey;
    public static KeyBinding yDownKey;

    public static boolean show = true;

    private static int getScreenWidth(){
        return MinecraftClient.getInstance().getWindow().getScaledWidth();
    }

    private static int getScreenHeight() {
        return MinecraftClient.getInstance().getWindow().getScaledHeight();
    }

    public static int calculateAdjustedPositionX(int adjustPercentX) {
        int screenWidth = getScreenWidth();
        // Convert the adjustment from percentage to pixels
        int adjustPixelsX = (int) (screenWidth * (adjustPercentX / 100.0));
        return adjustPixelsX;
    }

    public static int calculateAdjustedPositionY(int adjustPercentY) {
        int screenHeight = getScreenHeight();
        // Convert the adjustment from percentage to pixels
        int adjustPixelsY = (int) (screenHeight * (adjustPercentY / 100.0));
        return adjustPixelsY;
    }

    public static void registerKeysPressed(){
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (positionKey.wasPressed()) {
            show = !show;
        } else if (xUpKey.wasPressed()){
            AutoConfigInitializer config = AutoConfig.getConfigHolder(AutoConfigInitializer.class).getConfig();
            config.adjustHeartPosX += 1;
            AutoConfig.getConfigHolder(AutoConfigInitializer.class).save();
        } else if (xDownKey.wasPressed()){
            AutoConfigInitializer config = AutoConfig.getConfigHolder(AutoConfigInitializer.class).getConfig();
            config.adjustHeartPosX -= 1;
            AutoConfig.getConfigHolder(AutoConfigInitializer.class).save();
        } else if (yUpKey.wasPressed()){
            AutoConfigInitializer config = AutoConfig.getConfigHolder(AutoConfigInitializer.class).getConfig();
            config.adjustHeartPosY -= 1;
            AutoConfig.getConfigHolder(AutoConfigInitializer.class).save();
        } else if (yDownKey.wasPressed()){
            AutoConfigInitializer config = AutoConfig.getConfigHolder(AutoConfigInitializer.class).getConfig();
            config.adjustHeartPosY += 1;
            AutoConfig.getConfigHolder(AutoConfigInitializer.class).save();
        }
});
    }

    public static void registerKeys() {
        positionKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            KEY_BINDING_HEARTS_POSITION, 
            InputUtil.Type.KEYSYM, 
            GLFW.GLFW_KEY_H, 
            KEY_CATEGORY_HEARTS_POSITION
        ));

        xUpKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            KEY_BINDING_HEARTS_POSITION_X_UP, 
            InputUtil.Type.KEYSYM, 
            GLFW.GLFW_KEY_RIGHT, 
            KEY_CATEGORY_HEARTS_POSITION
        ));

        xDownKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            KEY_BINDING_HEARTS_POSITION_X_DOWN, 
            InputUtil.Type.KEYSYM, 
            GLFW.GLFW_KEY_LEFT, 
            KEY_CATEGORY_HEARTS_POSITION
        ));

        yUpKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            KEY_BINDING_HEARTS_POSITION_Y_UP, 
            InputUtil.Type.KEYSYM, 
            GLFW.GLFW_KEY_UP, 
            KEY_CATEGORY_HEARTS_POSITION
        ));

        yDownKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            KEY_BINDING_HEARTS_POSITION_Y_DOWN, 
            InputUtil.Type.KEYSYM, 
            GLFW.GLFW_KEY_DOWN, 
            KEY_CATEGORY_HEARTS_POSITION
        ));

        registerKeysPressed();
        
    }
}

