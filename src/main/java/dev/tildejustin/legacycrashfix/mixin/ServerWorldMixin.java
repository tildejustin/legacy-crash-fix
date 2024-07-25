package dev.tildejustin.legacycrashfix.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import dev.tildejustin.legacycrashfix.NextTickListEntryHashSet;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;

import java.util.HashSet;

@Mixin(ServerWorld.class)
public abstract class ServerWorldMixin {
    @Dynamic
    @ModifyExpressionValue(
            method = {"<init>", /* 1.6- */ "<init>(Lnet/minecraft/server/MinecraftServer;Lnet/minecraft/class_101;Ljava/lang/String;ILnet/minecraft/class_1156;Lnet/minecraft/class_839;Lnet/minecraft/class_1555;)V"},
            at = @At(value = "NEW", target = "java/util/HashSet"), require = 1
    )
    private HashSet<?> useHashSetPreservingEqualitySemantics(HashSet<?> original) {
        return NextTickListEntryHashSet.newHashSet();
    }
}
