package com.rmarmorstein.mccon.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.rmarmorstein.mccon.McCon;
import com.rmarmorstein.mccon.Messages;

public class mccon implements CommandExecutor {
	
	private McCon plugin;
	
	public mccon(McCon instance) {
		this.plugin = instance;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
		if(args.length == 0) {
			sender.sendMessage(ChatColor.GRAY + "[McCon] " + ChatColor.GREEN + "McCon Version " + plugin.getDescription().getVersion());
			sender.sendMessage(ChatColor.GRAY + "[McCon] " + ChatColor.WHITE + "McCon is a Open-Source Economy plugin avalible on dev.bukkit.org!");
			sender.sendMessage(ChatColor.GRAY + "[McCon] " + ChatColor.WHITE + "The McCon project is Lead by " + ChatColor.RED + "Rmarmorstein");
			return true;
		} else if(args.length ==1) {
			if(args[0].equalsIgnoreCase("reload")) {
				if(sender.hasPermission("mccon.admin.reload")) {
					plugin.reloadConfig();
					plugin.getServer().getPluginManager().disablePlugin(plugin);
					plugin.getServer().getPluginManager().enablePlugin(plugin);
					sender.sendMessage(ChatColor.GRAY + "[McCon] " + ChatColor.GREEN + "McCon was reloaded successfully.");
					return true;
				} else {
					sender.sendMessage(Messages.no_perm);
					return false;
				}
			} else if(args[0].equalsIgnoreCase("info")) {
				sender.sendMessage(ChatColor.GRAY + "[McCon] " + ChatColor.GREEN + "McCon Version " + plugin.getDescription().getVersion());
				sender.sendMessage(ChatColor.GRAY + "[McCon] " + ChatColor.WHITE + "McCon is a Open-Source Economy plugin avalible on dev.bukkit.org!");
				sender.sendMessage(ChatColor.GRAY + "[McCon] " + ChatColor.WHITE + "The McCon project is Lead by " + ChatColor.RED + "Rmarmorstein");
				return true;
			} else {
				sender.sendMessage(Messages.inv_args);
				return false;
			}
		} else {
			sender.sendMessage(Messages.inv_args);
			return false;
		}
	}

}
