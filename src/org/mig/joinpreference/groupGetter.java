package org.mig.joinpreference;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.configuration.MemorySection;
import org.bukkit.entity.Player;

public class groupGetter {
	private String group;
	private List <String> groupList = new ArrayList<>();
	
	private final joinPreference main;
	
	public groupGetter(joinPreference jp){
		this.main = jp;
	}
	
 	public groupGetter(joinPreference jp, Player p){
 		this.main = jp;
 		makeGroupList();
 		if(p!=null){
 			group = getGroup(p);
 		}
	}
	
	public Map<String, Object> makeGroupList(){
		return ((MemorySection) main.getConfig().get("Groups")).getValues(false);
	}
		
	public String getGroup(Player p){
		Map<String, Object> groups = makeGroupList();
		for(String s: groups.keySet()){
			if(!s.equals("default") && p.hasPermission("group."+s)){
				return s;
			}
		}
		return "default";
	}
	
	public void setGroupRank(String g, int i){
		main.getConfig().set("Groups."+g, i);
		main.saveConfig();
		main.reloadConfig();
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
	
	public int getGroupRank(String g){
		if(main.getConfig().contains("Groups."+g))
			return (int) main.getConfig().get("Groups."+g);
		return 0;
	}
	
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
	
	public boolean delGroup(String g) {
		if(main.getConfig().contains("Groups."+g)){
			main.getConfig().set("Groups."+g, null);
			main.saveConfig();
			main.reloadConfig();
			return true;
		}
		return false;
	}
	
	public List<String> getGroupList(){
		Map<String, Object> groups = makeGroupList();
		for(String s: groups.keySet()){
			groupList.add(s);
		}
		return groupList;
	}
}
