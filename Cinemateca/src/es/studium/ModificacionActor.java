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


public class ModificacionActor extends WindowAdapter implements ActionListener
{

	Frame ventana1 = new Frame("Actores - Modificación");

	CanvasImagen canvas = new CanvasImagen();

	Choice choActores = new Choice();
	Button btnEditar = new Button("Editar");

	Frame ventana2 = new Frame("Editando Actor...");
	Label lblElec = new Label("¿Qué actor desea modificar?", Label.CENTER);
	Label lblElecc = new Label("", Label.CENTER);
	Label lblNombre = new Label("Nombre");
	TextField txtNombre = new TextField(30);
	Label lblApellidos = new Label("Apellidos");
	TextField txtApellidos = new TextField(30);
	Label lblSalario = new Label("Salario (Euros)");
	TextField txtSalario = new TextField(30);
	Button btnAceptar = new Button("Aceptar");
	Button btnLimpiar = new Button("Limpiar");

	Dialog diaFeedback = new Dialog(ventana2, "Confirmación", true);
	Label lblDiaF = new Label();

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

	HashMap<String, Integer> mapaActores = new HashMap();

	GridBagLayout gridbag = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();

	String sentenciaSQL = "";

	int idActorSeleccionado;
	
	String actorSeleccionado;

	String actorNuevo = "";

	public ModificacionActor()
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
		ventana1.add(choActores, gbc);

		gbc.gridy = 4;
		btnEditar.addActionListener(this);
		ventana1.add(btnEditar, gbc);

		ventana1.setSize(600, 330);
		ventana1.addWindowListener(this);
		ventana1.setResizable(false);
		ventana1.setLocationRelativeTo(null);
		ventana1.setVisible(true);

