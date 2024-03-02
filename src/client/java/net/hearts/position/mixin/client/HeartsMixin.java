package net.hearts.position.mixin.client;

import net.hearts.position.HeartType;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.shedaniel.autoconfig.AutoConfig;
import net.hearts.position.event.KeyInputHandler;
import net.hearts.position.config.AutoConfigInitializer;

@Mixin(InGameHud.class)
public class HeartsMixin {

        @Inject(method = "renderHealthBar", at = @At("HEAD"))
        public void renderHealthBar0(DrawContext context, PlayerEntity player, int x, int y, int lines, int regeneratingHeartIndex, float maxHealth, int lastHealth, int health, int absorption, boolean blinking, CallbackInfo ci){
            if(KeyInputHandler.show){
            
            int i = 9 * (player.getWorld().getLevelProperties().isHardcore() ? 5 : 0);
            int j = MathHelper.ceil((double)maxHealth / 2.0);
            int k = MathHelper.ceil((double)absorption / 2.0);
            int l = j * 2;
            
            for(int m = j + k - 1; m >= 0; --m) {
                int n = m / 10;
                int o = m % 10;
                int p = ( (KeyInputHandler.calculateAdjustedPositionX(AutoConfig.getConfigHolder(AutoConfigInitializer.class).getConfig().adjustHeartPosX))) - n * lines; // x-coordinate
                int q = ( (KeyInputHandler.calculateAdjustedPositionY(AutoConfig.getConfigHolder(AutoConfigInitializer.class).getConfig().adjustHeartPosY))) - o * 8; // y-coordinate
                if (lastHealth + absorption <= 4) {
                    p += 23;
                }
                
                if (m < j && m == regeneratingHeartIndex) {
                    p -= 2;
                }
                
                drawHeart(context, HeartType.EMPTY, p, q, i, blinking, false);
                int r = m * 2;
                boolean bl = m >= j;
                if (bl) {
                    int s = r - l;
                    if (s < absorption) {
                        boolean bl2 = s + 1 == absorption && absorption % 2 != 0;
                        drawHeart(context, bl2 ? HeartType.YELLOW_HALF : HeartType.YELLOW_FULL, p, q, i, false, bl2);
                    }
                }
                
                boolean bl3;
                /*if (blinking && r < health) {
                    bl3 = r + 1 == health && r % 2 != 0;
                    drawHeart(context, bl3 ? HeartType.RED_HALF : HeartType.RED_FULL, p, q, i, false, bl3);
                }*/
                
                if (r < lastHealth) {
                    bl3 = r + 1 == lastHealth && lastHealth % 2 != 0;
                    drawHeart(context, bl3 ? HeartType.RED_HALF : HeartType.RED_FULL, p, q, i, false, bl3);
                }
            }
            }
        }
        
        private void drawHeart(DrawContext context, HeartType type, int x, int y, int v, boolean blinking, boolean halfHeart) {
            Identifier ICONS = new Identifier("textures/gui/icons.png");
            context.drawTexture(ICONS, x, y, type.u, v, 9, 9);
        }
        
    }