package com.ryzor.cooldown2;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class CommandCC implements CommandExecutor {

	private MainCooldown plugin = MainCooldown.getPlugin(MainCooldown.class);
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			
			Player pl = (Player) sender;
			
			if(args.length == 1) {
				if(args[0].equals("reload")) {
					plugin.reloadConfig();
					pl.sendMessage(ChatColor.YELLOW + "[CC] " + ChatColor.GREEN + "Config reload success!");
					return true;
				}
			} else {
				pl.sendMessage(ChatColor.YELLOW + "[CC] " + ChatColor.RED + "Please input an argument!");
				return true;
			}
		} else {
			sender.sendMessage(ChatColor.YELLOW + "[CC] " + ChatColor.RED + "You must be a player to execute this command!");
			return true;
		}
		return false;
	}

}
