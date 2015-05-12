package org.mig.joinpreference;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.configuration.MemorySection;
import org.bukkit.entity.Player;

//Used to get the group of the player and deal with groups in general
public class GroupGetter {
	private String group;
	private List <String> groupList = new ArrayList<>();
	
	private final JoinPreference main;
	
	//constructors
	public GroupGetter(JoinPreference jp){
		this.main = jp;
	}
 	public GroupGetter(JoinPreference jp, Player p){
 		this.main = jp;
 		makeGroupList();
 		if(p!=null){
 			group = getGroup(p);
 		}
	}
	
 	//return a list of the groups that are listed in the config
	public Map<String, Object> makeGroupList(){
		return ((MemorySection) main.getConfig().get("Groups")).getValues(false);
	}
		
	//get a players group and if none listed return default
	public String getGroup(Player p){
		Map<String, Object> groups = makeGroupList();
		for(String s: groups.keySet()){
			if(!s.equals("default") && p.hasPermission("group."+s)){
				return s;
			}
		}
		return "default";
	}
	
	//set a groups rank in the preference order
	public void setGroupRank(String g, int i){
		main.getConfig().set("Groups."+g, i);
		main.saveConfig();
		main.reloadConfig();
	}
	
	//return a players group
	public String getPlayerGroup(){
		return group;
	}
	
	//return a groups rank
	public int getGroupRank(String g){
		if(main.getConfig().contains("Groups."+g))
			return (int) main.getConfig().get("Groups."+g);
		return 0;
	}
	
	
	//add a group to the config
	public boolean addGroup(String g, int r){
		if(!main.getConfig().contains("Groups."+g)){
			main.getConfig().set("Groups."+ g, r);
			main.saveConfig();
			main.reloadConfig();
			return true;
		}
		else{
			return false;
		}
	}
	
	//delete a group from the config
	public boolean delGroup(String g) {
		if(main.getConfig().contains("Groups."+g)){
			main.getConfig().set("Groups."+g, null);
			main.saveConfig();
			main.reloadConfig();
			return true;
		}
		return false;
	}
	
	//return a list of all the groups in the config
	public List<String> getGroupList(){
		Map<String, Object> groups = makeGroupList();
		for(String s: groups.keySet()){
			groupList.add(s);
		}
		return groupList;
	}
}
