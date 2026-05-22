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
import java.util.HashMap;

public class ModificacionDirector extends WindowAdapter implements ActionListener
{

	Frame ventana1 = new Frame("Directores - Modificación");
	
	CanvasImagen canvas = new CanvasImagen();
	
	Choice choDirectores = new Choice();
	Button btnEditar = new Button("Editar");

	Frame ventana2 = new Frame("Editando Director...");
	Label lblElec = new Label("¿Qué director desea modificar?", Label.CENTER);
	Label lblElecc = new Label("", Label.CENTER);
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

	HashMap<String, Integer> mapaDirectores = new HashMap();
	
	GridBagLayout gridbag = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();

	String sentenciaSQL = "";

	int idDirectorSeleccionado;
	
	String directorSeleccionado;

	String directorNuevo = "";
	
	String logs;
	
	String ultimaSencia = "";
	
	public ModificacionDirector()
	{

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
		rellenarChoice();

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
		ventana1.add(choDirectores, gbc);

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
			Image img = Toolkit.getDefaultToolkit().getImage("img\\directores\\modDir.png");
			g.drawImage(img, 0, 0,this.getWidth(), this.getHeight(), this);
		}
	}

	private void rellenarChoice()
	{

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

		catch (ClassNotFoundException cnfe)
		{
			dialogoComprobacion(cnfe, "", "");
		} catch (SQLException se)
		{
			dialogoComprobacion(se, "", "");
		} finally
		{
			try
			{
				BD.desconectarBD();
			} catch (SQLException se)
			{
				dialogoComprobacion(se, "", "");

			}
		}
	}
	
	public void obtenerDatosDirector(Choice choDirectores) {
		String director = choDirectores.getSelectedItem();
		idDirectorSeleccionado = mapaDirectores.get(director);
		sentenciaSQL = BD.consultaSQLDirectores + " WHERE idDirector = ?";
		
		logs = "Sentencia: " + sentenciaSQL + "\n Valores seleccionados por el usuario: " + idDirectorSeleccionado + ", " + director;
		Utilidades.guardarLog(
				Utilidades.formatearTexto(Usuario.nombre, "INFO", this.getClass().getSimpleName(), logs));
		
		try

		{
			BD.conectarBD();
			BD.ps = BD.connection.prepareStatement(sentenciaSQL);
			BD.ps.setInt(1, idDirectorSeleccionado);
			BD.rs = BD.ps.executeQuery();
			BD.rs.next();
			String nombre = BD.rs.getString("nombreDirector");
			txtNombre.setText(nombre);
			String apellidos = (BD.rs.getString("apellidosDirector"));
			txtApellidos.setText(apellidos);
			String nacionalidad = (BD.rs.getString("nacionalidadDirector"));
			txtNacionalidad.setText(nacionalidad);
			directorSeleccionado = nombre + " " + apellidos + " (" + nacionalidad + ")";
			lblElecc.setText("Estas editando a: " + directorSeleccionado);
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
	
	public void modificarDirector(TextField nombreText, TextField apellidosText, TextField nacionalidadText) {
		
		String nombre = nombreText.getText().trim();
		String apellidos = apellidosText.getText().trim();
		String nacionalidad = nacionalidadText.getText().trim();
		
		String sentenciaSQL = "UPDATE directores SET nombreDirector = ?, apellidosDirector = ?, nacionalidadDirector = ? WHERE idDirector = ?";

		directorNuevo = nombre + " " + apellidos + " (" + nacionalidad + ")";
		
		try {
			
				BD.conectarBD();
				BD.ps = BD.connection.prepareStatement(sentenciaSQL);
				BD.ps.setString(1, nombre);
				BD.ps.setString(2, apellidos);
				BD.ps.setString(3, nacionalidad);
				BD.ps.setInt(4, idDirectorSeleccionado);
				BD.ps.executeUpdate();
				logs = "Sentencia: " + sentenciaSQL + "\n Valores escritos por el usuario: " + nombre + ", " + apellidos + ", " + nacionalidad;
				Utilidades.guardarLog(
						Utilidades.formatearTexto(Usuario.nombre, "INFO", this.getClass().getSimpleName(), logs));
				dialogoComprobacion(null, directorSeleccionado, directorNuevo);
				directorSeleccionado = directorNuevo;
				lblElecc.setText("Estás editando : " + directorSeleccionado);
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

		rellenarChoice();
	}

	public void dialogoComprobacion(Exception e, String directorS, String directorN)
	{
		if (e == null)
		{
			diaFeedback.setTitle("Enhorabuena");
			diaFeedback.setBackground(new Color(180, 211, 178));
			lblDiaF.setText("Se ha modificado a \"" + directorS + "\", ahora es \"" + directorN + "\".");
			lblDiaF.setBackground(diaFeedback.getBackground());
		} else
		{
			diaFeedback.setTitle("Error");
			diaFeedback.setBackground(new Color(243, 70, 74));
			lblDiaF.setBackground(diaFeedback.getBackground());

			switch (e.getClass().getSimpleName())
			{

			case "ClassNotFoundException":
				Utilidades.formatearTexto(Usuario.nombre, "ERROR", this.getClass().getSimpleName(), e.getMessage());
				lblDiaF.setText("Error de driver. [" + e.getMessage() + "]");
				break;
			case "SQLException":
				Utilidades.formatearTexto(Usuario.nombre, "ERROR", this.getClass().getSimpleName(), e.getMessage());
				lblDiaF.setText("Error de conexión: url, usuario o clave. [" + e.getMessage() + "]");
				break;

			default:
				Utilidades.formatearTexto(Usuario.nombre, "ERROR", this.getClass().getSimpleName(), e.getMessage());
				lblDiaF.setText("Error. [" + e.getMessage() + "]");
			}
		}
		diaFeedback.pack();
		diaFeedback.setVisible(true);

	}

	public static void main(String[] args)
	{
		new ModificacionDirector();
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource().equals(btnEditar))
		{
			if (choDirectores.getSelectedIndex() != 0)
			{
				obtenerDatosDirector(choDirectores);
				ventana2.setVisible(true);

			} else
			{
				choDirectores.requestFocus();
			}
		}

		else if (e.getSource() == btnAceptar)
		{

			if ((txtNombre.getText().trim().isEmpty()) || (txtApellidos.getText().trim().isEmpty())
					|| (txtNacionalidad.getText().trim().isEmpty()))
			{
				Utilidades.guardarLog(
						Utilidades.formatearTexto(Usuario.nombre, "WARNING", this.getClass().getSimpleName(), "Intento de Modificación, faltan campos por completar"));
				dialogoComprobacion(new Exception("Rellene todos los campos"), "", "");
			}
			else if (!txtNombre.getText().matches("^[\\p{L} .'-]+$") || !txtApellidos.getText().matches("^[\\p{L} .'-]+$") || !txtNacionalidad.getText().matches("^[\\p{L} .'-]+$")) {
				Utilidades.formatearTexto(Usuario.nombre, "WARNING", this.getClass().getSimpleName(), "Campos con carácteres no válidos");
				dialogoComprobacion(new Exception("Alguno de los campos contiene carácteres no válidos"), "", "");
			}

			else
			{
				modificarDirector(txtNombre, txtApellidos, txtNacionalidad);
			}

		} else if (e.getSource() == btnLimpiar)
		{
			txtNombre.setText("");
			txtApellidos.setText("");
			txtNacionalidad.setText("");
			txtNombre.requestFocus();
		}
		if (e.getSource() == mnuAltDir)
		{
			new AltaDirector();
		} else if (e.getSource() == mnuBajaDir)
		{
			new BajaDirector();
		} else if (e.getSource() == mnuModDir)
		{
			new ModificacionDirector();
		} else if (e.getSource() == mnuConsDir)
		{
			new ConsultaDirector();
		} else if (e.getSource() == mnuAltPel)
		{
			new AltaPelicula();
		} else if (e.getSource() == mnuBajaPel)
		{
			new BajaPelicula();
		} else if (e.getSource() == mnuConsPel)
		{
			new ConsultaPelicula();
		} else if (e.getSource() == mnuModPel){
			new ModificacionPelicula();
		}
		else if (e.getSource() == mnuAltAct)
		{
			new AltaActor();
		} else if (e.getSource() == mnuBajaAct)
		{
			new BajaActor();
		} else if (e.getSource() == mnuModAct)
		{
			new ModificacionActor();
		} else if (e.getSource() == mnuConsAct)
		{
			new ConsultaActor();
		}
		else if (e.getSource() == mnuAltPelAct)
		{
			new AltaPelAct();
		} else if (e.getSource() == mnuBajaPelAct)
		{
			new BajaPelAct();
		} else if (e.getSource() == mnuModPelAct)
		{
			new ModificacionPelAct();
		} else if (e.getSource() == mnuConsPelAct)
		{
			new ConsultaPelAct();
		}
	}

	@Override
	public void windowClosing(WindowEvent e)
	{

		if (e.getSource() == diaFeedback)
		{
			diaFeedback.dispose();
		}

		if (e.getSource() == ventana2)
		{
			ventana2.dispose();
		} else if (e.getSource() == ventana1)
		{
			ventana1.dispose();
		}

	}
}