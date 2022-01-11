package presentacion;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import NegocioImpl.PersonaNegocioImpl;
import entidades.Persona;
import negocio.PersonaNegocio;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AgregarPersona extends JPanel {
	
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDni;
	private DefaultListModel<Persona> DLModel;
	private Persona p= new Persona();
	private PersonaNegocio pNegocio=new PersonaNegocioImpl();
	
	public AgregarPersona() {
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{72, 74, 41, 102, 153, 0};
		gridBagLayout.rowHeights = new int[]{34, 14, 22, 0, 14, 20, 40, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblNombre = new JLabel("Nombre");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 1;
		gbc_lblNombre.gridy = 1;
		add(lblNombre, gbc_lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.addKeyListener(new KeyAdapter() {
			//filtrado de  numeros
			@Override
			public void keyTyped(KeyEvent arg0) {				
				if(!Character.isAlphabetic(arg0.getKeyChar())) {
					arg0.consume();
				}				
			}
		});
		GridBagConstraints gbc_txtNombre = new GridBagConstraints();
		gbc_txtNombre.insets = new Insets(0, 0, 5, 5);
		gbc_txtNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNombre.gridx = 3;
		gbc_txtNombre.gridy = 1;
		add(txtNombre, gbc_txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellido");
		GridBagConstraints gbc_lblApellido = new GridBagConstraints();
		gbc_lblApellido.insets = new Insets(0, 0, 5, 5);
		gbc_lblApellido.gridx = 1;
		gbc_lblApellido.gridy = 3;
		add(lblApellido, gbc_lblApellido);
		
		txtApellido = new JTextField();
		txtApellido.addKeyListener(new KeyAdapter() {
			
			//filtrado de  numeros
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isLetter(e.getKeyChar())) {
					e.consume();
				}
			}
		});
		GridBagConstraints gbc_txtApellido = new GridBagConstraints();
		gbc_txtApellido.insets = new Insets(0, 0, 5, 5);
		gbc_txtApellido.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtApellido.gridx = 3;
		gbc_txtApellido.gridy = 3;
		add(txtApellido, gbc_txtApellido);
		txtApellido.setColumns(10);
		
		JLabel lblDni = new JLabel("Dni");
		GridBagConstraints gbc_lblDni = new GridBagConstraints();
		gbc_lblDni.insets = new Insets(0, 0, 5, 5);
		gbc_lblDni.gridx = 1;
		gbc_lblDni.gridy = 5;
		add(lblDni, gbc_lblDni);
		
		txtDni = new JTextField();
		txtDni.addKeyListener(new KeyAdapter() {
			//filtrado de letras
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
			}
		});
		GridBagConstraints gbc_txtDni = new GridBagConstraints();
		gbc_txtDni.insets = new Insets(0, 0, 5, 5);
		gbc_txtDni.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDni.gridx = 3;
		gbc_txtDni.gridy = 5;
		add(txtDni, gbc_txtDni);
		txtDni.setColumns(10);
		
		//evento click btnAceptar
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validacionesAgregar()) {
					//seteado de persona
					p.setNombre(txtNombre.getText().trim());
					p.setApellido(txtApellido.getText().trim());
					p.setDni(txtDni.getText().trim());
					
					//agregar a bd
					if(pNegocio.agregarPersona(p)) {
						JOptionPane.showMessageDialog(null, "Persona agregada");
					}
					else {
						JOptionPane.showMessageDialog(null, "No se pudo agregar");
					}
					
					
					
					txtNombre.setText("");
					txtApellido.setText("");
					txtDni.setText("");
				}
				
			}
		});
		
		GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
		gbc_btnAceptar.insets = new Insets(0, 0, 5, 5);
		gbc_btnAceptar.gridx = 1;
		gbc_btnAceptar.gridy = 7;
		add(btnAceptar, gbc_btnAceptar);
		
	}
	
	public void SetDefaultListModel(DefaultListModel<Persona> DLM) {
		DLModel = DLM;
	}
	
	public boolean validacionesAgregar() {
		if(txtNombre.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null,"Ingrese un nombre","ATENCION!",2);
			return false;
		}
	
		if(txtApellido.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null,"Ingrese un apellido","ATENCION!",2);
			return false;
		}
		
		if(txtDni.getText().trim().isEmpty()) {
			//JOptionPane.showMessageDialog(null, "Error: ingrese un dni");
			JOptionPane.showMessageDialog(null,"Ingrese un dni","ATENCION!",2);
			return false;
		}
		
		return true;		
	}
	
	

}
