package Dao;

import java.util.ArrayList;

import entidades.Persona;

public interface PersonaDao {

		public int AgregarPersona(Persona p);
		public Persona obtenerPersona(String dni);
		public ArrayList<Persona> readAll();
		public int EliminarPersona(String dni);
		public int ModificarPersona(Persona p);
}
