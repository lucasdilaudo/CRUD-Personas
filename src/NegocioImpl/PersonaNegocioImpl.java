package NegocioImpl;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import DaoImpl.PersonaDaoImpl;
import Dao.PersonaDao;
import entidades.Persona;
import negocio.PersonaNegocio;

public class PersonaNegocioImpl implements PersonaNegocio {
	private PersonaDaoImpl pDao = new PersonaDaoImpl();
	private Persona p=new Persona();
	
	public PersonaNegocioImpl() {
		
	}
	

	public boolean existePersona(Persona per) {
		//Persona p=new Persona();
		//PersonaDao pDao=new PersonaDao();
		p=pDao.obtenerPersona(per.getDni());
		if(p.getDni().equals(per.getDni())) {
			return true;
		}
		return false;
	}


	@Override
	public boolean agregarPersona(Persona per) {
		// TODO Auto-generated method stub
		if(existePersona(per)==false) {
			
			if(pDao.AgregarPersona(per) >0) {
				return true;
			}else {
				return false;
			}
		}
		return false;
		
	}


	@Override
	public ArrayList<Persona> readAll() {
		
		return pDao.readAll();
		
	}


	@Override
	public boolean eliminarPersona(String Dni) {
		
		if(pDao.EliminarPersona(Dni)==1) return true;
		else return false;
		
		
		
	}


	@Override
	public boolean modificarPersona(Persona p) {
		
		if(pDao.ModificarPersona(p)==1) return true;
		else return false;
	}
	

	
	
	
}
