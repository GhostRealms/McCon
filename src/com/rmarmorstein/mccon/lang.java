package com.rmarmorstein.mccon;

import org.bukkit.ChatColor;

public final class lang {
	
	private static McCon plugin;
	
	public lang(McCon instance) { 
		lang.setPlugin(instance);
	}

	public static McCon getPlugin() {
		return plugin;
	}

	public static void setPlugin(McCon plugin) {
		lang.plugin = plugin;
	}
	
	public static String colorize(String s) {
		String colorized;
		colorized = ChatColor.translateAlternateColorCodes('&', s);
		return colorized;
	}
	
	public static String getPrefix() {
		String old = getPlugin().getConfig().getString("lang.prefix");
		return colorize(old);
	}
	
	public static String getNoPermsMsg() {
		return getPrefix() + colorize(getPlugin().getConfig().getString("lang.no-permissions"));
	}
}
