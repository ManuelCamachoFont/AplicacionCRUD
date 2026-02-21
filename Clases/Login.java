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
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Login extends WindowAdapter implements ActionListener
{

	Frame ventana = new Frame("Login");
	
	Label lblInfo = new Label("Introduzca sus datos de usuario para iniciar sesión");
	Label lblUsuario = new Label ("Usuario");
	TextField txtUsuario = new TextField("Usuario", 20);
	Label lblClave = new Label ("Clave");
	TextField txtClave = new TextField("Password", 20);
	Button btnIniciar = new Button("Iniciar");
	Button btnLimpiar = new Button("Limpiar");
	
	GridBagLayout gridbag = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();
	
	final String usuario = "Manuel";
	final String clave = "1234";
	
	Dialog dlgError = new Dialog(ventana, "Error", true);
	Label lblMensaje = new Label("Error de credenciales");
	
	
	public Login() {
		
		ventana.setLayout(gridbag);
		ventana.setBackground(new Color(213, 255, 255));
		ventana.setFont(new Font("SansSerif", 0, 12));
		
		gbc.insets = new Insets (7, 7, 7, 7);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		ventana.add(lblInfo, gbc);
		gbc.gridwidth = 1;
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		ventana.add(lblUsuario, gbc);
		
		gbc.gridx = 1;
		ventana.add(txtUsuario, gbc);
		
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		ventana.add(lblClave, gbc);
		
		gbc.gridx = 1;
		txtClave.setEchoChar('*');
		ventana.add(txtClave, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		btnIniciar.addActionListener(this);
		ventana.add(btnIniciar, gbc);
		
		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.EAST;
		btnLimpiar.addActionListener(this);
		ventana.add(btnLimpiar, gbc);
		
		ventana.setSize(350, 230);
		ventana.addWindowListener(this);
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
		
		dlgError.addWindowListener(this);
		dlgError.setLayout(new FlowLayout());
		dlgError.add(lblMensaje);
		dlgError.setResizable(false);
		dlgError.setSize(160, 80);
		dlgError.setLocationRelativeTo(null);
		
	}
	
	public static void main(String[] args)
	{
		new Login();
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		if (e.getSource() == dlgError) {
			dlgError.setVisible(false);
		}
		else if (e.getSource() == ventana){
			ventana.dispose();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == btnLimpiar) {
			txtUsuario.setText("");
			txtClave.setText("");
			txtUsuario.requestFocus();
		}
		
		if (e.getSource() == btnIniciar) {
			
			String usuarioEscrito = txtUsuario.getText();
			String claveEscrita = txtClave.getText();
			if (usuario.equals(usuarioEscrito)&&clave.equals(claveEscrita)) { 
				new Principal2();
				java.awt.Window[] ventanas = java.awt.Window.getWindows();
				for (java.awt.Window v : ventanas) {
					if (v instanceof Frame) {
						Frame f = (Frame) v;

						if (!f.getTitle().equals("Menú principal")) {
							f.dispose();
						}
					}
				}
				ventana.dispose();
			}
			else {
				dlgError.setVisible(true);
			}

		}
		
	}

}