		// Ventana 2
		ventana2.setLayout(gridbag);
		ventana2.setBackground(new Color(120, 175, 169));

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
		ventana2.add(lblSalario, gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;
		ventana2.add(txtSalario, gbc);

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

	public class CanvasImagen extends Canvas {
		public void paint(Graphics g) {
			Image img = Toolkit.getDefaultToolkit().getImage("img\\actores\\modAct.jpg");
			g.drawImage(img, 0, 0,this.getWidth(), this.getHeight(), this);
		}
	}

<<<<<<< HEAD:Cinemateca/src/es/studium/ModificacionActor.java
	private void rellenarChoice()
	{
		try
=======
	private void rellenarChoice() {
		choActores.removeAll();
		mapaActores.clear();
		try {
>>>>>>> 39a8d1f (funcionalidad todas las ventanas):src/es/studium/ModificacionActor.java

			BD.conectarBD();
			BD.ps = BD.connection.prepareStatement(BD.consultaSQLActores);
			BD.rs = BD.ps.executeQuery();
<<<<<<< HEAD:Cinemateca/src/es/studium/ModificacionActor.java
			choActores.add("Seleccionar un actor...");
			while (BD.rs.next())
			{
				choActores.add(BD.rs.getInt("idActor") + " | " + BD.rs.getString("nombreActor") + " | "
						+ BD.rs.getString("apellidosActor") + " | " + BD.rs.getString("salarioActor"));
=======
			choActores.add("Elige un actor...");
			while (BD.rs.next()) {
				int id = BD.rs.getInt("idActor");
				String nombre = BD.rs.getString("nombreActor");
				String apellidos = BD.rs.getString("apellidosActor");
				String actor = nombre + " " + apellidos;
				mapaActores.put(actor, id);
				choActores.add(actor);
>>>>>>> 39a8d1f (funcionalidad todas las ventanas):src/es/studium/ModificacionActor.java
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
	
	public void obtenerDatosActor(Choice choActores) {
		String actor = choActores.getSelectedItem();
		idActorSeleccionado = mapaActores.get(actor);
		sentenciaSQL = BD.consultaSQLActores + " WHERE idActor = ?";
		try

		{
			BD.conectarBD();
			BD.ps = BD.connection.prepareStatement(sentenciaSQL);
			BD.ps.setInt(1, idActorSeleccionado);
			BD.rs = BD.ps.executeQuery();
			BD.rs.next();
			String nombre = BD.rs.getString("nombreActor");
			txtNombre.setText(nombre);
			String apellidos = (BD.rs.getString("apellidosActor"));
			txtApellidos.setText(apellidos);
			String salario = (BD.rs.getString("salarioActor"));
			txtSalario.setText(salario + "€");
			actorSeleccionado = nombre + " " + apellidos + " (" + salario + "€)";
			lblElecc.setText("Estas editando a: " + actorSeleccionado);
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

	public void modificarActor(TextField nombreText, TextField apellidosText, TextField salarioText) {
		
		String nombre = nombreText.getText().trim();
		String apellidos = apellidosText.getText().trim();
		String salarioString = salarioText.getText().trim();
		String salarioFormateado = salarioString.replace(',','.').replace("€", "");
		
		String sentenciaSQL = "UPDATE actores SET nombreActor = ?, apellidosActor = ?, salarioActor = ? WHERE idActor = ?";

		actorNuevo = nombre + " " + apellidos + " (" + salarioFormateado + "€)";
		
		try {
			float salario = Float.parseFloat(salarioFormateado);
			if (salario < 0) {
				dialogoComprobacion(new Exception("El salario no puede ser negativo"), "", "");
			} else {
				BD.conectarBD();
				BD.ps = BD.connection.prepareStatement(sentenciaSQL);
				BD.ps.setString(1, nombre);
				BD.ps.setString(2, apellidos);
				BD.ps.setFloat(3, salario);
				BD.ps.setInt(4, idActorSeleccionado);
				BD.ps.executeUpdate();
				dialogoComprobacion(null, actorSeleccionado, actorNuevo);
				actorSeleccionado = actorNuevo;
				lblElecc.setText("Estás editando : " + actorSeleccionado);
				ventana2.validate();
			}
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

	public void dialogoComprobacion(Exception e, String actorS, String actorN)
	{
		if (e == null)
		{
			diaFeedback.setTitle("Enhorabuena");
			diaFeedback.setBackground(new Color(180, 211, 178));
			lblDiaF.setText("Se ha modificado a \"" + actorS + "\", ahora es \"" + actorN + "\".");
<<<<<<< HEAD:Cinemateca/src/es/studium/ModificacionActor.java
		} else
		{
=======
			lblDiaF.setBackground(diaFeedback.getBackground());
		} else {
>>>>>>> 39a8d1f (funcionalidad todas las ventanas):src/es/studium/ModificacionActor.java
			diaFeedback.setTitle("Error");
			diaFeedback.setBackground(new Color(243, 70, 74));
			lblDiaF.setBackground(diaFeedback.getBackground());

			switch (e.getClass().getSimpleName())
			{

			case "ClassNotFoundException":
				lblDiaF.setText("Error de driver. [" + e.getMessage() + "]");
				break;
			case "SQLException":
				if (e.getMessage().contains("Incorrect decimal value"))
				{
					lblDiaF.setText("El formato no es válido. Escriba un número. [" + e.getMessage() + "]");
				} else
				{
					lblDiaF.setText("Error de conexión: url, usuario o clave. [" + e.getMessage() + "]");
				}
				break;
			case "NumberFormatException":
				lblDiaF.setText(
						"El salario contiene carácteres no válidos. Escriba un número [" + e.getMessage() + "]");
				break;
			default:
				lblDiaF.setText("Error. [" + e.getMessage() + "]");
			}
		}
		diaFeedback.pack();
		diaFeedback.setVisible(true);

	}

	public static void main(String[] args)
	{
		new ModificacionActor();
	}

	@Override
<<<<<<< HEAD:Cinemateca/src/es/studium/ModificacionActor.java
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource().equals(btnEditar))
		{
			if (choActores.getSelectedIndex() != 0)
			{
				actorSeleccionado = (choActores.getSelectedItem().split("\\|")[1].trim() + " "
						+ choActores.getSelectedItem().split("\\|")[2].trim());
				lblElecc.setText("Estás editando : " + (choActores.getSelectedItem().split("\\|")[1]).trim() + " "
						+ (choActores.getSelectedItem().split("\\|")[2]).trim());
				idActor = choActores.getSelectedItem().split("\\|")[0];
				sentenciaSQL = BD.consultaSQLActores + " WHERE idActor = ?";

				try

				{
					BD.conectarBD();
					BD.ps = BD.connection.prepareStatement(sentenciaSQL);
					BD.ps.setString(1, idActor);
					BD.rs = BD.ps.executeQuery();
					BD.rs.next();
					txtNombre.setText(BD.rs.getString("nombreActor"));
					txtApellidos.setText(BD.rs.getString("apellidosActor"));
					txtSalario.setText(BD.rs.getString("salarioActor"));
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
=======
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnEditar)) {
			if (choActores.getSelectedIndex() != 0) {
				obtenerDatosActor(choActores);
>>>>>>> 39a8d1f (funcionalidad todas las ventanas):src/es/studium/ModificacionActor.java
				ventana2.setVisible(true);

			} else
			{
				choActores.requestFocus();
			}
		}

<<<<<<< HEAD:Cinemateca/src/es/studium/ModificacionActor.java
		else if (e.getSource() == btnAceptar)
		{

			if ((txtNombre.getText().trim().isEmpty()) || (txtApellidos.getText().trim().isEmpty())
					|| (txtSalario.getText().trim().isEmpty()))
			{
				dialogoComprobacion(new Exception("Rellene todos los campos"), "", "");
			}

			else
			{
=======
		else if (e.getSource() == btnAceptar) {

			if ((txtNombre.getText().trim().isEmpty()) || (txtApellidos.getText().trim().isEmpty())
					|| (txtSalario.getText().trim().isEmpty())) {
				dialogoComprobacion(new Exception("Rellene todos los campos"), "", "");
			}

			else {
				modificarActor(txtNombre, txtApellidos, txtSalario);
			}
>>>>>>> 39a8d1f (funcionalidad todas las ventanas):src/es/studium/ModificacionActor.java

				String nombre = txtNombre.getText();
				String apellidos = txtApellidos.getText();
				Float salario = Float.parseFloat(txtSalario.getText());
				String sentenciaSQL = "UPDATE actores SET nombreActor = ?, apellidosActor = ?, salarioActor = ? WHERE idActor = ?";

				actorNuevo = nombre + " " + apellidos;
				boolean salarioValido = false;

				try
				{

					if (salario < 0)
					{
						dialogoComprobacion(new Exception("El salario no puede ser negativo"), "", "");
					} else
					{
						salarioValido = true;
					}
					if (salarioValido)
					{
						BD.conectarBD();
						BD.ps = BD.connection.prepareStatement(sentenciaSQL);
						BD.ps.setString(1, nombre);
						BD.ps.setString(2, apellidos);
						BD.ps.setFloat(3, salario);
						BD.ps.setString(4, idActor);
						BD.ps.executeUpdate();
						dialogoComprobacion(null, actorSeleccionado, actorNuevo);
						actorSeleccionado = actorNuevo;
						lblElecc.setText("Estás editando : " + actorNuevo);
						ventana2.validate();
					}
				} catch (NumberFormatException nfe)
				{
					dialogoComprobacion(nfe, "", "");

				} catch (ClassNotFoundException cnfe)
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
					}

					catch (SQLException se)
					{
						dialogoComprobacion(se, "", "");
					}

				}

				rellenarChoice();
			}

		} else if (e.getSource() == btnLimpiar)
		{
			txtNombre.setText("");
			txtApellidos.setText("");
			txtSalario.setText("");
			txtNombre.requestFocus();
		}
<<<<<<< HEAD:Cinemateca/src/es/studium/ModificacionActor.java
		if ((e.getSource() == mnuAltDir) || (e.getSource() == mnuAltDir2))
		{
			new AltaDirector();
		} else if ((e.getSource() == mnuBajaDir) || (e.getSource() == mnuBajaDir2))
		{
			new BajaDirector();
		} else if ((e.getSource() == mnuModDir) || (e.getSource() == mnuModDir2))
		{
			new ModificacionDirector();
		} else if ((e.getSource() == mnuConsDir) || (e.getSource() == mnuConsDir2))
		{
			new ConsultaDirector();
		} else if ((e.getSource() == mnuAltPel) || (e.getSource() == mnuAltPel2))
		{
			new AltaPelicula();
		} else if ((e.getSource() == mnuBajaPel) || (e.getSource() == mnuBajaPel2))
		{
			new BajaPelicula();
		} else if ((e.getSource() == mnuConsPel) || (e.getSource() == mnuConsPel2))
		{
			new ConsultaPelicula();
		} else if ((e.getSource() == mnuAltAct) || (e.getSource() == mnuAltAct2))
		{
			new AltaActor();
		} else if ((e.getSource() == mnuBajaAct) || (e.getSource() == mnuBajaAct2))
		{
			new BajaActor();
		} else if ((e.getSource() == mnuModAct) || (e.getSource() == mnuModAct2))
		{
			new ModificacionActor();
		} else if ((e.getSource() == mnuConsAct) || (e.getSource() == mnuConsAct2))
		{
			new ConsultaActor();
		} else if ((e.getSource() == mnuModPel) || (e.getSource() == mnuAltPelAct) || (e.getSource() == mnuBajaPelAct)
				|| (e.getSource() == mnuModPelAct) || (e.getSource() == mnuConsPelAct))
		{
			diaDesarrollo.setVisible(true);
=======
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
>>>>>>> 39a8d1f (funcionalidad todas las ventanas):src/es/studium/ModificacionActor.java
		}
	}

	@Override
	public void windowClosing(WindowEvent e)
	{

		if (e.getSource() == diaFeedback)
		{
			diaFeedback.dispose();
<<<<<<< HEAD:Cinemateca/src/es/studium/ModificacionActor.java
		} else if (e.getSource() == diaDesarrollo)
		{
			diaDesarrollo.dispose();
		}

		if (e.getSource() == ventana2)
		{
=======
		}
		if (e.getSource() == ventana2) {
>>>>>>> 39a8d1f (funcionalidad todas las ventanas):src/es/studium/ModificacionActor.java
			ventana2.dispose();
		} else if (e.getSource() == ventana1)
		{
			ventana1.dispose();
		}

	}
}