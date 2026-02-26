package es.studium;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

// ¿Si se repite los datos?  ¿Cambiar a preparedStatement?
public class AltaDirector extends WindowAdapter implements ActionListener {
	Frame ventana = new Frame("Directores - Alta");
	Label lblDirector = new Label("Introduzca un nuevo Director");
	TextField txtNombre = new TextField(25);
	TextField txtApellidos = new TextField(25);
	TextField txtNacionalidad = new TextField(25);
	Label lblNombre = new Label("Nombre");
	Label lblApellidos = new Label("Apellidos");
	Label lblNacionalidad = new Label("Nacionalidad");
	Button btnAceptar = new Button("Aceptar");
	Button btnLimpiar = new Button("Limpiar");
	Dialog dialogo = new Dialog(ventana, "Comprobación", true);
	Label lblDia = new Label();
	GridBagLayout gridbag = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();

	MenuBar mnuBar = new MenuBar();
	Menu mnuDirectores = new Menu("Directores");
	Menu mnuPeliculas = new Menu("Películas");
	Menu mnuActores = new Menu("Actores");
	Menu mnuPelAct = new Menu("Peliculas_Actores");
	MenuItem mnuAltDir = new MenuItem("Alta");
	MenuItem mnuBajaDir = new MenuItem("Baja");
	MenuItem mnuModDir = new MenuItem("Modificación");
	MenuItem mnuConsDir = new MenuItem("Consulta");
	MenuItem mnuAltPel = new MenuItem("Alta");
	MenuItem mnuBajaPel = new MenuItem("Baja");
	MenuItem mnuModPel = new MenuItem("Modificación");
	MenuItem mnuConsPel = new MenuItem("Consulta");
	MenuItem mnuAltAct = new MenuItem("Alta");
	MenuItem mnuBajaAct = new MenuItem("Baja");
	MenuItem mnuModAct = new MenuItem("Modificación");
	MenuItem mnuConsAct = new MenuItem("Consulta");
	MenuItem mnuAltPelAct = new MenuItem("Alta");
	MenuItem mnuBajaPelAct = new MenuItem("Baja");
	MenuItem mnuConsPelAct = new MenuItem("Modificación");
	MenuItem mnuModPelAct = new MenuItem("Consulta");

	// Dialogo para la parte del tercer trimestre
	Dialog diaDesarrollo = new Dialog(ventana, "Acceso Denegado", true);
	Label lblDesarrollo = new Label("Esta parte está en desarrollo");

