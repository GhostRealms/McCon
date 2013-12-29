/**
 * McCon is Licensed under GNU General Public License V3
 * For more information, please View the License in the GitHub Repository.
 * 
 * The Wonderful contributors
 * @author River Marmorstein
 * @author Rmarmorstein
 */



package com.rmarmorstein.mccon;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import com.rmarmorstein.mccon.external.Metrics;
import com.rmarmorstein.mccon.external.Updater;
import com.rmarmorstein.mccon.external.Updater.UpdateResult;

public class McCon extends JavaPlugin {
	
	private Logger log;
	
	//Configuration Values
	public int defaultbal;
	public boolean allownegbal;
	public int minbal;
	public int maxbal;
	
	//MySQL settings
	public boolean usingmysql;
	public String hostname;
	public int port;
	public String username;
	public String password;
	public String databasename;
	
	//Plugin Metrics and Updater Settings
	private boolean exploitServerOwnersWithMetricsCollections;
	private boolean shouldIUpdateForYouMaster;
	
	@Override
	public void onEnable() {
		log = this.getLogger();
		log.info("Starting Initialization of McCon v. " + this.getDescription().getVersion());
		this.saveDefaultConfig();
		log.info("Grabbing Config Values...");
		log.info("Checking to see if you want updates & Allow Metrics?");
		
		exploitServerOwnersWithMetricsCollections = this.getConfig().getBoolean("opt-out");
		shouldIUpdateForYouMaster = this.getConfig().getBoolean("updater");
		
		if(!exploitServerOwnersWithMetricsCollections) {
			log.info("You have Metrics Turned on! Thanks for your Statistics!!!!");
			try{
				Metrics metrics = new Metrics(this);
				metrics.start();
			} catch (Exception ex) {
				log.severe("You Opted for Metrics, but there was an error. Please report this!!!");
			}
		} else {
			log.info("Metrics are off! :( You can still use the Plugin though.");
		}
		
		if(shouldIUpdateForYouMaster) {
			log.info("You want Automagical Updates... Lets check for an Update!");
			Updater update = new Updater(this, 71080, this.getFile(), Updater.UpdateType.DEFAULT, true);
			if(update.getResult() == UpdateResult.SUCCESS) {
				log.info("We got the Latest version!");
			} else {
				log.info("No Update found, or update was unsuccessful.");
			}
		} else {
			log.info("You dont have Automatic updates enabled in the config.. Skipping this step.");
		}
		
		defaultbal = this.getConfig().getInt("default-balance");
		minbal = this.getConfig().getInt("min-balance");
		allownegbal = this.getConfig().getBoolean("allow-negative");
		maxbal = this.getConfig().getInt("max-bal");
		
		log.info("Got the Economy values, grabbing Database info...");
		
		String dbtype = this.getConfig().getString("database");
		if(dbtype.toLowerCase().equals("mysql")) {
			usingmysql = true;
			log.info("We're Using MySQL! Initializing the Database now...");
		} else {
			usingmysql = false;
			log.info("We're Using SQLite! Initializing the Database now...");
		}
		
		
		
	}
	
	@Override
	public void onDisable() {
		
	}
	

}
