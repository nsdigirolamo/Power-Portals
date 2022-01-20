package io.github.nsdigirolamo.powerportals.eventlisteners;

import io.github.nsdigirolamo.powerportals.models.Linkage;
import io.github.nsdigirolamo.powerportals.utils.LinkUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;

public class TeleportTriggerListener implements Listener {

    ArrayList<Linkage> linkages = LinkUtil.getLinkages();

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location location = player.getLocation();
        for (Linkage linkage: linkages) {
            for (Block block: linkage.getEntranceTriggers()) {
                if (block.getLocation().add(0.5, 0, 0.5).distance(location) <= 0.5) {
                    LinkUtil.triggerLinkage(player, linkage, block);
                    // Use return to break both for loops.
                    return;
                }
            }
        }
    }
}
