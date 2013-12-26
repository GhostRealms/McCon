package com.rmarmorstein.mccon;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Balance implements CommandExecutor {
	
	private McCon plugin;
	
	public void Balance(McCon instance) {
		this.plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(args.length == 0) {
			return true;
		} else if(args.length == 1) {
			return true;
		} else {
			sender.sendMessage(Messages.inv_args);
			return false;
		}
	}

}
