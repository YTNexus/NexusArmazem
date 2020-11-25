package Nexus.br.armazem.main;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import Nexus.br.armaze.mysql.connection;
import Nexus.br.inventarios.main.inventarioabobora;
import Nexus.br.inventarios.main.inventariocactus;
import Nexus.br.inventarios.main.inventariomelancia;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;

public class main extends JavaPlugin {

	public static String prefix = "§3[NexusArmazemV2] ";
	public static main jp;
	public static Economy econ = null;
	public static EconomyResponse r;
	private static final Logger log = Logger.getLogger("Minecraft");
	
	@Override
	public void onEnable() {
		
		jp = this;
		Bukkit.getConsoleSender().sendMessage(prefix + "Plugin iniciado!");
		getCommand("armazem").setExecutor(new comandos());
		getServer().getPluginManager().registerEvents(new cactus(), this);
		getServer().getPluginManager().registerEvents(new melancia(), this);
		getServer().getPluginManager().registerEvents(new abobora(), this);
		getServer().getPluginManager().registerEvents(new inventariocactus(), this);
		getServer().getPluginManager().registerEvents(new inventario(), this);
		getServer().getPluginManager().registerEvents(new inventariomelancia(), this);
		getServer().getPluginManager().registerEvents(new inventarioabobora(), this);
		saveDefaultConfig();
		connection.open();
		
		if (!setupEconomy() ) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
		
	}
	
	@Override
	public void onDisable() {
		
		Bukkit.getConsoleSender().sendMessage(prefix + "Plugin desligando!");
		connection.close();
		
	}
	
	private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
	
}
