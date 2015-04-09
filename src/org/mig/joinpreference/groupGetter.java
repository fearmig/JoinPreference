package org.mig.joinpreference;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

public class groupGetter {
	private String group;
	private Player player;
	private int rank;
	private List <String> groupList = new ArrayList<>();
	
	private final joinPreference main;
	
 	public groupGetter(joinPreference jp, Player p){
 		this.main = jp;
 		makeGroupList();
 		if(p!=null){
 			player = p;
 			setPlayerGroup();
 		}
	}
	
	private void makeGroupList(){
		groupList = main.getConfig().getStringList("Groups");
	}
	
	/*public void setRank(String g, int i){
		main.getConfig().set("Groups."+g, i);
		main.saveConfig();
		main.reloadConfig();
	}*/
	private void setPlayerGroup(){
		for(int i = 0; i < groupList.size(); i++){
			if(player.hasPermission("group."+groupList.get(i))){
				if(group ==null){
					group = groupList.get(i);
					setPlayerRank();
				}
				else{
					if(getRank(group)> getRank(groupList.get(i))){
						group = groupList.get(i);
						setPlayerRank();
					}
				}
			}
		}
	}
	private void setPlayerRank(){
		rank = getRank(group);
	}
	
	public int getRank(String g){
		for(int i = 0; i < groupList.size(); i++){
			if(groupList.get(i).equals(g))
				return i;		
		}
		return -1;
	}
	
	public String getPlayerGroup(){
		return group;
	}
	
	public int getPlayerRank(){
		return rank;
	}
}
