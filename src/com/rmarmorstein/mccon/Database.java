package com.rmarmorstein.mccon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.bukkit.Bukkit;

public class Database {
	
	Connection c = null;
	private McCon plugin;
	
	public Database(McCon plugin) {
		this.plugin = plugin;
		try {
			Class.forName("java.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:balances.db");
			createTables(c);
			if(!c.isClosed()) {
				c.close();
			}
		} catch(Exception ex) {
			if(McCon.debugmode) {
				ex.printStackTrace();
				Bukkit.getPluginManager().disablePlugin(plugin);
			} else {
				plugin.log.severe("An SQL Error has Occored. Disabling McCon. Please Enable Debug Mode.");
				Bukkit.getPluginManager().disablePlugin(plugin);
			}
		}
		plugin.log.info("McCon Database initialized.");
	}
	
	public void createTables(Connection c) {
		try {
		Statement stmt = null;
		stmt = c.createStatement();
		String sql = "CREATE TABLE IF NOT EXISTS balance" +
				"(NAME TEXT PRIMARY KEY NOT NULL," +
				"BALANCE REAL)";
		stmt.executeUpdate(sql);
		stmt.close();
		c.commit();
		c.close();
		} catch (Exception ex) {
			if(McCon.debugmode) {
				ex.printStackTrace();
				Bukkit.getPluginManager().disablePlugin(plugin);
			} else {
				plugin.log.severe("McCon has encountered a Database Error. Please Enable Debug Mode.");
				plugin.log.severe("McCon now Disabling");
				Bukkit.getPluginManager().disablePlugin(plugin);
			}
		}
		
	}
	
	public Connection getConnection() {
		return c;
	}
	
	public double getBalance(String user) {
		String name = user;
		
	}

}
