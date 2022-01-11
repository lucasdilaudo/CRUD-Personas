package presentacion;

import javax.swing.JPanel;

import NegocioImpl.PersonaNegocioImpl;
import entidades.Persona;
import negocio.PersonaNegocio;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Button;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import java.awt.SystemColor;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;

public class EliminarPersona extends JPanel {

	
	private DefaultListModel<Persona> Dmodel;
	private PersonaNegocioImpl PerNeg = new PersonaNegocioImpl();
	
	/**
	 * Create the panel.
	 */
	public EliminarPersona() {
	
		Dmodel = new DefaultListModel<>();
		CargarLista();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{40, 258, 40, 0};
		gridBagLayout.rowHeights = new int[]{33, 224, 30, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		
		Button button = new Button("ELIMINAR");
		button.setForeground(SystemColor.desktop);
		button.setBackground(SystemColor.control);
		
		JLabel lblEliminarPersonas = new JLabel("Eliminar usuarios");
		GridBagConstraints gbc_lblEliminarPersonas = new GridBagConstraints();
		gbc_lblEliminarPersonas.anchor = GridBagConstraints.WEST;
		gbc_lblEliminarPersonas.insets = new Insets(0, 0, 5, 5);
		gbc_lblEliminarPersonas.gridx = 1;
		gbc_lblEliminarPersonas.gridy = 0;
		add(lblEliminarPersonas, gbc_lblEliminarPersonas);

		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 1;
		add(scrollPane, gbc_scrollPane);
		JList list = new JList();
		scrollPane.setViewportView(list);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setModel(Dmodel);
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.fill = GridBagConstraints.BOTH;
		gbc_button.gridx = 1;
		gbc_button.gridy = 2;
		add(button, gbc_button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(list.isSelectionEmpty()) JOptionPane.showMessageDialog(null, "Seleccione una persona");
				else {
					String dni = Dmodel.getElementAt(list.getSelectedIndex()).getDni();
					if(PerNeg.eliminarPersona(dni)) {
						JOptionPane.showMessageDialog(null, "Persona eliminada con exito");
						CargarLista();
						list.clearSelection();
					}
					else JOptionPane.showMessageDialog(null, "No se pudo eliminar la persona");
				}
			
			
				
			}
		
		});


	}






public void CargarLista() {
		Dmodel.clear();
		ArrayList<Persona> ap = PerNeg.readAll();
		for (Persona per : ap) {
			Dmodel.addElement(per);
		}
		
		
		
	}
}
