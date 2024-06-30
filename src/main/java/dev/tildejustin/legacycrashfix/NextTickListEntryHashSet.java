package dev.tildejustin.legacycrashfix;

import net.minecraft.world.ScheduledTick;

import java.util.*;

// source: https://github.com/TheRandomLabs/RandomPatches/blob/1.12/src/main/java/com/therandomlabs/randompatches/hook/WorldServerHook.java
// see https://github.com/SleepyTrousers/EnderCore/issues/105#issuecomment-475957390
public final class NextTickListEntryHashSet extends HashSet<ScheduledTick> {
    private final transient Set<NextTickListEntryWrapper> backingSet = new HashSet<>();
    private final transient NextTickListEntryWrapper wrapper = new NextTickListEntryWrapper();

    public static HashSet<ScheduledTick> newHashSet() {
        return new NextTickListEntryHashSet();
    }

    @Override
    public int size() {
        return backingSet.size();
    }

    @Override
    public boolean contains(Object entry) {
        wrapper.entry = entry;
        return backingSet.contains(wrapper);
    }

    @Override
    public boolean add(ScheduledTick entry) {
        return backingSet.add(new NextTickListEntryWrapper(entry));
    }

    @Override
    public boolean remove(Object entry) {
        wrapper.entry = entry;
        return backingSet.remove(wrapper);
    }

    public static final class NextTickListEntryWrapper {
        Object entry;

        public NextTickListEntryWrapper() {
        }

        public NextTickListEntryWrapper(Object entry) {
            this.entry = entry;
        }

        @Override
        public int hashCode() {
            return entry.hashCode();
        }

        @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
        @Override
        public boolean equals(Object entry) {
            return this.entry.equals(((NextTickListEntryWrapper) entry).entry);
        }
    }
}
