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

// ¿Si se repite los datos?  ¿Cambiar a preparedStatement?
public class AltaPelicula extends WindowAdapter implements ActionListener {
	Frame ventana = new Frame("Peliculas - Alta");
	CanvasImagen canvas = new CanvasImagen();
	Label lblPelicula = new Label("Introduzca una nueva Película", Label.CENTER);
	TextField txtTitulo = new TextField(25);
	TextField txtGenero = new TextField(25);
	TextField txtEstreno = new TextField(25);
	Label lblTitulo = new Label("Título");
	Label lblGenero = new Label("Género");
	Label lblEstreno = new Label("Fecha de Estreno (DD-MM-AAAA)");
	Label lblDirector = new Label("Director");
	Choice choDirector = new Choice();
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
	
	HashMap<String, Integer> mapaDirectores = new HashMap<>();

	public AltaPelicula() {

		ventana.setLayout(gridbag);
		ventana.setBackground(new Color(120, 175, 169));
		ventana.setFont(new Font("SanSerif", 0, 12));
		rellenarChoice();

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
		gbc.gridheight = 5;
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
		lblPelicula.setFont(new Font("Serif", 1, 20));
		ventana.add(lblPelicula, gbc);
		gbc.gridwidth = 1;

		gbc.gridx = 1;
		gbc.gridy = 1;
		ventana.add(lblTitulo, gbc);

		gbc.gridx = 2;
		gbc.gridy = 1;
		ventana.add(txtTitulo, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		ventana.add(lblGenero, gbc);

		gbc.gridx = 2;
		gbc.gridy = 2;
		ventana.add(txtGenero, gbc);

		gbc.gridx = 1;
		gbc.gridy = 3;
		ventana.add(lblEstreno, gbc);

		gbc.gridx = 2;
		gbc.gridy = 3;
		ventana.add(txtEstreno, gbc);

		gbc.gridx = 1;
		gbc.gridy = 4;
		ventana.add(lblDirector, gbc);
		gbc.gridx = 2;
		gbc.gridy = 4;
		ventana.add(choDirector, gbc);
 
		gbc.fill = GridBagConstraints.SOUTHWEST;
		gbc.anchor = GridBagConstraints.SOUTHWEST;
		gbc.gridx = 0;
		gbc.gridy = 5;
		btnAceptar.addActionListener(this);
		ventana.add(btnAceptar, gbc);

		gbc.fill = GridBagConstraints.SOUTHEAST;
		gbc.anchor = GridBagConstraints.SOUTHEAST;
		gbc.gridx = 2;
		gbc.gridy = 5;
		btnLimpiar.addActionListener(this);
		ventana.add(btnLimpiar, gbc);

		ventana.addWindowListener(this);
		ventana.setLocationRelativeTo(null);
		ventana.setSize(800, 320);
		ventana.setResizable(false);
		ventana.setVisible(true);

		// Dialogo Confirmación
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
	        Image img = Toolkit.getDefaultToolkit().getImage("img\\peliculas\\altPel.png");
	        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	    }
	}

	public void darAlta(Choice choDirectores) {

		String titulo = txtTitulo.getText();
		String genero = txtGenero.getText();
<<<<<<< HEAD:Cinemateca/src/es/studium/AltaPelicula.java
		String estreno = txtEstreno.getText();
=======
		String estreno = txtEstreno.getText().trim().replaceAll("[./ ]", "-");
		DateTimeFormatter formatoEntrada = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate fecha = LocalDate.parse(estreno, formatoEntrada);
		DateTimeFormatter formatoSalida = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String fechaFormateada = fecha.format(formatoSalida);
		String director = choDirectores.getSelectedItem();
		int idDirector = mapaDirectores.get(director);
>>>>>>> 39a8d1f (funcionalidad todas las ventanas):src/es/studium/AltaPelicula.java
		String sentenciaSQL = "INSERT INTO peliculas (tituloPelicula, generoPelicula, fechaEstrenoPelicula, idDirectorFK) VALUES (?, ?, ?, ?)";

		try {
			BD.conectarBD();
			BD.ps = BD.connection.prepareStatement(sentenciaSQL);
			BD.ps.setString(1, titulo);
			BD.ps.setString(2, genero);
			BD.ps.setString(3, fechaFormateada);
			BD.ps.setInt(4, idDirector);
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

	private void rellenarChoice() {
		choDirector.removeAll();
		mapaDirectores.clear();
		try

		{
			BD.conectarBD();
			BD.ps = BD.connection.prepareStatement(BD.consultaSQLDirectores);
			BD.rs = BD.ps.executeQuery();
			choDirector.add("Seleccionar un director...");
			while (BD.rs.next()) {
				int id = BD.rs.getInt("idDirector");
				String nombre = BD.rs.getString("nombreDirector");
				String apellidos = BD.rs.getString("apellidosDirector");
				String nacionalidad = BD.rs.getString("nacionalidadDirector");
				String director = nombre + " " + apellidos + " (" + nacionalidad + ")";
				mapaDirectores.put(director, id);
				choDirector.add(director);
				
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
		new AltaPelicula();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnLimpiar) {
			txtTitulo.setText("");
			txtGenero.setText("");
			txtEstreno.setText("");
		}

		if (e.getSource() == btnAceptar) {
			if ((choDirector.getSelectedIndex() == 0) || (txtTitulo.getText().trim().isEmpty())
					|| (txtGenero.getText().trim().isEmpty()) || (txtEstreno.getText().trim().isEmpty())) {
				dialogoComprobacion(new Exception("Rellene todos los campos"));
			}
			else if(!txtEstreno.getText().matches("^\\d{2}[-/]\\d{2}[-/]\\d{4}$")){
				dialogoComprobacion(new Exception("Formato de fecha incorrecto"));
			}
			else {
					darAlta(choDirector);
					rellenarChoice();
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
