package Nexus.br.armazem.main;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;

import com.intellectualcrafters.plot.api.PlotAPI;

import Nexus.br.armazem.mysql.metodos;

public class cactus implements Listener{

	@EventHandler
	public void aoDropar(ItemSpawnEvent e) {
		
		PlotAPI api = new PlotAPI();
		Location loc = e.getLocation();
		
		if(api.getPlot(loc) != null) {
			
			if(e.getEntity().getItemStack().getType() == Material.CACTUS) {
				
				String plot = api.getPlot(loc).getId().toString();
				
				if(!metodos.contains(plot)) {
					
					metodos.setPlot(plot);
					
				}
				
				metodos.addCactus(plot, 1);
				e.setCancelled(true);
				
			}
			
		} else {
			
			return;
			
		}
		
	}
	
}
