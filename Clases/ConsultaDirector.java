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
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConsultaDirector extends WindowAdapter implements ActionListener {

	Frame ventana = new Frame("Directores - Consultas");
	TextArea txtInfo = new TextArea();
	Button btnConsulta = new Button("Consultas");

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

	GridBagLayout gridbag = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();

	Dialog diaFeedback = new Dialog(ventana, "", true);
	Label lblDiaFeedback = new Label("");

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/p_cine";
	String usuario = "studium";
	String password = "Studium2025#";

	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;

	public ConsultaDirector() {
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

		ventana.setMenuBar(mnuBar);

		ventana.setLayout(gridbag);
		ventana.setBackground(new Color(213, 255, 255));
		ventana.setFont(new Font("SanSerif", 0, 12));
		txtInfo.setFont(new Font("MONOSPACED", 1, 12));
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(5, 5, 5, 5);

		gbc.gridy = 0;
		txtInfo.setEditable(false);
		ventana.add(txtInfo, gbc);

		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		btnConsulta.addActionListener(this);
		ventana.add(btnConsulta, gbc);

		ventana.addWindowListener(this);
		ventana.setLocationRelativeTo(null);
		ventana.setSize(800, 400);
		ventana.setResizable(false);
		ventana.setVisible(true);

		diaFeedback.setLayout(new FlowLayout());
		diaFeedback.add(lblDiaFeedback);
		diaFeedback.addWindowListener(this);
		diaFeedback.setLocationRelativeTo(null);
		diaFeedback.setSize(320, 80);
		diaFeedback.setResizable(false);
		diaFeedback.setVisible(false);
	}

	public static void main(String[] args) {
		new ConsultaDirector();

	}

	public void consultar(String sentenciaSQL) {

		try {

			Class.forName(driver);
			connection = DriverManager.getConnection(url, usuario, password);
			statement = connection.createStatement();
			rs = statement.executeQuery(sentenciaSQL);
			String columna = "%5s       |     %-15s    |     %-15s    |     %-15s    |\n -------------------------------------------------------------------------------------------------------- \n";
			txtInfo.setText(String.format(columna, "ID", "NOMBRE", "APELLIDOS", "NACIONALIDAD"));
			while (rs.next()) {
				txtInfo.append(String.format(columna, rs.getString("idDirector"), rs.getString("nombreDirector"),
						rs.getString("apellidosDirector"), rs.getString("nacionalidadDirector")));
			}
			diaFeedback.setTitle("Confirmación");
			lblDiaFeedback.setText("La consulta se ha realizado con éxito");
			diaFeedback.setBackground(new Color(180, 211, 178));
		} catch (ClassNotFoundException cnfe) {
			diaFeedback.setTitle("Error");
			lblDiaFeedback.setText("Error de driver");
			diaFeedback.setBackground(new Color(243, 70, 74));
		} catch (SQLException se) {
			diaFeedback.setTitle("Error");
			lblDiaFeedback.setText("Error de conexión: url, usuario o clave" + se.getMessage());
			diaFeedback.setBackground(new Color(243, 70, 74));
		} finally {
			try {
				if (connection != null) {

					connection.close();
				}
			} catch (SQLException se) {
				diaFeedback.setTitle("Error");
				lblDiaFeedback.setText("Error al cerrar conexión");
				diaFeedback.setBackground(new Color(243, 70, 74));
			}
		}
		diaFeedback.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnConsulta) {
			txtInfo.setText("");
			String sentenciaSQL = "SELECT * FROM directores";
			consultar(sentenciaSQL);
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
		}

	}

	@Override
	public void windowClosing(WindowEvent e) {
		if (e.getSource() == diaFeedback) {
			diaFeedback.setVisible(false);
		} else if (e.getSource() == ventana) {
			ventana.dispose();
		}
	}

}