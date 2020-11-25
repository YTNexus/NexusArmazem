package Nexus.br.armazem.main;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.intellectualcrafters.plot.api.PlotAPI;

import Nexus.br.armaze.mysql.metodos;
import Nexus.br.inventarios.main.inventarioabobora;
import Nexus.br.inventarios.main.inventariocactus;
import Nexus.br.inventarios.main.inventariomelancia;

public class inventario implements Listener{

	public static void inv(Player p, String plot) {
		
		Inventory inv = Bukkit.createInventory(null, 3*9, "§8Armazem");
		
		ItemStack item1 = new ItemStack(Material.CACTUS);
		ItemMeta item1meta = item1.getItemMeta();
		item1meta.setDisplayName("§aCactus");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("§7Clique com o §fbotão direito §7para coletar um inventário");
		lore.add("§7e com o §fesquerdo §7para vender os seus §fcactus!");
		lore.add("");
		lore.add("§7Terreno tem: §f" + metodos.getCactus(plot));
		item1meta.setLore(lore);
		item1.setItemMeta(item1meta);
		inv.setItem(11, item1);
		
		ItemStack item2 = new ItemStack(Material.MELON);
		ItemMeta item2meta = item2.getItemMeta();
		item2meta.setDisplayName("§aMelancia");
		ArrayList<String> lore2 = new ArrayList<String>();
		lore2.add("§7Clique com o §fbotão direito §7para coletar um inventário");
		lore2.add("§7e com o §fesquerdo §7para vender as suas §fmelancias!");
		lore2.add("");
		lore2.add("§7Terreno tem: §f" + metodos.getMelancia(plot));
		item2meta.setLore(lore2);
		item2.setItemMeta(item2meta);
		inv.setItem(13, item2);
		
		ItemStack item3 = new ItemStack(Material.PUMPKIN);
		ItemMeta item3meta = item3.getItemMeta();
		item3meta.setDisplayName("§aAbóbora!");
		ArrayList<String> lore3 = new ArrayList<String>();
		lore3.add("§7Clique com o §fbotão direito §7para coletar um inventário");
		lore3.add("§7e com o §fesquerdo §7para vender as suas §fabóbora!");
		lore3.add("");
		lore3.add("§7Terreno tem: §f" + metodos.getAbobora(plot));
		item3meta.setLore(lore3);
		item3.setItemMeta(item3meta);
		inv.setItem(15, item3);
		
		p.openInventory(inv);
		
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		
		Player p = (Player)e.getWhoClicked();
		ClickType Direito = ClickType.RIGHT;
		ClickType Esquerdo = ClickType.LEFT;
		Inventory inv = e.getInventory();
		
		if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) return;
		if (inv.getName().equals("§8Armazem")) {
			e.setCancelled(true);
			switch (e.getRawSlot()) {
				
			case 11:
				
				if(e.getClick() == Direito) {
					
					Integer check = 0;
					PlotAPI api = new PlotAPI();
					String getPlot = api.getPlot(p.getLocation()).getId().toString();
					int quantidademenor = 0;
					
					if(metodos.getCactus(getPlot) <= 0) {
						
						p.sendMessage("§cVocê não tem cactus suficientes!");
						p.closeInventory();
						return;
						
					}
					
					for (ItemStack item : p.getInventory().getContents()) {
						
						if (item == null) {
							
							if(metodos.getCactus(getPlot) >= 1 && metodos.getCactus(getPlot) < 64) {
								
								int valor = metodos.getCactus(getPlot);
								ItemStack cactus = new ItemStack(Material.CACTUS, valor);
								p.getInventory().addItem(cactus);
								metodos.removeCactus(getPlot, valor);
								quantidademenor = valor;
								p.closeInventory();
								
							}
							
							
							if(metodos.getCactus(getPlot) >= 64) {
								
								if(item == null) {
								
									check++;
									ItemStack cactus = new ItemStack(Material.CACTUS, 64);
									p.getInventory().addItem(cactus);
									metodos.removeCactus(getPlot, 64);
									
								}
								
							}
							
					    }
					}
						
						
					if(check == 0 && quantidademenor == 0) {
						
						p.sendMessage("§cSeu inventário está lotado!");
						p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1, 1);
						p.closeInventory();
						break;
						
					}
					
					
					int quantidade = check*64;
					int valor1 = quantidademenor + quantidade;
					
					p.sendMessage("§aVocê coletou o total de §7" + valor1 + "§a cactus!");
					p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 1);
					p.closeInventory();
					
				
				}
				
