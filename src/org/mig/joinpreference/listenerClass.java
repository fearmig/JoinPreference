package org.mig.joinpreference;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerQuitEvent;

//This class listens to the players actions and reacts to some of them
public class ListenerClass implements Listener{
	
	private final JoinPreference main;
	
	//constructor
	public ListenerClass(JoinPreference p){
		this.main = p;
	}
	
	//when a player joins add a player to the online player list.
	@EventHandler
	public void onJoin(PlayerJoinEvent event){
		PlayerHandler ph = new PlayerHandler(main);
		ph.addPlayer(event.getPlayer());
	}
	
	//when someone tries to login check to see if the server is full and react accordingly
	@EventHandler
	public void onLoginAttempt(PlayerLoginEvent event){
		Calculations c = new Calculations(main);
		GroupGetter g = new GroupGetter(main, event.getPlayer());
		PlayerHandler p = new PlayerHandler(main);
		//continue if the server is full
		if(c.isFull()){
			//if there is a player of lower rank kick that player and allow in the higher rank
			if(g.getGroupRank(g.getPlayerGroup())>0){
				Player temp;
				temp = p.findLowerRank(g.getGroupRank(g.getPlayerGroup()));
				if(temp!=null){
					p.kickPlayer(temp);
				}
				else{
					event.disallow(Result.KICK_OTHER ,main.getConfig().getString("PreJoinKickMessage"));
				}
			}
			//if there is no player of lower rank don't allow the player to join
			else{
				event.disallow(Result.KICK_OTHER ,main.getConfig().getString("PreJoinKickMessage"));
			}
		}
	}
	
	//when a player leaves remove them from the online players list
	@EventHandler
	public void onLeave(PlayerQuitEvent event){
		PlayerHandler ph = new PlayerHandler(main);
		ph.removePlayer(event.getPlayer());
	}
}
