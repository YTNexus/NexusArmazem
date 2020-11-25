package Nexus.br.armaze.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;

import Nexus.br.armazem.main.main;

public class metodos extends connection{

	public static boolean contains(String plot) {
		
		PreparedStatement stm = null;
		
		try {
			
			stm = con.prepareStatement("SELECT * FROM `armazem` WHERE `plot` = ?;");
			stm.setString(1, plot.toLowerCase());
			ResultSet rs = stm.executeQuery(); 
			while(rs.next()) {
				
				return true;
				
			}
			
			return false;
			
		} catch (SQLException e) {
			
			return false;
			
		}
		
	}
	
	public static void setPlot(String plot) {
		
		PreparedStatement stm = null;
		
		try {
			
			stm = con.prepareStatement("INSERT INTO `armazem` (`plot`, `cactus`, `abobora`, `melancia`) VALUES (?,?,?,?);");
			stm.setString(1, plot.toLowerCase());
			stm.setInt(2, 0);
			stm.setInt(3, 0);
			stm.setInt(4, 0);
			stm.executeUpdate();
			
			Bukkit.getConsoleSender().sendMessage(main.prefix + "§6O terreno " + plot + " foi criado com sucesso!");
			
		} catch (SQLException e) {
			
			Bukkit.getConsoleSender().sendMessage(main.prefix + "§6Nao foi possivel colocar o jogador " + plot);
			e.printStackTrace();
			
		}
	}
	
	public static int getCactus(String plot) {
		
		if(contains(plot)) {
			
			PreparedStatement stm = null;
			try {
				
				stm = con.prepareStatement("SELECT * FROM `armazem` WHERE `plot` = ?;");
				stm.setString(1, plot.toLowerCase());
				ResultSet rs = stm.executeQuery();
				
				while (rs.next()) {
					
					return rs.getInt("cactus");
					
				}
				
			} catch (SQLException e) {
				
				Bukkit.getConsoleSender().sendMessage("Erro grave!!! contacte rapido o dono do plugin!");
				e.printStackTrace();
				
			}
			
			return 0;
			
		} else {
			
			setPlot(plot);
			return 0;
			
		}
		
	}
	
	public static int getAbobora(String plot) {
		
		if(contains(plot)) {
			
			PreparedStatement stm = null;
			try {
				
				stm = con.prepareStatement("SELECT * FROM `armazem` WHERE `plot` = ?;");
				stm.setString(1, plot.toLowerCase());
				ResultSet rs = stm.executeQuery();
				
				while (rs.next()) {
					
					return rs.getInt("abobora");
					
				}
				
			} catch (SQLException e) {
				
				Bukkit.getConsoleSender().sendMessage("Erro grave!!! contacte rapido o dono do plugin!");
				e.printStackTrace();
				
			}
			
			return 0;
			
		} else {
			
			setPlot(plot);
			return 0;
			
		}
		
	}
	
	public static int getMelancia(String plot) {
		
		if(contains(plot)) {
			
			PreparedStatement stm = null;
			try {
				
				stm = con.prepareStatement("SELECT * FROM `armazem` WHERE `plot` = ?;");
				stm.setString(1, plot.toLowerCase());
				ResultSet rs = stm.executeQuery();
				
				while (rs.next()) {
					
					return rs.getInt("melancia");
					
				}
				
			} catch (SQLException e) {
				
				Bukkit.getConsoleSender().sendMessage("Erro grave!!! contacte rapido o dono do plugin!");
				e.printStackTrace();
				
			}
			
			return 0;
			
		} else {
			
			setPlot(plot);
			return 0;
			
		}
		
	}
	
	public static void setCactus(String plot, int quantidade) {
		
		if(contains(plot)) {
			
			PreparedStatement stm = null;
			
			try {
				
				stm = con.prepareStatement("UPDATE `armazem` SET `cactus` = ? WHERE `plot` = ?");
				stm.setInt(1, quantidade);
				stm.setString(2, plot.toLowerCase());
				stm.executeUpdate();
				
			} catch (SQLException e) {
				
				Bukkit.getConsoleSender().sendMessage(main.prefix + "§6Nao foi possivel setar a quantidade ao jogador " + plot);
				e.printStackTrace();
				
			}
			
		} else {
			
			setPlot(plot);
			
		}
		
	}
	
	public static void setMelancia(String plot, int quantidade) {
		
		if(contains(plot)) {
			
			PreparedStatement stm = null;
			
			try {
				
				stm = con.prepareStatement("UPDATE `armazem` SET `melancia` = ? WHERE `plot` = ?");
				stm.setInt(1, quantidade);
				stm.setString(2, plot.toLowerCase());
				stm.executeUpdate();
				
			} catch (SQLException e) {
				
				Bukkit.getConsoleSender().sendMessage(main.prefix + "§6Nao foi possivel setar a quantidade ao jogador " + plot);
				e.printStackTrace();
				
			}
			
		} else {
			
			setPlot(plot);
			
		}
		
	}
	
	public static void setAbobora(String plot, int quantidade) {
		
		if(contains(plot)) {
			
			PreparedStatement stm = null;
			
			try {
				
				stm = con.prepareStatement("UPDATE `armazem` SET `abobora` = ? WHERE `plot` = ?");
				stm.setInt(1, quantidade);
				stm.setString(2, plot.toLowerCase());
				stm.executeUpdate();
				
			} catch (SQLException e) {
				
				Bukkit.getConsoleSender().sendMessage(main.prefix + "§6Nao foi possivel setar a quantidade ao jogador " + plot);
				e.printStackTrace();
				
			}
			
		} else {
			
			setPlot(plot);
			
		}
		
	}
	
	public static void addAbobora(String plot, int quantidade) {
		
		if(contains(plot)) {
			
			setAbobora(plot, getAbobora(plot) + quantidade);
			
		} else {
			
			setPlot(plot);
			
		}
		
	}
	
	public static void addMelancia(String plot, int quantidade) {
		
		if(contains(plot)) {
			
			setMelancia(plot, getMelancia(plot) + quantidade);
			
		} else {
			
			setPlot(plot);
			
		}
		
	}
	
	public static void addCactus(String plot, int quantidade) {
		
		if(contains(plot)) {
			
			setCactus(plot, getCactus(plot) + quantidade);
			
		} else {
			
			setPlot(plot);
			
		}
		
	}
	
	public static void removeCactus(String plot, int quantidade) {
		
		if(contains(plot)) {
			
			setCactus(plot, getCactus(plot) - quantidade);
			
		} else {
			
			setPlot(plot);
			
		}
		
	}
	
	public static void removeMelancia(String plot, int quantidade) {
		
		if(contains(plot)) {
			
			setMelancia(plot, getMelancia(plot) - quantidade);
			
		} else {
			
			setPlot(plot);
			
		}
		
	}
	
	public static void removeAbobora(String plot, int quantidade) {
		
		if(contains(plot)) {
			
			setAbobora(plot, getAbobora(plot) - quantidade);
			
		} else {
			
			setPlot(plot);
			
		}
		
	}
	
}
