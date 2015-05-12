package org.mig.joinpreference;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

//This class handles just about everything with the player
public class PlayerHandler {

	private final JoinPreference main;
	
	//list of online players
	public static List <Player> players= new ArrayList<>();
	
	//constructor
	public PlayerHandler(JoinPreference p){
		main = p;
	}
	
	//add a player to the online player list
	public void addPlayer(Player p){
		players.add(p);
	}
	
	//remove a player from the online player list
	public void removePlayer(Player p){
		players.remove(p);
	}
	
	//get the most recently joined player
	public Player getMostRecentJoin(){
		return players.get(players.size()-1);
	}
	
	//check to see if a player is lower rank and if so return that player, if not
	//return null
	public Player findLowerRank(int r){
		for(int i = players.size()-1; i >= 0; i--){
			Player p = players.get(i);
			GroupGetter g = new GroupGetter(main, p);
			if(g.getGroupRank(g.getPlayerGroup())<r){
				return p;
			}
		}
		return null;
	}
	
	//kick a player from the game and display the kickmessage to them
	public void kickPlayer(Player p){
		players.remove(p);
		p.kickPlayer(main.getConfig().getString("KickPlayerMessage"));
	}
	
	//Fill the list with all online players
	@SuppressWarnings("deprecation")
	public void fillPlayerList(){
		for(Player p: main.getServer().getOnlinePlayers()){
			if(!players.contains(p)){
				players.add(p);
			}
		}
	}
}
