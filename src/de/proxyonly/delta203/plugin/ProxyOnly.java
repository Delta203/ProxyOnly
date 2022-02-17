package de.proxyonly.delta203.plugin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ProxyOnly extends JavaPlugin implements Listener {

	public static ProxyOnly plugin;
	
	@Override
	public void onEnable() {
		plugin = this;
		ConfigYML.create();
		ConfigYML.load();
		
		Bukkit.getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler
	public void onLogin(PlayerLoginEvent e) {
		Player p = e.getPlayer();
		if(ConfigYML.get().getBoolean("print_connection")) {
			Bukkit.getConsoleSender().sendMessage(p.getName() + " | " + p.getUniqueId());
			Bukkit.getConsoleSender().sendMessage("Address: " + e.getAddress().getAddress());
			Bukkit.getConsoleSender().sendMessage("Hostaddress: " + e.getAddress().getHostAddress());
			Bukkit.getConsoleSender().sendMessage("Hostname: " + e.getAddress().getHostName());
			Bukkit.getConsoleSender().sendMessage("Real-Hostaddress: " + e.getRealAddress().getHostAddress());
			Bukkit.getConsoleSender().sendMessage("Real-Hostname: " + e.getRealAddress().getHostName());
		}
		
		/* Check valid host address */
		if(!e.getRealAddress().getHostAddress().equals(ConfigYML.get().getString("ip"))) {
			e.disallow(PlayerLoginEvent.Result.KICK_OTHER, ConfigYML.get().getString("disallow_message").replace('&', '§'));
		}
	}
}
