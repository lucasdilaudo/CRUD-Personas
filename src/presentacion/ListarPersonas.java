package presentacion;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import NegocioImpl.PersonaNegocioImpl;
import entidades.Persona;
import negocio.PersonaNegocio;
import javax.swing.JScrollPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;

public class ListarPersonas extends JPanel {
	private JTable table;
	private String[] Columnas = {"Nombre","Apellido","Dni"};
	private DefaultTableModel dtm;
	private JTable table_1;
	PersonaNegocioImpl PerNeg = new PersonaNegocioImpl();

	/**
	 * Create the panel.
	 */
	public ListarPersonas() {
		
		dtm = new DefaultTableModel(null, Columnas);

		cargarTabla();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{30, 320, 30, 0};
		gridBagLayout.rowHeights = new int[]{37, 238, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
			
			JScrollPane scrollPane = new JScrollPane();
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.gridx = 1;
			gbc_scrollPane.gridy = 1;
			add(scrollPane, gbc_scrollPane);
			table = new JTable();
			table.setModel(dtm);
			scrollPane.setViewportView(table);
			
			
				table.getColumnModel().getColumn(0).setResizable(false);
				table.getColumnModel().getColumn(0).setPreferredWidth(103);
				table.getColumnModel().getColumn(1).setResizable(false);
				table.getColumnModel().getColumn(1).setPreferredWidth(103);
				table.getColumnModel().getColumn(2).setResizable(false);
				table.getColumnModel().getColumn(2).setPreferredWidth(103);
				table.setRowSelectionAllowed(false);

	}

	
	
	
	public void cargarTabla() {
		dtm.setRowCount(0);
		dtm.setColumnCount(0);
		dtm.setColumnIdentifiers(Columnas);
		ArrayList<Persona> ap = PerNeg.readAll();
		

		for (Persona p : ap) {
			Object[] a = {p.getNombre(),p.getApellido(),p.getDni()};
			
			dtm.addRow(a);
			
		}
		
		
		
		
	}
}
