package dev.tildejustin.legacycrashfix.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import dev.tildejustin.legacycrashfix.NextTickListEntryHashSet;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.HashSet;

@Mixin(ServerWorld.class)
public abstract class ServerWorldMixin {
    @ModifyExpressionValue(method = "<init>", at = @At(value = "INVOKE", target = "Lcom/google/common/collect/Sets;newHashSet()Ljava/util/HashSet;"))
    private HashSet<?> useHashSetPreservingEqualitySemantics(HashSet<?> original) {
        return NextTickListEntryHashSet.newHashSet();
    }
}
