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
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.HashMap;


public class AltaPelAct extends WindowAdapter implements ActionListener {
	Frame ventana = new Frame("Películas Actores - Alta");
	CanvasImagen canvas = new CanvasImagen();
	Label lblTitulo = new Label("Introduzca una nueva relación", Label.CENTER);
	Label lblTitulo2 = new Label("entre Películas y Actores", Label.CENTER);
	Label lblPelicula = new Label("Película");
	Label lblActor = new Label("Actor");
	Choice choPeliculas = new Choice();
	Choice choActores = new Choice();
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
	
	HashMap<String, Integer> mapaPeliculas = new HashMap<>();
	HashMap<String, Integer> mapaActores = new HashMap<>();

	public AltaPelAct() {

		ventana.setLayout(gridbag);
		ventana.setBackground(new Color(120, 175, 169));
		ventana.setFont(new Font("SanSerif", 0, 12));
		rellenarPeliculas();
		rellenarActores();

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
		gbc.gridheight = 4;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 0.3;
		gbc.weighty = 1;
		canvas.setPreferredSize(new java.awt.Dimension(150, 280));
		ventana.add(canvas, gbc);
		gbc.gridheight = 1;

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		lblTitulo.setFont(new Font("Serif", 1, 20));
		ventana.add(lblTitulo, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		lblTitulo2.setFont(new Font("Serif", 1, 20));
		ventana.add(lblTitulo2, gbc);
		gbc.gridwidth = 1;

		gbc.gridx = 1;
		gbc.gridy = 2;
		ventana.add(lblPelicula, gbc);

		gbc.gridx = 2;
		gbc.gridy = 2;
		ventana.add(choPeliculas, gbc);

		gbc.gridx = 1;
		gbc.gridy = 3;
		ventana.add(lblActor, gbc);

		gbc.gridx = 2;
		gbc.gridy = 3;
		ventana.add(choActores, gbc);

		gbc.fill = GridBagConstraints.SOUTHWEST;
		gbc.anchor = GridBagConstraints.SOUTHWEST;
		gbc.gridx = 0;
		gbc.gridy = 4;
		btnAceptar.addActionListener(this);
		ventana.add(btnAceptar, gbc);

		gbc.fill = GridBagConstraints.SOUTHEAST;
		gbc.anchor = GridBagConstraints.SOUTHEAST;
		gbc.gridx = 2;
		gbc.gridy = 4;
		btnLimpiar.addActionListener(this);
		ventana.add(btnLimpiar, gbc);

		ventana.addWindowListener(this);
		ventana.setLocationRelativeTo(null);
		ventana.setSize(600, 300);
		ventana.setResizable(false);
		ventana.setVisible(true);

		
		dialogo.add(lblDia);
		dialogo.addWindowListener(this);
		dialogo.setLayout(new FlowLayout());
		dialogo.setSize(300, 80);
		dialogo.setResizable(true);
		dialogo.setLocationRelativeTo(null);
		dialogo.setVisible(false);
		
	}
	
	public class CanvasImagen extends Canvas {
	    public void paint(Graphics g) {
	        Image img = Toolkit.getDefaultToolkit().getImage("img\\pelact\\altPelAct.png");
	        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	    }
	}

	public void darAltaPelAct(Choice choPeliculas, Choice choActores) {

		String pelicula = choPeliculas.getSelectedItem();
		int idPelicula = mapaPeliculas.get(pelicula);
		String actor = choActores.getSelectedItem();
		int idActor = mapaActores.get(actor);
		String sentenciaSQL = "INSERT INTO peliculas_actores (idPeliculaFK, idActorFK) VALUES (?, ?)";

		try {
			BD.conectarBD();
			BD.ps = BD.connection.prepareStatement(sentenciaSQL);
			BD.ps.setInt(1, idPelicula);
			BD.ps.setInt(2, idActor);
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

	private void rellenarPeliculas() {
		choPeliculas.removeAll();
		mapaPeliculas.clear();
		
		try

		{
			BD.conectarBD();
			BD.ps = BD.connection.prepareStatement(BD.consultaSQLPel);
			BD.rs = BD.ps.executeQuery();
			choPeliculas.add("Seleccionar una película...");
			while (BD.rs.next()) {
				int id = BD.rs.getInt("idPelicula");
				String titulo = BD.rs.getString("tituloPelicula");
				java.time.LocalDate fecha = BD.rs.getObject("fechaEstrenoPelicula", java.time.LocalDate.class);
				int anio = fecha.getYear();
				String pelicula = titulo + " (" + anio +")";
				mapaPeliculas.put(pelicula, id);
				choPeliculas.add(pelicula);
			}
		}

		catch (ClassNotFoundException cnfe) {
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
	
	private void rellenarActores() {
		choActores.removeAll();
		mapaActores.clear();
		try

		{
			BD.conectarBD();
			BD.ps = BD.connection.prepareStatement(BD.consultaSQLActores);
			BD.rs = BD.ps.executeQuery();
			choActores.add("Seleccionar un actor...");
			while (BD.rs.next()) {
				int id = BD.rs.getInt("idActor");
				String nombreCompleto = BD.rs.getString("nombreActor") + " " + BD.rs.getString("apellidosActor");
				mapaActores.put(nombreCompleto, id);
				choActores.add(nombreCompleto);
			}
		}

		catch (ClassNotFoundException cnfe) {
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
			lblDia.setBackground(dialogo.getBackground());
		} else {
			dialogo.setTitle("Error");
			dialogo.setBackground(new Color(243, 70, 74));
			lblDia.setBackground(dialogo.getBackground());

			switch (e.getClass().getSimpleName()) {

			case "ClassNotFoundException":
				lblDia.setText("Error de driver. [" + e.getMessage() + "]");
				break;
			case "SQLException":
				lblDia.setText("Error de conexión: url, usuario o clave. [" + e.getMessage() + "]");
				break;
			case "DateTimeParseException":
				lblDia.setText("Formato de fecha incorrecto. [" + e.getMessage() + "]");
				break;
			default:
				lblDia.setText("Error. [" + e.getMessage() + "]");
			}
		}
		dialogo.pack();
		dialogo.setVisible(true);
		
	}

	public static void main(String[] args) {
		new AltaPelAct();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnLimpiar) {
			choPeliculas.select(0);
			choActores.select(0);	
		}

		if (e.getSource() == btnAceptar) {
			if ((choPeliculas.getSelectedIndex() == 0) || choActores.getSelectedIndex() == 0) {
				dialogoComprobacion(new Exception("Rellene todos los campos"));
			}
			else {
					darAltaPelAct(choPeliculas, choActores);
					rellenarPeliculas();
					rellenarActores();
			}
		}

		if (e.getSource() == mnuAltDir) {
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
	public void windowClosing(WindowEvent e) {
		if (e.getSource() == dialogo) {
			dialogo.setVisible(false);
		}
		else if (e.getSource() == ventana) {
			ventana.dispose();
		}

	}
}
