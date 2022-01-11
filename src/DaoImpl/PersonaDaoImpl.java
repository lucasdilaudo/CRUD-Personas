package DaoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Dao.PersonaDao;
import entidades.Persona;

public class PersonaDaoImpl implements PersonaDao{

	private static String delete = "DELETE FROM personas WHERE Dni=?";
	private static String insert = "INSERT INTO personas(Dni,Nombre,Apellido) VALUES (?,?,?)";
	private static String select = "SELECT * FROM personas WHERE Dni=?";	
	private static String readall = "SELECT * FROM personas";
	private static String update = "UPDATE personas SET Apellido = ?, Nombre = ? WHERE Dni = ?";
	public PersonaDaoImpl() {
		
	}
	

	
	
	@Override
	public int AgregarPersona(Persona p) {
		int filas=0;
		try {
			Connection cn = Conexion.getConexion().getSQLConnection();
			PreparedStatement pst = cn.prepareStatement(insert);
			pst.setString(1, p.getDni());
			pst.setString(2, p.getNombre());
			pst.setString(3, p.getApellido());
			filas = pst.executeUpdate();		
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filas;
	}




	@Override
	public Persona obtenerPersona(String dni) {
		Persona x = new Persona();
		Connection cn = Conexion.getConexion().getSQLConnection();
		try
		{
			
		
			PreparedStatement pst = cn.prepareStatement(select);
			pst.setString(1, dni);
		
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				x.setDni(rs.getString(1));
				x.setNombre(rs.getString("Nombre"));
				x.setApellido(rs.getString("Apellido"));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return x;
	}




	@Override
	public ArrayList<Persona> readAll() {
		ArrayList<Persona> Ap = new ArrayList<>();
		Connection cn = Conexion.getConexion().getSQLConnection();
		try
		{
			
		
			PreparedStatement pst = cn.prepareStatement(readall);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				Persona newper = new Persona();
				newper.setDni(rs.getString(1));
				newper.setNombre(rs.getString(2));
				newper.setApellido(rs.getString(3));
				
				Ap.add(newper);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return Ap;
	}




	@Override
	public int EliminarPersona(String dni) {
		int filas=0;
		Connection cn = Conexion.getConexion().getSQLConnection();
		try
		{
			
		
			PreparedStatement pst = cn.prepareStatement(delete);
			pst.setString(1, dni);
		
			filas = pst.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return filas;
		
	}




	@Override	
	public int ModificarPersona(Persona p) {
		int filas=0;
		Connection cn = Conexion.getConexion().getSQLConnection();
		try
		{
			
		
			PreparedStatement pst = cn.prepareStatement(update);
			pst.setString(1, p.getApellido());
			pst.setString(2, p.getNombre());
			pst.setString(3, p.getDni());
			filas = pst.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return filas;
		
	}
	
	
	

}
