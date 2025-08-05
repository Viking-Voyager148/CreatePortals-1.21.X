package net.voyager.createportals.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;

import java.util.*;

/**
 * Central registry for all known portals in the game.
 * Tracks all PortalEntry instances and allows lookup by name, position, and dimension.
 */
public class PortalRegistry {

    // Singleton instance
    private static PortalRegistry INSTANCE;

    // Master list of all registered portals
    private final Set<PortalEntry> allPortals = new HashSet<>();

    // Quick lookup: portals by name
    private final Map<String, List<PortalEntry>> portalsByName = new HashMap<>();

    // Quick lookup: portals by position & dimension
    private final Map<String, PortalEntry> portalsByLocation = new HashMap<>();

    private PortalRegistry() {}

    public static PortalRegistry getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PortalRegistry();
        }
        return INSTANCE;
    }

    /**
     * Registers a portal in the global registry.
     */
    public void registerPortal(PortalEntry entry) {
        if (entry == null || entry.name() == null) return;

        // Avoid duplicate entries at same location
        String key = generateKey(entry.position(), entry.dimension());
        if (portalsByLocation.containsKey(key)) return;

        allPortals.add(entry);

        portalsByName.computeIfAbsent(entry.name(), k -> new ArrayList<>()).add(entry);
        portalsByLocation.put(key, entry);
    }

    /**
     * Unregisters a portal from the registry.
     */
    public void unregisterPortal(BlockPos pos, ResourceKey<Level> dimension) {
        String key = generateKey(pos, dimension);
        PortalEntry entry = portalsByLocation.remove(key);
        if (entry == null) return;

        allPortals.remove(entry);
        List<PortalEntry> namedList = portalsByName.get(entry.name());
        if (namedList != null) {
            namedList.remove(entry);
            if (namedList.isEmpty()) portalsByName.remove(entry.name());
        }
    }

