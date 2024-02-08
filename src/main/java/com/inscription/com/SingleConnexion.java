package com.inscription.com;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnexion {
	
	static private Connection connexion ;
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useSSL=false","root","Kaba987k@");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnexion() {
		return connexion ;
	}
}


