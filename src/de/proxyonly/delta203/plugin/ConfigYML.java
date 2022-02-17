package de.proxyonly.delta203.plugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigYML {

	private static File file = new File(ProxyOnly.plugin.getDataFolder(), "config.yml");
	private static FileConfiguration cfg; 
	
	public static FileConfiguration get() {
		return cfg;
	}
	
	public static void load() {
		try {
			cfg.load(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}

	public static void save() {
		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void create() {
		if(!ProxyOnly.plugin.getDataFolder().exists()) {
			ProxyOnly.plugin.getDataFolder().mkdir();
		}
		
		try {
	        if(!file.exists()) {
	        	java.nio.file.Files.copy(ProxyOnly.plugin.getResource("config.yml"), file.toPath());	
	        	cfg = YamlConfiguration.loadConfiguration(file);
	    	}else {
	    		cfg = YamlConfiguration.loadConfiguration(file);
	    	}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
