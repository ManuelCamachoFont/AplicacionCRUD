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
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class ConsultaPelAct extends WindowAdapter implements ActionListener
{

	Frame ventana = new Frame("Películas Actores - Consultas");
	CanvasImagen canvas = new CanvasImagen();
	Panel tabla = new Panel();
	ScrollPane scroll = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);
	Button btnConsulta = new Button("Consultas");
	Panel panelBotones = new Panel();
	Button btnOrdenActores = new Button("Ordenar por actores");
	Button btnOrdenPeliculas = new Button("Ordenar por películas");
	Button btnPdf = new Button("Exportar a PDF");

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
	
	GridBagLayout gridbag = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();

	Dialog diaFeedback = new Dialog(ventana, "", true);
	Label lblDiaFeedback = new Label("");

	

	public ConsultaPelAct()
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

		ventana.setMenuBar(mnuBar);

		ventana.setLayout(gridbag);
		ventana.setBackground(new Color(120, 175, 169));
		ventana.setFont(new Font("SanSerif", 0, 12));
	
		gbc.insets = new Insets(5, 5, 5, 5);

		gbc.fill = GridBagConstraints.BOTH;
		
		gbc.gridy = 0;
		gbc.gridx = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		tabla.setBackground(Color.BLACK);
		tabla.setLayout(new GridLayout(0, 3, 1, 1));
		scroll.add(tabla);
		scroll.setVisible(false);
		ventana.add(scroll, gbc);

		ventana.add(canvas, gbc);
		
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		btnConsulta.addActionListener(this);
		ventana.add(btnConsulta, gbc);

		
		panelBotones.setLayout(new GridLayout(1, 2, 5, 5));
		btnOrdenPeliculas.addActionListener(this);
		panelBotones.add(btnOrdenPeliculas);
		btnOrdenActores.addActionListener(this);
		panelBotones.add(btnOrdenActores);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		ventana.add(panelBotones, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.CENTER;
		btnPdf.addActionListener(this);

		ventana.add(btnPdf, gbc);

		ventana.addWindowListener(this);
		ventana.setLocationRelativeTo(null);
		ventana.setSize(800, 645);
		ventana.setResizable(false);
		ventana.setVisible(true);

		diaFeedback.setLayout(new FlowLayout());
		diaFeedback.add(lblDiaFeedback);
		diaFeedback.addWindowListener(this);
		diaFeedback.setLocationRelativeTo(null);
		diaFeedback.setSize(320, 80);
		diaFeedback.setResizable(false);
		diaFeedback.setVisible(false);

	}

	public static void main(String[] args)
	{
		new ConsultaPelAct();

	}
	
	class CanvasImagen extends Canvas {
	    public void paint(Graphics g) {
	        Image img = Toolkit.getDefaultToolkit().getImage("img\\pelact\\consPelAct.jpg");
	        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	    }
	}

	public void consultar(Panel tabla, String consulta)
	{
		tabla.removeAll();
		agregarCelda(tabla, "ID", true, 0);
		agregarCelda(tabla, "PELICULA", true, 0);
		agregarCelda(tabla, "ACTOR", true, 0);

		try
		{
			BD.conectarBD();
			BD.ps = BD.connection.prepareStatement(consulta);
			BD.rs = BD.ps.executeQuery();
			
			int filas = 1;
			while (BD.rs.next())
			{
				filas++;
				String id = String.valueOf(BD.rs.getInt("idPeliculaActor"));
				String pelicula = BD.rs.getString("pelicula");
				String actor = BD.rs.getString("actor");
				
				agregarCelda(tabla, id, false, filas);
				agregarCelda(tabla, pelicula, false, filas);
				agregarCelda(tabla, actor, false, filas);
				
			}
			canvas.setVisible(false);
			scroll.setVisible(true);
			int anchoScroll = scroll.getViewportSize().width;
			tabla.setPreferredSize(new java.awt.Dimension(anchoScroll, filas* 30));
			tabla.setSize(anchoScroll, filas* 30);
			tabla.revalidate();
			tabla.repaint();
			dialogoComprobacion(null);
		} catch (ClassNotFoundException cnfe)
		{
			dialogoComprobacion(cnfe);
		} catch (SQLException se)
		{
			dialogoComprobacion(se);
		} finally
		{
			try
			{
				BD.desconectarBD();
			} catch (SQLException se)
			{
				dialogoComprobacion(se);
			}
		}
		
	}
	
	private void agregarCelda(Panel tabla, String texto, boolean encabezado, int fila) {
        Label celda = new Label(texto);
        celda.setBackground(Color.WHITE);
        celda.setAlignment(Label.CENTER);
        
        if (encabezado) {
            celda.setFont(new Font("Arial", Font.BOLD, 14));
            celda.setBackground(new Color(19, 38, 92));
            celda.setForeground(Color.WHITE);
        } else {
            celda.setFont(new Font("Monospaced", Font.PLAIN, 13));
            if (fila % 2 == 0) {
            	celda.setBackground(new Color(173, 216, 230));
            } else {
            	celda.setBackground(Color.WHITE);
            }
        }
        
        tabla.add(celda);
    }
	
	public void dialogoComprobacion(Exception e) {
		if (e == null) {
			diaFeedback.setTitle("Enhorabuena");
			diaFeedback.setBackground(new Color(180, 211, 178));
			lblDiaFeedback.setText("La consulta se ha realizado con éxito");
			lblDiaFeedback.setBackground(diaFeedback.getBackground());
		} else {
			diaFeedback.setTitle("Error");
			diaFeedback.setBackground(new Color(243, 70, 74));
			lblDiaFeedback.setBackground(diaFeedback.getBackground());

			switch (e.getClass().getSimpleName()) {

			case "ClassNotFoundException":
				lblDiaFeedback.setText("Error de driver. [" + e.getMessage() + "]");
				break;
			case "SQLException":
				lblDiaFeedback.setText("Error de conexión: url, usuario o clave. [" + e.getMessage() + "]");
				break;
			
			default:
				lblDiaFeedback.setText("Error. [" + e.getMessage() + "]");
			}
		}
		diaFeedback.pack();
		diaFeedback.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource().equals(btnConsulta))
		{
			consultar(tabla, BD.consultaSQLPelAct);
		}
		else if (e.getSource().equals(btnOrdenPeliculas))
		{
			consultar(tabla, BD.consultaSQLPelAct);
		}
		else if (e.getSource().equals(btnOrdenActores))
		{
			consultar(tabla, BD.consultaSQLPelAct);
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
		} else if (e.getSource() == ventana)
		{
			ventana.dispose();
		}
	}

}