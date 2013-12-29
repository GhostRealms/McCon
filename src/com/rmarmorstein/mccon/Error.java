package com.rmarmorstein.mccon;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Error {
	
	public enum ErrorType {
		INSUFFICENTFUNDS,
		NOPERMS,
		INVALIDARGUMENTS;
		
	}
	
	Player p;
	
	public Error(String username, boolean shouldILog, ErrorType ertype) {
		if(username.toUpperCase() != "CONSOLE") {
			p = Bukkit.getPlayer(username);
		} else {
			p = null;
		}
		
		if(p == null) {
			
		}
		
	}

}
