package org.mig.joinpreference;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

public class playerHandler {
	private final joinPreference main;
	public static List <Player> players= new ArrayList<>();
	
	public playerHandler(joinPreference p){
		main = p;
	}
	public void addPlayer(Player p){
		players.add(p);
	}
	public void removePlayer(Player p){
		players.remove(p);
	}
	public Player getMostRecentJoin(){
		return players.get(players.size()-1);
	}
	public Player findLowerRank(int r){
		for(int i = players.size()-1; i >= 0; i--){
			Player p = players.get(i);
			groupGetter g = new groupGetter(main, p);
			if(g.getPlayerRank()>r){
				return p;
			}
		}
		return null;
	}
	public void kickPlayer(Player p){
		players.remove(p);
		p.kickPlayer(main.getConfig().getString("KickPlayerMessage"));
	}
	@SuppressWarnings("deprecation")
	public void fillPlayerList(){
		for(Player p: main.getServer().getOnlinePlayers()){
			if(!players.contains(p)){
				players.add(p);
			}
		}
	}
}
