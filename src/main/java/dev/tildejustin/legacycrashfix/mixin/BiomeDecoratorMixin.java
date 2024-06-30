package dev.tildejustin.legacycrashfix.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.world.World;
import net.minecraft.world.biome.*;
import org.spongepowered.asm.mixin.*;

import java.util.Random;

@Mixin(BiomeDecorator.class)
public abstract class BiomeDecoratorMixin {
    @WrapMethod(method = "method_3848", require = 0)
    private void synchronizeDecorating(World world, Random random, Biome biome, int i, int j, Operation<Void> original) {
        synchronized (this) {
            original.call(world, random, biome, i, j);
        }
    }

    @Dynamic
    @WrapMethod(method = "method_3848(Lnet/minecraft/world/World;Ljava/util/Random;II)V", require = 0)
    private void synchronizeDecorating(World world, Random random, int i, int j, Operation<Void> original) {
        synchronized (this) {
            original.call(world, random, i, j);
        }
    }
}
