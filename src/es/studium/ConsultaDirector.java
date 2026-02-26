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
import java.sql.SQLException;

public class ConsultaDirector extends WindowAdapter implements ActionListener {

	Frame ventana = new Frame("Directores - Consultas");
	TextArea txtInfo = new TextArea();
	Button btnConsulta = new Button("Consultas");
	Button btnPdf = new Button("Exportar a PDF");

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

	GridBagLayout gridbag = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();

	Dialog diaFeedback = new Dialog(ventana, "", true);
	Label lblDiaFeedback = new Label("");

	// Dialogo para la parte del tercer trimestre
	Dialog diaDesarrollo = new Dialog(ventana, "Acceso Denegado", true);
	Label lblDesarrollo = new Label("Esta parte está en desarrollo");

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

		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		btnPdf.addActionListener(this);
		ventana.add(btnPdf, gbc);

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

	public static void main(String[] args) {
		new ConsultaDirector();

	}

	public void consultar() {

		try {

			BD.conectarBD();
			BD.ps = BD.connection.prepareStatement(BD.consultaSQLDirectores);
			BD.rs = BD.ps.executeQuery();
			String columna = "%5s       |     %-15s    |     %-15s    |     %-15s    |\n -------------------------------------------------------------------------------------------------------- \n";
			txtInfo.setText(String.format(columna, "ID", "NOMBRE", "APELLIDOS", "NACIONALIDAD"));
			while (BD.rs.next()) {
				txtInfo.append(String.format(columna, BD.rs.getString("idDirector"), BD.rs.getString("nombreDirector"),
						BD.rs.getString("apellidosDirector"), BD.rs.getString("nacionalidadDirector")));
			}
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
			diaFeedback.setTitle("Enhorabuena");
			diaFeedback.setBackground(new Color(180, 211, 178));
			lblDiaFeedback.setText("La consulta se ha realizado con éxito");
		} else {
			diaFeedback.setTitle("Error");
			diaFeedback.setBackground(new Color(243, 70, 74));

			switch (e.getClass().getSimpleName()) {

			case "ClassNotFoundException":
				lblDiaFeedback.setText("Error de driver. [" + e.getMessage() + "]");
				break;
			case "SQLException":
				lblDiaFeedback.setText("Error de conexión: url, usuario o clave. [" + e.getMessage() + "]");
				break;

			default:
				lblDiaFeedback.setText("Error. [" + e.getMessage() + "]");
			}
		}
		diaFeedback.pack();
		diaFeedback.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnConsulta) {
			txtInfo.setText("");
			consultar();
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
		if (e.getSource() == diaFeedback) {
			diaFeedback.dispose();
		} else if (e.getSource() == diaDesarrollo) {
			diaDesarrollo.dispose();
		} else if (e.getSource() == ventana) {
			ventana.dispose();
		}
	}

}