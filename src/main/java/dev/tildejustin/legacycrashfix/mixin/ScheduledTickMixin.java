package dev.tildejustin.legacycrashfix.mixin;

import net.minecraft.util.ScheduledTick;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;

import java.util.concurrent.atomic.AtomicLong;

// source: https://github.com/KingContaria/seedqueue/blob/master/src/main/java/me/contaria/seedqueue/mixin/server/synchronization/ScheduledTickMixin.java
@Mixin(ScheduledTick.class)
public abstract class ScheduledTickMixin {
    @Unique
    private static final AtomicLong atomicIdCounter = new AtomicLong();

    @Mutable
    @Shadow
    private long id;

    @Redirect(method = "<init>", at = @At(value = "FIELD", target = "Lnet/minecraft/util/ScheduledTick;id:J", opcode = Opcodes.PUTFIELD))
    private void atomicIdCounter(ScheduledTick tick, long l) {
        this.id = atomicIdCounter.incrementAndGet();
    }
}
