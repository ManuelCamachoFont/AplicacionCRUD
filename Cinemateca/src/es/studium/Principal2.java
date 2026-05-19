package es.studium;

import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Principal2 extends WindowAdapter implements ActionListener
{

	Frame ventana = new Frame("Menú principal");

	CanvasImagen canvas = new CanvasImagen();
	
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
	MenuItem mnuModPelAct = new MenuItem("Modificacion");
	MenuItem mnuConsPelAct = new MenuItem("Consulta");
	
	Label lblInfo = new Label("¡Bienvenido " + Usuario.nombre + "! se ha establecido conexión", Label.CENTER);
	Button btnLogOut = new Button("Log Out");
	Button btnAyuda = new Button("Ayuda");
	

	public Principal2()
	{

		ventana.setLayout(null);
		ventana.setSize(800, 800);
		ventana.setBackground(new Color(120, 175, 169));
		Utilidades.aplicarIcono("ico/icono.png", ventana);

		canvas.setBounds(0, 0, 800, 800);
		ventana.add(canvas);
		
		lblInfo.setBounds(190, 625, 400, 40);
		ventana.add(lblInfo);
		
		btnLogOut.setBounds(75, 725, 200, 40); 
	    btnLogOut.addActionListener(this);
	    btnLogOut.setForeground(new Color(255, 215, 0));
	    btnLogOut.setBackground(Color.BLACK);
		ventana.add(btnLogOut);
		
		btnAyuda.setBounds(500, 725, 200, 40); 
		btnAyuda.addActionListener(this);
	    btnAyuda.setForeground(new Color(255, 215, 0));
	    btnAyuda.setBackground(Color.BLACK);
		ventana.add(btnAyuda);
		
		ventana.setComponentZOrder(lblInfo, 0);
	    ventana.setComponentZOrder(btnLogOut, 1);
	    ventana.setComponentZOrder(btnAyuda, 2);
	    ventana.setComponentZOrder(canvas, 3);

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

		ventana.addWindowListener(this);
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);

	}
	
	private class CanvasImagen extends Canvas {
	    Image fondo = Toolkit.getDefaultToolkit().getImage("img/principal/background.png");
	    Image logo = Toolkit.getDefaultToolkit().getImage("img/principal/logo.gif");
	    
	    Image imagenBuffer;
	    Graphics gBuffer;

	    @Override
	    public void update(Graphics g) {
	        paint(g);
	    }

	    @Override
	    public void paint(Graphics g) {
	        if (imagenBuffer == null) {
	            imagenBuffer = createImage(this.getWidth(), this.getHeight());
	            gBuffer = imagenBuffer.getGraphics();
	        }

	        gBuffer.drawImage(fondo, 0, 0, this.getWidth(), this.getHeight(), this);
	        
	        int logoAncho = 400;
	        int logoAlto = 300;
	        int logoX = ((this.getWidth() - logoAncho) / 2) - 2;
	        int logoY = ((this.getHeight() - logoAlto) / 2) - 26;
	        gBuffer.drawImage(logo, logoX, logoY, logoAncho, logoAlto, this);
	        
	        g.drawImage(imagenBuffer, 0, 0, this);
	    }
	}

	public static void main(String[] args)
	{
		new Principal2();
	}

	@Override
	public void windowClosing(WindowEvent e)
	{
		if (e.getSource() == ventana) {
			Utilidades.guardarLog(Utilidades.formatearTexto(Usuario.nombre, "INFO", this.getClass().getSimpleName(), "Salida de la aplicación"));
		System.exit(0);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == btnAyuda) {
			Utilidades.consultarAyuda();
			Utilidades.guardarLog(Utilidades.formatearTexto(Usuario.nombre, "INFO", this.getClass().getSimpleName(), "Apertura de manual de ayuda"));
		}
		else if (e.getSource() == mnuAltDir)
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

		if (e.getSource() == btnLogOut)
		{
			Utilidades.guardarLog(Utilidades.formatearTexto(Usuario.nombre, "INFO", this.getClass().getSimpleName(), "Sesión cerrada"));
			Usuario.cerrarSesion();
			new Principal();
			java.awt.Window[] ventanas = java.awt.Window.getWindows();
			for (java.awt.Window v : ventanas)
			{
				if (v instanceof Frame)
				{
					Frame f = (Frame) v;

					if (!f.getTitle().equals("Cinemateca"))
					{
						f.dispose();
					}
				}
			}
		}

	}
}
