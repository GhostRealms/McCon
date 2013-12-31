/**
 * McCon is Licensed under GNU General Public License V3
 * For more information, please View the License in the GitHub Repository.
 * 
 * The Wonderful contributors
 * @author River Marmorstein
 * @author Rmarmorstein
 * 
 * People who we stole stuff from ;)
 * @author -_Husky_-
 * MySQL and Sqlite
 * 
 * @author Hidendra
 * Plugin Metrics
 * 
 * @author Gravity
 * Bukkit auto-updater :)
 */



package com.rmarmorstein.mccon;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import com.rmarmorstein.mccon.commands.Balance;
import com.rmarmorstein.mccon.commands.pay;
import com.rmarmorstein.mccon.database.MySQL;
import com.rmarmorstein.mccon.database.SQLite;
import com.rmarmorstein.mccon.external.Metrics;
import com.rmarmorstein.mccon.external.Updater;
import com.rmarmorstein.mccon.external.Updater.UpdateResult;

public class McCon extends JavaPlugin {
	
	private McCon mccon = this;
	private Logger log;
	
	//Configuration Values
	public int defaultbal;
	public boolean allownegbal;
	public int minbal;
	public int maxbal;
	
	//MySQL settings
	public boolean usingmysql;
	public String hostname;
	public String port;
	public String username;
	public String password;
	public String databasename;
	
	//Plugin Metrics and Updater Settings
	private boolean exploitServerOwnersWithMetricsCollections;
	private boolean shouldIUpdateForYouMaster;
	
	
	//Database Connection (We need this or we're screwed)
	public Connection c = null;
	private String tablename = null;
	
	//Some SQL Queries here for Easy (Lazy) changing
	String createTables;
	
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
		
		log.info("Grabbing the Economy settings...");
		
		defaultbal = this.getConfig().getInt("default-balance");
		minbal = this.getConfig().getInt("min-balance");
		allownegbal = this.getConfig().getBoolean("allow-negative");
		maxbal = this.getConfig().getInt("max-bal");
		
		log.info("Got the Economy values, grabbing Database info...");
		
		createTables = "CREATE TABLE IF NOT EXISTS `mccon` (Username varchar(16), Balance number(32));";
		
		
		String dbtype = this.getConfig().getString("database");
		if(dbtype.toLowerCase().equals("mysql")) {
			usingmysql = true;
			log.info("We're Using MySQL! Initializing the Database now...");
			hostname = this.getConfig().getString("mysql.host");
			port = this.getConfig().getString("mysql.port");
			databasename = this.getConfig().getString("mysql.database");
			username = this.getConfig().getString("mysql.user");
			password = this.getConfig().getString("mysql.pass");
			
			MySQL sql = new MySQL(this, hostname, port, databasename, username, password);
			c = sql.openConnection();
		} else {
			usingmysql = false;
			log.info("We're Using SQLite! Initializing the Database now...");
			SQLite sql = new SQLite(this, this.getDataFolder().toString());
			c = sql.openConnection();
		}
		
		db db = new db(c, this);
		
		log.info("Database Connection has been established... Using: " + dbtype);
		
		try {
			Statement stmt = c.createStatement();
			stmt.executeQuery(createTables);
		} catch (SQLException e) {
			log.severe("Database issues... :( Please report these.");
			log.severe("COULD NOT CREATE STATEMENT.");
			e.printStackTrace();
		}
		
		log.info("Registration of Commands and Listeners is now taking Place...");
		
		getCommand("balance").setExecutor(new Balance(mccon));
		getCommand("pay").setExecutor(new pay(mccon));
		getCommand("mccon").setExecutor(new com.rmarmorstein.mccon.commands.mccon(mccon));
		
	}
	
	@Override
	public void onDisable() {
		log.info("Attempting to close SQL connection...");
		try {
			c.close();
		} catch (SQLException e) {
			log.severe("Unable to close SQL connection! Please report!");
			e.printStackTrace();
		}
		
		log.info("SQL connection closed.. Disabling McCon!");
		
	}
	
	public double getDefaultBalance() {
		return defaultbal;
	}
	
	public boolean getAllowNegativeBalance() {
		return allownegbal;
	}
	
	public double getMinimumBalance() {
		if(allownegbal) {
			return minbal;
		} else {
			return 0;
		}
	}
	
	public double getMaximumBalance() {
			return maxbal;
	}
}
