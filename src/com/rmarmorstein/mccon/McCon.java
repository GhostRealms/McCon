/**
 * McCon is Licensed under 
 * 
 * The Wonderful contributors
 * @author River Marmorstein
 * @author Rmarmorstein
 */



package com.rmarmorstein.mccon;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

public class McCon extends JavaPlugin {
	
	private Logger log;
	
	@Override
	public void onEnable() {
		log = this.getLogger();
		log.info("Starting Initialization of McCon v. " + this.getDescription().getVersion());
	}
	
	@Override
	public void onDisable() {
		
	}
	

}
