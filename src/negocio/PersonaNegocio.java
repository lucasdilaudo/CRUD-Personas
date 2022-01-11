package negocio;

import java.util.ArrayList;

import entidades.Persona;

public interface PersonaNegocio {

	public boolean agregarPersona(Persona per);
	public ArrayList<Persona> readAll();
	public boolean eliminarPersona(String Dni);
	public boolean modificarPersona(Persona p);
}
