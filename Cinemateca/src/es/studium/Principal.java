package es.studium;

import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;



public class Principal extends WindowAdapter implements ActionListener
{

	Frame ventana = new Frame("Cinemateca");
	Button btnLogIn = new Button("Log In");
	
	CanvasImagen canvas = new CanvasImagen();

	public Principal()
	{

		ventana.setLayout(null);
		ventana.setSize(800, 800);
		ventana.setBackground(new Color(120, 175, 169));
		Utilidades.aplicarIcono("ico/icono.png", ventana);
		
		canvas.setBounds(0, 0, 800, 800);
		ventana.add(canvas);
		
		btnLogIn.setBounds(300, 685, 200, 40); 
	    btnLogIn.addActionListener(this);
	    btnLogIn.setForeground(new Color(255, 215, 0));
	    btnLogIn.setBackground(Color.BLACK);
		ventana.add(btnLogIn);
		
		ventana.setComponentZOrder(btnLogIn, 0);
	    ventana.setComponentZOrder(canvas, 1);
		
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
		new Principal();
	}

	@Override
	public void windowClosing(WindowEvent e)
	{ 
		System.exit(0);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == btnLogIn)
		{
			new Login();
		}
	}
}
