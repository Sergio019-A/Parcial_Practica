package co.edu.poli.examen2_Acero.servicios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConexionBD {

	private static ConexionBD instancia;
	private Connection conexion;

	private ConexionBD() throws Exception {

		Properties props = cargarEnv();

		String url  = props.getProperty("DB_URL");
		String user = props.getProperty("DB_USER");
		String pass = props.getProperty("DB_PASSWORD");

		if (url == null || user == null || pass == null) {
			throw new RuntimeException(
				"Faltan variables de entorno DB_URL, DB_USER o DB_PASSWORD en el .env");
		}

		Class.forName("com.mysql.cj.jdbc.Driver");
		conexion = DriverManager.getConnection(url, user, pass);
	}

	/**
	 * Intenta cargar el .env en este orden:
	 *  1. Directorio de trabajo (util al correr con javafx:run desde el proyecto)
	 *  2. Classpath (src/main/resources/.env copiado por Maven al target)
	 */
	private static Properties cargarEnv() throws Exception {
		Properties props = new Properties();

		// 1. Buscar en el directorio de trabajo
		File envFile = new File(".env");
		if (envFile.exists()) {
			try (BufferedReader br = new BufferedReader(new FileReader(envFile))) {
				parsearLineas(br, props);
				return props;
			}
		}

		// 2. Buscar en el classpath
		try (InputStream is = ConexionBD.class.getClassLoader().getResourceAsStream(".env")) {
			if (is != null) {
				try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
					parsearLineas(br, props);
					return props;
				}
			}
		}

		return props;
	}

	private static void parsearLineas(BufferedReader br, Properties props) throws Exception {
		String line;
		while ((line = br.readLine()) != null) {
			line = line.trim();
			if (line.isEmpty() || line.startsWith("#")) continue;
			String[] parts = line.split("=", 2);
			if (parts.length == 2) {
				props.setProperty(parts[0].trim(), parts[1].trim());
			}
		}
	}

	public static ConexionBD getInstancia() throws Exception {
		if (instancia == null) {
			instancia = new ConexionBD();
		}
		return instancia;
	}

	public Connection getConexion() throws Exception {
		if (conexion == null || conexion.isClosed()) {
			instancia = new ConexionBD();
			return instancia.conexion;
		}
		return conexion;
	}
}
