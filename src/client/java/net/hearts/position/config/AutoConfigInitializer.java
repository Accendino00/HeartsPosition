package net.hearts.position.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "heartsposition")
public class AutoConfigInitializer implements ConfigData {
    @ConfigEntry.Gui.Tooltip
    public int adjustHeartPosX = 59;
    
    @ConfigEntry.Gui.Tooltip
    public int adjustHeartPosY = 54;
    
}
