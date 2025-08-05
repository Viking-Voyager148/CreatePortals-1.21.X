package net.voyager.createportals.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;

import java.util.UUID;


public record PortalEntry(String name, BlockPos position, ResourceKey<Level> dimension, UUID owner) {
}