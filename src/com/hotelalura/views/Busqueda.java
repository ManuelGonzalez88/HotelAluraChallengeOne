package com.hotelalura.views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.hotelalura.controller.HuespedController;
import com.hotelalura.controller.ReservaController;
import com.hotelalura.main.MenuPrincipal;
import com.hotelalura.modelo.Huesped;
import com.hotelalura.modelo.Reserva;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHuesped;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;
	private ReservaController reservaController;
	private HuespedController huespedController;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Busqueda() {
		
		reservaController = new ReservaController();
		huespedController = new HuespedController();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);

		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 280, 42);
		contentPane.add(lblNewLabel_4);

		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Fecha Check In");
		modelo.addColumn("Fecha Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scroll_table,
				null);
		scroll_table.setVisible(true);

		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
		modeloHuesped.addColumn("Número de Huesped");
		modeloHuesped.addColumn("Nombre");
		modeloHuesped.addColumn("Apellido");
		modeloHuesped.addColumn("Fecha de Nacimiento");
		modeloHuesped.addColumn("Nacionalidad");
		modeloHuesped.addColumn("Telefono");
		modeloHuesped.addColumn("Número de Reserva");
		
		cargarReservas(this.reservaController.cargarReservas());
		cargarHuespedes(this.huespedController.cargarHuespedes());
		
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")),
				scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);

		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);

			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);

		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAtras.setBackground(Color.white);
				labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);

		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);

		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (JOptionPane.showConfirmDialog(null, "¿Desea salir de la sesión?") == 0) {
					MenuPrincipal principal = new MenuPrincipal();
					principal.setVisible(true);
					dispose();
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) { // Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) { // Al usuario quitar el mouse por el botón este volverá al estado
													// original
				btnexit.setBackground(Color.white);
				labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);

		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);

		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				buscar(scroll_table);
				
				if(modelo.getDataVector().isEmpty()) {
					cargarReservas(reservaController.cargarReservas());
				}
				
				if (modeloHuesped.getDataVector().isEmpty()) {
					cargarHuespedes(huespedController.cargarHuespedes());
				}

			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);

		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));

		JPanel btnEditar = new JPanel();
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int confirmar = JOptionPane.showConfirmDialog(null, "¿Desea modificar los datos?"); 

				if(confirmar == JOptionPane.YES_OPTION){
					editar(scroll_table);
				}

			}
		});
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);

		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);

		JPanel btnEliminar = new JPanel();
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int confirmar = JOptionPane.showConfirmDialog(null, "¿Desea eliminar los datos?"); 

				if(confirmar == JOptionPane.YES_OPTION){
					eliminar(scroll_table);
				}

			}
		});
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);

		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);
	}

