package dev.tildejustin.legacycrashfix.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.*;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Random;

@Mixin(BiomeDecorator.class)
public abstract class BiomeDecoratorMixin {
    @WrapMethod(method = "decorate")
    private void synchronizeDecorator(World world, Random random, Biome biome, BlockPos pos, Operation<Void> original) {
        synchronized (this) {
            original.call(world, random, biome, pos);
        }
    }
}
