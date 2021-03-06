package com.ryzor.cooldown2;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class MainCooldown extends JavaPlugin implements Listener {

	public HashMap<UUID, Integer> cdtime = new HashMap<UUID, Integer>();
	public int mastercd = this.getConfig().getInt("cooldown-time");

	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(new EventsClass(), this);
		this.getCommand("chatcooldown").setExecutor(new CommandCC());
		loadConfig();
		runnablerunner();

	}

	public void loadConfig() {
		getConfig().options().copyDefaults(true);
		saveConfig();
	}

	public void runnablerunner() {
		new BukkitRunnable() {

			@Override
			public void run() {

				if (cdtime.isEmpty()) {
					return;
				}

				for (UUID uuid : cdtime.keySet()) {
					int timeleft = cdtime.get(uuid);

					if (timeleft <= 0) {
						cdtime.remove(uuid);
					}else{
						cdtime.put(uuid, timeleft - 1);
					}
				}

			}

		}.runTaskTimer(this, 0, 20);
	}

}