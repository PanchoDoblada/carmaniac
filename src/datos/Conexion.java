package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Conexion {

	public static void main(String[] args) {
		
		try {
			
			//Crear conexion
			
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/carmaniac", "root", "");
			
			//Crear Statement
			
			Statement statement = conexion.createStatement();		
			
		}catch(Exception e) {
			System.out.println("No conecta");
			
			e.printStackTrace();
		}

	}

}
