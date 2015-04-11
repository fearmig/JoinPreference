package org.mig.joinpreference;

import java.util.Map;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commands implements CommandExecutor{
	groupGetter gg = new groupGetter(joinPreference.joinP);
	
	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if(args[0].equalsIgnoreCase("addgroup")){
			if(args.length==3){
				if(sender instanceof Player){
					Player p = (Player) sender;
					groupGetter gg = new groupGetter(joinPreference.joinP);
					if(gg.addGroup(args[1], Integer.parseInt(args[2])))
						p.sendMessage("Group succesfully added.");
				}
			}
		}
		if(args[0].equalsIgnoreCase("setgrouprank")){
			if(args.length==3){
				if(sender instanceof Player){
					Player p = (Player) sender;
					gg.setGroupRank(args[1], Integer.parseInt(args[2])); 
					p.sendMessage("Rank succesfully set.");
				}
			}
		}
		if(args[0].equalsIgnoreCase("listgroups")){
			if(args.length==1){
				if(sender instanceof Player){
					Map<String, Object> groups = gg.makeGroupList();
					Player p = (Player) sender;
					p.sendMessage("Groups: Rank");
					for(String s: groups.keySet()){
						p.sendMessage(s + ": " + joinPreference.joinP.getConfig().getInt("Groups."+s));
					}
				}
			}
		}
		if(args[0].equalsIgnoreCase("help")){
			if(args.length==1){
				if(sender instanceof Player){
					Player p = (Player) sender;
					p.sendMessage(ChatColor.GOLD + "Join Preference Help: " + "\n" + "\n" + ChatColor.RED + "/jpref addGroup (groupName) (rank)" + 
							ChatColor.WHITE + "     Adds a group and the group's rank." + "\n" + ChatColor.RED + "/jpref setGroupRank (groupName) (rank)" +
							ChatColor.WHITE + "     Change an existing groups rank." + "\n" + ChatColor.RED + "/jpref listGroups" +
							ChatColor.WHITE + "     List all groups and their ranks.");
				}
			}
		}
		return false;
	}
}
