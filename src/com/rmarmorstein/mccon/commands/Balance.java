package com.rmarmorstein.mccon.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.rmarmorstein.mccon.McCon;

public class Balance implements CommandExecutor {
	
	double bal;
	
	private McCon plugin;

	public Balance(McCon instance) {
		this.plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		// TODO Auto-generated method stub
		return false;
	}
}
