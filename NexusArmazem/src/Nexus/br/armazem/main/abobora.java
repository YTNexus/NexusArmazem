package Nexus.br.armazem.main;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;

import com.intellectualcrafters.plot.api.PlotAPI;

import Nexus.br.armaze.mysql.metodos;

public class abobora implements Listener{

	@EventHandler
	public void aoDropar(ItemSpawnEvent e) {
		
		PlotAPI api = new PlotAPI();
		Location loc = e.getLocation();
		
		if(api.getPlot(loc) != null) {
			
			if(e.getEntity().getItemStack().getType() == Material.PUMPKIN) {
			
				String plot = api.getPlot(loc).getId().toString();
				
				if(!metodos.contains(plot)) {
					
					metodos.setPlot(plot);
					
				}
				
				metodos.addAbobora(plot, 1);
				e.setCancelled(true);
				
			}
			
		} else {
			
			return;
			
		}
		
	}
	
}
