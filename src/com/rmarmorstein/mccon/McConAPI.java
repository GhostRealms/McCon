package com.rmarmorstein.mccon;

import java.util.List;

public class McConAPI {
	
	private static McCon plugin;
	public static String[] dbop = {"flatfile", "mysql", "sqlite", "redis"};
	
	public McConAPI(McCon p) {
		McConAPI.setPlugin(p);
	}
	
	public static McCon getPlugin() {
		return plugin;
	}

	public static void setPlugin(McCon plugin) {
		McConAPI.plugin = plugin;
	
	
	public static String getCurrencyName() {
		return getPlugin().getConfig().getString("currency-name");
	}
	
	public static String getCurrencyNamePlural() {
		return getPlugin().getConfig().getString("currency-plural");
	}
	
	public static String getCurrencySymbol() {
		return getPlugin().getConfig().getString("currency-symbol");
	}
	
	public static String getDatabaseTypeName() {
		return getPlugin().getConfig().getString("datbase");
	}
	
	public static boolean getPurgeAccount() {
		boolean bool = getPlugin().getConfig().getBoolean("purge-accpunts");
		return bool;
	}
	
	public static boolean getAllowNegative() {
		return getPlugin().getConfig().getBoolean("allow-negative");
	}
	
	public static double getMinimumBalance() {
		if(getAllowNegative()) {
			return getPlugin().getConfig().getDouble("min-balance");
		} else {
			return -1;
		}
	}
	
	public static double getMaxBalance() {
		return getPlugin().getConfig().getDouble("max-balance");
	}
	
	protected static boolean getMetricsOptOut() {
		return getPlugin().getConfig().getBoolean("opt-out");
	}
	
	public static boolean getShouldUseMetrics() {
		boolean old = getMetricsOptOut();
		boolean newVal;
		if(old) {
			newVal = false;
		} else {
			newVal = true;
		}
		
		return newVal;
	}
	
	public static boolean getUpdateAllow() {
		return getPlugin().getConfig().getBoolean("updater");
	}
}
