package es.studium;

import java.awt.Button;
import java.awt.Canvas;
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

// ¿Si se repite los datos?  ¿Cambiar a preparedStatement?
public class AltaActor extends WindowAdapter implements ActionListener {
	Frame ventana = new Frame("Actores - Alta");
	CanvasImagen canvas = new CanvasImagen();
	Label lblActor = new Label("Introduzca un nuevo Actor", Label.CENTER);
	TextField txtNombre = new TextField(25);
	TextField txtApellidos = new TextField(25);
	TextField txtSalario = new TextField(25);
	Label lblNombre = new Label("Nombre");
	Label lblApellidos = new Label("Apellidos");
	Label lblSalario = new Label("Salario (Euros)");
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

	String logs;

	public AltaActor() {

		ventana.setLayout(gridbag);
		ventana.setBackground(new Color(120, 175, 169));
		Utilidades.aplicarIcono("ico/icono.png", ventana);
		ventana.setFont(new Font("SanSerif", 0, 12));

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
		lblActor.setFont(new Font("Serif", 1, 20));
		ventana.add(lblActor, gbc);
		gbc.gridwidth = 1;

		gbc.gridx = 1;
		gbc.gridy = 1;
		ventana.add(lblNombre, gbc);

		gbc.gridx = 2;
		gbc.gridy = 1;
		ventana.add(txtNombre, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		ventana.add(lblApellidos, gbc);

		gbc.gridx = 2;
		gbc.gridy = 2;
		ventana.add(txtApellidos, gbc);

		gbc.gridx = 1;
		gbc.gridy = 3;
		ventana.add(lblSalario, gbc);

		gbc.gridx = 2;
		gbc.gridy = 3;
		ventana.add(txtSalario, gbc);

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

		// Dialogo Confirmación
		dialogo.add(lblDia);
		dialogo.addWindowListener(this);
		dialogo.setLayout(new FlowLayout());
		dialogo.setSize(300, 80);
		dialogo.setResizable(false);
		dialogo.setLocationRelativeTo(null);
		dialogo.setVisible(false);

	}

	public class CanvasImagen extends Canvas {
		public void paint(Graphics g) {
			Image img = Toolkit.getDefaultToolkit().getImage("img\\actores\\altAct.jpg");
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		}
	}

	public void darAlta() {

		String nombre = txtNombre.getText();
		String apellidos = txtApellidos.getText();
		String salarioString = txtSalario.getText();
		String salarioFormateado = salarioString.replace(',', '.').replace("€", "");
		String sentenciaSQL = "INSERT INTO actores (idActor, nombreActor, apellidosActor, salarioActor) VALUES (null, ?, ?, ?)";
		logs = "Sentencia: " + sentenciaSQL + "\n Valores escritos por el usuario: " + nombre + ", " + apellidos + ", "
				+ salarioFormateado;
		try {
			float salario = Float.parseFloat(salarioFormateado);
			if (salario < 0) {
				dialogoComprobacion(new Exception("El salario no puede ser negativo"));
				Utilidades.guardarLog(Utilidades.formatearTexto(Usuario.nombre, "WARNING",
						this.getClass().getSimpleName(), "Intento de salario negativo"));
			} else {
				BD.conectarBD();
				BD.ps = BD.connection.prepareStatement(sentenciaSQL);
				BD.ps.setString(1, nombre);
				BD.ps.setString(2, apellidos);
				BD.ps.setFloat(3, salario);
				BD.ps.executeUpdate();
				Utilidades.guardarLog(
						Utilidades.formatearTexto(Usuario.nombre, "INFO", this.getClass().getSimpleName(), logs));
				dialogoComprobacion(null);
			}

		} catch (NumberFormatException nfe) {
			dialogoComprobacion(nfe);
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
				Utilidades.guardarLog(Utilidades.formatearTexto(Usuario.nombre, "ERROR",
						this.getClass().getSimpleName(), e.getMessage()));
				break;
			case "SQLException":
				if (e.getMessage().contains("Incorrect decimal value")) {
					lblDia.setText("El formato no es válido. Escriba un número. [" + e.getMessage() + "]");
					Utilidades.guardarLog(Utilidades.formatearTexto(Usuario.nombre, "WARNING",
							this.getClass().getSimpleName(), e.getMessage()));
				} else {
					lblDia.setText("Error de conexión: url, usuario o clave. [" + e.getMessage() + "]");
					Utilidades.guardarLog(Utilidades.formatearTexto(Usuario.nombre, "ERROR",
							this.getClass().getSimpleName(), e.getMessage()));
				}
				break;
			case "NumberFormatException":
				lblDia.setText("El salario contiene carácteres no válidos. Escriba un número [" + e.getMessage() + "]");
				Utilidades.guardarLog(Utilidades.formatearTexto(Usuario.nombre, "WARNING",
						this.getClass().getSimpleName(), e.getMessage()));
				break;
			default:
				lblDia.setText("Error. [" + e.getMessage() + "]");
				Utilidades.guardarLog(Utilidades.formatearTexto(Usuario.nombre, "ERROR",
						this.getClass().getSimpleName(), e.getMessage()));
			}
		}
		dialogo.pack();
		dialogo.setVisible(true);

	}

	public static void main(String[] args) {
		new AltaActor();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnLimpiar) {
			txtNombre.setText("");
			txtApellidos.setText("");
			txtSalario.setText("");
		}

		else if (e.getSource() == btnAceptar) {
			if ((txtNombre.getText().trim().isEmpty()) || (txtApellidos.getText().trim().isEmpty())
					|| (txtSalario.getText().trim().isEmpty())) {
				Utilidades.guardarLog(Utilidades.formatearTexto(Usuario.nombre, "WARNING",
						this.getClass().getSimpleName(), "Intento de Alta, faltan campos por completar"));
				dialogoComprobacion(new Exception("Rellene todos los campos"));
			} else if (!txtNombre.getText().matches("^[\\p{L} .'-]+$")
					|| !txtApellidos.getText().matches("^[\\p{L} .'-]+$")) {
				Utilidades.formatearTexto(Usuario.nombre, "WARNING", this.getClass().getSimpleName(),
						"Campos con carácteres no válidos");
				dialogoComprobacion(new Exception("Alguno de los campos contiene carácteres no válidos"));
			}

			else {
				darAlta();
			}
		}

		else if (e.getSource() == mnuAltDir) {
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
		if (e.getSource() == dialogo) {
			dialogo.dispose();
		}

		else if (e.getSource() == ventana) {
			ventana.dispose();
		}

	}
}
