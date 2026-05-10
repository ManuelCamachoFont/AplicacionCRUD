package es.studium;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.filechooser.FileSystemView;

public class Utilidades {
	public String formatear(String usuario, String texto) {
		LocalDateTime timestamp = LocalDateTime.now();
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		String cadenaFormateada = "[" + timestamp.format(formato) + "] " + "[ " + usuario + " ]" + texto;
		return cadenaFormateada;
	}

	public void guardar(String textoFormateado) {

		try {
			LocalDateTime timestamp = LocalDateTime.now();
			DateTimeFormatter formatoFichero = DateTimeFormatter.ofPattern("dd_MM_yyyy");
			DateTimeFormatter formatoDirectorio = DateTimeFormatter.ofPattern("MM_yyyy");
			String nombreDirectorio = timestamp.format(formatoDirectorio);

			switch (nombreDirectorio) {
			case ("01_2026"):
				nombreDirectorio = "Enero";
				break;
			case ("02_2026"):
				nombreDirectorio = "Febrero";
				break;
			case ("03_2026"):
				nombreDirectorio = "Marzo";
				break;
			case ("04_2026"):
				nombreDirectorio = "Abril";
				break;
			case ("05_2026"):
				nombreDirectorio = "Mayo";
				break;
			case ("06_2026"):
				nombreDirectorio = "Junio";
				break;
			case ("07_2026"):
				nombreDirectorio = "Julio";
				break;
			case ("08_2026"):
				nombreDirectorio = "Agosto";
				break;
			case ("09_2026"):
				nombreDirectorio = "Septiembre";
				break;
			case ("10_2026"):
				nombreDirectorio = "Octubre";
				break;
			case ("11_2026"):
				nombreDirectorio = "Noviembre";
				break;
			case ("12_2026"):
				nombreDirectorio = "Diciembre";
				break;
			}
			String nombreFichero = timestamp.format(formatoFichero).concat(".txt");

			File escritorio = FileSystemView.getFileSystemView().getHomeDirectory();

			File carpetaLog = new File(escritorio, "Logs");

			File carpetaMes = new File(carpetaLog, nombreDirectorio);

			if (!carpetaMes.exists()) {
				carpetaMes.mkdirs();
			}

			File archivoTxt = new File(carpetaMes, nombreFichero);

			FileWriter fw = new FileWriter(archivoTxt, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter salida = new PrintWriter(bw);
			salida.println(textoFormateado);
			salida.close();
			bw.close();
			fw.close();
		} catch (IOException ioe) {
			System.err.println("Error de archivo");
		}

	}
}
