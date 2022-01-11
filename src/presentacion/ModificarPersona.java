package presentacion;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import NegocioImpl.PersonaNegocioImpl;
import entidades.Persona;
import negocio.PersonaNegocio;

import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.TreeSet;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ModificarPersona extends JPanel {
	private JTextField textNombre;
	private JTextField textApellido;
	private JTextField textDni;
	private JButton btnModificar;
	private DefaultListModel<Persona> dmodel = new DefaultListModel<>();
	PersonaNegocio perneg = new PersonaNegocioImpl();
	private JLabel lblSeleccioneLaPersona;

	/**
	 * Create the panel.
	 */
	public ModificarPersona() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{60, 364, 60, 0};
		gridBagLayout.rowHeights = new int[]{25, 216, 35, -12, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		lblSeleccioneLaPersona = new JLabel("Seleccione la persona que desea modificar");
		GridBagConstraints gbc_lblSeleccioneLaPersona = new GridBagConstraints();
		gbc_lblSeleccioneLaPersona.anchor = GridBagConstraints.WEST;
		gbc_lblSeleccioneLaPersona.insets = new Insets(0, 0, 5, 5);
		gbc_lblSeleccioneLaPersona.gridx = 1;
		gbc_lblSeleccioneLaPersona.gridy = 0;
		add(lblSeleccioneLaPersona, gbc_lblSeleccioneLaPersona);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 1;
		add(scrollPane, gbc_scrollPane);
		
		JList list = new JList();
		cargarLista();
		list.setModel(dmodel);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Persona x = new Persona();
				x = dmodel.getElementAt(list.getSelectedIndex());
				textNombre.setText(x.getNombre());
				textApellido.setText(x.getApellido());
				textDni.setText(x.getDni());
			}
		});
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 2;
		add(panel, gbc_panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		textNombre = new JTextField();
		/**textNombre.addKeyListener(new KeyAdapter() {
			@Override
	
			public void keyTyped(KeyEvent e) {
				if(!Character.isLetter(e.getKeyChar())) {
					e.consume();
				}
			}
		
		});**/
		panel.add(textNombre);
		textNombre.setColumns(10);
		
		textApellido = new JTextField();
		textApellido.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isLetter(e.getKeyChar())) {
					e.consume();
				}
			}
		});
		panel.add(textApellido);
		textApellido.setColumns(10);
		
		textDni = new JTextField();
		textDni.setEditable(false);
		panel.add(textDni);
		textDni.setColumns(10);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(controlarCampos()) {
					Persona x = new Persona();
					x.setApellido(textApellido.getText());
					x.setNombre(textNombre.getText());
					x.setDni(textDni.getText());
					if(perneg.modificarPersona(x)) { 
						cargarLista();
						JOptionPane.showMessageDialog(null, "Persona modificada con exito");
						
					}
					else JOptionPane.showMessageDialog(null, "No se pudo modificar");
					vaciarCampos();
				} else JOptionPane.showMessageDialog(null, "No debe haber campos vacios");
			
				
			
			}
		});
		panel.add(btnModificar);
	}
		
		private void cargarLista() {
			dmodel.clear();
			ArrayList<Persona> ap = new ArrayList<>(); 
			ap = perneg.readAll();
			
			for (Persona per : ap) {
				dmodel.addElement(per);
			}
			
		}	
		private boolean controlarCampos() { //controla que los campos no esten vacios
			boolean x = true;
			
			if(textApellido.getText().isEmpty()) x=false;
			if(textNombre.getText().isEmpty()) x=false;
			if(textDni.getText().isEmpty()) x=false;
			
			return x;
		}
		
		private void vaciarCampos() {
			textApellido.setText("");
			textNombre.setText("");
			textDni.setText("");
		
		}
			

	

}