				if(e.getClick() == Esquerdo) {
					
					PlotAPI api = new PlotAPI();
					String plot = api.getPlot(p.getLocation()).getId().toString();
					inventariocactus.cactusvender(p, plot);
					
				}
				
				break;
				
				
			case 13:
				
				if(e.getClick() == Direito) {
					
					Integer check = 0;
					PlotAPI api = new PlotAPI();
					String getPlot = api.getPlot(p.getLocation()).getId().toString();
					int quantidademenor = 0;
					
					if(metodos.getMelancia(getPlot) <= 0) {
						
						p.sendMessage("§cVocê não tem melancias suficientes!");
						p.closeInventory();
						return;
						
					}
					
					for (ItemStack item : p.getInventory().getContents()) {
						
						if (item == null) {
							
							if(metodos.getMelancia(getPlot) >= 1 && metodos.getMelancia(getPlot) < 64) {
								
								int valor = metodos.getMelancia(getPlot);
								ItemStack cactus = new ItemStack(Material.MELON, valor);
								p.getInventory().addItem(cactus);
								metodos.removeMelancia(getPlot, valor);
								quantidademenor = valor;
								p.closeInventory();
								
							}
							
							
							if(metodos.getMelancia(getPlot) >= 64) {
								
								if(item == null) {
								
									check++;
									ItemStack cactus = new ItemStack(Material.MELON, 64);
									p.getInventory().addItem(cactus);
									metodos.removeMelancia(getPlot, 64);
									
								}
								
							}
							
					    }
					}
						
						
					if(check == 0 && quantidademenor == 0) {
						
						p.sendMessage("§cSeu inventário está lotado!");
						p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1, 1);
						p.closeInventory();
						break;
						
					}
					
					
					int quantidade = check*64;
					int valor1 = quantidademenor + quantidade;
					
					p.sendMessage("§aVocê coletou o total de §7" + valor1 + "§a melancias!");
					p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 1);
					p.closeInventory();
					
				
				}
				
				if(e.getClick() == Esquerdo) {
					
					PlotAPI api = new PlotAPI();
					String plot = api.getPlot(p.getLocation()).getId().toString();
					inventariomelancia.cactusvender(p, plot);
					
				}
				
				break;
				
			case 15:
				
				if(e.getClick() == Direito) {
					
					Integer check = 0;
					PlotAPI api = new PlotAPI();
					String getPlot = api.getPlot(p.getLocation()).getId().toString();
					int quantidademenor = 0;
					
					if(metodos.getAbobora(getPlot) <= 0) {
						
						p.sendMessage("§cVocê não tem abóboras suficientes!");
						p.closeInventory();
						return;
						
					}
					
					for (ItemStack item : p.getInventory().getContents()) {
						
						if (item == null) {
							
							if(metodos.getAbobora(getPlot) >= 1 && metodos.getAbobora(getPlot) < 64) {
								
								int valor = metodos.getAbobora(getPlot);
								ItemStack cactus = new ItemStack(Material.PUMPKIN, valor);
								p.getInventory().addItem(cactus);
								metodos.removeAbobora(getPlot, valor);
								quantidademenor = valor;
								p.closeInventory();
								
							}
							
							
							if(metodos.getAbobora(getPlot) >= 64) {
								
								if(item == null) {
								
									check++;
									ItemStack cactus = new ItemStack(Material.PUMPKIN, 64);
									p.getInventory().addItem(cactus);
									metodos.removeAbobora(getPlot, 64);
									
								}
								
							}
							
					    }
					}
						
						
					if(check == 0 && quantidademenor == 0) {
						
						p.sendMessage("§cSeu inventário está lotado!");
						p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1, 1);
						p.closeInventory();
						break;
						
					}
					
					
					int quantidade = check*64;
					int valor1 = quantidademenor + quantidade;
					
					p.sendMessage("§aVocê coletou o total de §7" + valor1 + "§a aboboras!");
					p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 1);
					p.closeInventory();
					
				
				}
				
				if(e.getClick() == Esquerdo) {
					
					PlotAPI api = new PlotAPI();
					String plot = api.getPlot(p.getLocation()).getId().toString();
					inventarioabobora.cactusvender(p, plot);
					
				}
				
				break;
				
			}
		}
	}
}
