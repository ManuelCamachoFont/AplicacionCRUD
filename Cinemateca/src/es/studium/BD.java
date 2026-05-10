package es.studium;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BD {

	public static String driver = "com.mysql.cj.jdbc.Driver";
	public static String url = "jdbc:mysql://localhost:3306/p_cine";
	public static String usuario = "studium";
	public static String clave = "Studium2025#";
	
	public static String consultaSQLUsuarios = "SELECT * FROM usuarios";
	
	public static String eliminarSQLDirector = "DELETE from directores WHERE idDirector = ?";
	public static String eliminarSQLPelicula =  "DELETE from peliculas WHERE idPelicula = ?";
	public static String eliminarSQLActor =  "DELETE from actores WHERE idActor = ?";
	public static String eliminarSQLPelAct =  "DELETE from peliculas_actores WHERE idPeliculaActor = ?";	
	
	public static String consultaSQLDirectores = "SELECT * FROM directores";
	public static String consultaSQLDirectoresN = "SELECT * FROM directores ORDER BY nombreDirector, apellidosDirector";
	public static String consultaSQLDirectoresA = "SELECT * FROM directores ORDER BY apellidosDirector, nombreDirector";
	public static String consultaSQLDirectoresNa = "SELECT * FROM directores ORDER BY nacionalidadDirector, apellidosDirector, nombreDirector";
	
	public static String consultaSQLActores = "SELECT * FROM actores";
	public static String consultaSQLActoresN = "SELECT * FROM actores ORDER BY nombreActor, apellidosActor";
	public static String consultaSQLActoresA = "SELECT * FROM actores ORDER BY apellidosActor, nombreActor";
	public static String consultaSQLActoresS = "SELECT * FROM actores ORDER BY salarioActor";
	
	public static String consultaSQLPel = "SELECT * FROM peliculas";
	public static String consultaSQLPeliculas = "SELECT idPelicula, tituloPelicula, generoPelicula, DATE_FORMAT(fechaEstrenoPelicula, '%d/%m/%Y') AS 'fechaEstrenoPelicula', CONCAT(apellidosDirector, ', ', nombreDirector) AS 'nombreCompletoDirector' FROM peliculas JOIN directores ON directores.idDirector = peliculas.idDirectorFK";
	public static String consultaSQLPeliculas2 = "SELECT idPelicula, tituloPelicula, generoPelicula, YEAR(fechaEstrenoPelicula) AS 'fechaEstrenoPelicula', CONCAT('Dir: ', apellidosDirector, ', ', nombreDirector) AS 'nombreCompletoDirector' FROM peliculas JOIN directores ON directores.idDirector = peliculas.idDirectorFK";
	public static String consultaSQLPeliculas3 = "SELECT idPelicula, tituloPelicula, generoPelicula, DATE_FORMAT(fechaEstrenoPelicula, '%d/%m/%Y') AS 'fechaEstrenoPelicula', CONCAT(apellidosDirector, ', ', nombreDirector) AS 'nombreCompletoDirector', idDirector FROM peliculas JOIN directores ON directores.idDirector = peliculas.idDirectorFK";
	public static String consultaSQLPeliculasT = "SELECT idPelicula, tituloPelicula, generoPelicula, DATE_FORMAT(fechaEstrenoPelicula, '%d/%m/%Y') AS 'fechaEstrenoPelicula', CONCAT(apellidosDirector, ', ', nombreDirector) AS 'nombreCompletoDirector' FROM peliculas JOIN directores ON directores.idDirector = peliculas.idDirectorFK ORDER BY tituloPelicula";
	public static String consultaSQLPeliculasG = "SELECT idPelicula, tituloPelicula, generoPelicula, DATE_FORMAT(fechaEstrenoPelicula, '%d/%m/%Y') AS 'fechaEstrenoPelicula', CONCAT(apellidosDirector, ', ', nombreDirector) AS 'nombreCompletoDirector' FROM peliculas JOIN directores ON directores.idDirector = peliculas.idDirectorFK ORDER BY generoPelicula, YEAR(fechaEstrenoPelicula)";
	public static String consultaSQLPeliculasF = "SELECT idPelicula, tituloPelicula, generoPelicula, DATE_FORMAT(fechaEstrenoPelicula, '%d/%m/%Y') AS 'fechaEstrenoPelicula', CONCAT(apellidosDirector, ', ', nombreDirector) AS 'nombreCompletoDirector' FROM peliculas JOIN directores ON directores.idDirector = peliculas.idDirectorFK ORDER BY YEAR(fechaEstrenoPelicula)";
	public static String consultaSQLPeliculasD = "SELECT idPelicula, tituloPelicula, generoPelicula, DATE_FORMAT(fechaEstrenoPelicula, '%d/%m/%Y') AS 'fechaEstrenoPelicula', CONCAT(apellidosDirector, ', ', nombreDirector) AS 'nombreCompletoDirector' FROM peliculas JOIN directores ON directores.idDirector = peliculas.idDirectorFK ORDER BY 5";
	
	public static String consultaSQLPelAct = "SELECT idPeliculaActor, CONCAT(tituloPelicula, ' (', YEAR(fechaEstrenoPelicula), ')') AS 'pelicula', CONCAT(nombreActor, ' ', apellidosActor) AS 'actor' FROM peliculas JOIN peliculas_actores ON peliculas_actores.idPeliculaFK = peliculas.idPelicula JOIN actores ON actores.idActor = peliculas_actores.idActorFK";
	public static String consultaSQLPelAct2 = "SELECT idPeliculaActor, idPelicula, idActor, CONCAT(tituloPelicula, ' (', YEAR(fechaEstrenoPelicula), ')') AS 'pelicula', CONCAT(nombreActor, ' ', apellidosActor) AS 'actor' FROM peliculas JOIN peliculas_actores ON peliculas_actores.idPeliculaFK = peliculas.idPelicula JOIN actores ON actores.idActor = peliculas_actores.idActorFK";
	
	
	public static Connection connection = null;
	public static Statement statement = null;
	public static ResultSet rs = null;
	public static PreparedStatement ps = null;

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
