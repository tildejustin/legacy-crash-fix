package dev.tildejustin.legacycrashfix.mixin;

import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Biome.class)
public class BiomeMixin {
    @Shadow
    @Final
    public static Biome OCEAN;

    // source: Legacy Ranked, RedLime
    @Inject(method = "byId", at = @At(value = "RETURN", ordinal = 0), cancellable = true, require = 0)
    private static void nullSafeById(int id, CallbackInfoReturnable<Biome> cir) {
        if (cir.getReturnValue() == null) {
            System.err.println("Biome ID '" + id + "' is null, defaulting to OCEAN");
            cir.setReturnValue(OCEAN);
        }
    }
}
