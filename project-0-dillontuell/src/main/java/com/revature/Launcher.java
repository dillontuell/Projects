package com.revature;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.models.Menu;
import com.revature.utils.ConnectionUtil;

public class Launcher {

	public static void main(String[] args) {
		
		
		try(Connection conn = ConnectionUtil.getConnection()){
			System.out.println("CONNECTION SUCCESSFUL :)");
		} catch (SQLException e) { 
			System.out.println("connection failed... :(");
			e.printStackTrace();
		}
		
		
		Menu menu = new Menu();
		
		
		menu.displayMenu();
		
		
	}
	
}
		
		

		
	
	

