package es.studium;

import java.awt.Desktop;
import java.awt.Frame;
import java.awt.Image;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;


public class Utilidades {
	public static String formatearTexto(String usuario, String nivel, String clase, String texto) {
		LocalDateTime timestamp = LocalDateTime.now();
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		String cadenaFormateada = "[" + timestamp.format(formato) + "] [" + usuario + "] [" + nivel + "] [" + clase + "] " + texto;
		return cadenaFormateada;
	}

	public static void guardarLog(String textoFormateado) {

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

			File carpetaLog = new File("Logs");

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
	
	
	public static void consultarAyuda() {
		try {
		    File archivo = new File("help/manual.html");
		    String rutaAbsoluta = archivo.getAbsolutePath();
		    
		    List<String> comando = new ArrayList<>();
		    comando.add("cmd");
		    comando.add("/c");
		    comando.add("start");
		    comando.add("chrome");
		    comando.add("--app=file:///" + rutaAbsoluta);
		    
		    comando.add("--window-size=800,600"); 

		    ProcessBuilder pb = new ProcessBuilder(comando);
		    pb.start();

		} catch (Exception ex) {
			if (Desktop.isDesktopSupported()) {
				Desktop desktop = Desktop.getDesktop();
				if (desktop.isSupported(Desktop.Action.BROWSE)) {
					try {
						File archivo = new File("help/manual.html");
						URI uri = archivo.toURI();
						desktop.browse(uri);
					} catch (IOException ioe) {
						System.out.println(ioe.getMessage());
					}
				}
			}
		}
	}
	
	
	public static Image cargarIcono(String ruta) {
        try {
            return ImageIO.read(new File(ruta));
        } catch (Exception e) {
            return null;
        }
    }

    public static void aplicarIcono(String ruta, Frame... ventanas) {
        Image icono = cargarIcono(ruta);
        if (icono != null) {
            for (Frame ventana : ventanas) {
                ventana.setIconImage(icono);
            }
        }
    }
	
}
