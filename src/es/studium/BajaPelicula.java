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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;


public class BajaPelicula extends WindowAdapter implements ActionListener {
	Frame ventana = new Frame("Películas - Baja");

	Label lblElec = new Label("¿Qué película desea eliminar?");
	Choice lista = new Choice();
	Button btnElim = new Button("Eliminar");

	Dialog dialogo = new Dialog(ventana, "¿Segur@?", true);
	Label lblDia = new Label("");
	Label lblDia2 = new Label("¿Estás seguro de eliminarlo?");
	Button btnDiaSi = new Button("Si");
	Button btnDiaNo = new Button("No");

	Dialog diaFeedback = new Dialog(dialogo, "Confirmación", true);
	Label lblDiaFeedback = new Label("");

	GridBagLayout gridbag = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();

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

	public BajaPelicula() {
		ventana.setFont(new Font("SansSerif", 0, 12));
		ventana.setBackground(new Color(243, 70, 74));

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

		Usuario.permisosBasico(mnuDirectores, mnuPeliculas, mnuActores, mnuBajaDir, mnuModDir, mnuConsDir, mnuBajaPel,
				mnuConsPel, mnuBajaAct, mnuModAct, mnuConsAct);

		ventana.setMenuBar(mnuBar);

		ventana.setLayout(gridbag);

		datos();
		gbc.insets = new Insets(10, 10, 10, 10);

		gbc.gridx = 0;
		gbc.gridy = 0;
		lblElec.setFont(new Font("SanSerif", 3, 20));
		ventana.add(lblElec, gbc);

		gbc.gridy = 1;
		ventana.add(lista, gbc);

		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		btnElim.addActionListener(this);
		ventana.add(btnElim, gbc);

		ventana.setSize(340, 220);
		ventana.addWindowListener(this);
		ventana.setLocationRelativeTo(null);
		ventana.setResizable(false);
		ventana.setVisible(true);

		// Dialogo de confirmación

		dialogo.setSize(450, 198);
		dialogo.addWindowListener(this);
		dialogo.setLayout(gridbag);
		dialogo.setBackground(new Color(255, 165, 0));
		dialogo.setFont(new Font("SanSerif", 2, 12));

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridwidth = 4;
		gbc.anchor = GridBagConstraints.CENTER;
		dialogo.add(lblDia, gbc);

		gbc.gridy = 1;
		dialogo.add(lblDia2, gbc);
		gbc.gridwidth = 1;

		btnDiaSi.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.SOUTHWEST;
		btnDiaSi.setBackground(new Color(180, 211, 178));
		dialogo.add(btnDiaSi, gbc);

		btnDiaNo.addActionListener(this);
		gbc.gridx = 3;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.SOUTHEAST;
		btnDiaNo.setBackground(new Color(243, 70, 74));
		dialogo.add(btnDiaNo, gbc);

		dialogo.setLocationRelativeTo(null);
		dialogo.setResizable(false);
		dialogo.setVisible(false);

		// Dialogo FeedBack
		diaFeedback.setLayout(new FlowLayout());
		diaFeedback.add(lblDiaFeedback, gbc);
		diaFeedback.setSize(300, 80);
		diaFeedback.addWindowListener(this);
		diaFeedback.setLocationRelativeTo(null);
		dialogo.setResizable(false);
		diaFeedback.setVisible(false);

	}

	public void datos() {

		try {
			lista.removeAll();
			BD.conectarBD();
			BD.rs = BD.statement.executeQuery(BD.consultaSQLPeliculas);
			lista.add("Elige una película...");
			while (BD.rs.next()) {
				lista.add(BD.rs.getInt("idPelicula") + " | " + BD.rs.getString("tituloPelicula") + " | "
						+ BD.rs.getString("generoPelicula") + " | " + BD.rs.getString("fechaEstrenoPelicula") + " | "
						+ BD.rs.getInt("idDirectorFK"));
			}
		} catch (ClassNotFoundException cnfe) {
			diaFeedback.setBackground(new Color(243, 70, 74));
			lblDiaFeedback.setText("Error " + cnfe.getMessage());
		} catch (SQLException se) {
			diaFeedback.setBackground(new Color(243, 70, 74));
			lblDiaFeedback.setText("Error " + se.getMessage());
		} finally {
			try {
				BD.desconectarBD();
			} catch (SQLException se) {
				diaFeedback.setBackground(new Color(243, 70, 74));
				lblDiaFeedback.setText("Error " + se.getMessage());
			}
		}

	}

	public void darBaja() {

		String sentenciaSQL = BD.eliminarSQLPelicula + lista.getSelectedItem().split(" ")[0];

		try {
			BD.conectarBD();
			BD.statement.executeUpdate(sentenciaSQL);
			diaFeedback.setBackground(new Color(180, 211, 178));
			lblDiaFeedback.setText("Se ha eliminado la pelicula");
		} catch (ClassNotFoundException cnfe) {
			diaFeedback.setBackground(new Color(243, 70, 74));
			lblDiaFeedback.setText("Error de driver " + cnfe);
		} catch (SQLException se) {
			diaFeedback.setBackground(new Color(243, 70, 74));
			lblDiaFeedback.setText("Error de conexión: url, usuario o clave " + se.getMessage());
		} finally {
			try {
				BD.desconectarBD();
			} catch (SQLException se) {
				diaFeedback.setBackground(new Color(243, 70, 74));
				lblDiaFeedback.setText("Error al cerrar conexión " + se);
			}

		}
		diaFeedback.setVisible(true);
	}

	public static void main(String[] args) {
		new BajaPelicula();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnElim) {
			if (lista.getSelectedIndex() != 0) {
				lblDia.setText("Se va a eliminar a \"" + lista.getSelectedItem() + "\"");
				dialogo.setVisible(true);
			}
		}

		if (e.getSource() == btnDiaSi) {
			darBaja();
			dialogo.setVisible(false);
			datos();

		} else if (e.getSource() == btnDiaNo) {
			dialogo.setVisible(false);

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
		if (e.getSource() == dialogo) {
			dialogo.setVisible(false);

		} else if (e.getSource() == diaFeedback) {
			diaFeedback.setVisible(false);
		} else {
			ventana.dispose();
		}
	}
}
