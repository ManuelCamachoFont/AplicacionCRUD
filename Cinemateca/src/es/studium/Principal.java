package es.studium;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;



public class Principal extends WindowAdapter implements ActionListener
{

	Frame ventana = new Frame("Cinemateca");
	Label lblTitulo = new Label("Base de Datos");
	Button btnLogIn = new Button("Log In");

	GridBagLayout gridbag = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();

	public Principal()
	{

		ventana.setLayout(gridbag);
		ventana.setSize(400, 400);
		ventana.setBackground(new Color(213, 255, 255));
		ventana.setFont(new Font("SansSerif", 0, 12));

		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		lblTitulo.setFont(new Font("Serif", 1, 42));
		ventana.add(lblTitulo, gbc);
		
		
		gbc.insets = new Insets (0, 0, 50, 0);
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		btnLogIn.addActionListener(this);
		gbc.anchor = GridBagConstraints.SOUTH;
		ventana.add(btnLogIn, gbc);

		ventana.addWindowListener(this);
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
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
