package com.moneybags.tempfly.aesthetic;

import com.moneybags.tempfly.aesthetic.actionbar.ActionBar;
import com.moneybags.tempfly.aesthetic.actionbar.LegacyActionBar;
import com.moneybags.tempfly.aesthetic.actionbar.ModernActionBar;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.moneybags.tempfly.TempFly;

public class ActionBarAPI {
	
	private static ActionBar actionBar;
    
    public static void initialize(TempFly tempfly) {
        // Try modern ActionBar first (works with Bukkit 1.12+, including 1.20+ and 26.1.2+)
        try {
            // Check if modern API is available
            Class.forName("net.md_5.bungee.api.ChatMessageType");
            actionBar = new ModernActionBar(tempfly);
        } catch (ClassNotFoundException e) {
            // Fall back to legacy implementation
            actionBar = new LegacyActionBar(tempfly);
        } catch (Exception e) {
            // Log and fall back to legacy
            tempfly.getLogger().warning("Failed to initialize ModernActionBar: " + e.getMessage());
            actionBar = new LegacyActionBar(tempfly);
        }
    }
    
    public static void sendActionBar(final Player player, final String message) {
    	if (actionBar != null) {
    		actionBar.sendActionBar(player, message);
    	}
    }
    
    public static void sendActionBar(final Player player, final String message, int duration) {
    	if (actionBar != null) {
    		actionBar.sendActionBar(player, message, duration);
    	}
    }
}
