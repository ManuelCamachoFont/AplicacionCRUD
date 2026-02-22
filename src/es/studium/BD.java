package es.studium;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BD {

	public static String driver = "com.mysql.cj.jdbc.Driver";
	public static String url = "jdbc:mysql://localhost:3306/p_cine";
	public static String usuario = "studium";
	public static String clave = "Studium2025#";
	
	public static String consultaSQLUsuarios = "SELECT * FROM usuarios";
	
	public static String eliminarSQLDirector = "DELETE from directores WHERE idDirector = ";
	public static String eliminarSQLPelicula =  "DELETE from peliculas WHERE idPelicula = ";
	public static String eliminarSQLActor =  "DELETE from actores WHERE idActor = ";
	
	
	public static String consultaSQLDirectores = "SELECT * FROM directores";
	public static String consultaSQLActores = "SELECT * FROM actores";
	public static String consultaSQLPeliculas = "SELECT * FROM peliculas";
	
	public static Connection connection = null;
	public static Statement statement = null;
	public static ResultSet rs = null;

	public static void conectarBD() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		connection = DriverManager.getConnection(url, usuario, clave);
		statement = connection.createStatement();
	}

	public static void desconectarBD() throws SQLException {
		if (connection != null) {

			connection.close();
		}
	}

}
