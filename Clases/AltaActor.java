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
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

// ¿Si se repite los datos?  ¿Cambiar a preparedStatement?
public class AltaActor extends WindowAdapter implements ActionListener
{
	Frame ventana = new Frame("Actores - Alta");
	Label lblActor = new Label("Introduzca un nuevo Actor");
	TextField txtNombre = new TextField(25);
	TextField txtApellidos = new TextField(25);
	TextField txtSalario = new TextField(25);
	Label lblNombre = new Label("Nombre");
	Label lblApellidos = new Label("Apellidos");
	Label lblSalario = new Label("Salario (Número)");
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
	

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/p_cine";
	String usuario = "studium";
	String password = "Studium2025#";
	
	Connection connection = null;
	Statement statement = null;

	public AltaActor()
	{

		ventana.setLayout(gridbag);
		ventana.setBackground(new Color(180, 211, 178));
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

		// Ventana Alta
		gbc.insets = new Insets(10, 10, 10, 10);

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		lblActor.setFont(new Font("Serif", 1, 20));
		ventana.add(lblActor, gbc);
		gbc.gridwidth = 1;

		gbc.gridx = 0;
		gbc.gridy = 1;
		ventana.add(lblNombre, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		ventana.add(txtNombre, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		ventana.add(lblApellidos, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		ventana.add(txtApellidos, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		ventana.add(lblSalario, gbc);

		gbc.gridx = 1;
		gbc.gridy = 3;
		ventana.add(txtSalario, gbc);

		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.SOUTHWEST;
		gbc.anchor = GridBagConstraints.SOUTHWEST;
		gbc.gridx = 0;
		gbc.gridy = 4;
		btnAceptar.addActionListener(this);
		ventana.add(btnAceptar, gbc);

		gbc.fill = GridBagConstraints.SOUTHEAST;
		gbc.anchor = GridBagConstraints.SOUTHEAST;
		gbc.gridx = 1;
		gbc.gridy = 4;
		btnLimpiar.addActionListener(this);
		ventana.add(btnLimpiar, gbc);

		ventana.addWindowListener(this);
		ventana.setLocationRelativeTo(null);
		ventana.setSize(400, 280);
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

	public void darAlta()
	{

		String nombre = txtNombre.getText();
		String apellidos = txtApellidos.getText();
		String salario = txtSalario.getText();
		String sentencia = "INSERT INTO actores VALUES (null, \"" + nombre + "\", \"" + apellidos + "\", \""
				+ salario + "\")";

		try
		{
			Class.forName(driver);
			connection = DriverManager.getConnection(url, usuario, password);
			statement = connection.createStatement();
			statement.executeUpdate(sentencia);
			dialogo.setTitle("Enhorabuena");
			dialogo.setBackground(new Color(180, 211, 178));
			dialogo.setSize(300, 80);
			lblDia.setText("El alta se ha realizado con éxito");
			
		} catch (ClassNotFoundException cnfe)
		{
			dialogo.setTitle("Error");
			dialogo.setBackground(new Color(243, 70, 74));
			lblDia.setText("Error de driver "+ cnfe);
		} catch (SQLException se)
		{
			dialogo.setTitle("Error");
			dialogo.setBackground(new Color(243, 70, 74));
			lblDia.setText("Error de usuario, contraseña o url " + se);
		} finally
		{
			try
			{
				if (connection != null)
				{

					connection.close();
				}
			} catch (SQLException se)
			{
				dialogo.setTitle("Error");
				dialogo.setBackground(new Color(243, 70, 74));
				lblDia.setText("Error al cerrar conexión " + se);
			}
		}
		dialogo.setVisible(true);
	}

	public static void main(String[] args)
	{
		new AltaActor();

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == btnLimpiar)
		{
			txtNombre.setText("");
			txtApellidos.setText("");
			txtSalario.setText("");
		}

		if (e.getSource() == btnAceptar)
		{
			darAlta();
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
		} else if (e.getSource() == mnuAltAct)
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
	}

	@Override
	public void windowClosing(WindowEvent e)
	{
		if (e.getSource() == dialogo)
		{
			dialogo.setVisible(false);
		}

		else if (e.getSource() == ventana)
		{
			ventana.dispose();
		}

	}
}
