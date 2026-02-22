package es.studium;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Principal2 extends WindowAdapter implements ActionListener
{

	Frame ventana = new Frame("Menú principal");
	
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
	
	Label lblInfo = new Label("Conexión establecida con la base de datos");
	Button btnLogOut = new Button("Log Out");
	
	GridBagLayout gridbag = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();
	
	
	public Principal2() {
		
		ventana.setLayout(gridbag);
		ventana.setSize(400, 400);
		ventana.setBackground(new Color(213, 255, 255));
		ventana.setFont(new Font("SansSerif", 0, 12));
		
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
		
		Usuario.permisosBasico(mnuDirectores, mnuPeliculas, mnuActores, mnuBajaDir, mnuModDir, mnuConsDir, mnuBajaPel, mnuConsPel, mnuBajaAct,  mnuModAct, mnuConsAct);
		
		ventana.setMenuBar(mnuBar);
			
		gbc.gridx = 1;
		gbc.gridy = 1;
		ventana.add(lblInfo, gbc);
		
		gbc.gridy = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.SOUTHEAST;
		btnLogOut.addActionListener(this);
		ventana.add(btnLogOut, gbc);
		
		ventana.addWindowListener(this);
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
	}
	
	public static void main(String[] args)
	{
		new Principal2();
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == mnuAltDir) {
			new AltaDirector();
		}
		else if (e.getSource() == mnuBajaDir) {
			new BajaDirector();
		}
		else if (e.getSource() == mnuModDir) {
			new ModificacionDirector();
		}
		else if (e.getSource() == mnuConsDir) {
			new ConsultaDirector();
		}
		else if (e.getSource() == mnuAltPel) {
			new AltaPelicula();
		}
		else if (e.getSource() == mnuBajaPel) {
			new BajaPelicula();
		}
		else if (e.getSource() == mnuConsPel) {
			new ConsultaPelicula();
		}
		else if (e.getSource() == mnuAltAct) {
			new AltaActor();
		}
		else if (e.getSource() == mnuBajaAct) {
			new BajaActor();
		}
		else if (e.getSource() == mnuModAct) {
			new ModificacionActor();
		}
		else if (e.getSource() == mnuConsAct) {
			new ConsultaActor();
		}
		
		if (e.getSource() == btnLogOut) {
			Usuario.cerrarSesion();
			new Principal();
			java.awt.Window[] ventanas = java.awt.Window.getWindows();
			for (java.awt.Window v : ventanas) {
				if (v instanceof Frame) {
					Frame f = (Frame) v;

					if (!f.getTitle().equals("Cinemateca")) {
						f.dispose();
					}
				}
			}
		}
		
	}
}
