package entidades;

public class Persona {
	
	private String Dni;
	private String Nombre;
	private String Apellido;
	
	
	public Persona() {
		this.Dni="0";
		this.Nombre="";
		this.Apellido="";
	}

	public Persona(String dni, String nombre, String apellido) {
		super();
		Dni = dni;
		Nombre = nombre;
		Apellido = apellido;
	}

	public String getDni() {
		return Dni;
	}

	public void setDni(String dni) {
		Dni = dni;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	
	public String getApellido() {
		return Apellido;
	}

	public void setApellido(String apellido) {
		Apellido = apellido;
	}

	@Override
	public String toString() {
		return "DNI: " + Dni + ", Nombre: " + Nombre + ", Apellido: " + Apellido;
	}
	
}
