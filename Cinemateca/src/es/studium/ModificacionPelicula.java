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

public class ModificacionPelicula extends WindowAdapter implements ActionListener {

	Frame ventana1 = new Frame("Películas - Modificación");

	CanvasImagen canvas = new CanvasImagen();

	Choice choPeliculas = new Choice();
	Choice choDirectores = new Choice();
	Button btnEditar = new Button("Editar");

	Frame ventana2 = new Frame("Editando Película...");
	Label lblElec = new Label("¿Qué película desea modificar?", Label.CENTER);
	Label lblElecc = new Label("", Label.CENTER);
	TextField txtTitulo = new TextField(25);
	TextField txtGenero = new TextField(25);
	TextField txtEstreno = new TextField(25);
	Label lblTitulo = new Label("Título");
	Label lblGenero = new Label("Género");
	Label lblEstreno = new Label("Fecha de Estreno (DD-MM-AAAA)");
	Label lblDirector = new Label("Director");
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

	HashMap<String, Integer> mapaPeliculas = new HashMap();
	HashMap<String, Integer> mapaDirectores = new HashMap();

	GridBagLayout gridbag = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();

	String sentenciaSQL = "";

	int idPeliculaSeleccionada;

	String peliculaSeleccionada;

	String peliculaNueva = "";
	
	String logs;

