package com.rmarmorstein.mccon.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.rmarmorstein.mccon.McCon;
import com.rmarmorstein.mccon.lang;

public class pay implements CommandExecutor {
	
	private McCon plugin;
	
	public pay(McCon instance) {
		this.plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(args.length == 2) {
			
			return true;
		} else {
			sender.sendMessage(lang.inv_args);
			return false;
		}
	}

}
