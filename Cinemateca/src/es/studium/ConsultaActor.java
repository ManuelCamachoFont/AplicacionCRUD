package es.studium;

import java.awt.Button;
import java.awt.Canvas;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Desktop;
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
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

import com.itextpdf.io.exceptions.IOException;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;

public class ConsultaActor extends WindowAdapter implements ActionListener
{

	Frame ventana = new Frame("Actores - Consultas");
	CanvasImagen canvas = new CanvasImagen();
	Panel tabla = new Panel();
	ScrollPane scroll = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);
	Button btnConsulta = new Button("Consultas");
	Panel panelBotones = new Panel();
	Button btnOrdenNombre = new Button("Ordenar por nombre");
	Button btnOrdenApellidos = new Button("Ordenar por apellidos");
	Button btnOrdenSalario = new Button("Ordenar por salario");
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

	String ultimaConsulta = "";
	String logs;

	Panel panelFiltros = new Panel(new FlowLayout(FlowLayout.CENTER, 10, 10));
	Choice choFiltro = new Choice();
	TextField txtFiltro = new TextField(20);
	Button btnAddFiltro = new Button("Añadir Filtro");
	Button btnLimpiarFiltro = new Button("Limpiar");
	Label lblFiltrosActivos = new Label("Filtros: Ninguno");
	ArrayList<String> sentenciaFiltros = new ArrayList<>();
	ArrayList<String> filtrosSentencia = new ArrayList<>();
	

	public ConsultaActor()
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
		Utilidades.aplicarIcono("ico/icono.png", ventana);
		ventana.setLayout(gridbag);
		ventana.setBackground(new Color(120, 175, 169));
		ventana.setFont(new Font("SanSerif", 0, 12));



		gbc.insets = new Insets(5, 5, 5, 5);

		gbc.fill = GridBagConstraints.BOTH;

		gbc.gridy = 0;
		gbc.gridx = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		tabla.setBackground(new Color(120, 175, 169));
		tabla.setLayout(null);
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


		panelBotones.setLayout(new GridLayout(1, 3, 5, 5));
		btnOrdenNombre.addActionListener(this);
		btnOrdenNombre.setEnabled(false);
		panelBotones.add(btnOrdenNombre);
		btnOrdenApellidos.addActionListener(this);
		btnOrdenApellidos.setEnabled(false);
		panelBotones.add(btnOrdenApellidos);
		btnOrdenSalario.setEnabled(false);
		btnOrdenSalario.addActionListener(this);
		panelBotones.add(btnOrdenSalario);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		ventana.add(panelBotones, gbc);

		choFiltro.add("Nombre");
		choFiltro.add("Apellidos");
		choFiltro.add("Salario mayor que");
		choFiltro.add("Salario menor que");
		panelFiltros.add(choFiltro);
		panelFiltros.add(txtFiltro);
		btnAddFiltro.addActionListener(this);
		panelFiltros.add(btnAddFiltro);
		btnLimpiarFiltro.addActionListener(this);
		panelFiltros.add(btnLimpiarFiltro);

		gbc.gridy = 3;
		gbc.gridx = 0;
		gbc.gridwidth = 3;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER;
		ventana.add(panelFiltros, gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.anchor = GridBagConstraints.CENTER;
		ventana.add(lblFiltrosActivos, gbc);

		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.anchor = GridBagConstraints.CENTER;
		btnPdf.addActionListener(this);
		btnPdf.setEnabled(false);
		ventana.add(btnPdf, gbc);

		ventana.addWindowListener(this);
		ventana.setSize(800, 645);
		ventana.setLocationRelativeTo(null);
		ventana.setResizable(false);
		ventana.setVisible(true);

		diaFeedback.setLayout(new FlowLayout());
		diaFeedback.add(lblDiaFeedback);
		diaFeedback.addWindowListener(this);
		diaFeedback.setSize(320, 80);
		diaFeedback.setLocationRelativeTo(null);
		diaFeedback.setResizable(false);
		diaFeedback.setVisible(false);

	}

	public static void main(String[] args)
	{
		new ConsultaActor();

	}

	class CanvasImagen extends Canvas {
		public void paint(Graphics g) {
			Image img = Toolkit.getDefaultToolkit().getImage("img\\actores\\consAct.jpg");
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		}
	}

	public void consultar(Panel tabla, String consulta, ArrayList<String> filtro)
	{
		tabla.removeAll();
		canvas.setVisible(false);
		scroll.setVisible(true);
		ventana.validate();

		int anchoScroll = scroll.getViewportSize().width;
		int anchoId = 80;
		int anchoNombre = 280;
		int anchoApellidos = 280;
		int anchoSalario = anchoScroll - anchoId - anchoNombre - anchoApellidos - 1;
		
		agregarCelda(tabla, "ID", true, 0, 0, anchoId);
		agregarCelda(tabla, "NOMBRE", true, 0, anchoId, anchoNombre);
		agregarCelda(tabla, "APELLIDOS", true, 0, anchoId + anchoNombre, anchoApellidos);
		agregarCelda(tabla, "SALARIO", true, 0, anchoId + anchoNombre + anchoApellidos, anchoSalario);
		try
		{
			BD.conectarBD();
			BD.ps = BD.connection.prepareStatement(consulta);
			for (int i = 0; i < filtro.size(); i++) {
				BD.ps.setString(i + 1, filtro.get(i));
			}
			BD.rs = BD.ps.executeQuery();
			logs = ultimaConsulta + " | Filtros: " + filtrosSentencia;
			Utilidades.guardarLog(Utilidades.formatearTexto(Usuario.nombre, "INFO",
					this.getClass().getSimpleName(), logs));


			int filas = 0;
			while (BD.rs.next())
			{
				filas++;
				String id = String.valueOf(BD.rs.getInt("idActor"));
				String nombre = BD.rs.getString("nombreActor");
				String apellidos = BD.rs.getString("apellidosActor");
				String salario = BD.rs.getString("salarioEuro");

				agregarCelda(tabla, id, false, filas, 0, anchoId);
				agregarCelda(tabla, nombre, false, filas, anchoId, anchoNombre);
				agregarCelda(tabla, apellidos, false, filas, anchoId + anchoNombre, anchoApellidos);
				agregarCelda(tabla, salario, false, filas, anchoId + anchoNombre + anchoApellidos, anchoSalario);

			}
			tabla.setPreferredSize(new java.awt.Dimension(anchoScroll, filas * 30));
			tabla.setSize(anchoScroll, filas * 30);
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

	private void agregarCelda(Panel tabla, String texto, boolean encabezado, int fila, int columna, int ancho) {
		Label celda = new Label(texto, Label.CENTER);
		celda.setBackground(Color.WHITE);
		celda.setBounds(columna + 1, fila * 30 + 1, ancho - 1, 29);

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

	public void addFiltro() {
		String seleccion = choFiltro.getSelectedItem();
		String filtro = txtFiltro.getText().trim();
		String campoSQL = "";

		if (filtro.isEmpty()) { 
			return;
		}

		try {
			switch (seleccion) {
			case "Nombre":
				campoSQL = "nombreActor";
				sentenciaFiltros.add(campoSQL + " LIKE ?");
				filtrosSentencia.add("%" + filtro + "%");
				break;

			case "Apellidos":
				campoSQL = "apellidosActor";
				sentenciaFiltros.add(campoSQL + " LIKE ?");
				filtrosSentencia.add("%" + filtro + "%");
				break;

			case "Salario mayor que":
				Float.parseFloat(filtro); 
				campoSQL = "salarioActor";
				sentenciaFiltros.add(campoSQL + " >= ?");
				filtrosSentencia.add(filtro);
				break;

			case "Salario menor que":
				Float.parseFloat(filtro);
				campoSQL = "salarioActor";
				sentenciaFiltros.add(campoSQL + " <= ?");
				filtrosSentencia.add(filtro);
				break;
			}

			if (lblFiltrosActivos.getText().equals("Filtros: Ninguno")) {
				lblFiltrosActivos.setText("Filtros: " + seleccion + "(" + filtro + ")");
			} else {
				lblFiltrosActivos.setText(lblFiltrosActivos.getText() + ", " + seleccion + "(" + filtro + ")");
			}
			txtFiltro.setText("");}
		catch (NumberFormatException nfe) {
			dialogoComprobacion(new Exception("Para filtrar por salario debe introducir un número válido."));
		}

	}
	
	public void generarPdf(String destino, String consulta, ArrayList<String> filtros) {
		try {
			PdfWriter writer = new PdfWriter(destino);
			PdfDocument pdf = new PdfDocument(writer);
			Document document = new Document(pdf, PageSize.A4.rotate());
			
			PdfFont negrita = PdfFontFactory.createRegisteredFont(StandardFonts.HELVETICA_BOLD);
			PdfFont fuente = PdfFontFactory.createFont(StandardFonts.HELVETICA);
			
			Table table = new Table(UnitValue.createPercentArray(new float[] {1, 3, 3, 3})).useAllAvailableWidth();
			table.addHeaderCell(new Cell().add(new Paragraph("ID").setFont(negrita)));
			table.addHeaderCell(new Cell().add(new Paragraph("Nombre").setFont(negrita)));
			table.addHeaderCell(new Cell().add(new Paragraph("Apellidos").setFont(negrita)));
			table.addHeaderCell(new Cell().add(new Paragraph("Salario").setFont(negrita)));
			
			BD.conectarBD();
			BD.ps = BD.connection.prepareStatement(consulta);
			for (int i = 0; i < filtros.size(); i++) {
				BD.ps.setString(i + 1,  filtros.get(i));
			}
			BD.rs = BD.ps.executeQuery();
			Utilidades.guardarLog(
					Utilidades.formatearTexto(Usuario.nombre, "INFO", this.getClass().getSimpleName(), "PDF Generado con la última consulta realizada"));
			
			while(BD.rs.next()) {
				String id = String.valueOf(BD.rs.getInt("idActor"));
				String nombre = BD.rs.getString("nombreActor");
				String apellidos = BD.rs.getString("apellidosActor");
				String salario = BD.rs.getString("salarioEuro");
				table.addCell(new Cell().add(new Paragraph(id).setFont(fuente)));
				table.addCell(new Cell().add(new Paragraph(nombre).setFont(fuente)));
				table.addCell(new Cell().add(new Paragraph(apellidos).setFont(fuente)));
				table.addCell(new Cell().add(new Paragraph(salario).setFont(fuente)));
			}
			
			document.add(new Paragraph("Listado de Actores").setFont(negrita).setFontSize(14));
			document.add(table);
			
			document.close();
			BD.desconectarBD();
			Desktop.getDesktop().open(new File(destino));
		}catch (IOException ioe) {
			dialogoComprobacion(new Exception("No se puede abrir otra instancia de PDF, cierre el documento actual"));
		}
		catch (Exception e){
			dialogoComprobacion(e);
		}
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
				Utilidades.guardarLog(
						Utilidades.formatearTexto(Usuario.nombre, "ERROR", this.getClass().getSimpleName(), e.getMessage()));
				lblDiaFeedback.setText("Error de driver. [" + e.getMessage() + "]");
				break;
			case "SQLException":
				Utilidades.guardarLog(
						Utilidades.formatearTexto(Usuario.nombre, "ERROR", this.getClass().getSimpleName(), e.getMessage()));
				lblDiaFeedback.setText("Error de conexión: url, usuario o clave. [" + e.getMessage() + "]");
				break;

			default:
				Utilidades.guardarLog(
						Utilidades.formatearTexto(Usuario.nombre, "ERROR", this.getClass().getSimpleName(), e.getMessage()));
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
		{	String sqlFinal = BD.consultaSQLActores;
		if (!sentenciaFiltros.isEmpty()) {
			sqlFinal += " WHERE " + String.join(" AND ", sentenciaFiltros);
		}
		ultimaConsulta = sqlFinal;
		consultar(tabla, sqlFinal, filtrosSentencia);
		btnOrdenNombre.setEnabled(true);
		btnOrdenApellidos.setEnabled(true);
		btnOrdenSalario.setEnabled(true);
		btnPdf.setEnabled(true);
		}else if (e.getSource().equals(btnAddFiltro)) {
			addFiltro();
		}
		else if (e.getSource().equals(btnLimpiarFiltro)) {
			sentenciaFiltros.clear();
			filtrosSentencia.clear();
			lblFiltrosActivos.setText("Filtros: Ninguno");
			ultimaConsulta = BD.consultaSQLActores;
		} else if(e.getSource().equals(btnPdf)) {
			generarPdf("ConsultaAct_" + System.currentTimeMillis() + ".pdf", ultimaConsulta, filtrosSentencia);
		}
		else if (e.getSource().equals(btnOrdenNombre))
		{
			String sqlFinal = BD.consultaSQLActores;
			if (!sentenciaFiltros.isEmpty()) {
				sqlFinal += " WHERE " + String.join(" AND ", sentenciaFiltros);
			}
			
			sqlFinal += " ORDER BY nombreActor, apellidosActor";
			ultimaConsulta  = sqlFinal;
			consultar(tabla, ultimaConsulta, filtrosSentencia);

		}
		else if (e.getSource().equals(btnOrdenApellidos))
		{
			String sqlFinal = BD.consultaSQLActores;
			if (!sentenciaFiltros.isEmpty()) {
				sqlFinal += " WHERE " + String.join(" AND ", sentenciaFiltros);
			}
			
			sqlFinal += " ORDER BY apellidosActor, nombreActor";
			ultimaConsulta  = sqlFinal;
			consultar(tabla, ultimaConsulta, filtrosSentencia);	
		}
		else if (e.getSource().equals(btnOrdenSalario))
		{
			String sqlFinal = BD.consultaSQLActores;
			if (!sentenciaFiltros.isEmpty()) {
				sqlFinal += " WHERE " + String.join(" AND ", sentenciaFiltros);
			}
			
			sqlFinal += " ORDER BY salarioActor DESC";
			ultimaConsulta  = sqlFinal;
			consultar(tabla, ultimaConsulta, filtrosSentencia);
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
		}else if (e.getSource() == ventana)
		{
			ventana.dispose();
		}
	}

}