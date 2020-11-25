package Nexus.br.armaze.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.bukkit.Bukkit;

import Nexus.br.armazem.main.main;

public class connection extends main{

	public static Connection con = null;
	
	public static void open() {
		
		String USER = jp.getConfig().getString("database.user");
		String DATABASE = jp.getConfig().getString("database.database");
		String HOST = jp.getConfig().getString("database.host");
		String PASSWORD = jp.getConfig().getString("database.pass");
		Integer PORT = jp.getConfig().getInt("database.port");
		
		String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;
		
		try {
			
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			Bukkit.getConsoleSender().sendMessage(prefix + "§3Conexao com MySql aberta!");
			createTable();
			
		} catch (SQLException e) {
			
			Bukkit.getConsoleSender().sendMessage(prefix + "§3Nao foi possivel abrir conexao com o MySql!");
			Bukkit.getConsoleSender().sendMessage(prefix + "§3Desligando o plugin!");
			main.jp.getPluginLoader().disablePlugin(main.jp);
			
		}
		
	}
	
	public static void close() {
		
		if(con != null) {
			
			try {
				
				con.close();
				Bukkit.getConsoleSender().sendMessage(prefix + "§3Conexao com MySql foi encerrada com sucesso!");
				
			} catch (SQLException e) {
				
				Bukkit.getConsoleSender().sendMessage(prefix + "§3Nao foi possivel fechar conexao com MySql!");
				
			}
		}
	}
	
	public static void createTable() {
		
		if(con != null) {
			
			PreparedStatement stm = null;
			
			try {
				
				stm = con.prepareStatement("CREATE TABLE IF NOT EXISTS `armazem`(`id` INT NOT NULL AUTO_INCREMENT,`plot` VARCHAR(50) NULL, `cactus` INT NULL ,`abobora` INT NULL , `melancia` INT NULL ,PRIMARY KEY (`id`));");
				stm.executeUpdate();
				Bukkit.getConsoleSender().sendMessage(prefix + "§3Tabela carregada com sucesso!");
				
			} catch (SQLException e) {
				
				e.printStackTrace();
				Bukkit.getConsoleSender().sendMessage(prefix + "§3Nao foi possivel carregar a tabela");
				
			}
			
		}
		
	}
	
}
