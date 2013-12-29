package com.rmarmorstein.mccon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	
	public void setBalance(String user, double amount) throws SQLException {
		Statement st = c.createStatement();
		st.executeUpdate("INSERT INTO mccon(username, balance) VALUES ('" + user + "', '" + amount + "');");
	}
	
	public void addBalance(String user, double amount) throws SQLException {
		double oldbal = getBalance(user);
		double newbal = oldbal + amount;
		if(newbal > plugin.getMaximumBalance()) {
			newbal = plugin.getMaximumBalance();
		} else if(newbal < plugin.getMinimumBalance()) {
			newbal = plugin.getMinimumBalance();
		}
		Statement st = c.createStatement();
		st.executeUpdate("INSERT INTO mccon(username, balance) VALUES ('" + user + "', '" + newbal + "');");
	}
	
	public void takeBalance(String user, double amount) throws SQLException {
		double oldbal = getBalance(user);
		double newbal = oldbal - amount;
		if(newbal < plugin.getMinimumBalance()) {
			
		}
		Statement st = c.createStatement();
		st.executeUpdate("INSERT INTO mccon(username, balance) VALUES ('" + user + "', '" + newbal + "');");
	}
	
	public boolean hasAccount(String user) { 
		ResultSet rs;
		try {
			rs = c.createStatement().executeQuery("SELECT * FROM mccon WHERE username = '" + user + "';");
			if(rs.getString("username") != null) {
				return true;
			} else {
				return false;
			}
		} catch(Exception ex) {
			plugin.getLogger().severe("SQL Error querying for account..");
			ex.printStackTrace();
			return false;
		}
	}

}
