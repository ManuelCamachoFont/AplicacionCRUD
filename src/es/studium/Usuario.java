package es.studium;

import java.awt.Menu;
import java.awt.MenuItem;
import java.sql.SQLException;

public class Usuario {

	public static String nombre = "";
	public static String tipo = "";

	public static void cerrarSesion() {
		nombre = "";
		tipo = "";
	}

	public static boolean validar(String usuario, String clave) {

		boolean encontrado = false;
		try {
			BD.conectarBD();
			String sentenciaSQL = (BD.consultaSQLUsuarios + " WHERE nombreUsuario = ? AND claveUsuario = SHA2(?, 256)");
			BD.ps = BD.connection.prepareStatement(sentenciaSQL);
			BD.ps.setString(1, usuario);
			BD.ps.setString(2, clave);
			BD.rs = BD.ps.executeQuery();
			if (BD.rs.next()) {
				nombre = BD.rs.getString("nombreUsuario");
				tipo = BD.rs.getString("tipoUsuario").toUpperCase();
				encontrado = true;
			}
		} catch (ClassNotFoundException cnfe) {
			System.out.println(cnfe.getMessage());
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		} finally {
			try {
				BD.desconectarBD();
			} catch (SQLException se) {
				System.out.println(se.getMessage());
			}
		}
		return encontrado;
	}

	public static void permisosBasico(Menu mnu, MenuItem... items) {
		if (tipo.equals("BASICO")) {
			for (MenuItem item : items) {
				mnu.remove(item);
			}

		}
	}
}
