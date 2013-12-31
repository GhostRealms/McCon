package com.rmarmorstein.mccon;

import org.bukkit.plugin.Plugin;

public class McConAPI {
	
	@SuppressWarnings("unused")
	private McCon plugin = new McCon();
	private Plugin newplugin;
	private String newpluginname;
	
	
	public McConAPI(Plugin p) {
		this.newplugin = p;
		this.newpluginname = p.getName();
	}
	
	

}
