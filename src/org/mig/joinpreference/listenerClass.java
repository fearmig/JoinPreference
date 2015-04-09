package org.mig.joinpreference;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

public class listenerClass implements Listener{
	private final joinPreference main;
	
	public listenerClass(joinPreference p){
		this.main = p;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event){
		playerHandler ph = new playerHandler(main);
		ph.addPlayer(event.getPlayer());
	}
	
	@EventHandler
	public void onLoginAttempt(PlayerLoginEvent event){
		calculations c = new calculations(main);
		groupGetter g = new groupGetter(main, event.getPlayer());
		playerHandler p = new playerHandler(main);
		if(c.isFull()){
			if(g.getPlayerRank()!=-1){
				Player temp;
				temp = p.findLowerRank(g.getPlayerRank());
				if(temp!=null){
					p.kickPlayer(temp);
				}
				else{
					event.disallow(Result.KICK_OTHER ,main.getConfig().getString("PreJoinKickMessage"));
				}
			}
			else{
				event.disallow(Result.KICK_OTHER ,main.getConfig().getString("PreJoinKickMessage"));
			}
		}
	}
}
