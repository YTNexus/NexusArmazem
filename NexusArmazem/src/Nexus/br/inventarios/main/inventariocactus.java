package Nexus.br.inventarios.main;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.intellectualcrafters.plot.api.PlotAPI;

import Nexus.br.armazem.main.main;
import Nexus.br.armazem.mysql.metodos;

public class inventariocactus implements Listener {

	public static void cactusvender(Player p, String Plot) {
		
		Inventory inv = Bukkit.createInventory(null, 5*9, "§8Vender cactus");
		
		PlotAPI api = new PlotAPI();
		String plotget = api.getPlot(p.getLocation()).getId().toString();
		
		if(metodos.getCactus(api.getPlot(p.getLocation()).getId().toString()) >= 0) {
			
		ItemStack item1 = new ItemStack(Material.STORAGE_MINECART);
		
		ItemMeta item1meta = item1.getItemMeta();
		item1meta.setDisplayName("§eVendar");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("§7Clique aqui para vender o total");
		lore.add("§7de §f" + metodos.getCactus(plotget) + " cactus!");
		lore.add("");
		lore.add("§7Irão dá o total de §f" + metodos.getCactus(plotget)*main.jp.getConfig().getInt("valores.cactus") + " §7coins");
		if(p.hasPermission("armazem.bonus")) {
			
			lore.add("§7Bônus por ser §6VIP: §f" + main.jp.getConfig().getDouble("bonus") + "%");
			
		}
		lore.add("");
		lore.add("§fClique aqui para confirmar a sua venda!");
		item1meta.setLore(lore);
		item1.setItemMeta(item1meta);
		inv.setItem(22, item1);
		
		}
		
		if(metodos.getCactus(api.getPlot(p.getLocation()).getId().toString()) <= 0) {
			
		ItemStack item1 = new ItemStack(Material.MINECART);
		
		ItemMeta item1meta = item1.getItemMeta();
		item1meta.setDisplayName("§eVendar");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("§cVocê não tem cactos para vender!");
		item1meta.setLore(lore);
		item1.setItemMeta(item1meta);
		inv.setItem(22, item1);
		
		}
		
		p.openInventory(inv);
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		
		Player p = (Player)e.getWhoClicked();
		Inventory inv = e.getInventory();
		
		if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) return;
		if (inv.getName().equals("§8Vender cactus")) {
			e.setCancelled(true);
			switch (e.getRawSlot()) {
				
			case 22:
				
				
				
				PlotAPI api = new PlotAPI();
				String getPlot = api.getPlot(p.getLocation()).getId().toString();
				int valor = main.jp.getConfig().getInt("valores.cactus");
				double bonus = main.jp.getConfig().getDouble("bonus");
				
				if(metodos.getCactus(getPlot) == 0) {
					
					p.closeInventory();
					p.sendMessage("§cVocê não tem cactus para vender!");
					break;
					
				}
				
				if(metodos.getCactus(getPlot) >= 0) {
					
					if(api.getPlot(p.getLocation()).getOwners().contains(p.getUniqueId())) {
						
						if(p.hasPermission("armazem.bonus")) {
							
							int x = metodos.getCactus(getPlot);
							main.econ.depositPlayer(p, valor*x*bonus);
							metodos.removeCactus(getPlot, x);
							p.sendMessage("§aVocê vendeu um total de §7" + x + " §acactus por um valor total de §7" + valor*x*bonus);
							p.closeInventory();
							
							return;
							
						}
						
						p.closeInventory();
						int x = metodos.getCactus(getPlot);
						main.econ.depositPlayer(p, valor*x);
						p.sendMessage("§aVocê vendeu um total de §7" + x + " §acactus por um valor total de $§7" + valor*x);
						metodos.removeCactus(getPlot, x);
						
					} else {
						
						p.sendMessage("§cApenas os donos do terreno podem vender os cactus!");
						p.closeInventory();
						
					}
					
					
				}
				
				break;
				
			}
		}
	}
}
