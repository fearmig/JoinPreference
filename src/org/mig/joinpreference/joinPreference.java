package org.mig.joinpreference;

import org.bukkit.plugin.java.JavaPlugin;

public class joinPreference extends JavaPlugin{
	public final listenerClass l = new listenerClass(this);
	static joinPreference joinP;
	
	public void onEnable() {
		getConfig().options().copyDefaults(true);
		saveConfig();
		@SuppressWarnings("unused")
		groupGetter g = new groupGetter(this, null);
		getCommand("jpref").setExecutor(new commands());
		getServer().getPluginManager().registerEvents(this.l, this);
		playerHandler ph = new playerHandler(this);
		ph.fillPlayerList();
		joinP = this;
	}
	public void onDisable(){
		joinP = null;
	}
}
