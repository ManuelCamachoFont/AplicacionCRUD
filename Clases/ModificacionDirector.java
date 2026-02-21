package es.studium;

import java.awt.Button;
import java.awt.Choice;
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ModificacionDirector extends WindowAdapter implements ActionListener {

	Frame ventana1 = new Frame("Directores - Modificación");
	Choice choDirectores = new Choice();
	Button btnEditar = new Button("Editar");

	Frame ventana2 = new Frame("Director - Modificación");
	Label lblElec = new Label("¿Qué director desea modificar?");
	Label lblElecc = new Label("");
	Label lblNombre = new Label("Nombre");
	TextField txtNombre = new TextField(30);
	Label lblApellidos = new Label("Apellidos");
	TextField txtApellidos = new TextField(30);
	Label lblNacionalidad = new Label("Nacionalidad");
	TextField txtNacionalidad = new TextField(30);
	Button btnAceptar = new Button("Aceptar");
	Button btnLimpiar = new Button("Limpiar");

	Dialog diaFeedback = new Dialog(ventana2, "Confirmación", true);
	Label lblDiaF = new Label();
	Dialog diaError = new Dialog(ventana2, "Error", true);
	Label lblDiaE = new Label();

	MenuBar mnuBar = new MenuBar();
	Menu mnuDirectores = new Menu("Directores");
	Menu mnuPeliculas = new Menu("Películas");
	Menu mnuActores = new Menu("Actores");
	MenuItem mnuAltDir = new MenuItem("Alta");
	MenuItem mnuBajaDir = new MenuItem("Baja");
	MenuItem mnuModDir = new MenuItem("Modificación");
	MenuItem mnuConsDir = new MenuItem("Consulta");
	MenuItem mnuAltPel = new MenuItem("Alta");
	MenuItem mnuBajaPel = new MenuItem("Baja");
	MenuItem mnuConsPel = new MenuItem("Consulta");
	MenuItem mnuAltAct = new MenuItem("Alta");
	MenuItem mnuBajaAct = new MenuItem("Baja");
	MenuItem mnuModAct = new MenuItem("Modificación");
	MenuItem mnuConsAct = new MenuItem("Consulta");

	MenuBar mnuBar2 = new MenuBar();
	Menu mnuDirectores2 = new Menu("Directores");
	Menu mnuPeliculas2 = new Menu("Películas");
	Menu mnuActores2 = new Menu("Actores");
	MenuItem mnuAltDir2 = new MenuItem("Alta");
	MenuItem mnuBajaDir2 = new MenuItem("Baja");
	MenuItem mnuModDir2 = new MenuItem("Modificación");
	MenuItem mnuConsDir2 = new MenuItem("Consulta");
	MenuItem mnuAltPel2 = new MenuItem("Alta");
	MenuItem mnuBajaPel2 = new MenuItem("Baja");
	MenuItem mnuConsPel2 = new MenuItem("Consulta");
	MenuItem mnuAltAct2 = new MenuItem("Alta");
	MenuItem mnuBajaAct2 = new MenuItem("Baja");
	MenuItem mnuModAct2 = new MenuItem("Modificación");
	MenuItem mnuConsAct2 = new MenuItem("Consulta");

	GridBagLayout gridbag = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/p_cine";
	String usuario = "studium";
	String password = "Studium2025#";

	String sentenciaSQL = "SELECT * FROM directores";
	String idDirector = "";

	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;

	public ModificacionDirector() {

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

		// Menú Directores
		mnuAltDir2.addActionListener(this);
		mnuDirectores2.add(mnuAltDir2);
		mnuBajaDir2.addActionListener(this);
		mnuDirectores2.add(mnuBajaDir2);
		mnuModDir2.addActionListener(this);
		mnuDirectores2.add(mnuModDir2);
		mnuConsDir2.addActionListener(this);
		mnuDirectores2.add(mnuConsDir2);
		mnuBar2.add(mnuDirectores2);

		// Menú Películas
		mnuAltPel2.addActionListener(this);
		mnuPeliculas2.add(mnuAltPel2);
		mnuBajaPel2.addActionListener(this);
		mnuPeliculas2.add(mnuBajaPel2);
		mnuConsPel2.addActionListener(this);
		mnuPeliculas2.add(mnuConsPel2);
		mnuBar2.add(mnuPeliculas2);

		// Menú Actores
		mnuAltAct2.addActionListener(this);
		mnuActores2.add(mnuAltAct2);
		mnuBajaAct2.addActionListener(this);
		mnuActores2.add(mnuBajaAct2);
		mnuModAct2.addActionListener(this);
		mnuActores2.add(mnuModAct2);
		mnuConsAct2.addActionListener(this);
		mnuActores2.add(mnuConsAct2);
		mnuBar2.add(mnuActores2);

		ventana1.setMenuBar(mnuBar);
		ventana2.setMenuBar(mnuBar2);

		// Ventana 1
		ventana1.setLayout(gridbag);
		ventana1.setBackground(new Color(213, 255, 255));
		gbc.insets = new Insets(10, 10, 10, 10);
		rellenarChoice();

		gbc.gridx = 0;
		gbc.gridy = 0;
		lblElec.setFont(new Font("SansSerif", 3, 20));
		ventana1.add(lblElec, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		ventana1.add(choDirectores, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		btnEditar.addActionListener(this);
		ventana1.add(btnEditar, gbc);

		ventana1.setSize(340, 220);
		ventana1.addWindowListener(this);
		ventana1.setResizable(false);
		ventana1.setLocationRelativeTo(null);
		ventana1.setVisible(true);

		// Ventana 2
		ventana2.setLayout(gridbag);
		ventana2.setBackground(new Color(213, 255, 255));

		gbc.insets = new Insets(30, 5, 5, 5);

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		ventana2.add(lblElecc, gbc);
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridwidth = 1;

		gbc.gridx = 0;
		gbc.gridy = 1;
		ventana2.add(lblNombre, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		ventana2.add(txtNombre, gbc);

		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 2;
		ventana2.add(lblApellidos, gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		ventana2.add(txtApellidos, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		ventana2.add(lblNacionalidad, gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;
		ventana2.add(txtNacionalidad, gbc);

		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.SOUTHWEST;
		gbc.anchor = GridBagConstraints.SOUTHWEST;
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.insets = new Insets(0, 30, 30, 0);
		btnAceptar.addActionListener(this);
		ventana2.add(btnAceptar, gbc);

		gbc.fill = GridBagConstraints.SOUTHEAST;
		gbc.anchor = GridBagConstraints.SOUTHEAST;
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.insets = new Insets(0, 0, 30, 30);
		btnLimpiar.addActionListener(this);
		ventana2.add(btnLimpiar, gbc);

		ventana2.addWindowListener(this);
		ventana2.setLocationRelativeTo(null);
		ventana2.setSize(500, 320);
		ventana2.setResizable(false);
		ventana2.setVisible(false);

		// Dialogo
		diaFeedback.setLayout(new FlowLayout());
		diaFeedback.addWindowListener(this);
		diaFeedback.setSize(400, 80);
		diaFeedback.add(lblDiaF);
		diaFeedback.setResizable(false);
		diaFeedback.setLocationRelativeTo(null);
		diaFeedback.setVisible(false);

	}

	private void rellenarChoice() {
		
		choDirectores.removeAll();
		try

		{
			Class.forName(driver);
			connection = DriverManager.getConnection(url, usuario, password);
			statement = connection.createStatement();
			rs = statement.executeQuery(sentenciaSQL);
			choDirectores.add("Seleccionar un director...");
			while (rs.next()) {
				choDirectores.add(rs.getInt("idDirector") + " | " + rs.getString("nombreDirector") + " | "
						+ rs.getString("apellidosDirector") + " | " + rs.getString("nacionalidadDirector"));
			}
		}

		catch (ClassNotFoundException cnfe) {
			diaError.setBackground(new Color(243, 70, 74));
			lblDiaF.setText("Error " + cnfe);
		} catch (SQLException se) {
			diaFeedback.setBackground(new Color(243, 70, 74));
			lblDiaF.setText("Error " + se);
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException se) {
				diaFeedback.setBackground(new Color(243, 70, 74));
				lblDiaF.setText("Error " + se);

			}
		}
	}

	public static void main(String[] args) {
		new ModificacionDirector();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnEditar)) {
			if (choDirectores.getSelectedIndex() != 0) {
				lblElecc.setText("Estás editando : " + (choDirectores.getSelectedItem().split(" ")[2]) + " "
						+ (choDirectores.getSelectedItem().split(" ")[4]));
				idDirector = choDirectores.getSelectedItem().split(" ")[0];
				sentenciaSQL = "SELECT * FROM directores WHERE idDirector = " + idDirector;

				try

				{
					Class.forName(driver);
					connection = DriverManager.getConnection(url, usuario, password);
					statement = connection.createStatement();
					rs = statement.executeQuery(sentenciaSQL);
					choDirectores.add("Seleccionar un director...");
					rs.next();
					txtNombre.setText(rs.getString("nombreDirector"));
					txtApellidos.setText(rs.getString("apellidosDirector"));
					txtNacionalidad.setText(rs.getString("nacionalidadDirector"));
				}

				catch (ClassNotFoundException cnfe) {
					diaFeedback.setBackground(new Color(243, 70, 74));
					lblDiaF.setText("Error " + cnfe);
				} catch (SQLException se) {
					diaError.setBackground(new Color(243, 70, 74));
					lblDiaF.setText("Error " + se);
				} finally {
					try {
						if (connection != null) {
							connection.close();
						}
					} catch (SQLException se) {
						diaError.setBackground(new Color(243, 70, 74));
						lblDiaF.setText("Error " + se);

					}
				}
				ventana2.setVisible(true);

			} else {
				choDirectores.requestFocus();
			}
		}

		else if (e.getSource() == btnAceptar) {

			// ¿Añadir  getText!=isEmpty()?
			sentenciaSQL = "UPDATE directores SET nombreDirector = '" + txtNombre.getText() + "', apellidosDirector = '"
					+ txtApellidos.getText() + "', nacionalidadDirector = '" + txtNacionalidad.getText()
					+ "' WHERE idDirector = " + idDirector;

			try {

				Class.forName(driver);
				connection = DriverManager.getConnection(url, usuario, password);
				System.out.println("Conexión establecida");
				statement = connection.createStatement();
				statement.executeUpdate(sentenciaSQL);
				diaFeedback.setBackground(new Color(180, 211, 178));
				lblDiaF.setText("Se ha realizado correctamente la modificación");
			} catch (ClassNotFoundException cnfe) {
				diaFeedback.setBackground(new Color(243, 70, 74));
				lblDiaF.setText("Error " + cnfe);
			} catch (SQLException se) {
				diaFeedback.setBackground(new Color(243, 70, 74));
				lblDiaF.setText("Error " + se);
			} finally {
				try {
					if (connection != null) {
						connection.close();
					}
				}

				catch (SQLException se) {
					diaFeedback.setBackground(new Color(243, 70, 74));
					lblDiaF.setText("Error " + se);
					diaFeedback.setVisible(true);
				}

			}
			diaFeedback.setVisible(true);
			rellenarChoice();

		} else if (e.getSource() == btnLimpiar) {
			txtNombre.setText("");
			txtApellidos.setText("");
			txtNacionalidad.setText("");
			txtNombre.requestFocus();
		}
		if ((e.getSource() == mnuAltDir) || (e.getSource() == mnuAltDir2)) {
			new AltaDirector();
		} else if ((e.getSource() == mnuBajaDir) || (e.getSource() == mnuBajaDir2)) {
			new BajaDirector();
		} else if ((e.getSource() == mnuModDir) || (e.getSource() == mnuModDir2)) {
			new ModificacionDirector();
		} else if ((e.getSource() == mnuConsDir) || (e.getSource() == mnuConsDir2)) {
			new ConsultaDirector();
		} else if ((e.getSource() == mnuAltPel) || (e.getSource() == mnuAltPel2)) {
			new AltaPelicula();
		} else if ((e.getSource() == mnuBajaPel) || (e.getSource() == mnuBajaPel2)) {
			new BajaPelicula();
		} else if ((e.getSource() == mnuConsPel) || (e.getSource() == mnuConsPel2)) {
			new ConsultaPelicula();
		} else if ((e.getSource() == mnuAltAct) || (e.getSource() == mnuAltAct2)) {
			new AltaActor();
		} else if ((e.getSource() == mnuBajaAct) || (e.getSource() == mnuBajaAct2)) {
			new BajaActor();
		} else if ((e.getSource() == mnuModAct) || (e.getSource() == mnuModAct2)){
			new ModificacionActor();
		} else if ((e.getSource() == mnuConsAct) || (e.getSource() == mnuConsAct2)) {
			new ConsultaActor();
		}
	}

	@Override
	public void windowClosing(WindowEvent e) {

		if (e.getSource() == diaFeedback) {
			diaFeedback.setVisible(false);
		}

		if (e.getSource() == ventana2) {
			ventana2.dispose();
		} else if (e.getSource() == ventana1) {
			ventana1.dispose();
		}

	}
}