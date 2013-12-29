package com.rmarmorstein.mccon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.plugin.Plugin;

public class db {
	
	private Connection c = null;
	private McCon plugin;

	
	public db(Connection c, McCon instance) {
		this.c = c;
		this.plugin = instance;
	}
	
	public double getBalance(String user) {
		ResultSet rs;
		double balance;
		try {
			rs = c.createStatement().executeQuery("SELECT * FROM mccon WHERE username = '" + user + "';");
			rs.next();
			if(rs.getString("username") != null) { 
				balance = rs.getDouble("balance");
			} else {
				balance = plugin.getDefaultBalance();
			}
		} catch (SQLException e) {
			plugin.getLogger().severe("Error getting SQL data! Please Report this");
			e.printStackTrace();
			balance = plugin.getDefaultBalance();
		}
		return balance;
		
	}

}