	public ModificacionPelicula() {

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
		rellenarChoiceP();

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
		ventana1.add(choPeliculas, gbc);

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
		gbc.gridy = 1;
		ventana2.add(lblTitulo, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		ventana2.add(txtTitulo, gbc);

		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 2;
		ventana2.add(lblGenero, gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		ventana2.add(txtGenero, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		ventana2.add(lblEstreno, gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;
		ventana2.add(txtEstreno, gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		ventana2.add(lblDirector, gbc);
		gbc.gridx = 1;
		gbc.gridy = 4;
		ventana2.add(choDirectores, gbc);

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
		ventana2.setSize(500, 320);
		ventana2.setLocationRelativeTo(null);
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
			Image img = Toolkit.getDefaultToolkit().getImage("img\\peliculas\\modPel.png");
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
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
				String estreno = BD.rs.getString("fechaEstrenoPelicula");
				String director = BD.rs.getString("nombreCompletoDirector");
				String pelicula = titulo + " (" + estreno + ") " + director;
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

	private void rellenarChoiceD() {

		choDirectores.removeAll();
		mapaDirectores.clear();
		try

		{
			BD.conectarBD();
			BD.ps = BD.connection.prepareStatement(BD.consultaSQLDirectores);
			BD.rs = BD.ps.executeQuery();
			choDirectores.add("Elige un director...");
			while (BD.rs.next()) {
				int id = BD.rs.getInt("idDirector");
				String nombre = BD.rs.getString("nombreDirector");
				String apellidos = BD.rs.getString("apellidosDirector");
				String nacionalidad = BD.rs.getString("nacionalidadDirector");
				String director = nombre + " " + apellidos + " (" + nacionalidad + ")";
				mapaDirectores.put(director, id);
				choDirectores.add(director);
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

	public void obtenerDatosPelicula(Choice choPeliculas) {
		String pelicula = choPeliculas.getSelectedItem();
		idPeliculaSeleccionada = mapaPeliculas.get(pelicula);
		sentenciaSQL = BD.consultaSQLPeliculas3 + " WHERE idPelicula = ?";
		
		logs = "Sentencia: " + sentenciaSQL + "\n Valores seleccionados por el usuario: " + idPeliculaSeleccionada + " " + pelicula;
		Utilidades.guardarLog(
				Utilidades.formatearTexto(Usuario.nombre, "INFO", this.getClass().getSimpleName(), logs));
		
		try

		{
			BD.conectarBD();
			BD.ps = BD.connection.prepareStatement(sentenciaSQL);
			BD.ps.setInt(1, idPeliculaSeleccionada);
			BD.rs = BD.ps.executeQuery();
			BD.rs.next();
			String titulo = BD.rs.getString("tituloPelicula");
			txtTitulo.setText(titulo);
			String genero = BD.rs.getString("generoPelicula");
			txtGenero.setText(genero);
			String estreno = BD.rs.getString("fechaEstrenoPelicula");
			txtEstreno.setText(estreno);
			String director = BD.rs.getString("nombreCompletoDirector");
			int idDirector = BD.rs.getInt("idDirector");
			String[] estrenoFecha = estreno.split("/");
			String anio = estrenoFecha[estrenoFecha.length - 1];
			peliculaSeleccionada = titulo + " (" + anio + ") " + director;
			lblElecc.setText("Estas editando a: " + peliculaSeleccionada);

			rellenarChoiceD();
			for (int i = 0; i < choDirectores.getItemCount(); i++) {
				String item = choDirectores.getItem(i);
				Integer idItem = mapaDirectores.get(item);
				if (idItem != null && idItem == idDirector) {
					choDirectores.select(i);
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

	public void modificarPelicula(TextField tituloText, TextField generoText, TextField estrenoText,
			Choice choDirectores) {
		
		try {
			String titulo = tituloText.getText().trim();
			String genero = generoText.getText().trim();
			String estreno = estrenoText.getText().trim().replaceAll("[./ ]", "-");
			String directorSeleccionado = choDirectores.getSelectedItem();
			int idDirector = mapaDirectores.get(directorSeleccionado);

			String sentenciaSQL = "UPDATE peliculas SET tituloPelicula = ?, generoPelicula = ?, fechaEstrenoPelicula = ?, idDirectorFK = ? WHERE idPelicula = ?";
		
			DateTimeFormatter formatoEntrada = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			LocalDate fecha = LocalDate.parse(estreno, formatoEntrada);
			java.sql.Date fechaFormateada = java.sql.Date.valueOf(fecha);
			int anio = fecha.getYear();
			
			peliculaNueva = titulo + " (" + anio + ") " + directorSeleccionado;

			BD.conectarBD();
			BD.ps = BD.connection.prepareStatement(sentenciaSQL);
			BD.ps.setString(1, titulo);
			BD.ps.setString(2, genero);
			BD.ps.setDate(3, fechaFormateada);
			BD.ps.setInt(4, idDirector);
			BD.ps.setInt(5, idPeliculaSeleccionada);
			BD.ps.executeUpdate();
			logs = "Sentencia: " + sentenciaSQL + "\n Valores escritos por el usuario: " + titulo + ", " + genero + ", " + estreno + ", " + directorSeleccionado;
			Utilidades.guardarLog(
					Utilidades.formatearTexto(Usuario.nombre, "INFO", this.getClass().getSimpleName(), logs));
			dialogoComprobacion(null, peliculaSeleccionada, peliculaNueva);
			peliculaSeleccionada = peliculaNueva;
			lblElecc.setText("Estás editando : " + peliculaSeleccionada);
			ventana2.validate();

		} catch (java.time.format.DateTimeParseException dtpe) {
			dialogoComprobacion(new Exception("Formato de fecha incorrecto. Use DD-MM-AAAA"), "", "");
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
		rellenarChoiceD();
	}

	public void dialogoComprobacion(Exception e, String peliculaS, String peliculaN) {
		if (e == null) {
			diaFeedback.setTitle("Enhorabuena");
			diaFeedback.setBackground(new Color(180, 211, 178));
			lblDiaF.setText("Se ha modificado a \"" + peliculaS + "\", ahora es \"" + peliculaN + "\".");
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
						Utilidades.formatearTexto(Usuario.nombre, "WARNING", this.getClass().getSimpleName(), e.getMessage()));
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
		new ModificacionPelicula();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnEditar)) {
			if (choPeliculas.getSelectedIndex() != 0) {
				obtenerDatosPelicula(choPeliculas);
				ventana2.setVisible(true);

			} else {
				choPeliculas.requestFocus();
			}
		}

		else if (e.getSource() == btnAceptar) {

			if ((txtTitulo.getText().trim().isEmpty()) || (txtGenero.getText().trim().isEmpty())
					|| (txtEstreno.getText().trim().isEmpty()) || choDirectores.getSelectedIndex() == 0) {
				Utilidades.guardarLog(
						Utilidades.formatearTexto(Usuario.nombre, "WARNING", this.getClass().getSimpleName(), "Intento de Modificación, faltan campos por completar"));
				dialogoComprobacion(new Exception("Rellene todos los campos"), "", "");
			}else if(!txtEstreno.getText().matches("^\\d{2}[-/]\\d{2}[-/]\\d{4}$")){
				Utilidades.guardarLog(
						Utilidades.formatearTexto(Usuario.nombre, "INFO", this.getClass().getSimpleName(), "Formato de fecha incorrecto"));
				dialogoComprobacion(new Exception("Formato de fecha incorrecto"), "", "");
			}

			else {
				modificarPelicula(txtTitulo, txtGenero, txtEstreno, choDirectores);
			}

		} else if (e.getSource() == btnLimpiar) {
			txtTitulo.setText("");
			txtGenero.setText("");
			txtEstreno.setText("");
			choDirectores.select(0);
			txtTitulo.requestFocus();
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