	public AltaDirector() {

		ventana.setLayout(gridbag);
		ventana.setBackground(new Color(180, 211, 178));
		ventana.setFont(new Font("SanSerif", 0, 12));

		// Menú Directores
		mnuAltDir.addActionListener(this);
		mnuDirectores.add(mnuAltDir);
		mnuBajaDir.addActionListener(this);
		mnuDirectores.add(mnuBajaDir);
		mnuModDir.addActionListener(this);
		mnuDirectores.add(mnuModDir);
		mnuConsDir.addActionListener(this);
		mnuDirectores.add(mnuConsDir);
		mnuBar.add(mnuDirectores);

		// Menú Películas
		mnuAltPel.addActionListener(this);
		mnuPeliculas.add(mnuAltPel);
		mnuBajaPel.addActionListener(this);
		mnuPeliculas.add(mnuBajaPel);
		mnuModPel.addActionListener(this);
		mnuPeliculas.add(mnuModPel);
		mnuConsPel.addActionListener(this);
		mnuPeliculas.add(mnuConsPel);
		mnuBar.add(mnuPeliculas);

		// Menú Actores
		mnuAltAct.addActionListener(this);
		mnuActores.add(mnuAltAct);
		mnuBajaAct.addActionListener(this);
		mnuActores.add(mnuBajaAct);
		mnuModAct.addActionListener(this);
		mnuActores.add(mnuModAct);
		mnuConsAct.addActionListener(this);
		mnuActores.add(mnuConsAct);
		mnuBar.add(mnuActores);

		// Menú Peliculas_Actores
		mnuAltPelAct.addActionListener(this);
		mnuPelAct.add(mnuAltPelAct);
		mnuBajaPelAct.addActionListener(this);
		mnuPelAct.add(mnuBajaPelAct);
		mnuModPelAct.addActionListener(this);
		mnuPelAct.add(mnuModPelAct);
		mnuConsPelAct.addActionListener(this);
		mnuPelAct.add(mnuConsPelAct);
		mnuBar.add(mnuPelAct);

		Usuario.permisosBasico(mnuDirectores, mnuBajaDir, mnuModDir, mnuConsDir);
		Usuario.permisosBasico(mnuPeliculas, mnuBajaPel, mnuModPel, mnuConsPel);
		Usuario.permisosBasico(mnuActores, mnuBajaAct, mnuModAct, mnuConsAct);
		Usuario.permisosBasico(mnuPelAct, mnuBajaPelAct, mnuModPelAct, mnuConsPelAct);

		ventana.setMenuBar(mnuBar);

		// Ventana Alta
		gbc.insets = new Insets(10, 10, 10, 10);

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		lblDirector.setFont(new Font("Serif", 1, 20));
		ventana.add(lblDirector, gbc);
		gbc.gridwidth = 1;

		gbc.gridx = 0;
		gbc.gridy = 1;
		ventana.add(lblNombre, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		ventana.add(txtNombre, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		ventana.add(lblApellidos, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		ventana.add(txtApellidos, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		ventana.add(lblNacionalidad, gbc);

		gbc.gridx = 1;
		gbc.gridy = 3;
		ventana.add(txtNacionalidad, gbc);

		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.SOUTHWEST;
		gbc.anchor = GridBagConstraints.SOUTHWEST;
		gbc.gridx = 0;
		gbc.gridy = 4;
		btnAceptar.addActionListener(this);
		ventana.add(btnAceptar, gbc);

		gbc.fill = GridBagConstraints.SOUTHEAST;
		gbc.anchor = GridBagConstraints.SOUTHEAST;
		gbc.gridx = 1;
		gbc.gridy = 4;
		btnLimpiar.addActionListener(this);
		ventana.add(btnLimpiar, gbc);

		ventana.addWindowListener(this);
		ventana.setLocationRelativeTo(null);
		ventana.setSize(400, 280);
		ventana.setResizable(false);
		ventana.setVisible(true);

		// Dialogo Confirmación
		dialogo.add(lblDia);
		dialogo.addWindowListener(this);
		dialogo.setLayout(new FlowLayout());
		dialogo.setSize(300, 80);
		dialogo.setResizable(false);
		dialogo.setLocationRelativeTo(null);
		dialogo.setVisible(false);

		// Dialogo tercer trimestre
		diaDesarrollo.add(lblDesarrollo);
		diaDesarrollo.addWindowListener(this);
		diaDesarrollo.setLayout(new FlowLayout());
		diaDesarrollo.setBackground(Color.YELLOW);
		diaDesarrollo.setSize(300, 80);
		diaDesarrollo.setResizable(false);
		diaDesarrollo.setLocationRelativeTo(null);
		diaDesarrollo.setVisible(false);
	}

	public void darAlta() {

		String nombre = txtNombre.getText();
		String apellidos = txtApellidos.getText();
		String nacionalidad = txtNacionalidad.getText();
		String sentenciaSQL = "INSERT INTO directores (idDirector, nombreDirector, apellidosDirector, nacionalidadDirector) VALUES (null, ?, ?, ?)";

		try {
			BD.conectarBD();
			BD.ps = BD.connection.prepareStatement(sentenciaSQL);
			BD.ps.setString(1, nombre);
			BD.ps.setString(2, apellidos);
			BD.ps.setString(3, nacionalidad);
			BD.ps.executeUpdate();
			dialogoComprobacion(null);

		} catch (ClassNotFoundException cnfe) {
			dialogoComprobacion(cnfe);
		} catch (SQLException se) {
			dialogoComprobacion(se);
		} finally {
			try {
				BD.desconectarBD();
			} catch (SQLException se) {
				dialogoComprobacion(se);
			}
		}

	}

	public void dialogoComprobacion(Exception e) {
		if (e == null) {
			dialogo.setTitle("Enhorabuena");
			dialogo.setBackground(new Color(180, 211, 178));
			lblDia.setText("El alta se ha realizado con éxito");
		} else {
			dialogo.setTitle("Error");
			dialogo.setBackground(new Color(243, 70, 74));

			switch (e.getClass().getSimpleName()) {

			case "ClassNotFoundException":
				lblDia.setText("Error de driver. [" + e.getMessage() + "]");
				break;
			case "SQLException":
				lblDia.setText("Error de conexión: url, usuario o clave. [" + e.getMessage() + "]");
				break;

			default:
				lblDia.setText("Error. [" + e.getMessage() + "]");
			}
		}
		dialogo.pack();
		dialogo.setVisible(true);

	}

	public static void main(String[] args) {
		new AltaDirector();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnLimpiar) {
			txtNombre.setText("");
			txtApellidos.setText("");
			txtNacionalidad.setText("");
		}

		if (e.getSource() == btnAceptar) {
			if ((txtNombre.getText().trim().isEmpty()) || (txtApellidos.getText().trim().isEmpty())
					|| (txtNacionalidad.getText().trim().isEmpty())) {
				dialogoComprobacion(new Exception("Rellene todos los campos"));
			} else {
				darAlta();
			}
		}

		if (e.getSource() == mnuAltDir) {
			new AltaDirector();
		} else if (e.getSource() == mnuBajaDir) {
			new BajaDirector();
		} else if (e.getSource() == mnuModDir) {
			new ModificacionDirector();
		} else if (e.getSource() == mnuConsDir) {
			new ConsultaDirector();
		} else if (e.getSource() == mnuAltPel) {
			new AltaPelicula();
		} else if (e.getSource() == mnuBajaPel) {
			new BajaPelicula();
		} else if (e.getSource() == mnuConsPel) {
			new ConsultaPelicula();
		} else if (e.getSource() == mnuAltAct) {
			new AltaActor();
		} else if (e.getSource() == mnuBajaAct) {
			new BajaActor();
		} else if (e.getSource() == mnuModAct) {
			new ModificacionActor();
		} else if (e.getSource() == mnuConsAct) {
			new ConsultaActor();
		} else if ((e.getSource() == mnuModPel) || (e.getSource() == mnuAltPelAct) || (e.getSource() == mnuBajaPelAct)
				|| (e.getSource() == mnuModPelAct) || (e.getSource() == mnuConsPelAct)) {
			diaDesarrollo.setVisible(true);
		}
	}

	@Override
	public void windowClosing(WindowEvent e) {
		if (e.getSource() == dialogo) {
			dialogo.dispose();
		}

		else if (e.getSource() == diaDesarrollo) {
			diaDesarrollo.dispose();
		}

		else if (e.getSource() == ventana) {
			ventana.dispose();
		}

	}
}
