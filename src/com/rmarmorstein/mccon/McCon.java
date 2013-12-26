package com.rmarmorstein.mccon;

import java.sql.SQLException;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class McCon extends JavaPlugin {
	
	public Logger log;
	public static boolean debugmode = false;
	private Database db;
	
	@Override
	public void onEnable() {
		log = this.getLogger();
		if(this.getConfig().getBoolean("debug")) {
			debugmode = true;
		}
		Database db = new Database(this);
		this.db = db;
		log.info("McCon: v. " + this.getDescription().getVersion() + " has been loaded");
	}
	
	@Override
	public void onDisable() {
		
	}

}
