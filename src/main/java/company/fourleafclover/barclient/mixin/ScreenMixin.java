package company.fourleafclover.barclient.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Screen.class)
public class ScreenMixin {
    @Inject(method="sendMessage(Ljava/lang/String;Z)V",at=@At("HEAD"),cancellable = true)
    public void sendMessage(String message, boolean toHud, CallbackInfo ci) {
        if (message.startsWith(".")) {
            MinecraftClient.getInstance().player.sendMessage(Text.of("Prevented you from sending a message!"),false);
            ci.cancel();
        }
        System.out.println("User sent "+message+" to the server.");
    }
}




