package org.mig.joinpreference;

import org.bukkit.plugin.java.JavaPlugin;

public class JoinPreference extends JavaPlugin{
	public final ListenerClass l = new ListenerClass(this);
	public static JoinPreference joinP;
	
	//when the plugin is enabled this executes
	public void onEnable() {
		//create a config if one doesn't exist already
		getConfig().options().copyDefaults(true);
		saveConfig();
		
		//initialize GroupGetter
		@SuppressWarnings("unused")
		GroupGetter g = new GroupGetter(this, null);
		
		//set the command executer
		getCommand("jpref").setExecutor(new Commands());
		
		//register my event listiner
		getServer().getPluginManager().registerEvents(this.l, this);
		
		//initialize PlayerHandler
		PlayerHandler ph = new PlayerHandler(this);
		
		//fill up the online player list
		ph.fillPlayerList();
		
		
		joinP = this;
	}
	
	//on disable set static variable to null
	public void onDisable(){
		joinP = null;
	}
}
