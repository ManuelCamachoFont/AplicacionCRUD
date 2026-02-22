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
			String sentenciaSQL = (BD.consultaSQLUsuarios + "WHERE nombreUsuario = \"" + usuario
					+ "\" AND claveUsuario = \"" + clave + "\"");
			BD.rs = BD.statement.executeQuery(sentenciaSQL);

			if (BD.rs.next()) {
				nombre = BD.rs.getString("nombreUsuario");
				tipo = BD.rs.getString("tipoUsuario").toUpperCase();
				encontrado = true;
			}
		} catch (ClassNotFoundException cnfe) {
			System.out.println(cnfe);
		} catch (SQLException se) {
			System.out.println(se);
		} finally {
			try {
				if (BD.connection != null) {

					BD.connection.close();
				}
			} catch (SQLException se) {
				System.out.println(se);
			}
		}
		return encontrado;
	}

	public static void permisosBasico(Menu mnuDirectores, Menu mnuPeliculas, Menu mnuActores, MenuItem mnuBajaDir,
			MenuItem mnuModDir, MenuItem mnuConsDir, MenuItem mnuBajaPel, MenuItem mnuConsPel, MenuItem mnuBajaAct,
			MenuItem mnuModAct, MenuItem mnuConsAct) {

		if (tipo.equals("BASICO")) {
			mnuDirectores.remove(mnuBajaDir);
			mnuDirectores.remove(mnuModDir);
			mnuDirectores.remove(mnuConsDir);

			mnuPeliculas.remove(mnuBajaPel);
			mnuPeliculas.remove(mnuConsPel);

			mnuActores.remove(mnuBajaAct);
			mnuActores.remove(mnuModAct);
			mnuActores.remove(mnuConsAct);
		}
	}

	public static void permisosBasico2(Menu mnuDirectores2, Menu mnuPeliculas2, Menu mnuActores2, MenuItem mnuBajaDir2,
			MenuItem mnuModDir2, MenuItem mnuConsDir2, MenuItem mnuBajaPel2, MenuItem mnuConsPel2, MenuItem mnuBajaAct2,
			MenuItem mnuModAct2, MenuItem mnuConsAct2) {

		if (tipo.equals("BASICO")) {
			mnuDirectores2.remove(mnuBajaDir2);
			mnuDirectores2.remove(mnuModDir2);
			mnuDirectores2.remove(mnuConsDir2);

			mnuPeliculas2.remove(mnuBajaPel2);
			mnuPeliculas2.remove(mnuConsPel2);

			mnuActores2.remove(mnuBajaAct2);
			mnuActores2.remove(mnuModAct2);
			mnuActores2.remove(mnuConsAct2);
		}
	}
}
