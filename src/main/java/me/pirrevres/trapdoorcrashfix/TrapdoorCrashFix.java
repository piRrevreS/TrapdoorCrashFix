package me.pirrevres.trapdoorcrashfix;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.stream.Collectors;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class TrapdoorCrashFix extends JavaPlugin implements Listener {
    private final Cache<Block, Int> cache = CacheBuilder.newBuilder().expireAfterWrite(1L, TimeUnit.SECONDS).build();

    private int limit;

    private List<String> filter;

    private boolean report;

    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, (Plugin)this);
        saveDefaultConfig();
        reloadConfig();
        this.limit = getConfig().getInt("redstone-updates-limit");
        this.filter = (List<String>)getConfig().getStringList("filter").stream().map(String::toUpperCase).collect(Collectors.toList());
        this.report = getConfig().getBoolean("report-location");
    }

    @EventHandler
    public void onBlockRedstone(BlockRedstoneEvent e) {
        Block block = e.getBlock();
        if (!this.filter.isEmpty()) {
            String typeName = block.getType().name();
            Iterator<String> iterator = this.filter.iterator();
            while (true) {
                if (iterator.hasNext()) {
                    String type = iterator.next();
                    if (typeName.contains(type))
                        break;
                    continue;
                }
                return;
            }
        }
        Int detected = (Int)this.cache.getIfPresent(block);
        if (detected == null) {
            detected = new Int(1);
            this.cache.put(block, detected);
        } else {
            detected.add(1);
        }
        if (detected.intValue() > this.limit) {
            e.setNewCurrent(0);
            if (this.report && detected.intValue() == this.limit + 1) {
                getLogger().log(Level.WARNING, "TrapdoorCrash detected at " + format(block));
                detected.setValue(this.limit + 2);
            }
        }
    }

    private String format(Block block) {
        Location location = block.getLocation();
        return location.getWorld().getName() + ", " + location.getBlockX() + ", " + location.getBlockY() + ", " + location.getBlockZ();
    }
}