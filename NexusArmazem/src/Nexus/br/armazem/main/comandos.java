package Nexus.br.armazem.main;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.intellectualcrafters.plot.api.PlotAPI;

import Nexus.br.armazem.mysql.metodos;

public class comandos implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lb, String[] args) {
		
		if(!(s instanceof Player)) {
			
			s.sendMessage("");
			s.sendMessage("§cApenas jogadores podem executar este comando!");
			s.sendMessage("");
			return true;
			
		}
		
		if(cmd.getName().equalsIgnoreCase("armazem")) {
			
			Player p = (Player)s;
			
			PlotAPI api = new PlotAPI();
				
			if(api.getPlot(p.getLocation()) != null) {
					
				if(api.getPlot(p.getLocation()).getOwners().contains(p.getUniqueId())) {
					
					String plot = api.getPlot(p.getLocation()).getId().toString();
					
					if(!metodos.contains(plot)) {
						
						metodos.setPlot(plot);
						
					}
					
					inventario.inv(p, plot);
					p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 1);
					
				} else if(api.getPlot(p.getLocation()).getTrusted().contains(p.getUniqueId())){
					
					String plot = api.getPlot(p.getLocation()).getId().toString();
					
					if(!metodos.contains(plot)) {
						
						metodos.setPlot(plot);
						
					}
					
					inventario.inv(p, plot);
					p.sendMessage("§aInventário abriu!");
					
				} else {
					
					p.sendMessage("§cVocê não tem permissão no terreno ou não tem trust!");
					
				}
				
					
			} else {
					
				p.sendMessage("§cVocê precisa está em um terreno!");
					
			}
			
		}
		
		return false;
	}

}
