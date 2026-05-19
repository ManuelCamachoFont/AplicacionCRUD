package es.studium;

import java.awt.Button;
import java.awt.Canvas;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class ModificacionPelAct extends WindowAdapter implements ActionListener {

	Frame ventana1 = new Frame("Películas Actores - Modificación");

	CanvasImagen canvas = new CanvasImagen();

	Choice choPelAct = new Choice();
	
	Button btnEditar = new Button("Editar");

	Frame ventana2 = new Frame("Editando relación...");
	Label lblPeliculas = new Label("Películas");
	Choice choPeliculas = new Choice();
	Label lblActores = new Label("Actores");
	Choice choActores= new Choice();
	Label lblElec = new Label("¿Qué relación desea modificar?", Label.CENTER);
	Label lblElecc = new Label("", Label.CENTER);
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
	Menu mnuPelAct = new Menu("Peliculas-Actores");
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
	MenuItem mnuModPelAct = new MenuItem("Modificación");
	MenuItem mnuConsPelAct = new MenuItem("Consulta");

	HashMap<String, Integer> mapaPelAct = new HashMap();
	HashMap<String, Integer> mapaPeliculas = new HashMap();
	HashMap<String, Integer> mapaActores = new HashMap();

	GridBagLayout gridbag = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();

	String sentenciaSQL = "";

	int idPelActSeleccionada;

	String pelActSeleccionada;

	String pelActNueva = "";
	
	String logs;

	public ModificacionPelAct() {

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

		ventana1.setMenuBar(mnuBar);

		// Ventana 1
		ventana1.setLayout(gridbag);
		ventana1.setBackground(new Color(120, 175, 169));
		Utilidades.aplicarIcono("ico/icono.png", ventana1);
		gbc.insets = new Insets(10, 10, 10, 10);
		rellenarChoicePelAct();

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 4;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 0.1;
		gbc.weighty = 1;
		canvas.setPreferredSize(new java.awt.Dimension(150, 280));
		ventana1.add(canvas, gbc);
		gbc.gridheight = 1;

		gbc.gridx = 1;
		gbc.gridy = 0;
		lblElec.setFont(new Font("SansSerif", 3, 20));
		ventana1.add(lblElec, gbc);

		gbc.gridy = 3;
		ventana1.add(choPelAct, gbc);

		gbc.gridy = 4;
		btnEditar.addActionListener(this);
		ventana1.add(btnEditar, gbc);

		ventana1.setSize(600, 300);
		ventana1.addWindowListener(this);
		ventana1.setResizable(false);
		ventana1.setLocationRelativeTo(null);
		ventana1.setVisible(true);

		// Ventana 2
		ventana2.setLayout(gridbag);
		ventana2.setBackground(new Color(120, 175, 169));
		Utilidades.aplicarIcono("ico/icono.png", ventana2);

		gbc.insets = new Insets(30, 5, 5, 5);

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		ventana2.add(lblElecc, gbc);
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridwidth = 1;

		gbc.gridx = 0;
		gbc.gridy = 2;
		ventana2.add(lblPeliculas, gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		ventana2.add(choPeliculas, gbc);

		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 3;
		ventana2.add(lblActores, gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;
		ventana2.add(choActores, gbc);

		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.SOUTHWEST;
		gbc.anchor = GridBagConstraints.SOUTHWEST;
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.insets = new Insets(10, 30, 30, 0);
		btnAceptar.addActionListener(this);
		ventana2.add(btnAceptar, gbc);

		gbc.fill = GridBagConstraints.SOUTHEAST;
		gbc.anchor = GridBagConstraints.SOUTHEAST;
		gbc.gridx = 1;
		gbc.gridy = 5;
		gbc.insets = new Insets(10, 0, 30, 30);
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

	public class CanvasImagen extends Canvas {
		public void paint(Graphics g) {
			Image img = Toolkit.getDefaultToolkit().getImage("img\\pelAct\\modPelAct.png");
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		}
	}

	private void rellenarChoicePelAct() {

		choPelAct.removeAll();
		mapaPelAct.clear();
		try

		{
			BD.conectarBD();
			BD.ps = BD.connection.prepareStatement(BD.consultaSQLPelAct);
			BD.rs = BD.ps.executeQuery();
			choPelAct.add("Elige una relación...");
			while (BD.rs.next()) {
				int id = BD.rs.getInt("idPeliculaActor");
				String pelicula = BD.rs.getString("pelicula");
				String actor= BD.rs.getString("actor");
				String pelAct = pelicula + " - " +  actor;
				mapaPelAct.put(pelAct, id);
				choPelAct.add(pelAct);
			}
		}

		catch (ClassNotFoundException cnfe) {
			dialogoComprobacion(cnfe, "", "");
		} catch (SQLException se) {
			dialogoComprobacion(se, "", "");
		} finally {
			try {
				BD.desconectarBD();
			} catch (SQLException se) {
				dialogoComprobacion(se, "", "");

			}
		}
	}

	private void rellenarChoiceP() {

		choPeliculas.removeAll();
		mapaPeliculas.clear();
		try

		{
			BD.conectarBD();
			BD.ps = BD.connection.prepareStatement(BD.consultaSQLPeliculas2);
			BD.rs = BD.ps.executeQuery();
			choPeliculas.add("Elige una película...");
			while (BD.rs.next()) {
				int id = BD.rs.getInt("idPelicula");
				String titulo = BD.rs.getString("tituloPelicula");
				String fecha = BD.rs.getString("fechaEstrenoPelicula");
				String director = BD.rs.getString("nombreCompletoDirector");
				String pelicula = titulo + " (" + fecha + ") " +  director;
				mapaPeliculas.put(pelicula, id);
				choPeliculas.add(pelicula);
			}
		}

		catch (ClassNotFoundException cnfe) {
			dialogoComprobacion(cnfe, "", "");
		} catch (SQLException se) {
			dialogoComprobacion(se, "", "");
		} finally {
			try {
				BD.desconectarBD();
			} catch (SQLException se) {
				dialogoComprobacion(se, "", "");

			}
		}
	}
	
	private void rellenarChoiceA() {

		choActores.removeAll();
		mapaActores.clear();
		
		try {
			
			BD.conectarBD();
			BD.ps = BD.connection.prepareStatement(BD.consultaSQLActores);
			BD.rs = BD.ps.executeQuery();
			choActores.add("Elige un actor...");
			while (BD.rs.next()) {
				int id = BD.rs.getInt("idActor");
				String nombre = BD.rs.getString("nombreActor");
				String apellidos = BD.rs.getString("apellidosActor");
				String actor = nombre + " " + apellidos;
				mapaActores.put(actor, id);
				choActores.add(actor);
			}
		} catch (ClassNotFoundException cnfe) {
			dialogoComprobacion(cnfe, "", "");
		} catch (SQLException se) {
			dialogoComprobacion(se, "", "");
		} finally {
			try {
				BD.desconectarBD();
			} catch (SQLException se) {
				dialogoComprobacion(se, "", "");
			}
		}

	}

	public void obtenerDatosPelAct(Choice choPelAct) {
		String pelAct = choPelAct.getSelectedItem();
		idPelActSeleccionada = mapaPelAct.get(pelAct);
		sentenciaSQL = BD.consultaSQLPelAct2 + " WHERE idPeliculaActor = ?";
		
		logs = "Sentencia: " + sentenciaSQL + "\n Valores seleccionados por el usuario: " + idPelActSeleccionada + " " + pelAct;
		Utilidades.guardarLog(
				Utilidades.formatearTexto(Usuario.nombre, "INFO", this.getClass().getSimpleName(), logs));
		try

		{
			BD.conectarBD();
			BD.ps = BD.connection.prepareStatement(sentenciaSQL);
			BD.ps.setInt(1, idPelActSeleccionada);
			BD.rs = BD.ps.executeQuery();
			BD.rs.next();
			String pelicula = BD.rs.getString("pelicula");
			int idPelicula = BD.rs.getInt("idPelicula");
			String actor = BD.rs.getString("actor");
			int idActor = BD.rs.getInt("idActor");
			pelActSeleccionada = pelicula + " - " + actor;
			lblElecc.setText("Estas editando a: " + pelActSeleccionada);

			rellenarChoiceP();
			for (int i = 0; i < choPeliculas.getItemCount(); i++) {
				String item = choPeliculas.getItem(i);
				Integer idItem = mapaPeliculas.get(item);
				if (idItem != null && idItem.equals(idPelicula)) {
					choPeliculas.select(i);
					break;
				}
			}
			rellenarChoiceA();
			for (int i = 0; i < choActores.getItemCount(); i++) {
				String item = choActores.getItem(i);
				Integer idItem = mapaActores.get(item);
				if (idItem != null && idItem.equals(idActor)) {
					choActores.select(i);
					break;
				}
			}
		}

		catch (ClassNotFoundException cnfe) {
			dialogoComprobacion(cnfe, "", "");
		} catch (SQLException se) {
			dialogoComprobacion(se, "", "");
		} finally {
			try {
				BD.desconectarBD();
			} catch (SQLException se) {
				dialogoComprobacion(se, "", "");

			}
		}

	}

	public void modificarPelAct(Choice choPeliculas, Choice choActores) {

		String peliculaSeleccionada = choPeliculas.getSelectedItem();
		int idPelicula = mapaPeliculas.get(peliculaSeleccionada);
		String actorSeleccionado = choActores.getSelectedItem();
		int idActor = mapaActores.get(actorSeleccionado);
		
		String sentenciaSQL = "UPDATE peliculas_actores SET idPeliculaFK = ?, idActorFK= ? WHERE idPeliculaActor = ?";
		
		logs = "Sentencia: " + sentenciaSQL + "\n Valores seleccionados por el usuario: " + idPelicula + " " + peliculaSeleccionada + ", " + idActor + " " + actorSeleccionado;
		Utilidades.guardarLog(
				Utilidades.formatearTexto(Usuario.nombre, "INFO", this.getClass().getSimpleName(), logs));

		try {
			
			pelActNueva = peliculaSeleccionada + " - " + actorSeleccionado;

			BD.conectarBD();
			BD.ps = BD.connection.prepareStatement(sentenciaSQL);
			BD.ps.setInt(1, idPelicula);
			BD.ps.setInt(2, idActor);
			BD.ps.setInt(3, idPelActSeleccionada);
			BD.ps.executeUpdate();
			dialogoComprobacion(null, peliculaSeleccionada, pelActNueva);
			pelActSeleccionada = pelActNueva;
			lblElecc.setText("Estás editando : " + pelActSeleccionada);
			ventana2.validate();

		} catch (NumberFormatException nfe) {
			dialogoComprobacion(nfe, "", "");

		} catch (ClassNotFoundException cnfe) {
			dialogoComprobacion(cnfe, "", "");
		} catch (SQLException se) {
			dialogoComprobacion(se, "", "");
		} finally {
			try {
				BD.desconectarBD();
			}

			catch (SQLException se) {
				dialogoComprobacion(se, "", "");
			}

		}

		rellenarChoiceP();
		rellenarChoiceA();
	}

	public void dialogoComprobacion(Exception e, String pelActS, String pelActN) {
		if (e == null) {
			diaFeedback.setTitle("Enhorabuena");
			diaFeedback.setBackground(new Color(180, 211, 178));
			lblDiaF.setText("Se ha modificado a \"" + pelActS + "\", ahora es \"" + pelActN + "\".");
			lblDiaF.setBackground(diaFeedback.getBackground());
		} else {
			diaFeedback.setTitle("Error");
			diaFeedback.setBackground(new Color(243, 70, 74));
			lblDiaF.setBackground(diaFeedback.getBackground());

			switch (e.getClass().getSimpleName()) {

			case "ClassNotFoundException":
				Utilidades.guardarLog(
						Utilidades.formatearTexto(Usuario.nombre, "ERROR", this.getClass().getSimpleName(), e.getMessage()));
				lblDiaF.setText("Error de driver. [" + e.getMessage() + "]");
				break;
			case "SQLException":
				Utilidades.guardarLog(
						Utilidades.formatearTexto(Usuario.nombre, "ERROR", this.getClass().getSimpleName(), e.getMessage()));
				lblDiaF.setText("Error de conexión: url, usuario o clave. [" + e.getMessage() + "]");
				break;
			case "DateTimeParseException":
				Utilidades.guardarLog(
						Utilidades.formatearTexto(Usuario.nombre, "ERROR", this.getClass().getSimpleName(), e.getMessage()));
				lblDiaF.setText("Formato de fecha incorrecto. Use DD-MM-AAAA [" + e.getMessage() + "]");
				break;

			default:
				Utilidades.guardarLog(
						Utilidades.formatearTexto(Usuario.nombre, "ERROR", this.getClass().getSimpleName(), e.getMessage()));
				lblDiaF.setText("Error. [" + e.getMessage() + "]");
			}
		}
		diaFeedback.pack();
		diaFeedback.setVisible(true);

	}

	public static void main(String[] args) {
		new ModificacionPelAct();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnEditar)) {
			if (choPelAct.getSelectedIndex() != 0) {
				obtenerDatosPelAct(choPelAct);
				ventana2.setVisible(true);

			} else {
				choPeliculas.requestFocus();
			}
		}

		else if (e.getSource() == btnAceptar) {

			if ((choPeliculas.getSelectedIndex() == 0 || choActores.getSelectedIndex() == 0)) {
				Utilidades.guardarLog(
						Utilidades.formatearTexto(Usuario.nombre, "WARNING", this.getClass().getSimpleName(), "Intento de Modificación, faltan campos por completar"));
				dialogoComprobacion(new Exception("Rellene todos los campos"), "", "");
			}

			else {
				modificarPelAct(choPeliculas, choActores);
			}

		} else if (e.getSource() == btnLimpiar) {
			choPeliculas.select(0);
			choActores.select(0);
			choPeliculas.requestFocus();
		}
		if ((e.getSource() == mnuAltDir)) {
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
		} else if (e.getSource() == mnuModPel) {
			new ModificacionPelicula();
		} else if (e.getSource() == mnuAltAct) {
			new AltaActor();
		} else if (e.getSource() == mnuBajaAct) {
			new BajaActor();
		} else if (e.getSource() == mnuModAct) {
			new ModificacionActor();
		} else if (e.getSource() == mnuConsAct) {
			new ConsultaActor();
		} else if (e.getSource() == mnuAltPelAct) {
			new AltaPelAct();
		} else if (e.getSource() == mnuBajaPelAct) {
			new BajaPelAct();
		} else if (e.getSource() == mnuModPelAct) {
			new ModificacionPelAct();
		} else if (e.getSource() == mnuConsPelAct) {
			new ConsultaPelAct();
		}
	}

	@Override
	public void windowClosing(WindowEvent e) {

		if (e.getSource() == diaFeedback) {
			diaFeedback.dispose();
		}

		if (e.getSource() == ventana2) {
			ventana2.dispose();
		} else if (e.getSource() == ventana1) {
			ventana1.dispose();
		}

	}
}