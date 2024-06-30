package dev.tildejustin.legacycrashfix.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.class_0_123;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Random;

@Mixin(class_0_123.class)
public abstract class BiomeDecoratorMixin {
    @WrapMethod(method = "method_0_563")
    private void synchronizeDecorator(World world, Random random, Biome biome, BlockPos pos, Operation<Void> original) {
        synchronized (this) {
            original.call(world, random, biome, pos);
        }
    }
}