//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
	private void headerMousePressed(java.awt.event.MouseEvent evt) {
		xMouse = evt.getX();
		yMouse = evt.getY();
	}

	private void headerMouseDragged(java.awt.event.MouseEvent evt) {
		int x = evt.getXOnScreen();
		int y = evt.getYOnScreen();
		this.setLocation(x - xMouse, y - yMouse);
	}
	
	
	/**
	 * Carga las reservas
	 * @param reservas
	 */
	
	private void cargarReservas(List<Reserva> reservas) {
		
		reservas.forEach(reserva -> modelo.addRow(
				new Object[] {
						reserva.getId(),
						new SimpleDateFormat("yyyy-MM-dd").format(reserva.getFechaEntrada()),
						new SimpleDateFormat("yyyy-MM-dd").format(reserva.getFechaSalida()),
						reserva.getValor(),
						reserva.getFormaPago()
				}));
	}
	
	/**
	 * cargas los huespedes
	 */
	private void cargarHuespedes(List<Huesped> huespedes) {
		
		huespedes.forEach(huesped -> modeloHuesped.addRow(
				new Object[] {
						huesped.getId(),
						huesped.getNombre(),
						huesped.getApellido(),
						new SimpleDateFormat("yyyy-MM-dd").format(huesped.getFecha_de_nacimiento()),
						huesped.getNacionalidad(),
						huesped.getTelefono(),
						huesped.getId_reserva()
				}));
	}
	
	/**
	 * Busca datos en la tabla, por id en la tabla de reserva, y por apellido en la tabla huespedes
	 * @param scroll_table pestaña reservas
	 */
	private void buscar(JScrollPane scroll_table) {
		
		if(scroll_table.isVisible()) {
			
			List<Reserva> reserva = reservaController.buscarReserva(txtBuscar.getText());
			limpiarTabla(modelo);
			cargarReservas(reserva);
			
		} else {
			
			List<Huesped> huesped = huespedController.buscarHuesped(txtBuscar.getText());
			limpiarTabla(modeloHuesped);
			cargarHuespedes(huesped);
			
		}
		
	}
	
	/**
	 * Limpia la tabla
	 */
	private void limpiarTabla(DefaultTableModel modelo) {
		modelo.getDataVector().clear();
	}
	
	/**
	 * Elimina el itrm seleccionado
	 * se le pasa como parametro la pestaña de la tabla, si se elimina una reserva, elimina 
	 * el huesped, y si se elimina el huesped, busca el id de la reserva y la elimina
	 * @param scroll_table
	 */
	private void eliminar(JScrollPane scroll_table) {
		if(scroll_table.isVisible()) {
			
			if(!tieneFilaElegida(tbReservas)) {
				JOptionPane.showMessageDialog(this, "Por favor, elije un item");
	            return;
			}
            Integer id = Integer.valueOf(modelo.getValueAt(
            		tbReservas.getSelectedRow(),0).toString());
            int filasModificadas;
            
            filasModificadas = this.reservaController.eliminar(id);

            JOptionPane.showMessageDialog(this, String.format("%d item eliminado con éxito!", filasModificadas));
                
			
			
		} else {
			
			if(!tieneFilaElegida(tbHuespedes)) {
				JOptionPane.showMessageDialog(this, "Por favor, elije un item");
	            return;
			}
	
            Integer huesped = Integer.parseInt(modeloHuesped.getValueAt(
            		tbHuespedes.getSelectedRow(),6).toString());
            
            System.out.println(huesped);
            
            reservaController.eliminar(huesped);

            JOptionPane.showMessageDialog(this, String.format("Item eliminado con éxito!"));
			
		}
		
		limpiarTabla(modelo);
		limpiarTabla(modeloHuesped);
		cargarReservas(reservaController.cargarReservas());
		cargarHuespedes(huespedController.cargarHuespedes());
	}
	
	/**
	 * Comprueba si tiene seleccionado un item
	 * @return true si hay fila o columna seleccionada
	 */
	private boolean tieneFilaElegida(JTable tabla) {
        return tabla.getSelectedRowCount() != 0 || tabla.getSelectedColumnCount() != 0;
    }
	
	/**
	 * 
	 */
	private void editar(JScrollPane scroll_table) {
		
		if(scroll_table.isVisible()) {
			
			Integer id = null;
			
			try {
			id = Integer.valueOf(modelo.getValueAt(
						tbReservas.getSelectedRow(),0).toString());
			}catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Por favor, elije un item");
			}
				
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date fechaEntrada = null;
			Date fechaSalida = null;
			try {
				fechaEntrada = dateFormat.parse(
						modelo.getValueAt(tbReservas.getSelectedRow(),1).toString());
				fechaSalida = dateFormat.parse(
						modelo.getValueAt(tbReservas.getSelectedRow(),2).toString());
			} catch (ParseException e) {
				System.out.println("error en convertir fecha");
				e.printStackTrace();
			}
			
			String valor = modelo.getValueAt(
					tbReservas.getSelectedRow(),3).toString();
			
			String formaPago = modelo.getValueAt(
					tbReservas.getSelectedRow(),4).toString();
			
			Reserva reserva = new Reserva(id, fechaEntrada, fechaSalida, valor, formaPago);
			
			reservaController.editar(reserva);
			
			JOptionPane.showMessageDialog(this, String.format("Se modifico con exito!"));
			
		} else {
			
			if(!tieneFilaElegida(tbHuespedes)) {
				JOptionPane.showMessageDialog(this, "Por favor, elije un item");
	            return;
			}
			
			Integer id = Integer.valueOf(modeloHuesped.getValueAt(
					tbHuespedes.getSelectedRow(), 0).toString());
			
			String nombre = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 1).toString();
			
			String apellido = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 2).toString();
			
			Date fecha_de_nacimiento = null;
			
			try {
				fecha_de_nacimiento = new SimpleDateFormat("yyyy-MM-dd").parse(
						modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 3).toString());
			} catch (ParseException e) {
				System.out.println("error en convertir fecha");
				e.printStackTrace();
			}
			
			String nacionalidad = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 4).toString();
			
			String telefono = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 5).toString();
			
			Huesped huesped = new Huesped(id, nombre, apellido, fecha_de_nacimiento, nacionalidad, telefono);
			
			huespedController.editar(huesped);

			
			JOptionPane.showMessageDialog(this, String.format("Se modifico con exito!"));
			
		}
		
		limpiarTabla(modelo);
		limpiarTabla(modeloHuesped);
		cargarReservas(reservaController.cargarReservas());
		cargarHuespedes(huespedController.cargarHuespedes());
		
	}
	
}
