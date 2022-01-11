package DaoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {
	private String host = "jdbc:mysql://localhost:3306/";
	private String user = "root";
	private String pass = "root";
	private String dbName = "bdpersonas";
	private static Conexion instancia;
	private Connection cn;
	
	private Conexion() {
		try
		{
			cn = DriverManager.getConnection(host+dbName,user,pass);
	
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static Conexion getConexion () {
		
		
		try {
			if(instancia==null) {
			instancia = new Conexion();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return instancia;
		
	}
	
	
	public Connection getSQLConnection() {
		return cn;
	}
	
	
	
	public void CerrarConexion() {
		
		try {
			cn.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	
	
	
}