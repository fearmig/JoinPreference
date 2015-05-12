package org.mig.joinpreference;

import java.util.Map;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

//This class contains all the commands that a player can execute
public class Commands implements CommandExecutor{
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		
		GroupGetter gg = new GroupGetter(JoinPreference.joinP);
		
		//add a group to the config
		if(args[0].equalsIgnoreCase("addgroup")){
			if(args.length==3){
				if(sender instanceof Player){
					Player p = (Player) sender;
					if(gg.addGroup(args[1], Integer.parseInt(args[2])))
						p.sendMessage("Group succesfully added.");
				}
			}
		}
		
		//delete a group from the config
		if(args[0].equalsIgnoreCase("delgroup")){
			if(args.length==2){
				if(sender instanceof Player){
					Player p = (Player) sender;
					if(gg.delGroup(args[1]))
						p.sendMessage("Group succesfully deleted.");
				}
			}
		}
		
		//set a groups rank
		if(args[0].equalsIgnoreCase("setgrouprank")){
			if(args.length==3){
				if(sender instanceof Player){
					Player p = (Player) sender;
					gg.setGroupRank(args[1], Integer.parseInt(args[2])); 
					p.sendMessage("Rank succesfully set.");
				}
			}
		}
		
		//list all the groups
		if(args[0].equalsIgnoreCase("listgroups")){
			if(args.length==1){
				if(sender instanceof Player){
					Map<String, Object> groups = gg.makeGroupList();
					Player p = (Player) sender;
					p.sendMessage("Groups: Rank");
					for(String s: groups.keySet()){
						p.sendMessage(s + ": " + JoinPreference.joinP.getConfig().getInt("Groups."+s));
					}
				}
			}
		}
		
		//display a help message to the player
		if(args[0].equalsIgnoreCase("help")){
			if(args.length==1){
				if(sender instanceof Player){
					Player p = (Player) sender;
					p.sendMessage(ChatColor.GOLD + "Join Preference Help: " + "\n" + "\n" + ChatColor.RED + "/jpref addGroup (groupName) (rank)" + 
							ChatColor.WHITE + "     Adds a group and the group's rank." + "\n" + ChatColor.RED + "/jpref delGroup (groupName)"+ 
							ChatColor.WHITE + "     Remove a group from the config." + "\n" + ChatColor.RED + "/jpref setGroupRank (groupName) (rank)" +
							ChatColor.WHITE + "     Change an existing groups rank." + "\n" + ChatColor.RED + "/jpref listGroups" +
							ChatColor.WHITE + "     List all groups and their ranks.");
				}
			}
		}
		return false;
	}
}
